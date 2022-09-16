package payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * 従業員の情報を保存するクラス
 */
@Entity
class Employee {
    private @Id @GeneratedValue Long id;
    private String name;
    private String role;


    Employee() {}

    /**
     * コンストラクタ
     * @param name 従業員名
     * @param role 役割
     */
    Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    /**
     * idを取得
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 従業員名をを取得
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * 役割を取得
     * @return String
     */
    public String getRole() {
        return this.role;
    }

    /**
     * フィールドに従業員idをセット
     * @param id 従業員id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * フィールドに従業員名をセット
     * @param name 従業員名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * フィールドに役割をセット
     * @param role 役割
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
     * 従業員の情報をハッシュ値にして返す
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.role);
    }

    /**
     * 従業員の情報を指定の文字列にして返す
     * @return String
     */
    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
    }

}
