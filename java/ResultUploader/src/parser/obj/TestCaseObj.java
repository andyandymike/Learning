package parser.obj;

import java.util.Properties;

public class TestCaseObj {
	
	public final static String CASE_NAME_KEY= "case_name";
	public final static String ATL_NAME_KEY= "atl_name";
	public final static String STATUS_KEY= "status";
	public final static String ELAPSED_TIME_KEY= "elapsed_time";
	public final static String SP_KEY= "sp";
	
	//result 
	private Properties result = new  Properties();
	
	public TestCaseObj(){
	}
	
	public TestCaseObj(String name,String atlName,String status,String elapsedTime,String sp){
		result.setProperty(CASE_NAME_KEY, name);
		result.setProperty(ATL_NAME_KEY, atlName);
		result.setProperty(STATUS_KEY, status);
		result.setProperty(ELAPSED_TIME_KEY, elapsedTime);
		result.setProperty(SP_KEY, sp);
	}
	
	public void setProp(String key,String value){
		result.setProperty(key, value);
	}

	public String getProp(String key){
		return result.getProperty(key);
	}
	
	public void dump(){
		for ( String key : result.stringPropertyNames() ) {
			System.out.print(key + ":" + result.getProperty(key));
			System.out.print(" | ");
		}
		System.out.println();
	}
	
}
