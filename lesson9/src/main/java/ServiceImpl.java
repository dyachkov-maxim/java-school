import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {

    @Override
    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "data", zip = true, identityBy = {String.class, double.class})
    public List<String> work(String item) {
        return null;
    }

    @Override
    @Cache(cacheType = CacheType.IN_MEMORY, listList = 100_000)
    public List<String> run(String item, double value, Date date) {
        return null;
    }

}
