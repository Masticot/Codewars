package codewars.regexparser;

public class Normal extends RegExp {
	private char character;
	
	public Normal(char character) {
		this.character = character;
	}
	
	@Override
	public String toString() {
		return "" + character;
	}
	
	@Override
	public String getType() {
		return "new Normal(" + character + ")";
	}
}
