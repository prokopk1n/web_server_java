import Application.MainApplication;
import org.springframework.boot.SpringApplication;
import org.testng.annotations.Test;

import java.io.IOException;

public class webTest {
    @Test
    public void runSeleniumIde() throws IOException {
        Process echo_hello = Runtime.getRuntime().exec("selenium-side-runner web-app.side");
    }
}
