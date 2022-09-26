package payroll.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 従業員情報を保存
 */
@Entity
public class Employee {

    /**
     * id
     */
    private @Id @GeneratedValue Long id;

    /**
     * firstName
     */
    private String firstName;

    /**
     * lastName
     */
    private String lastName;

    /**
     * role
     */
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
        return this.firstName + " " + this.lastName;
    }

    /**
     * 従業員名をフィールドにセット
     * @param name 従業員名
     */
    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    /**
     * 従業員idを取得
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 名前を取得
     * @return String
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * 名字を取得
     * @return String
     */
    public String getLastName() {
        return this.lastName;
    }


    /**
     * 担当名を取得
     * @return String
     */
    public String getRole() {
        return this.role;
    }

    /**
     * idをフィールドにセット
     * @param id 従業員id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 名前をフィールドにセット
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 名字をフィールドにセット
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 担当名をフィールドにセット
     * @param role 担当名
     */
    public void setRole(String role) {
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
