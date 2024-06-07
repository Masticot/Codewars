package simplifying;

public class Equation extends Expression {

    public Equation(final String equation) {
        super(equation);
    }
    
    @Override
    protected void setCoeffs(final String equation) {
    	String[] sides = equation.split("=");
        var leftCoeffs = new LinearCombination(sides[0]).getCoeffs();
        var rightCoeffs = new LinearCombination(sides[1]).getCoeffs();
        
        coeffs.putAll(leftCoeffs);
        
        for (var entry : rightCoeffs.entrySet()) {
            String var = entry.getKey();
            var coef = entry.getValue();
            coeffs.put(var, coeffs.getOrDefault(var, 0) - coef);
        }
    }
}
