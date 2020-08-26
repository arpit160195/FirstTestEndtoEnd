package firstproject.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	
	static ExtentReports report;
	
	public static ExtentReports reportRequisite() {
		//ExtentReports, ExtentSparkReporter
		String path = System.getProperty("user.dir") + "\\reports\\report.html";
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(path);
		sparkReport.config().setReportName("ExtentReportDemo");
		sparkReport.config().setDocumentTitle("ReportDemo");
		
		report = new ExtentReports();
		report.attachReporter(sparkReport);
		return report;
	}
	
}
