package codewars.regexp_parser;

public class ZeroOrMore extends RegExp {
	private RegExp expression;
	
	public ZeroOrMore(final RegExp expression) {
		this.expression = expression;
	}
	
	@Override
	public String toString() {
		return expression.toString() + "*";
 	}
	
	@Override
	public String getType() {
		return "new ZeroOrMore(" + expression.getType() + ")";
	}
}
