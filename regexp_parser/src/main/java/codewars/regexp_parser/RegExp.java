package codewars.regexp_parser;

public abstract class RegExp {
	protected String surroundString(String string) {
		return (string.length() > 1) ? "(" + string + ")" : string;
	}
	
	public abstract String getType();
}
