package func;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Test
public class CheckingConditionsTest {
    //private final Logger logger = Logger.getLogger(CheckingConditionsTest.class);
    final static Logger rootLogger = LogManager.getLogManager().getLogger("");


    @BeforeClass
    public static void beforeClass() {

    }

    @BeforeClass
    public static void beforeSuite() {
        rootLogger.setLevel(Level.ALL);
//        for (Handler h : rootLogger.getHandlers()) {
//            h.setLevel(Level.ALL);
//        }
    }

    @Test
    public void test1() {
        rootLogger.info("info");
        rootLogger.warning("warn");

        assert true;
    }

    @Test
    public void test2() {
        rootLogger.info("info");
        rootLogger.warning("warn");
        assert false;
    }

}
