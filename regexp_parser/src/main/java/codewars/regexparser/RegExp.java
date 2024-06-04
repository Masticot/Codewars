package codewars.regexparser;

public abstract class RegExp {
	protected String surroundString(String string) {
		return (string.length() > 1) ? "(" + string + ")" : string;
	}
	
	public abstract String getType();
}
