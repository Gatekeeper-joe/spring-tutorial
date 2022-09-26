package payroll.entity;

import lombok.Data;
import payroll.enums.Status;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 注文エンティティ
 */
@Entity
@Data
@Table(name = "CUSTOMER_ORDER")
public class Order {

    /** id */
    @Id @GeneratedValue private Long id;

    /** 注文内容 */
    private String description;

    /** ステータス */
    private Status status;

    /**
     * コンストラクタ
     */
    Order() {}

    /**
     * コンストラクタ
     * @param description 注文内容
     * @param status 状態
     */
    public Order(String description, Status status) {

        this.description = description;
        this.status = status;
    }

    /**
     * Orderオブジェクトと一致するかチェック
     * @param o オブジェクト
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(this.id, order.id) && Objects.equals(this.description, order.description)
            && this.status == order.status;
    }

    /**
     * 注文情報をもとにハッシュ値を生成
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.status);
    }

    /**
     * 注文情報から特定の文字列を生成
     * @return String
     */
    @Override
    public String toString() {
        return String.format(
            "Order{id=%d, description='%s', status='%s'}",
            this.id,
            this.description,
            this.status
        );
    }
}
