package payroll.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import payroll.controller.EmployeeController;
import payroll.entity.Employee;

/**
 * 従業員オブジェクトをモデルへ変換するクラス
 */
@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    /**
     * モデルに変換
     * @param employee 従業員オブジェクト
     * @return EntityModel<Employee>
     */
    @Override
    public EntityModel<Employee> toModel(Employee employee) {

        return EntityModel.of(employee,
            linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
            linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }
}
