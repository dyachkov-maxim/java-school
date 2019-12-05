import org.junit.Assert;
import org.junit.Test;

public class TextBrowserImpl {
    @Test
    public void testForValidUrl() {
        Browser browser = new BrowserImpl();
        Assert.assertTrue(browser.readContent("www.google.com"));
        Assert.assertTrue(browser.readContent("www.mail.ru"));
        Assert.assertTrue(browser.readContent("www.yandex.ru"));
    }

    @Test
    public void testNotValidUrl() {
        Browser browser = new BrowserImpl();
        Assert.assertFalse(browser.readContent("www.google1.com"));
        Assert.assertFalse(browser.readContent("www.dghfdjdjdj.ru"));
    }
}
