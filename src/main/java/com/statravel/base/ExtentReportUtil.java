package com.statravel.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil extends BaseUtil {

	String fileName = "extentreport.html";

	String fileName1 = "extentemailreport.html";

	public void extentReport() {
		// First is to create Extent Reports
		extent = new ExtentReports();

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Sta-Automation Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("STA-BDD");
		extent.attachReporter(htmlReporter);

	}

	public void flushReport() {
		extent.flush();
	}

}
