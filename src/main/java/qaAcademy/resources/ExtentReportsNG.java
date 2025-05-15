package qaAcademy.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	public static ExtentReports TestReports() {
		File file=new File(System.getProperty("user.dir")+"/reports/index.html");
		ExtentSparkReporter reporter=new ExtentSparkReporter(file);
		reporter.config().setReportName("Ecommerce Tests");
		reporter.config().setDocumentTitle("Ecommerce tests");
		ExtentReports reports=new ExtentReports();
		reports.attachReporter(reporter);
		return reports;
	}
}
