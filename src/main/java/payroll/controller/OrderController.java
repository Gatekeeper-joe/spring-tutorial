package payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import payroll.assembler.OrderModelAssembler;
import payroll.exception.OrderNotFoundException;
import payroll.form.OrderForm;
import payroll.repositry.OrderRepository;
import payroll.entity.Order;
import payroll.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * 注文用コントローラ
 */
@RestController
public class OrderController {
    /** DI:orderRepository */
    private final OrderRepository orderRepository;

    /**
     * OrderModelAssemblerクラス
     */
    private final OrderModelAssembler assembler;

    /**
     * コンストラクタ
     * @param orderRepository オブジェクト
     * @param assembler オブジェクト
     */
    @Autowired
    public OrderController(OrderRepository orderRepository, OrderModelAssembler assembler) {

        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    /**
     * 全件取得
     * @return CollectionModel<EntityModel<Order>>
     */
    @GetMapping("/orders")
    public CollectionModel<EntityModel<Order>> all() {

        List<EntityModel<Order>> orders = orderRepository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(
            orders,
            linkTo(
                methodOn(OrderController.class).all()
            )
                .withSelfRel()
        );
    }

    /**
     * 指定されたidに紐づく商品の情報を取得
     * @param id
     * @return EntityModel<Order>
     */
    @GetMapping("/orders/{id:[0-9]+}")
    public EntityModel<Order> one(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        return assembler.toModel(order);
    }

    /**
     * 注文情報を登録
     * @param orderForm 注文フォームオブジェクト
     * @return ResponseEntity<EntityModel<Order>>
     */
    @PostMapping("/orders")
    public ResponseEntity<EntityModel<Order>> newOrder(@RequestBody @Validated OrderForm orderForm) {

        Order order = new Order(
            orderForm.getDescription(),
            Status.IN_PROGRESS
        );

        Order newOrder = orderRepository.save(order);

        return ResponseEntity
            .created(
                linkTo(methodOn(OrderController.class)
                    .one(newOrder.getId())
                )
                .toUri()
            )
            .body(assembler.toModel(newOrder));
    }

    /**
     * 注文を取り消し
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/orders/{id:[0-9]+}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        if (Status.IN_PROGRESS.equals(order.getStatus())) {
            order.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
        }

        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
            .body(Problem.create()
                .withTitle("Method not allowed")
                .withDetail("You can't cancel an order that is in the " + order.getStatus() + " status"));
    }

    /**
     * 注文完了
     * @param id
     * @return 注文エンティティ
     */
    @PutMapping("/orders/{id:[0-9]+}")
    public ResponseEntity<?> complete(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        // 注文が正常に通った場合
        if (order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
        }

        //注文が通らなかった場合
        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
            .body(Problem.create()
                .withTitle("Method not allowed")
                .withDetail("You can't complete an order that is in the " + order.getStatus() + " status"));
    }
}
