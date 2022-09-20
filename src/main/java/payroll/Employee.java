package payroll;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 従業員情報を保存
 */
@Entity
class Employee {

    /** id */
    private @Id @GeneratedValue Long id;

    /** name */
    private String name;

    /** role */
    private String role;

    /** コンストラクタ */
    Employee() {}

    /**
     * コンストラクタ
     * @param name 従業員名
     * @param role 担当名
     */
    Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    /**
     * 従業員idを取得
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 従業員名を取得
     * @return String
     */
    public String getName() {
        return this.name;
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
     * 従業員名をフィールドにセット
     * @param name 従業員名
     */
    public void setName(String name) {
        this.name = name;
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

        if (this == o)
            return true;
        if (!(o instanceof Employee employee))
            return false;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
                && Objects.equals(this.role, employee.role);
    }

    /**
     * 従業員情報をもとにハッシュ値を生成
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.role);
    }

    /**
     * 従業員情報から特定の文字列を生成
     * @return String
     */
    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
    }
}