package payroll.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.entity.Employee;

/**
 * インターフェース
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
