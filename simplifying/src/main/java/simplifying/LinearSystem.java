package simplifying;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LinearSystem {
	private Equation[] equations;
	private Set<String> variables;
	private Map<String, Double> coeffs;
	
	public LinearSystem(final String[] equations) {
		this.equations = new Equation[equations.length];
		for (int i = 0; i < equations.length; i++) {
			this.equations[i] = new Equation(equations[i]);
		}
		
		variables = new LinkedHashSet<>();
        for (Equation eq : this.equations) {
            variables.addAll(eq.getCoeffs().keySet());
        }
	}
	
	public Map<String, Double> solve(final String freeVariable) {
		var variableIndex = mapVariablesToIndices(freeVariable);
		var matrix = buildMatrix(variableIndex);
		gaussJordanElimination(matrix);
		extractCoefficients(matrix, variableIndex);
		return coeffs;
	}

	public Set<String> getVariables() {
		return variables;
	}
	
    private Map<String, Integer> mapVariablesToIndices(final String freeVariable) {

        var variableIndex = new HashMap<String, Integer>();
        int index = 0;
        for (var variable : getVariables()) {
        	if (!variable.equals(freeVariable)) variableIndex.put(variable, index++);
        }
        variableIndex.put(freeVariable, index); // Assign the last index to the free variable

        return variableIndex;
    }

    private double[][] buildMatrix(Map<String, Integer> variableIndex) {
        double[][] matrix = new double[equations.length][variableIndex.size()];
        for (int i = 0; i < equations.length; i++) {
            for (var entry : equations[i].getCoeffs().entrySet()) {
                String var = entry.getKey();
                int coef = entry.getValue();
                int colIndex = variableIndex.get(var);
                matrix[i][colIndex] += coef;
            }
        }
        return matrix;
    }

    private void gaussJordanElimination(double[][] A) {
        int rows = A.length;
        int cols = A[0].length;

        int row = 0;
        for (int col = 0; col < cols - 1; col++) {
            if (row >= rows) break;

            int pivot = row;
            while (pivot < rows && A[pivot][col] == 0) {
                pivot++;
            }
            if (pivot == rows) continue;

            if (pivot != row) {
                double[] temp = A[row];
                A[row] = A[pivot];
                A[pivot] = temp;
            }

            double pivotValue = A[row][col];
            for (int j = col; j < cols; j++) {
                A[row][j] /= pivotValue;
            }

            for (int i = 0; i < rows; i++) {
                if (i != row) {
                    double factor = A[i][col];
                    for (int j = col; j < cols; j++) {
                        A[i][j] -= factor * A[row][j];
                    }
                }
            }
            row++;
        }
    }

    private void extractCoefficients(double[][] matrix, Map<String, Integer> variableIndex) {
        coeffs = new HashMap<>();
        for (var entry : variableIndex.entrySet()) {
            String var = entry.getKey();
            int index = entry.getValue();
            if (index == getVariables().size() - 1) {
            	coeffs.put(var, 1.0);
            	continue;
            }
            coeffs.put(var, -matrix[index][matrix[0].length - 1]);
        }
    }
}
