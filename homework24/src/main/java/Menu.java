import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Repository repository;

    public Menu(Repository repository) {
        this.repository = repository;
    }

    public void show() {
        Scanner in = new Scanner(System.in);
            while (true) {
                int ret = general(in);
                if (ret == 4) {
                    break;
                }
            }
        in.close();

        System.exit(0);
    }

    private int general(Scanner in) {
        System.out.println("MAIN MENU");
        System.out.println("1 - Search");
        System.out.println("2 - Add");
        System.out.println("3 - Delete");
        System.out.println("4 - Exit");
        System.out.print("Input a number menu: ");
        int num = in.nextInt();

        switch (num) {
            case 1: search(in); break;
            case 2: add(in); break;
            case 3: delete(in); break;
            case 4: break;
            default:
                System.out.println("Error value");
        }

        return num;
    }

    private void delete(Scanner in) {
        System.out.print("Input id: ");
        int id = in.nextInt();

        int ret = repository.deleteRecord(id);
        if (ret == 1) {
            System.out.println("Success delete record for id: " + id);
        }
    }

    private void add(Scanner in) {
        System.out.println("Input data for recipe:");
        System.out.print("Input name: ");
        String name = in.next();
        System.out.print("Input description: ");
        String description = in.next();

        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setDescription(description);

        repository.addRecord(recipe);
    }

    private void search(Scanner in) {
        System.out.println("SEARCH MENU");
        System.out.println("0 - All");
        System.out.println("1 - For id");
        System.out.println("2 - For name");
        System.out.println("3 - back");
        System.out.print("Input a number menu: ");
        int num = in.nextInt();

        switch (num) {
            case 0: showAll(); break;
            case 1: findById(in); break;
            case 2: findByName(in);
            case 3: general(in); break;
        }
    }

    private void findByName(Scanner in) {
        System.out.print("Input name: ");
        String name = in.next();
        List<Recipe> recipes = repository.findByName(name);
        recipes.forEach(System.out::println);
    }

    private void showAll() {
        List<Recipe> recipes = repository.findAll();
        recipes.forEach(System.out::println);
    }

    private void findById(Scanner in) {
        System.out.print("Input id: ");
        int id = in.nextInt();
        Recipe recipe = repository.findById(id);
        if (recipe != null) {
            System.out.println(recipe);
        }
    }

}
