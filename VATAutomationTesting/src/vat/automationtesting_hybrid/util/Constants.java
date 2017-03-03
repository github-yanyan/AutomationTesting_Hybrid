package vat.automationtesting_hybrid.util;

public class Constants {
		//System Variables
//		public static final String baseURL = "http://automationpractice.com/index.php";
		public static final String Path_TestData = "C:/Users/VAT036/Desktop/VATAutomationTesting/src/vat/automationtesting_hybrid/files/TestFile.xlsx";
		public static final String Path_OR = "C:/Users/VAT036/Desktop/VATAutomationTesting/src/vat/automationtesting_hybrid/util/OR.txt";
		public static final String File_TestData = "TestFile.xlsx";
		public static final String Path_Screenshot = "C:/Users/VAT036/Desktop/VATAutomationTesting/Screenshot/";
		public static final String KEYWORD_FAILED = "FAILED";
		public static final String KEYWORD_PASSED = "PASSED";
		public static final String Path_ChromeDriver = "C:/chromedriver.exe";
		
		//Data Sheet Column Numbers
		public static final int Col_TestCaseID = 0;	
		public static final int Col_TestScenarioID =1 ;
		public static final int Col_RunMode =2 ;
		public static final int Col_Result =3 ;
		public static final int Col_PageObject =4 ;
		public static final int Col_PageObjectType =5 ;
		public static final int Col_ActionKeyword =6 ;
		public static final int Col_DataSet =7 ;
		public static final int Col_TestStepResult =8 ;
			
		// Data Engine Excel sheets
		public static final String Sheet_TestSteps = "Test Steps";
		public static final String Sheet_TestCases = "Test Cases";
		
		//Others
		public static final String Screenshot_JPEG = ".jpeg";
		public static final long waitTime = 30;
		public static final long explicitWaitTime = 10;
		public static final int pollingTime = 5;

}
