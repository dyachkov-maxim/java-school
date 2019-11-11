import java.util.Iterator;
import java.util.Scanner;

public class Main {
    private final static String FILE_NAME = "/home/max/IdeaProjects/java-school/lesson3/src/main/resources/test.txt";

    public static void main(String[] args) {
        TextParsing textParsing = new TextParsing(FILE_NAME);

        Scanner scanner = new Scanner(System.in);
        int value = 0;
        while (value != 7) {
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Выполнить задание:");
            System.out.println("1. Посчитать количество различных слов в файле.");
            System.out.println("2. Вывести на экран список различных слов файла, " +
                    "отсортированный по возрастанию их длины (компаратор сначала по длине слова, потом по тексту).");
            System.out.println("3. Подсчитать и вывести на экран сколько раз каждое слово встречается в файле.");
            System.out.println("4. Вывести на экран все строки файла в обратном порядке.");
            System.out.println("5. Реализация своего Iterator(а) для обхода списка в обратном порядке.");
            System.out.println("6. Вывести на экран строки, номера которых задаются пользователем в произвольном порядке.");
            System.out.println("7. Выход из приложения");
            System.out.print("Выбирите задание:");


            value = scanner.nextInt();

            switch (value) {
                case 1:
                    textParsing.printCountDifferentWords();
                    break;
                case 2:
                    textParsing.printUniqueWords();
                    break;
                case 3:
                    textParsing.printUniqueWordsSort();
                    break;
                case 4:
                    textParsing.printReverseLineOrder();
                    break;
                case 5: {
                    String[] mas = {"один", "два", "три"};
                    MyArrayList<String> strings = new MyArrayList<String>(mas);
                    Iterator <String> it = strings.iteratorReverse();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                }
                break;
                case 6: {
                    int number = 0;
                    System.out.print("Введите номер строки:");
                    number = scanner.nextInt();
                    textParsing.printLine(number);
                }

                break;
                default:
            }
        }
    }

}
