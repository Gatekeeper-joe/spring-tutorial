package payroll;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 検索時に指定した従業員のデータがDB上から見つからなかった場合に使用されるクラス
 */
@ControllerAdvice
class EmployeeNotFoundAdvice {

    /**
     * 検索にマッチしなかった場合にメッセージを返す
     * @param ex 従業員情報が見つからなかったことを示す例外
     * @return String
     */
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }
}
