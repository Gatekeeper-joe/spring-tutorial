package payroll.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import payroll.entity.Employee;
import payroll.assembler.EmployeeModelAssembler;
import payroll.exception.EmployeeNotFoundException;
import payroll.form.EmployeeForm;
import payroll.repositry.EmployeeRepository;

/**
 * 従業員情報用のコントローラ
 */
@RestController
public class EmployeeController {

    /** DI: EmployeeRepository */
    private final EmployeeRepository repository;

    /**
     * EmployeeModelAssemblerクラス
     */
    private final EmployeeModelAssembler assembler;

    /**
     * コンストラクタ
     * @param repository 従業員リポジトリ
     * @param assembler 従業員モデル変換
     */
    @Autowired
    public EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    /**
     * 従業員情報をすべて取得
     *
     * @return CollectionModel<EntityModel < Employee>>
     */
    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    /**
     * 従業員の詳細情報を取得
     * @param id 従業員id
     * @return EntityModel<Employee>
     */
    @GetMapping("/employees/{id:[0-9]+}")
    public EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }

    /**
     * 従業員情報を新規登録
     * @param employeeForm 従業員フォームオブジェクト
     * @return Employee
     */
    @PostMapping("/employees")
    public ResponseEntity<?> newEmployee(@RequestBody @Validated EmployeeForm employeeForm) {

        Employee newEmployee = new Employee(
            employeeForm.getFirstName(),
            employeeForm.getLastName(),
            employeeForm.getRole()
        );

        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    /**
     * 従業員情報を更新
     * @param employeeForm 従業員フォームオブジェクト
     * @param id 従業員id
     * @return ResponseEntity
     */
    @PutMapping("/employees/{id:[0-9]+}")
    public ResponseEntity<?> replaceEmployee(
        @RequestBody @Validated EmployeeForm employeeForm,
        @PathVariable Long id
    ) {

        Employee newEmployee = new Employee(
            employeeForm.getFirstName(),
            employeeForm.getLastName(),
            employeeForm.getRole()
        );

        Employee updatedEmployee = repository.findById(id)
            .map(employee -> {
                employee.setLastName(newEmployee.getLastName());
                employee.setFirstName(newEmployee.getFirstName());
                employee.setRole(newEmployee.getRole());
                return repository.save(employee);
            })
            .orElseGet(() -> {
                newEmployee.setId(id);
                return repository.save(newEmployee);
            });

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    /**
     * 従業員情報を削除
     * @param id 従業員id
     * @return ResponseEntity
     */
    @DeleteMapping("/employees/{id:[0-9]+}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
