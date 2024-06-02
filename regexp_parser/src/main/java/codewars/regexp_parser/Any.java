package codewars.regexp_parser;

public class Any extends RegExp {
	@Override
	public String toString() {
		return ".";
 	}
	
	@Override
	public String getType() {
		return "new Any()";
	}
}
