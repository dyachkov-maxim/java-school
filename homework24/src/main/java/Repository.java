import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Repository {

    // language = SQL
    private static final String CREATE_TABLE_RECIPE =
            "CREATE TABLE IF NOT EXISTS Recipe " +
                    "(id int NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(30), " +
                    "description VARCHAR(300), " +
                    "PRIMARY KEY (id));";

    // language = SQL
    private static final String DELETE_SQL = "DELETE FROM Recipe WHERE id = ?;";

    // language = SQL
    private static final String SELECT_ALL_SQL = "SELECT * FROM Recipe;";

    // language = SQL
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM Recipe WHERE id = ?";

    // language = SQL
    //private static final String SELECT_BY_NAME_SQL = "SELECT * FROM Recipe WHERE name LIKE :nameval";

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int addRecord(Recipe recipe) {
        jdbcTemplate.execute(CREATE_TABLE_RECIPE);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", recipe.getName());
        parameters.put("description", recipe.getDescription());

        return simpleJdbcInsert.execute(parameters);
    }

    public int deleteRecord(int id) {
        Object[] params = { id };
        int[] types = {Types.BIGINT};
        int rows = jdbcTemplate.update(DELETE_SQL, params, types);

        return rows;
    }

    public List<Recipe> findAll() {
        List <Recipe> recipes = jdbcTemplate.query(SELECT_ALL_SQL, new RecipeMapper());
        return recipes;
    }

    public Recipe findById(int id) {
        Object[] params = { id };
        int[] types = {Types.BIGINT};

        List <Recipe> recipes = jdbcTemplate.query(SELECT_BY_ID_SQL, params, types, new RecipeMapper());
        if (recipes.isEmpty()) {
            return null;
        }

        return recipes.get(0);
    }

    public List<Recipe> findByName(String name) {

        String query = String.format("SELECT * FROM Recipe WHERE name LIKE '%s%%';", name);
        List <Recipe> recipes = jdbcTemplate.query(query, new RecipeMapper());
        if (recipes.isEmpty()) {
            return null;
        }

        return recipes;
    }
}
