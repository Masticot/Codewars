package codewars.regexp_parser;

public class Or extends RegExp {
	private RegExp left;
	private RegExp right;
	
	public Or(final RegExp left, final RegExp right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return surroundString(left.toString() + "|" + right.toString());
 	}
	
	@Override
	public String getType() {
		return "new Or(" + left.getType() + ", " + right.getType() + ")";
	}
}
