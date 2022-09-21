package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DB内の情報を読み込むクラス
 */
@Configuration
class LoadDatabase {

    /** logインスタンス生成 */
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * リポジトリに登録した情報をログに出力
     * @param employeeRepository 従業員リポジトリ
     * @param orderRepository 注文リポジトリ
     * @return String
     */
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {

        return args -> {
            employeeRepository.save(new Employee("梶原", "丈一朗", "平社員"));
            employeeRepository.save(new Employee("大谷", "翔平", "部長"));

            employeeRepository.findAll().forEach(
                employee -> log.info("Preloaded " + employee)
            );

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));
        };
    }
}

