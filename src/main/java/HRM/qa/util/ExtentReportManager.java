package HRM.qa.util;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.time.LocalDateTime;

public class ExtentReportManager {

    private static final Logger logger = LogManager.getLogger(ExtentReportManager.class);

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Initialize the report (Overwrite existing report if exists)
    public static void initReports() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

            // Create file object to check if report already exists
            File reportFile = new File(reportPath);
            if (reportFile.exists()) {
                // If file exists, delete it to overwrite
                reportFile.delete();
                logger.info("Existing Extent Report deleted to overwrite.");
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Functional Test Results");
            sparkReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "QA Lead");

            logger.info("Extent report initialized at: " + reportPath);
        }
    }

    // Create test
    public static void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        logger.info("Created ExtentTest: " + testName);
    }

    public static void logInfo(String message) {
        test.get().info(message);
        logger.info("[Extent] " + message);
    }

    public static void logPass(String message) {
        test.get().pass(message);
        logger.info("[PASS] " + message);
    }

    public static void logFail(String message) {
        test.get().fail(message);
        logger.error("[FAIL] " + message);
    }

    public static void logSkip(String message) {
        test.get().skip(message);
        logger.warn("[SKIP] " + message);
    }

    public static void attachScreenshot(String path) {
        try {
            test.get().addScreenCaptureFromPath(path);
            logger.info("Attached screenshot: " + path);
        } catch (Exception e) {
            logger.error("Failed to attach screenshot: " + path, e);
        }
    }

    // Flush report (Call this in onFinish())
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
            logger.info("Flushed extent reports.");
        }
    }
}


