package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * インターフェース
 */
interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
