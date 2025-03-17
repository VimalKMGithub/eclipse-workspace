package day46;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@Override
	public void onStart(ITestContext context) {
		String fileName = "myReport.html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" + fileName);
		sparkReporter.config().setDocumentTitle("Automation Testing Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("ComputerName", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("TesterName", "Vimal");
		extent.setSystemInfo("Os", "Windows11");
		extent.setSystemInfo("BrowserName", "Chrome");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test case PASSED is: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is: " + result.getName());
		test.log(Status.FAIL, "Test case FAILED cause is: " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPPED is: " + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}