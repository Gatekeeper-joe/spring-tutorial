package payroll;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 従業員情報用のコントローラ
 */
@RestController
class EmployeeController {

    private final EmployeeRepository repository;

    /**
     * コンストラクタ
     * @param repository 従業員リポジトリ
     */
    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }


    /**
     * 従業員情報をすべて取得
     * @return java.util.List<Employee>
     */
    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }

    /**
     * 従業員情報を新規登録
     * @param newEmployee 従業員オブジェクト
     * @return Employee
     */
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    /**
     * idにマッチする従業員がいるかチェック
     * @param id 従業員id
     * @return java.util.Optional<Employee>
     */
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /**
     * 従業員情報を更新
     * @param newEmployee Employeeオブジェクト
     * @param id  従業員id
     * @return Employee
     */
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    /**
     * 従業員情報を削除
     * @param id 従業員id
     */
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}