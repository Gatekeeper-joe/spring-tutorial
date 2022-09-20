package payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 給与計算アプリのメインクラス
 */
@SpringBootApplication
public class PayrollApplication {

	/**
	 * mainメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PayrollApplication.class, args);
	}

}
