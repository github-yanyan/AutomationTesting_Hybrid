package vat.automationtesting_hybrid;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

//import vat.automationtesting_hybrid.util.Constants;
//import vat.automationtesting_hybrid.util.ExcelUtils;
import vat.automationtesting_hybrid.util.ActionKeywords;
import vat.automationtesting_hybrid.util.Constants;
import vat.automationtesting_hybrid.util.Datetime;
import vat.automationtesting_hybrid.util.ExcelUtils;
import vat.automationtesting_hybrid.util.Log;

public class ExecutionScript {
	public static Properties OR;
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];
		
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String sData;
	public static String sTestStepID;
	public static String sObjectType;
	public static boolean bResult;
	
	public ExecutionScript() throws NoSuchMethodException, SecurityException{
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();	
	}
	
    public static void main(String[] args) throws Exception {
    	ExcelUtils.setExcelFile(Constants.Path_TestData);
    	DOMConfigurator.configure("log4j.xml");
    	String Path_OR = Constants.Path_OR;
		FileInputStream fs = new FileInputStream(Path_OR);
		OR= new Properties(System.getProperties());
		OR.load(fs);

		ExecutionScript startExecution = new ExecutionScript();
		startExecution.execute_TestCase();

		System.out.println("Execution done...");
    }
		
    private void execute_TestCase() throws Exception {
	    	int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
			for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
				bResult = true;
				sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases); 
				sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);
				if (sRunMode.equals("Yes")){
					Log.startTestCase(sTestCaseID);
					iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
					iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
					bResult=true;
					for (;iTestStep<iTestLastStep;iTestStep++){
			    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
			    		sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
			    		sObjectType = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObjectType, Constants.Sheet_TestSteps);
			    		sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Constants.Sheet_TestSteps);
			    		sTestStepID = ExcelUtils.getCellData(iTestStep, Constants.Col_TestScenarioID, Constants.Sheet_TestSteps);
			    		execute_Actions();
						if(bResult==false){
							ExcelUtils.setCellData(Constants.KEYWORD_FAILED,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
							Log.endTestCase(sTestCaseID);
							break;
							}						
						}
					if(bResult==true){
					ExcelUtils.setCellData(Constants.KEYWORD_PASSED,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
					Log.endTestCase(sTestCaseID);	
						}					
					}
				}
    		}	
     
     private static void execute_Actions() throws Exception {
		for(int i=0;i<method.length;i++){
			
			if(method[i].getName().equals(sActionKeyword)){
				method[i].invoke(actionKeywords,sPageObject,sObjectType, sData);
				if(bResult==true){
					ExcelUtils.setCellData(Constants.KEYWORD_PASSED,
											iTestStep,
											Constants.Col_TestStepResult,
											Constants.Sheet_TestSteps);
					
					
					break;
				}else{
					ExcelUtils.setCellData(Constants.KEYWORD_FAILED,
											iTestStep,
											Constants.Col_TestStepResult,
											Constants.Sheet_TestSteps);
					Datetime.dateTime();
					System.out.println("print");
					ActionKeywords.screenshotFailed("[" + Constants.KEYWORD_FAILED + "]_" +
											  sTestCaseID + "_" +
											  sTestStepID + "_" +
											  Datetime.sdateTime);
					ActionKeywords.closeBrowser("","","");
					break;
				}
			}
		}
     }
     
}
