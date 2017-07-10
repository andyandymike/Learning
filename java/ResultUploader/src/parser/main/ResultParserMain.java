package parser.main;

import java.io.File;

import parser.db.ResultUploader;
import parser.obj.TestResultObj;

public class ResultParserMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if(!isValidParams(args)){
			System.exit(1);
		}
		
		String testSuite = args[0]; // oracle audit fileformat func_param generic SearchReplace teradata informix HANA webserviced
		String resultFilePath = args[1]; // "C:/Users/i074117/Downloads/fileformat.sum"
		String platform = args[2]; // "Linux"
		
		ResParser parser = new ResParser(testSuite, resultFilePath, platform);
		
		parser.parse();
		
		ResultUploader up = new ResultUploader();
		
		try{
			up.init();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		
		up.insertTestSuiteObj(parser.getResultObj());
		
		System.out.println("Done");
		System.exit(0);
	}
	
	public static boolean isValidParams(String[] args){
		
		if(args.length != 3){
			System.out.println("Use with params: ${TESTSUITE} ${sumfile} ${platform}");
			System.out.println("eg: \"fileformat\" \"fileformat.sum\" \"Linux\"");
			System.out.println("${platform} Linux|Windows|AIX|Solaris ");
			return false;
		}
		
//		if(!isValidBuild(args[0])){
//			System.out.println("Invalid build number: " + args[0]);
//			return false;
//		}
		
		if(!isValidFilePath(args[1])){
			System.out.println("Invalid file path: " + args[0]);
			return false;
		}
		
		return true;
	}
	
	public static boolean isValidBuild(String buildNum){
		boolean flag = false;
		String [] vers = buildNum.split("\\.");
		if(vers.length == 4)
			flag = true;
		
		return flag;
	}
	
	public static boolean isValidFilePath(String reslutPath){
		boolean flag = false;
		
		File file = new File(reslutPath);
		if(file.exists())
			flag = true;
		
		return flag;
	}
}
