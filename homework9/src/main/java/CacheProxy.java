import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CacheProxy implements InvocationHandler {
    private Object service;
    private String path;
    private Set<String> set;

    public CacheProxy(Service service, String path) {
        this.service = service;
        this.path = path;
        set = new HashSet<>();
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            Cache cache = method.getAnnotation(Cache.class);
            printInfo(cache);

            if (cache.cacheType() == CacheType.FILE) {
                unserialize();

                String text = "";

                if (set.contains(text)) {

                } else {
                    set.add(text);
                }
            }
        }

        return set;
    }

    private void printInfo(Cache cache) {
        info(cache.cacheType());
        info(cache.fileNamePrefix());
        info(cache.zip());
        info(cache.identityBy());
        info(cache.listList());
    }

    private void info(CacheType type) {
        switch (type) {
            case IN_MEMORY:
                System.out.println("Сохранять в памяти");
                break;
            case FILE:
                System.out.println("Сохранять в файле");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private void info(String prefix) {
        System.out.println("Префикс для файла: " + prefix);
    }

    private void info(boolean isZip) {
        System.out.println("Архивировать файл?  " + isZip);
    }

    private void info(Class<?>[] identityBy) {
        System.out.println("Уникальность элемента по полям:");
        for (Class<?> cl : identityBy) {
            System.out.println(cl.getName());
        }

    }

    private void info(int count) {
        System.out.println("Максимальное количество элементов в кеше: " + count);
    }

    private void serialize() {
        File file = new File(path);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(set);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void unserialize() {
        File file = new File(path);
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            set.clear();
            set = (Set<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
