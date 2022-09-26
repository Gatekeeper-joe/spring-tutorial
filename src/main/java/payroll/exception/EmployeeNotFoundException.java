package payroll.exception;

/**
 * 検索時に従業員が見つからなかった場合に発生
 */
public class EmployeeNotFoundException extends RuntimeException {

    /**
     * 例外メッセージを返す
     * @param id
     */
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
