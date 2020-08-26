package firstproject.config;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import firstproject.config.Configuration;

public class Listeners extends Configuration implements ITestListener {
	
	ExtentTest test;
	ExtentReports report = Reports.reportRequisite();
	ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName();
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
//			String screenShotPath = getScreenShot(testMethodName, driver);
//			extenttest.get().addScreenCaptureFromPath(screenShotPath, result.getMethod().getMethodName());
			extenttest.get().log(Status.PASS, "Test Case is passed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName();
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			extenttest.get().fail(result.getThrowable());
			String screenShotPath = getScreenShot(testMethodName, driver);
			extenttest.get().addScreenCaptureFromPath(screenShotPath, result.getMethod().getMethodName());
		} catch (Exception e) {
			
		}
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
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		test = report.createTest(context.getName());
		extenttest.set(test);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();
	}

}
