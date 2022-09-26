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
     * 従業員オブジェクトと一致するかチェック
     * @param o オブジェクト
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.firstName, employee.firstName)
            && Objects.equals(this.lastName, employee.lastName) && Objects.equals(this.role, employee.role);
    }

    /**
     * 従業員情報をもとにハッシュ値を生成
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.role);
    }

    /**
     * 従業員情報から特定の文字列を生成
     * @return String
     */
    @Override
    public String toString() {

        return String.format(
            "Employee{id=%d, firstName='%s', lastName='%s', role='%s'}",
            this.id,
            this.firstName,
            this.lastName,
            this.role
        );
    }
}
