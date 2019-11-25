import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
    private String[] classPath;

    public MyClassLoader(String[] classPath) {
        this.classPath = classPath;
    }

    @Override
    protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result = findClass(name);
        if (resolve) {
            resolveClass(result);
        }

        return result;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = findFile(name.replace('.', '/'), ".class");

        if (f == null) {
            return findSystemClass(name);
        }

        Class result = null;

        try {
            byte[] classBytes = loadFileAsBytes(f);
            result = defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(
                    "Cannot load class " + name + ": " + e);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException("Format of class file incorrect for class " + name + " : " + e);
        }

        return result;
    }


    private File findFile(String name, String extension) {
        File f;
        for (int i = 0; i < classPath.length; i++) {
            f = new File((new File(classPath[i])).getPath()
                    + File.separatorChar
                    + name.replace('/',
                    File.separatorChar)
                    + extension);
            if (f.exists()) {
                return f;
            }

        }
        return null;
    }

    public static byte[] loadFileAsBytes(File file) throws IOException {
        byte[] result = new byte[(int) file.length()];
        FileInputStream f = new FileInputStream(file);
        try {
            f.read(result, 0, result.length);
        } finally {
            try {
                f.close();
            } catch (Exception e) {
            }
        }
        return result;
    }

    @Override
    protected java.net.URL findResource(String name) {
        File f = findFile(name, "");
        if (f == null) {
            return null;
        }

        try {
            return f.toURL();
        } catch (java.net.MalformedURLException e) {
            return null;
        }
    }
}
