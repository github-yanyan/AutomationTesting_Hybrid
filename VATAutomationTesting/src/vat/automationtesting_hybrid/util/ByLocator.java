package vat.automationtesting_hybrid.util;


import org.openqa.selenium.By;

public class ByLocator {
public By getObject(String objectName,String objectType) throws Exception{
		if(objectType.equalsIgnoreCase("XPATH")){
			//find by xpath
			return By.xpath(objectName);
		} else if(objectType.equalsIgnoreCase("CLASSNAME")){
			//find by class
			return By.className(objectName);
		} else if(objectType.equalsIgnoreCase("NAME")){
			//find by name
			return By.name(objectName);
		} else if(objectType.equalsIgnoreCase("CSS")){
			//find by css
			return By.cssSelector(objectName);	
		} else if(objectType.equalsIgnoreCase("LINK")){
			//find by link
			return By.linkText(objectName);
		} else if(objectType.equalsIgnoreCase("PARTIALLINK")){
			//find by partial link
			return By.partialLinkText(objectName);
		} else {
			throw new Exception("Unknown object type");
		}
	}



}
