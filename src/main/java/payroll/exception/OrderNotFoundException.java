package payroll.exception;

/**
 * 注文未存在例外クラス
 */
public class OrderNotFoundException extends RuntimeException {
    /**
     * 存在しないidを数字で指定された場合
     * @param id
     */
    public OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }

    /**
     * 存在しないidを文字列で指定された場合
     * @param id
     */
    public OrderNotFoundException(String id) {
        super("Could not find order " + id);
    }
}
