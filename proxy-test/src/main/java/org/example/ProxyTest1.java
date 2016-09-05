package org.example;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

public class ProxyTest1 {
    
    public static void main(String[] args) throws Exception {
        
        String proxyUser = System.getProperty("https.proxyUser");
        String proxyPassword = System.getProperty("https.proxyPassword");

        Authenticator.setDefault(new ProxyAuthenticator(proxyUser, proxyPassword));

//        System.setProperty("https.proxyHost", "localhost");
//        System.setProperty("https.proxyPort", "3128");

        URL url = new URL("https://github.com/jbossdemocentral/bpms-travel-agency-demo-repo.git");

        StringBuilder result = new StringBuilder();

        final InputStream in = url.openStream();
        final InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
        final BufferedReader bufReader = new BufferedReader(inReader);
        String line = null;
        while ((line = bufReader.readLine()) != null) {
            result.append(line);
        }
        bufReader.close();
        inReader.close();
        in.close();

        System.out.println(result);
    }
    
    static class ProxyAuthenticator extends Authenticator {

        private String user, password;

        public ProxyAuthenticator(String user, String password) {
            this.user = user;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password.toCharArray());
        }
    }
}
