package payroll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 該当する注文データがなかった場合に使用されるクラス
 */
@ControllerAdvice
public class OrderNotFoundAdvice {

    /**
     * 指定のメッセージを返す
     * @param ex
     * @return String
     */
    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String orderNotFoundHandler(OrderNotFoundException ex) {
        return ex.getMessage();
    }
}
