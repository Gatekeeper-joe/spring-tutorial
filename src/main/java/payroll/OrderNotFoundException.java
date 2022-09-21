package payroll;

/**
 * 検索時にオーダーが見つからなかった場合に発生
 */
public class OrderNotFoundException extends RuntimeException {
    /**
     * 例外メッセージを返す
     * @param id
     */
    OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }
}
