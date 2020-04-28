import main.java.AccountService;
import main.java.Transaction;
import org.apache.log4j.Logger;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("Приложение запущено.");

        AccountService accountService = new AccountService();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Transaction(accountService, i));
            t.start();
        }
    }

}
