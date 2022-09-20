package payroll;

/**
 * 検索時に従業員が見つからなかった場合に発生
 */
class EmployeeNotFoundException extends RuntimeException {

    /**
     * 例外メッセージを返す
     * @param id
     */
    EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
