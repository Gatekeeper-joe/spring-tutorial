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

    /**
     * 従業員名を取得
     * @return String
     */
    public String getName() {
        return this.lastName + " " + this.firstName;
    }

    /**
     * 従業員名をフィールドにセット
     * @param name 従業員名
     */
    public void setName(String name) {
        String[] parts = name.split(" ");
        this.lastName = parts[0];
        this.firstName = parts[1];
    }
}
