package payroll.exception;

/**
 * 検索時に従業員が見つからなかった場合に発生
 */
public class EmployeeNotFoundException extends RuntimeException {

    /**
     * 存在しないidを数字で指定された場合
     * @param id
     */
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }

    /**
     * 存在しないidを文字列で指定された場合
     * @param id
     */
    public EmployeeNotFoundException(String id) {
        super("Could not find employee " + id);
    }
}
