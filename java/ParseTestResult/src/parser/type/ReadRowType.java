package parser.type;

public class ReadRowType extends ParserType {
	private String start_str;
	private String end_str;
	private String find_str;

	public ReadRowType(String start_str, String end_str){
		setType(TYPE_READ_ROW);
		setStart_str(start_str);
		setEnd_str(end_str);
		find_str = start_str;
	}

	public String getStart_str() {
		return start_str;
	}

	public void setStart_str(String start_str) {
		this.start_str = start_str;
	}

	public String getEnd_str() {
		return end_str;
	}

	public void setEnd_str(String end_str) {
		this.end_str = end_str;
	}

	public String getFind_str() {
		return find_str;
	}

	public void useEndStr() {
		find_str = end_str;
	}
	
	
	
}
