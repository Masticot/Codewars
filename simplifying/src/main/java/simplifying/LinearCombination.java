package simplifying;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinearCombination extends Expression {
	private final static String patternSimpleTerm = "([+-]?\\d*[a-zA-Z])";
	private final static String patternOpen = "([+-]?\\d*)\\(";
	private final static String patternClose= "(\\))";
	 
	public LinearCombination(final String combination) {
		super(combination);
	}
	
	@Override
	protected void setCoeffs(final String combination) {
        Pattern pattern = Pattern.compile(patternSimpleTerm + "|" + patternOpen + "|" + patternClose);
        Matcher matcher = pattern.matcher(combination.replace(" ", ""));
        
        processExpression(matcher, 1);
	}

    private void processExpression(Matcher matcher, int outerCoef) {

        while (matcher.find()) {
        	if (matcher.group(1) != null){
        		processTerm(matcher.group(1), outerCoef);
        	}
        	else if (matcher.group(2) != null) {
        		String term = matcher.group(2);
                int coef = processCoefficient(term, outerCoef);
                processExpression(matcher, coef);
            }
            else if (matcher.group(3) != null) {
            	return;
            }
        }
    }

    private void processTerm(String term, int outerCoef) {
        if (term.isEmpty()) return;

        int coef = processCoefficient(term, outerCoef);
        String varName = term.replaceAll("[\\d+-]", "");
        coeffs.put(varName, coeffs.getOrDefault(varName, 0) + coef);
    }
    
    private int processCoefficient(String term, int outerCoef) {
        int sign = term.startsWith("-") ? -1 : 1;
        String coefStr = term.replaceAll("[^\\d]", "");
        int coef = coefStr.isEmpty() ? 1 : Integer.parseInt(coefStr);
        return sign * outerCoef * coef;
    }
}
