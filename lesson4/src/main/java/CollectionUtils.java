import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static int indexOf(List<?> source, Object o) {

        int index = 0;
        if ((source == null) || (o == null)) {
            return -1;
        }

        for (Object e : source) {
            if (e.equals(o)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public static <T> List<? super T> limit(List<T> source, int size) {
        List<T> list = source.subList(0, size - 1);
        return list;
    }

    public static void add(List<? super Object> source, Object o) {
        source.add(o);
    }

    public static void removeAll(List<?> removeFrom, List<?>  c2) {
        boolean ret = removeFrom.removeAll(c2);
        int k = 0;
    }

    public static boolean containsAll(List<?> c1, List<?> c2) {
        return c1.containsAll(c2);
    }

    public static boolean containsAny(List<?> c1, List<?> c2) {
        for (Object obj:c2) {
            if (c1.contains(obj)) {
                return true;
            }
        }

        return false;
    }

    public static <T extends Comparable<T>> List<T> range(List<? extends T> list, T min, T max) {
        List<T> retList = new ArrayList<>();

        for (T e : list) {
            if ((e.compareTo(min) >= 0) && (e.compareTo(max) <= 0)) {
                retList.add(e);
            }
        }

        return retList;
    }

    public static <T> List<T> range(List<T> list, T min, T max, Comparator<T> comparator) {
        List<T> retList = new ArrayList<>();

        for (T e : list) {
            if ((comparator.compare(e, min) >= 0) && (comparator.compare(e, max) <= 0)) {
                retList.add(e);
            }
        }

        return retList;
    }
}
