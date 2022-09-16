package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DBに保存されているデータを読みこむ
 */
@Configuration
class LoadDatabase {

    /**
     * LoadDatabaseクラスのログを扱うオブジェクト
     */
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * mainメソッドが実行されると従業員の名前と役割をコンソールに出力
     * @param repository 従業員リポジトリ
     * @return String
     */
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
        };
    }
}