package simplifying;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Expression {
	protected Map<String, Integer> coeffs;
	
	public Expression(final String expression) {
		coeffs = new LinkedHashMap<>();
		setCoeffs(expression);
	}

	public Map<String, Integer> getCoeffs() {
		return coeffs;
	}
	
	protected abstract void setCoeffs(final String expression);
}
