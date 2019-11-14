import java.util.*;

public class CountMapIml<T> implements CountMap<T> {
    private Map<T, Integer> map;

    public CountMapIml() {
        map = new HashMap<>();
    }

    @Override
    public void add(T o) {
        add0(o, 1);
    }

    @Override
    public int getCount(T o) {
        return map.get(o) == null ? 0 : map.get(o);
    }

    @Override
    public int remove(T o) {
        int count = getCount(o);
        map.remove(o);

        return count;
    }

    @Override
    public int size() {
        return map.keySet().size();
    }

    @Override
    public void addAll(CountMap <T> source) {
        Map<T, Integer> sourceMap = source.toMap();
        Iterator<Map.Entry<T, Integer>> it = sourceMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<T, Integer> pair = it.next();
            add0(pair.getKey(), pair.getValue());
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return map;
    }

    private void add0(T t, Integer value) {
        if (map.containsKey(t)) {
            map.put(t, map.get(t) + value);
        } else {
            map.put(t, value);
        }
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.putAll(map);
    }
}
