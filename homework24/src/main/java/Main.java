import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringJdbcConfiguration.class);

        Repository repository = context.getBean(Repository.class);

        Menu menu = new Menu(repository);
        menu.show();
    }
}
