package payroll;

class EmployeeNotFoundException extends RuntimeException {

    /**
     * 検索時に従業員が見つからなかった場合に発生
     * @param id
     */
    EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}