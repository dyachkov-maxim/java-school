import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Account {
    private int count = 0;
    private boolean isLock;
    private long timeUnlock;
    private final PinValidator pinValidator;

    public Account() {
        this.timeUnlock = System.currentTimeMillis();
        pinValidator = new PinValidator();
    }

    public boolean isValid(int password) throws AccountIsLockedException {

        if (System.currentTimeMillis() < timeUnlock) {
            throw  new AccountIsLockedException(String.format("Аккаунт заблокирован до %s", dateAsString(timeUnlock)));
        }

        if (pinValidator.isValid(password)) {
            return true;
        }

        count++;
        if (count == 3) {
            timeUnlock = System.currentTimeMillis() + 3000;
            count = 0;
            throw  new AccountIsLockedException(String.format("Аккаунт заблокирован до %s", dateAsString(timeUnlock)));
        }

        return false;
    }

    private String dateAsString(long mills) {
        Date date = new Date(mills);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        return formatter.format(date);
    }
}
