package report;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportInit {

	static ExtentReports extent;
	ExtentSparkReporter spark;

	public ExtentReports config() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss z");
		Date date = new Date(System.currentTimeMillis());
		String ForDate = formatter.format(date);
		String path = System.getProperty("user.dir");
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(path + "/target/AboutTimeMobileReport" + ForDate + ".html");
		spark.config().setReportName("Tksys_AppIum_Report");
		extent.attachReporter(spark);
		return extent;
	}

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public static ExtentTest getTest() {
		return extentTest.get();
	}

	public static void setTest(ExtentTest test) {
		extentTest.set(test);
		System.out.println("Driver is set");
	}

	public void closeReport() {
		extent.flush();
	}
}
