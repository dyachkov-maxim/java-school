import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextParsing {
    private List<String> lines = new ArrayList<String>();

    public TextParsing(String path) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void printCountDifferentWords() {
        System.out.println("Count different words: " + uniqueWords().size());
    }

    void printArray(Object[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    void printUniqueWords() {

        ArrayList<String> arrayList = new ArrayList<String>();
        for (String str : lines) {
            arrayList.addAll(Arrays.asList(str.split(" ")));
        }

        for (String str : uniqueWords()) {
            System.out.println(String.format("%s - %d раз.", str, Collections.frequency(arrayList, str)));
        }

    }

    private Set<String> uniqueWords() {
        Set<String> set = new HashSet<String>();
        for (String str : lines) {
            set.addAll(Arrays.asList(str.split(" ")));
        }

        return set;
    }

    void printReverseLineOrder() {
        ListIterator li = lines.listIterator(lines.size());

        while(li.hasPrevious()) {
            System.out.println(li.previous());
        }
    }

    void printUniqueWordsSort() {
        TreeSet <String> treeSet = new TreeSet<String>(new MyComparator());
        treeSet.addAll(uniqueWords());
        printArray(treeSet.toArray());
    }

    void printLine(int number) {

        if ((number < 0) || (number > lines.size() - 1)) {
            System.out.println(String.format("Строка с индексом %d отсутствует."));
        }

        System.out.println(lines.get(number));
    }

    class MyComparator implements Comparator<String> {
        public int compare(String s, String t1) {
            return s.length() > t1.length() ? 1 : 0;
        }
    }
}
