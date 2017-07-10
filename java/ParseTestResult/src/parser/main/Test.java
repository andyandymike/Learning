package parser.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import parser.db.ResultUploader;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str = "Read_from_delimited_with_'|'_to_tab_delimited";
		str = str.replace("'", "\"");
		System.out.print(str);

		String resultFilePath = "C:/Users/i067382/PycharmProjects/eztestRobot/test/gbq2file.sum"; // Linux
		// String resultFilePath =
		// "C:/Users/i074117/Desktop/test_result_uploader/datatypes_ms.sum"; //
		// Win

		ResParser parser = new ResParser("test", resultFilePath, "Windows");

		parser.parse();
		parser.dumpResult();
		
		/*
		System.out.println("**********************************************************************");
		ResultUploader up = new ResultUploader();

		try {
			up.init();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		up.insertTestSuiteObj(parser.getResultObj());

		System.out.println("Done");
		*/
	}

}
