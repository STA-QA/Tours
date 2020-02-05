package com.statravel.base;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseUtil {

	 public static WebDriver driver; 

	 ExtentReports extent;

	 static ExtentTest scenarioDef;

	 static ExtentTest features;
	
	 String applicationURL = "";	

	



	
	public static void getScreenshot(WebDriver driver, String screenShotName) throws IOException
    {
        String dateName=new SimpleDateFormat("yyyyMMDDHHMMSS").format(new Date());
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        String destination=System.getProperty("user.dir")+"/FailedScreenshots/"+screenShotName+dateName+".png";
        File finalDestination=new File(destination);
        FileUtils.copyFile(source, finalDestination);

            
    }

}
