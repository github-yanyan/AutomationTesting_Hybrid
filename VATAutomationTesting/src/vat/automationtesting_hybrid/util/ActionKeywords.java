package vat.automationtesting_hybrid.util;

import static vat.automationtesting_hybrid.ExecutionScript.OR;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import vat.automationtesting_hybrid.ExecutionScript;

public class ActionKeywords {
	public static WebDriver driver;
	public static WebElement element;
	public static ByLocator getObj = new ByLocator();
	public static By ObjLocation;

//[KEYWORD] this will open browser
public static void openBrowser(String object, String objectType, String data){		
	Log.info("Opening Browser");
	try{				
		if(data.equalsIgnoreCase("Firefox")){
			driver = new FirefoxDriver();
			Log.info("Firefox browser started");				
			}
//		else if(data.equals("IE")){
//			driver=new InternetExplorerDriver();
//			Log.info("IE browser started");
//			}
		else if(data.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", Constants.Path_ChromeDriver);
			driver=new ChromeDriver();
			Log.info("Chrome browser started");
			}
		driver.manage().timeouts().implicitlyWait(Constants.waitTime, TimeUnit.SECONDS);
	}catch (Exception e){
		Log.error("Unable to open the Browser --- " + e.getMessage());
		ExecutionScript.bResult = false;
	}
}

//[KEYWORD] navigate to URL
public static void gotoURL(String object, String objectType, String data){
	try{
		Log.info("Navigating to URL " + data);
		driver.manage().timeouts().pageLoadTimeout(Constants.waitTime, TimeUnit.SECONDS);
		driver.navigate().to(data);
	}catch(TimeoutException e){
		Log.error("Page --- " + data +
				 " --- didn't load within " + Constants.waitTime +
				 " seconds: " + e.getMessage());
		ExecutionScript.bResult = false;
	}catch(Exception e){
		Log.error("Unable to navigate --- " + e.getMessage());
		ExecutionScript.bResult = false;
	}
}

//[KEYWORD] navigate back to one page on the browser's history
public static void backPage(String object, String objectType, String data){
	try{
		Log.info("Navigating backward to one page --- " + driver.getCurrentUrl());
		driver.manage().timeouts().pageLoadTimeout(Constants.waitTime, TimeUnit.SECONDS);
		driver.navigate().back();
	}catch(TimeoutException e){
		Log.error("Page didn't load within " + Constants.waitTime +
				 " seconds --- " + e.getMessage());
		ExecutionScript.bResult = false;
	}catch(Exception e){
		Log.error("Unable to navigate backward --- " + e.getMessage());
		ExecutionScript.bResult = false;
	}
}

//[KEYWORD] navigate forward to one page on the browser's history
public static void forwardPage(String object, String objectType, String data){
	try{
		Log.info("Navigating forward to one page --- " + driver.getCurrentUrl());
		driver.manage().timeouts().pageLoadTimeout(Constants.waitTime, TimeUnit.SECONDS);
		driver.navigate().forward();
	}catch(TimeoutException e){
		Log.error("Page didn't load within " + Constants.waitTime +
				  " seconds --- " + e.getMessage());
		ExecutionScript.bResult = false;
	}catch(Exception e){
		Log.error("Unable to navigate forward --- " + e.getMessage());
		ExecutionScript.bResult = false;
	}
}

//[KEYWORD] refresh current page
public static void refreshPage(String object, String objectType, String data){
	try{
		Log.info("Page refreshed --- " + driver.getCurrentUrl());
		driver.manage().timeouts().pageLoadTimeout(Constants.waitTime, TimeUnit.SECONDS);
		driver.navigate().refresh();
	}catch(TimeoutException e){
		Log.error("Page didn't load within " + Constants.waitTime +
				  " seconds --- " + e.getMessage());
		ExecutionScript.bResult = false;
	}catch(Exception e){
		Log.error("Unable to refresh page --- " + e.getMessage());
		ExecutionScript.bResult = false;
	}
}

//[KEYWORD] delete all cookies
public static void deleteAllCookies(String object, String objectType, String data){
	try{
		Log.info("All cookies deleted --- " + driver.getCurrentUrl());
		driver.manage().deleteAllCookies();
	}catch(Exception e){
		Log.error("Unable to delete all cookies --- " + e.getMessage());
		ExecutionScript.bResult = false;
	}
}

//[KEYWORD] retrieve specific cookie
public static void checkCookie(String object, String objectType, String data){
	//to be continue
}

//[KEYWORD] click the object
public static void click(String object,String objectType, String data){
	try{
		Log.info("Clicking on Webelement " + object);
		ObjLocation = getObj.getObject(OR.getProperty(object), objectType);
		fluentWait(ObjLocation).click();
	}catch(TimeoutException e){
		Log.error("Webelement --- " + object +
				 " --- didn't load within " + Constants.waitTime +
				 " seconds: " + e.getMessage());
		ExecutionScript.bResult = false;
	}catch(Exception e){
		Log.error("Unable to click --- " + e.getMessage());
		ExecutionScript.bResult = false;
    }
}

//[KEYWORD] input text in the object
public static void setText(String object, String objectType, String data){
	try{
		Log.info("Entering the text in " + object);
		ObjLocation = getObj.getObject(OR.getProperty(object), objectType);
		fluentWait(ObjLocation).sendKeys(data);
	 }catch(TimeoutException e){
		Log.error("Webelement --- " + object +
				 " --- didn't load within " + Constants.waitTime +
				 " seconds: " + e.getMessage());
		ExecutionScript.bResult = false;
	 }catch(Exception e){
		 Log.error("Unable to Enter UserName --- " + e.getMessage());
		 ExecutionScript.bResult = false;
	 	}
}

//[KEYWORD] wait for an asynchronous script to finish execution
public static void timeoutScript(String object, String objectType, String data){
	try{
		Log.info("Wait for script to load " + data + " seconds");
		driver.manage().timeouts().setScriptTimeout(Long.parseLong(data), TimeUnit.SECONDS);
	 }catch(TimeoutException e){
		Log.error("Script didn't load within " + data +
				 " seconds: " + e.getMessage());
		ExecutionScript.bResult = false;
	 }catch(Exception e){
		 Log.error("Error occur while waiting script to load --- " + e.getMessage());
		 ExecutionScript.bResult = false;
     }
}

//[KEYWORD] wait for a page load to complete
public static void timeoutPageLoad(String object, String objectType, String data){
	try{
		Log.info("Wait for page to load " + data + " seconds");
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(data), TimeUnit.SECONDS);
	 }catch(TimeoutException e){
		Log.error("Page didn't load within " + data +
				 " seconds: " + e.getMessage());
		ExecutionScript.bResult = false;
	 }catch(Exception e){
		 Log.error("Error occur while waiting page to load --- " + e.getMessage());
		 ExecutionScript.bResult = false;
     }
}

