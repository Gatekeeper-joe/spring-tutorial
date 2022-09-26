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
}
