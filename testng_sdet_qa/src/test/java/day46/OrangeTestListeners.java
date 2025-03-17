package day46;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class OrangeTestListeners implements ITestListener {
	@Override
	public void onStart(ITestContext context) {
		System.out.println("On start.....");
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("test start...");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("test success...");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("test failure..");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("test skipped..");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("On finish....");
	}
}