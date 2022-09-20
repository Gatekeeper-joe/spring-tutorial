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
     * 従業員情報をログに出力
     * @param repository 従業員リポジトリ
     * @return String
     */
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Employee("梶原", "平社員")));
            log.info("Preloading " + repository.save(new Employee("梶原", "平社員")));
        };
    }
}
