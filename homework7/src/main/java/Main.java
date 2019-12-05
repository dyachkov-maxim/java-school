import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class Main {
    public static final String PATH_1 = "/home/max/IdeaProjects/untitled4/folder1/";
    public static final String PATH_2 = "/home/max/IdeaProjects/untitled4/folder2/";

    private static final String CLASS_NAME = "TestModule";
    public static final String METHOD_NAME_INCORRECT = "say";
    public static final String METHOD_NAME_CORRECT = "sayHello";

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> trueClass = null;
        MyClassLoader loader = new MyClassLoader(new String[]{PATH_1});
        Class<?> clazz = Class.forName("TestModule", true, loader);
        System.out.println(clazz.getClassLoader());
        try {
            Method method = clazz.getMethod(METHOD_NAME_CORRECT);
            trueClass = clazz;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        MyClassLoader loader2 = new MyClassLoader(new String[]{PATH_2});
        Class<?> clazz2 = Class.forName("TestModule", true, loader2);
        System.out.println(clazz2.getClassLoader());
        try {
            Method method = clazz.getMethod(METHOD_NAME_CORRECT);
            trueClass = clazz2;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println(trueClass.getClassLoader());

//        Object object = clazz.newInstance();
//        System.out.println(object.getClass().getClassLoader());
//        System.out.println(object);

//        try {
//            Class<?> aClass = Class.forName(CLASS_NAME);
//            System.out.println(aClass.getName());
//        } catch (ClassNotFoundException e) {
//            System.out.println("Класс " + CLASS_NAME + " отсутсвует в ClassPath  ");
//        }
//
//        URLClassLoader classLoaderByDir1 = getClassLoaderByDir(PATH_1);
//        Class<?> clazzFirst = loadClass(classLoaderByDir1, CLASS_NAME);
//        if (searchMethod(clazzFirst, METHOD_NAME_CORRECT)) {
//            System.out.println("Norm classLoaderByDir1");
//        }
//
//        URLClassLoader classLoaderByDir2 = getClassLoaderByDir(PATH_2);
//        Class<?> clazzSecond = loadClass(classLoaderByDir2, CLASS_NAME);
//        if (searchMethod(clazzSecond, METHOD_NAME_CORRECT)) {
//            System.out.println("Norm classLoaderByDir2");
//        }
//    }
//
//    public static URLClassLoader getClassLoaderByDir(String directoryName) throws MalformedURLException {
//        File dir = new File(directoryName);
//        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{dir.toURI().toURL()});
//        return urlClassLoader;
//    }
//
//    private static Class<?> loadClass(URLClassLoader classLoader, String clazzName) {
//        Class<?> clazzSecond = null;
//        try {
//            clazzSecond = classLoader.loadClass(clazzName);
//            System.out.println("Из " + Arrays.asList(classLoader.getURLs()).get(0).getPath() + " загрузили класс " + clazzSecond.getName() + "\n");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Не удалось загрузить класс " + clazzName + " из класслоадера " + classLoader.toString());
//        }
//        return clazzSecond;
//    }
//
//    private static boolean searchMethod(Class<?> clazz, String methodName) {
//        try {
//            Method say = clazz.getMethod(methodName);
//            System.out.println("В классе " + clazz.getName() + " загрузчика " + clazz.getClassLoader() + " найден метод " + say.getName());
//            return true;
//        } catch (NoSuchMethodException e) {
//            System.out.println("В классе " + clazz.getName() + " загрузчика " + clazz.getClassLoader() + " метод " + methodName + " не найден!");
//        }
//        return false;
    }
}
