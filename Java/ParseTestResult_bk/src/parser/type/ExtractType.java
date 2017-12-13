package parser.type;

public class ExtractType extends ParserType {

	private String parse_key;
	private String report_key;
	private boolean ignore_space;
	
	public ExtractType(String parse_key, String report_key, boolean ignore_space){
		setType(TYPE_EXTRACT_VALUE);
		setParse_key(parse_key);
		setReport_key(report_key);
		setIgnore_space(ignore_space);
	}

	public ExtractType(String parse_key, String report_key){
		this(parse_key,report_key,true);
	}

	public String getParse_key() {
		return parse_key;
	}

	public void setParse_key(String parse_key) {
		this.parse_key = parse_key;
	}

	public String getReport_key() {
		return report_key;
	}

	public void setReport_key(String report_key) {
		this.report_key = report_key;
	}

	public boolean isIgnore_space() {
		return ignore_space;
	}

	public void setIgnore_space(boolean ignore_space) {
		this.ignore_space = ignore_space;
	}
	
	
	
}
