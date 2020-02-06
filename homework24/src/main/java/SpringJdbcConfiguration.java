import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringJdbcConfiguration {

    DriverManagerDataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:~/test", "max","");
    }

    @Bean
    Repository repository() {
        return new Repository();
    }

    @Bean
    SimpleJdbcInsert simpleJdbcInsert() {
        return new SimpleJdbcInsert(dataSource()).withTableName("Recipe");
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
