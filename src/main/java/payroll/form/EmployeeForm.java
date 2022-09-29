package payroll.form;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 従業員フォーム
 */
@Data
public class EmployeeForm {

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
