package priyaseleniumlearning.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterFm {

	public static ExtentReports getReportObject() {	
	String path = System.getProperty("user.dir")+ "//reports//index.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setReportName("Automation Results for Product");
	reporter.config().setDocumentTitle("Test Results");
	
	ExtentReports extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Priya");
	extent.createTest(path);
	return extent;
	}
	
}