//[KEYWORD] notify webdriver instance to wait for specific time
public static void waitImplicit(String object, String objectType, String data){
	try{
		Log.info("Wait for " + data + " seconds");
		driver.manage().timeouts().implicitlyWait(Long.parseLong(data), TimeUnit.SECONDS);
	 }catch(TimeoutException e){
		Log.error("Didn't load within " + data +
				 " seconds: " + e.getMessage());
		ExecutionScript.bResult = false;
	 }catch(Exception e){
		 Log.error("Error occur while waiting to load --- " + e.getMessage());
		 ExecutionScript.bResult = false;
     }
}

/*
 *[KEYWORD] used to advise the WebDriver instance to stand by
 *the execution till the time of certain condition is met or the maximum wait time is elapsed
 */
//public static void waitExplicit(String object, String objectType, String data){
//	WebDriverWait wait = new WebDriverWait(driver, Constants.explicitWaitTime);
//	try{
//		ExpectedConditions expCond;
//		Log.info("Explicit wait used " + data);
//	 }catch(TimeoutException e){
//		Log.error("Didn't load within " + data +
//				 " seconds: " + e.getMessage());
//		ExecutionScript.bResult = false;
//	 }catch(Exception e){
//		 Log.error("Error occur while waiting to load --- " + e.getMessage());
//		 ExecutionScript.bResult = false;
//     }
//}

//[KEYWORD] screenshot webbrowser content
public static void screenshot(String object, String objectType, String data){
	try {
		if(data.isEmpty()){
			Datetime.dateTime();
			data =  ExecutionScript.sTestCaseID + "_" +
					ExecutionScript.sTestStepID + "_" +
					Datetime.sdateTime + Constants.Screenshot_JPEG;
		} 
		Log.info("Screenshot of " + data + " taken");
		driver.manage().timeouts().pageLoadTimeout(Constants.waitTime, TimeUnit.SECONDS);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(Constants.Path_Screenshot + data +
											 Constants.Screenshot_JPEG));
	}catch(TimeoutException e){
		Log.info("Page --- " + data +
				 " --- didn't load within " + Constants.waitTime +
				 " seconds: " + e.getMessage());
		ExecutionScript.bResult = false;
	} catch (Exception e) {
		Log.error("Unable to take screenshot --- " + e.getMessage());
		ExecutionScript.bResult = false;
	}
}

//[KEYWORD] close the browser
public static void closeBrowser(String object, String objectType, String data){
	try{
		Log.info("Closing the browser");
		driver.quit();
	 }catch(Exception e){
		 Log.error("Unable to Close the Browser --- " + e.getMessage());
		 ExecutionScript.bResult = false;
     }
}

//[AUTO] fluentwait process
public static WebElement fluentWait(By locator){
	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		.withTimeout(Constants.waitTime, TimeUnit.SECONDS)
        .pollingEvery(Constants.pollingTime, TimeUnit.SECONDS)
        .ignoring(NoSuchElementException.class);

	element = wait.until(new Function<WebDriver, WebElement>() {
		  public WebElement apply(WebDriver drvr) {
		    return drvr.findElement(locator);
		  }
		});
	return element;
}

//[AUTO] screenshot when failed
public static void screenshotFailed(String imgName){
	try {
		Log.info("[FAILED]Screenshot of " + imgName + " taken");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(Constants.Path_Screenshot + imgName +
											 Constants.Screenshot_JPEG));
	} catch (Exception e) {
		Log.error("[FAILED]Unable to take screenshot --- " + e.getMessage());
	}
}

}
