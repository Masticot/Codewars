package codewars.regexparser;

import java.util.List;

public class Str extends RegExp {
	private List<RegExp> expressions;
	
	public Str(List<RegExp> expressions) {
		this.expressions = expressions;
	}
	
	@Override
	public String toString() {
		String string = "";
		for (final RegExp regexp : expressions) {
			string += regexp.toString();
		}
		return surroundString(string);
	}
	
	@Override
	public String getType() {
		String type = "new Str(";
		for (int i = 0; i < expressions.size(); i++) {
			type += expressions.get(i).getType();
			if (i < expressions.size() - 1) type += ", ";
		}
		return type + ")";
	}
}
