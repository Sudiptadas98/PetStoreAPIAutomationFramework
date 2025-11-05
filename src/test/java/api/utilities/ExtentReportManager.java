package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    // ------------------ Initialize Report ------------------
    public static ExtentReports createInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String reportName = "Test-Report-" + timestamp + ".html";
            String reportPath = System.getProperty("user.dir") + "/reports/" + reportName;

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("RestAssured Automation Test Project");
            spark.config().setReportName("Pet Store Users API");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Application", "Pet Store Users API");
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tool", "RestAssured + TestNG");
        }
        return extent;
    }

    // ------------------ Listener Methods ------------------

    @Override
    public void onStart(ITestContext context) {
        System.out.println("==== Test Suite Started ====");
        createInstance(); // initialize extent
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("==== Test Suite Finished ====");
        extent.flush(); // write report to disk
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(testName);
        testThread.set(test);
        test.log(Status.INFO, "Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = testThread.get();
        test.createNode(result.getName());
        test.log(Status.PASS, "✅ Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testThread.get();
        test.createNode(result.getName());
        test.log(Status.FAIL, "❌ Test Failed: " + result.getMethod().getMethodName());
        test.log(Status.FAIL, "Reason: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = testThread.get();
        test.createNode(result.getName());
        test.log(Status.SKIP, "⚠️ Test Skipped: " + result.getMethod().getMethodName());
    }
}