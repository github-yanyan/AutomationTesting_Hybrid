package vat.automationtesting_hybrid.util;
import org.apache.log4j.Logger;
public class Log {
	//Initialize Log4j logs
	private static Logger Log = Logger.getLogger(Log.class.getName());//

	//beginning of the test case
	public static void startTestCase(String sTestCaseName){

	   Log.info("****************************************************************************************");
	   Log.info("****************************************************************************************");
	   Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
	   Log.info("****************************************************************************************");
	   Log.info("****************************************************************************************");

	   }

	//ending of the test case
	public static void endTestCase(String sTestCaseName){
	   Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
	   Log.info("X");
	   Log.info("X");
	   Log.info("X");
	   Log.info("X");

	   }

	//Method per level of bugs
	public static void info(String message) {
		   Log.info(message);
		   }

	public static void warn(String message) {
	   Log.warn(message);
	   }

	public static void error(String message) {
	   Log.error(message);
	   }

	public static void fatal(String message) {
	   Log.fatal(message);
	   }

	public static void debug(String message) {
	   Log.debug(message);
	   }
}
