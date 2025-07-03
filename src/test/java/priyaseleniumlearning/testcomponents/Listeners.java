	package priyaseleniumlearning.testcomponents;
	
	import java.io.IOException;
	
	import org.openqa.selenium.WebDriver;
	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;
	
	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	
	import priyaseleniumlearning.resources.ExtentReporterFm;
	
	public class Listeners extends BaseTest implements ITestListener {
		ExtentTest test;
		ThreadLocal<ExtentTest> extentThread = new ThreadLocal<ExtentTest>();
		ExtentReports extent = ExtentReporterFm.getReportObject();
		
		@Override
		public void onTestStart(ITestResult result) {
			// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentThread.set(test);
		}
	
		@Override
		public void onTestSuccess(ITestResult result) {
			// TODO Auto-generated method stub
			extentThread.get().log(Status.PASS, "Test is Successful");
		}
	
		@Override
		public void onTestFailure(ITestResult result) {
			// TODO Auto-generated method stub
			extentThread.get().fail(result.getThrowable());
		
		try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		}
	
		@Override
		public void onTestSkipped(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void onTestFailedWithTimeout(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void onStart(ITestContext context) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void onFinish(ITestContext context) {
			// TODO Auto-generated method stub
			extent.flush();
		}
	
	
		
	}
