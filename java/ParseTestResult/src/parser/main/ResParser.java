package parser.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.obj.*;
import parser.type.*;

public class ResParser {
	
	private final static String SPERATOR= "^";
	
	private final static String DATE_STR= "Date :";
	private final static String BUILD_NUM= "BUILD:";
	
	private final static String TESTCASE_PLAN= "Number of testcase planed:";
	private final static String TESTCASE_RUN= "Number of testcase run:";
	private final static String TESTCASE_PASSED= "Number of testcase passed:";
	private final static String TESTCASE_FAILED= "Number of testcase failed:";
	
	private final static String TOTAL_TIME= "TOTAL ELAPSE TIME:";

	private final static String START_STR= "-------";
	private final static String END_STR= "";
	
	//
	private List<ParserType> list = new ArrayList<ParserType>();
	private TestResultObj resultObj = new TestResultObj();
	private String resultFilePath ;
	
	private void init(){
		list.add(new ExtractType(DATE_STR,TestResultObj.DATE_KEY));
		list.add(new ExtractType(BUILD_NUM,TestResultObj.BUILD_NUM_KEY));
		list.add(new ReadRowType(START_STR,END_STR));
		list.add(new ExtractType(TESTCASE_PLAN,TestResultObj.TESTCASE_PLAN_KEY));
		list.add(new ExtractType(TESTCASE_RUN,TestResultObj.TESTCASE_RUN_KEY));
		list.add(new ExtractType(TESTCASE_PASSED,TestResultObj.TESTCASE_PASSED_KEY));
		list.add(new ExtractType(TESTCASE_FAILED,TestResultObj.TESTCASE_FAILED_KEY));
		list.add(new ExtractType(TOTAL_TIME,TestResultObj.TOTAL_TIME_KEY,false));
	}
	
	public ResParser(String testSuite,String file,String platform){
		this.resultFilePath = file;
		init();
		
//		resultObj.set(TestResultObj.BUILD_NUM_KEY, version);
		resultObj.set(TestResultObj.TEST_SUITEE_KEY, testSuite);
		resultObj.set(TestResultObj.PLATFORM_KEY, platform.toUpperCase());
	}
	
	public void parse (){
		
		String result = "";
		
		// read result from file to string
		try {
			result = readFile(resultFilePath);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		// read function from file name
		String functionName = getFunctionName(resultFilePath);
		resultObj.set(TestResultObj.FUNCTION_NAME_KEY, functionName);
	
		// parse result
		for(ParserType type : list){
			if(type instanceof ExtractType){
				// read value from key string
				String findStr = ((ExtractType)type).getParse_key();
				String reportKey = ((ExtractType)type).getReport_key();
				
				String reportValue = extractStrBySep(result,findStr);
				boolean ignoreSpace = ((ExtractType)type).isIgnore_space();
				
				// get value end with space
				if(!ignoreSpace){
					int index = reportValue.indexOf(" ");
					if(index > -1)
					{
						reportValue = reportValue.substring(0,index);
					}
				}
				
				// convert execution time
				if(findStr.equals(TOTAL_TIME)){
					reportValue = getExecutionTime(reportValue);
				}else if(findStr.equals(DATE_STR)){
					reportValue = StrToFormatDate(reportValue);
				}
				
				if(reportValue == null || reportValue.trim() == ""){// if cannot get value from file, check env
					String envTmp = System.getenv(reportKey);
					if(envTmp!= null)
						reportValue = envTmp;
				}
				
				resultObj.set(reportKey, reportValue);
				
			}else if(type instanceof ReadRowType){
				// read row to get test case result
				String startStr = ((ReadRowType)type).getStart_str();
				String endStr = ((ReadRowType)type).getEnd_str();
				
				String[] testCaselist = getTestCases(result,startStr,endStr);
				for(String testCaseStr : testCaselist){
					TestCaseObj obj = strToTestCase(testCaseStr);
					resultObj.addTestCase(obj);
				}
			}
		}
	}
	
	public void dumpResult(){
		resultObj.dump();
	}

	
	private String readFile(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			sb.append(line);
			sb.append(SPERATOR);
		}
		bufferedReader.close();
		return sb.toString();
	}
	
