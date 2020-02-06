import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class RepositoryTest {
    @Test
    public void addRecipe() {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfiguration.class);
        Repository repository = context.getBean(Repository.class);

        Recipe recipe = new Recipe("Sup", "1231");
        Recipe recipe1 = new Recipe("Sup kur", "22222");
        Recipe recipe2 = new Recipe("Sup har", "333333");

        int count = repository.addRecord(recipe);
        count += repository.addRecord(recipe1);
        count += repository.addRecord(recipe2);

        assertTrue(count == 3);
    }

    @Test
    public void deleteRecord() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfiguration.class);
        Repository repository = context.getBean(Repository.class);

        int count = repository.deleteRecord(4);

        assertTrue(count == 1);
    }

    @Test
    public void getAllRecord() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfiguration.class);
        Repository repository = context.getBean(Repository.class);

        List<Recipe> recipeList = repository.findAll();

        assertTrue(recipeList.size() == 5);
    }
}