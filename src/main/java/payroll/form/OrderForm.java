package payroll.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import payroll.enums.Status;

import javax.validation.constraints.NotBlank;

/**
 * 注文フォーム
 */
@Data
public class OrderForm {

    /** 注文内容 */
    @NotBlank
    @Length(min = 11, max = 50)
    private String description;

    /** ステータス */
    private Status status;
}