	private String extractStrBySep(String string, String key)  {

		String value = "";
		Pattern p = Pattern.compile(key + "([^"+ SPERATOR +"]+)");
		Matcher m = p.matcher(string);
		if (m.find()) {
			value = m.group(1).trim(); 
		}
		
		return value;
	}
	
	private TestCaseObj strToTestCase(String string)  {

		TestCaseObj obj = new TestCaseObj();
		
		String [] list = string.split("\\s+");
		
		String case_name = "";
		String atl_name = "";
		String status = "";
		String elapsed_time = "0";
		
		
		if(list.length >= 5){
		// tcase001 WebService setup    Passed    577893480378925062 secs
			case_name = list[0];
			atl_name = list[1];
			status = list[list.length - 3];
			elapsed_time = list[list.length - 2];

			for (int i = 2; i < list.length - 3; i++) {
				atl_name += "_";
				atl_name += list[i];
			}
		}else if(list.length == 4){
			//BJ_046  Passed    577893480378925067 secs
			case_name = list[0];
			status = list[1];
			elapsed_time = list[2];
			
		}else{
			return obj;
		}
		
		// convert time 
		elapsed_time = getExecutionTime(elapsed_time);

		try{
			obj.setProp(TestCaseObj.CASE_NAME_KEY,case_name);
			obj.setProp(TestCaseObj.ATL_NAME_KEY,atl_name);
			obj.setProp(TestCaseObj.STATUS_KEY,status);
			obj.setProp(TestCaseObj.ELAPSED_TIME_KEY,elapsed_time);
			// TBD
			obj.setProp(TestCaseObj.SP_KEY,"NA");
			
		}catch(Exception e){
			return null;
		}
		
		return obj;
		
	}
	
	private String[] getTestCases (String str, String start_key, String end_key){
		String[] list = null;
		String startStr =  start_key + SPERATOR;
		String engStr = SPERATOR + end_key +SPERATOR;
		
		int beginIndex = str.indexOf(startStr) + startStr.length();
		String testCaseStr = str.substring(beginIndex);
		
		int endIndex = testCaseStr.indexOf(engStr);
		testCaseStr = testCaseStr.substring(0, endIndex);
		
		list = testCaseStr.split("\\" + SPERATOR);
		
		return list;
	}
	
	private String getFunctionName(String path){
		File file = new File(path);
		String name = file.getName();
		int index = name.lastIndexOf(".");
		if(index >= 0)
			name = name.substring(0, index);
		
		return name;
	}
	
	private String getExecutionTime(String value){
		
		if(value.contains(":")){
			long seconds = 0;
			try {
				if (value.contains(":")) {
					DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
					Date reference = dateFormat.parse("00:00:00");
					Date date = dateFormat.parse(value);
					seconds = (date.getTime() - reference.getTime()) / 1000L;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Long.toString(seconds);
		}
		
		long v = 4294967295L;
		long res = 0;
		
		try {
			res = Long.parseLong(value);
			res = (res & v) ;
		}catch(Exception e){
			return "0";
		}
		
		return Long.toString(res);
	}
	
	private String StrToFormatDate(String d){
		
		String formatterIn = "EEE MMM d HH:mm:ss yyyy";
		String formatterOut = "yyyy-MM-dd HH:mm:ss";
		
		String value = "";
		
		SimpleDateFormat dateFormatIn = new SimpleDateFormat(formatterIn);
		SimpleDateFormat dateFormatOut = new SimpleDateFormat(formatterOut);
		try {
			Date date = dateFormatIn.parse(d);
			value = dateFormatOut.format(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return value;
	}

	public TestResultObj getResultObj() {
		return resultObj;
	}
	
}
