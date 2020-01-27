import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BrowserImpl implements Browser {
    @Override
    public boolean readContent(String url) {
        if (!isValid(url)) {
            System.out.println("URL is not valid!");
            return false;
        }

        Socket soc = null;

        try {
            soc = isAvailable(url);

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())));
            out.println(String.format("GET %s", url));
            out.println();
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

            String inputLine;
            int count = 0;

            while ((inputLine = in.readLine()) != null) {
                count++;
                System.out.println(count);
                System.out.println(inputLine);
            }

            in.close();
        } catch (IOException e) {
            System.out.println("URL is not available!");
            if (soc != null) {
                try {
                    soc.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (soc != null) {
                try {
                    soc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    public boolean isValid(String url) {
        String regex = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
        try {
            Pattern patt = Pattern.compile(regex);
            Matcher matcher = patt.matcher(url);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }

    public Socket isAvailable(String url) throws IOException {
        Socket soc = new Socket();
        soc.connect(new InetSocketAddress(url, 80), 1000);

        return soc;
    }
}
