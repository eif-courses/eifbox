package eif.viko.lt.bd.talpykla.eifbox;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EifboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(EifboxApplication.class, args);
    }
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(HikariDataSource hikariDataSource){
        return new NamedParameterJdbcTemplate(hikariDataSource);
    }
}


