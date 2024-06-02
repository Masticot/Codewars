package conway_gol;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ConwayLifeTest{

  @Test
  public void testGlider() {
    int[][][][] tests = {
	    {
	      {{1,0,0},
	       {0,1,1},
	       {1,1,0}},
	      {{0,1,0},
	       {0,0,1},
	       {1,1,1}}
	    },
	    {
	      {{1,0,1},
	       {0,1,1},
	       {0,1,0}},
	      {{0,0,1},
	       {1,0,1},
	       {0,1,1}}
	    },
    };

    for (int[][][] test : tests) {
        int[][] res = ConwayLife.getGeneration(test[0], 1);
        assertArrayEquals(test[1], res);
    }
  }

  @Test
  public void complexTests() {
    int[][][][] tests = {
	    {
	      {{1,0,0,1,0},
	       {0,0,0,0,1},
	       {1,0,0,0,1},
	       {0,1,1,1,1}},
	      {{0,1,1,1,1},
	       {1,0,0,0,1},
	       {0,0,0,0,1},
	       {1,0,0,1,0}},
	    },
    };
    int[] generations = { 2 };

    for (int i = 0; i < tests.length; i++) {
        int[][] res = ConwayLife.getGeneration(tests[i][0], generations[i]);
        assertArrayEquals(tests[i][1], res);
    }
  }
}