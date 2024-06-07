package simplifying;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;


public class SolutionTest {
	
    private static String[][] examples = {
                         new String[] {"a + a = b", "b - d = c", "a + b = d"},
                         new String[] {"a + 3g = k", "-70a = g"},
                         new String[] {"-j -j -j + j = b"},
                         new String[] {"0S = T", "-2S + 6T = F", "8S + 9F = X", "1S + 6T - 3F + X = n", "-4S + 7T - 2F + 8X + 7n = o", "-0S - 7T + 2F + 8n - 6o = O"},
                         new String[] {"(-3f + q) + r = l", "4f + q = r", "-10f = q"},
                         new String[] {"-(-(-(-(-(g))))) - l  = h", "8l = g"},
                         };
    
    private static String[] formula = {"c + a + b",
                                       "-k + a",
                                       "-j - b",
                                       "4T - 2(6T - 1(3n + 4o + 4T) - 2X) + 2X - 3(3n + 4F) + 2(-3F + 1(1o - 3(2F + 6o)) - 3(5o + 3n - 2T)) - 3O",
                                       "20l + 20(q - 200f)",
                                       "h - l - g",
                                       };
    
    private static String[] expected = {"2a",
                                        "210a",
                                        "1j",
                                        "3985S",
                                        "-4580f",
                                        "-18l",
                                        };

    @Test
    public void sampleTests() {
        for (int i = 0 ; i < examples.length; i++) {
            sampleTest(i);
        }
    }
    
    private void sampleTest(int i) {
    	System.out.println(String.format("---------\nExamples:\t%s\nFormula:\t%s\nExpected:\t%s",
				    					Arrays.toString(examples[i]),
						                formula[i],
						                expected[i]));
		assertEquals(expected[i], EqSystem.simplify(examples[i], formula[i]));
    }
}