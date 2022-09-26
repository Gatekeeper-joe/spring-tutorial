package payroll.entity;

import lombok.Data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 従業員情報を保存
 */
@Entity
@Data
public class Employee {

    /** id */
    private @Id @GeneratedValue Long id;

    /** firstName */
    private String firstName;

    /** lastName */
    private String lastName;

    /** role */
    private String role;

    /**
     * コンストラクタ
     */
    public Employee() {}

    /**
     * コンストラクタ
     * @param firstName 名前
     * @param lastName 名字
     * @param role 担当名
     */
    public Employee(String lastName, String firstName, String role) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.role = role;
    }
}
