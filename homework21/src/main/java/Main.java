import org.h2.jdbcx.JdbcDataSource;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUser("max");
        ds.setURL("jdbc:h2:~/test");

        LessonDao ld = new LessonDao(ds);
//        List<Lesson> all = ld.getAll();
//
//        for (Lesson l:all
//             ) {
//            System.out.println("Name: " + l.getName());
//        }
//
//        Optional<Lesson> lesson = ld.get(3);
//        if (lesson.isPresent()) {
//            System.out.println(lesson.get().getName());
//        }

//        Lesson lesson = new Lesson("Fizika", LocalDate.now());
////        ld.save(lesson);
//        ld.delete(lesson);
    }
}
