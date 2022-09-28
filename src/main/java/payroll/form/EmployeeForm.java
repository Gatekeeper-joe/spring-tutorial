package payroll.form;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 従業員情報へのリクエストをバリデート
 */
@Data
public class EmployeeForm {

    /** id */
    private @Id @GeneratedValue Long id;

    /** firstName */
    @Size(max = 5)
    @NotBlank
    private String firstName;

    /** lastName */
    @Size(max = 5)
    @NotBlank
    private String lastName;

    /** role */
    @Size(max = 19)
    private String role;
}
