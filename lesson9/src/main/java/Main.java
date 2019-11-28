import exercise3.InvocationHandlerCalculator;

import java.lang.reflect.Proxy;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Service service = new ServiceImpl();

        Service serviceProxy = (Service) Proxy.newProxyInstance(
                ServiceImpl.class.getClassLoader(),
                ServiceImpl.class.getInterfaces(),
                new CacheProxy(service, "123.ser"));

        serviceProxy.run("sada", 4.6D, new Date());

//        CacheProxy cacheProxy = new CacheProxy("");
//        Service service = cacheProxy.cache(new ServiceImpl());

    }
}
