package com.statravel.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.vimalselvam.cucumber.listener.ExtentProperties;

public class Functions {

	String automationdashboard = "No";

	public static String screenshot(WebDriver driver, long ms) throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(scrFile, new File("./Reports/Screenshots_Fail/" + ms + ".png"));

		String dest = "Screenshots_Fail/" + ms + ".png";

		System.out.println("ScreenShot Taken");

		return dest;

	}

	public void dropDown(WebElement ele, String text) throws InterruptedException {
		ele.click();
		Thread.sleep(4000);
		Select dropdown = new Select(ele);
		dropdown.deselectByVisibleText(text);

	}

	public void sendDatatoCSVfile(String datatostore) {

		String path = System.getProperty("user.dir");
		String testdatapath = path + "\\src\\Testdata\\Bookingconfirmation.csv";

		try (PrintWriter writer = new PrintWriter(new File(testdatapath))) {
			StringBuilder sb = new StringBuilder();

			sb.append(datatostore);
			sb.append(',');
			sb.append('\n');

			writer.write(sb.toString());

			System.out.println("Refrence number stored in CSV file done!");

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public void reportfunction() {
		String timeStamp = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		if (automationdashboard.contentEquals("Yes")) {

			ExtentProperties extentProperties = ExtentProperties.INSTANCE;

			extentProperties.setKlovServerUrl("http://localhost");

			extentProperties.setKlovProjectName("CBE-Automation-Dashboard");

			extentProperties.setKlovReportName("Run    " + timeStamp);

			extentProperties.setMongodbHost("localhost");
			extentProperties.setMongodbPort(27017);
			extentProperties.setMongodbDatabase("klov");
			extentProperties.setReportPath("Reports/STAToursReport.html");

		} else {

			ExtentProperties extentProperties = ExtentProperties.INSTANCE;
			extentProperties.setReportPath("Reports/STAToursReport.html");

		}

	}

	

}
