package parser.obj;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestResultObj {
	
	public final static String DATE_KEY= "test_date";
	
	public final static String TESTCASE_PLAN_KEY= "test_planed";
	public final static String TESTCASE_RUN_KEY= "test_run";
	public final static String TESTCASE_PASSED_KEY= "test_passed";
	public final static String TESTCASE_FAILED_KEY= "test_failed";
	
	public final static String TOTAL_TIME_KEY= "test_time";
	
	public final static String FUNCTION_NAME_KEY= "function_name";
	public final static String BUILD_NUM_KEY= "build_version";
	public final static String TEST_SUITEE_KEY= "test_suite";
	public final static String PLATFORM_KEY= "platform";
	
	//result 
	private Properties result = new  Properties();
	private List<TestCaseObj> testCaseList= new ArrayList<TestCaseObj>();
	
	public void set(String key, String value){
		result.setProperty(key, value);
	}
	
	public String get(String key){
		return result.getProperty(key);
	}
	
	public void addTestCase(TestCaseObj obj){
		testCaseList.add(obj);
	}
	
	public List<TestCaseObj> getTestCaseList(){
		return testCaseList;
	}
	
	public void dump(){
		for ( String key : result.stringPropertyNames() ) {
			System.out.println(key + ": " + result.getProperty(key));
		}
		for (TestCaseObj obj : testCaseList){
			obj.dump();
		}
	}
}
