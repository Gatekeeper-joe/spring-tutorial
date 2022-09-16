package payroll;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepositoryから派生したインターフェース
 */
interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
