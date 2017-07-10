package parser.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import parser.obj.TestCaseObj;
import parser.obj.TestResultObj;

import com.acta.db.ActaDbException;
import com.acta.db.ActaDbManager;

public class ResultUploader {
	/*
	  public final static int ORACLE_DB =0;
	  public final static int MSSQLSERVER_DB =1;
	  public final static int DB2_DB =2;
	  public final static int INFORMIX_DB =3;
	  public final static int SYBASE_DB =4;
	  public final static int ODBC_DB =5;
	  public final static int MYSQL_DB = 6;
	  public final static int HANA_DB = 7;
	  public final static int CUSTOM_DB = 8;
	  public final static int SQLANYWHERE_DB = 9;
	  public final static int SQLANYWHEREASA_DB = 10;
	  public final static int DB_TYPE_RESERVED = 15;
	  
	  */
	/*
	select MAX(ID) from TR_TEST_SUITE

	INSERT INTO TR_TEST_SUITE("ID","BUILD_NUM","DATE","FUNCTION","PLANED_NUM","RUN_NUM","FAILED_NUM","PASSED_NUM") VALUES (1,'14.2.6.767','2015-05-05 09:41:38.0', 'file',2,2,0,2);

	INSERT INTO TR_TEST_CASE("ID","TEST_CASE","ATL_NAME","STATUS","SLAPSED_TIME","SP") VALUES (1,'tcase001','tstmpmap.atl','Analysis','00:00:17','NA')
	*/
	
	private final static String DB_PROP_PATH="db.properties";
	private final static int DB_TYPE=ActaDbManager.HANA_DB;
	
	private static String INSERT_TEST_CASE = "INSERT INTO TR_TEST_CASE(ID,TEST_CASE,ATL_NAME,STATUS,SLAPSED_TIME,SP) VALUES ({0},{1},{2},{3},{4},{5})";
	private static String INSERT_TEST_SUITE = "INSERT INTO TR_TEST_SUITE(ID,BUILD_NUM,DATE,FUNCTION,PLANED_NUM,RUN_NUM,FAILED_NUM,PASSED_NUM,TEST_SUITE,PLATFORM,ELAPSED_TIME) VALUES ({0},{1},{2},{3},{4},{5},{6},{7},{8},{9},{10})";
	private static String GET_TEST_SUITE_ID = "select MAX(ID) AS ID from TR_TEST_SUITE";

	private Connection conn;
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		ResultUploader up = new ResultUploader();
//		System.out.println(up.getMaxIDFromTestSuite());
//		
//		
//	}
	
	public ResultUploader(){
		
	}
	
	public void init() throws Exception{
		Properties _repositoryProperties = new Properties();
		
		loadProp(DB_PROP_PATH,_repositoryProperties);
		
		conn = ActaDbManager.getConnection(DB_TYPE,_repositoryProperties);
			
	}
	
	public void close(){
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadProp(String file, Properties prop){
		InputStream input = null;
		 
		try {
			
//			input = new FileInputStream(file);
	 
			// load a properties file
//			prop.load(input);
			
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(file));
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void runSql(String sql) {

		Statement stmt = null;
		
		try{
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.commit();
		
			stmt.close();
			
		}catch(Exception e ){
			e.printStackTrace();
		}finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}
	
	private String getStrForDB(String str){
		String value = "";
		str = str.replace("'", "\"");
		if(str != null){
			value = "'" + str + "'";
		}
		return value;
	}
	
	public int getMaxIDFromTestSuite(){
		int value= 0;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(GET_TEST_SUITE_ID);
			while (rs.next()) {
				String v = rs.getString("ID");
				if (v != null)
					value = Integer.parseInt(v);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
		}
		return value;
		
	}

	public void insertTestSuiteObj(TestResultObj obj){
		
		int id = getMaxIDFromTestSuite() + 1;
		String suite_id = Integer.toString(id);
		
		String buildNum = getStrForDB(obj.get((TestResultObj.BUILD_NUM_KEY)));
		String date = getStrForDB(obj.get((TestResultObj.DATE_KEY)));
		String function = getStrForDB(obj.get((TestResultObj.FUNCTION_NAME_KEY)));
		String planed = obj.get((TestResultObj.TESTCASE_PLAN_KEY));
		String run = obj.get((TestResultObj.TESTCASE_RUN_KEY));
		String failed = obj.get((TestResultObj.TESTCASE_FAILED_KEY));
		String passed = obj.get((TestResultObj.TESTCASE_PASSED_KEY));
		String testSuite = getStrForDB(obj.get((TestResultObj.TEST_SUITEE_KEY)));
		String platform = getStrForDB(obj.get((TestResultObj.PLATFORM_KEY)));
		String strTotalTime = obj.get((TestResultObj.TOTAL_TIME_KEY));
		float tmpTotalTime = Float.parseFloat(strTotalTime);
		int intTotalTime = Math.round(tmpTotalTime/60);
		String totalTime = getStrForDB(String.valueOf(intTotalTime));
		
		if("".trim() == passed.trim()){
			passed = "0";
		}
		if("".trim() == failed.trim()){
			failed = "0";
		}
		
		String [] para = {suite_id,buildNum,date,function,planed,run,failed,passed,testSuite,platform,totalTime};
		String sql = MessageFormat.format(INSERT_TEST_SUITE, para);
		
//		System.out.println(sql);
		runSql(sql);
		
		List<TestCaseObj> testCases = obj.getTestCaseList();
		for(TestCaseObj tcObj : testCases){
			insertTestCaseObj(suite_id, tcObj);
		}
		
	}
	
	public void insertTestCaseObj(String suite_id,TestCaseObj obj){
		
		String testName = getStrForDB(obj.getProp(TestCaseObj.CASE_NAME_KEY));
		String atlName = getStrForDB(obj.getProp(TestCaseObj.ATL_NAME_KEY));
		String status = getStrForDB(obj.getProp(TestCaseObj.STATUS_KEY));
		String execTime = getStrForDB(obj.getProp(TestCaseObj.ELAPSED_TIME_KEY));
		String sp = getStrForDB(obj.getProp(TestCaseObj.SP_KEY));
		
		String [] para = {suite_id,testName,atlName,status,execTime,sp};
		String sql = MessageFormat.format(INSERT_TEST_CASE, para);
		
//		System.out.println(sql);
		
		runSql(sql);
	}
	
	

}
