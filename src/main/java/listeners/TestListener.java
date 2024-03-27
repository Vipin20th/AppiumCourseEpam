package listeners;

import java.io.File;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import appiumBase.AppDriver;
import appiumBase.AppFactory;
import appiumBase.AppiumServer;
import report.ReportInit;

public class TestListener implements ITestListener {

	ExtentTest test;

	ExtentReports extent;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		ReportInit.setTest(test);
		
		
		String Platform = result.getMethod().getXmlTest().getLocalParameters().get("platform");
		AppFactory app = new AppFactory();
		if (Platform.contains("android")) {
			try {
				app.Android_LaunchApp();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (Platform.contains("ios")) {
			try {
				app.iOS_LaunchApp();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			ReportInit.getTest().log(Status.PASS, "Success");
			tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void onTestFailure(ITestResult result) {
		try {
			String testCaseName = result.getMethod().getMethodName();
			String path = System.getProperty("user.dir");
			String desitination;
			String Platform = result.getMethod().getXmlTest().getLocalParameters().get("platform");
			if (Platform.contains("android")) {
				desitination = path + "/ScreenShot/Android/" + testCaseName + ".jpg";
				File file = ((TakesScreenshot) AppDriver.getDriver()).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(file, new File(desitination));
			} else {
				desitination = path + "/ScreenShot/IOS/" + testCaseName + ".jpg";
				File file = ((TakesScreenshot) AppDriver.getDriver()).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(file, new File(desitination));
			}
			System.out.println(result.getThrowable());
			ReportInit.getTest().fail(result.getThrowable());
			ReportInit.getTest().addScreenCaptureFromPath(desitination);
			tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		//AppiumServer.Start();
		ReportInit report = new ReportInit();
		extent = report.config();

	}

	@Override
	public void onFinish(ITestContext context) {
		//AppiumServer.Stop();
		extent.flush();

	}

	public void tearDown() {
		AppDriver.getDriver().quit();
	}
}