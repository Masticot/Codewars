package simplifying;

public class EqSystem {

    public static String simplify(String[] equations, String formula) {
        var system = new LinearSystem(equations);
        for (var freeVar : system.getVariables()) {
        	var coeffs = system.solve(freeVar);
        	var combination = new LinearCombination(formula);
        	double k = 0;
        	
        	for (var entry : combination.getCoeffs().entrySet()) {
        		String var = entry.getKey();
        		int coef = entry.getValue();
        		Double coeffValue = coeffs.get(var);
        		if (coeffValue == null) {
        			throw new IllegalArgumentException("Variable \"" + var + "\" not found in the system coefficients.");
        		}
        		k += coef * coeffValue;
        	}
        	
        	if ((int) k - k < 10e-5) return (int) k + freeVar;
        }

        return "";
    }
}
