package codewars.whitespace;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class WhitespaceInterpreterTest {

	@Test
	public void testPush() {
		System.out.println("\n\nTesting push, output of numbers 0 through 3");
		String[][] tests = {
				{"   \t\n\t\n \t\n\n\n", "1"},
				{"   \t \n\t\n \t\n\n\n", "2"},
				{"   \t\t\n\t\n \t\n\n\n", "3"},
				{"    \n\t\n \t\n\n\n", "0"}
		};
		for (String[] test : tests) {
			assertEquals(test[1], WhitespaceInterpreter.execute(test[0], null));
		}
	}

	@Test
	public void testOutNumbers() {
		System.out.println("\n\nTesting ouput of numbers -1 through -3");
		String[][] tests = {
				{"  \t\t\n\t\n \t\n\n\n", "-1"},
				{"  \t\t \n\t\n \t\n\n\n", "-2"},
				{"  \t\t\t\n\t\n \t\n\n\n", "-3"},
		};
		for (String[] test : tests) {
			assertEquals(test[1], WhitespaceInterpreter.execute(test[0], null));
		}
	}

	@Test
	public void testFlowEdge() {
		System.out.println("\n\nTesting simple flow control edge case");
		assertThrows(Exception.class, () -> WhitespaceInterpreter.execute("", null));
	}

	@Test
	public void testOutLetters() {
		System.out.println("\n\nTesting output of letters A through C");
		String[][] tests = {
				{"   \t     \t\n\t\n  \n\n\n", "A"},
				{"   \t    \t \n\t\n  \n\n\n", "B"},
				{"   \t    \t\t\n\t\n  \n\n\n", "C"},
		};
		for (String[] test : tests) {
			assertEquals(test[1], WhitespaceInterpreter.execute(test[0], null));
		}
	}

	@Test
	public void testOutLettersWithComments() {
		System.out.println("\n\nTesting output of letters A through C with comments");
		String[][] tests = {
				{"blahhhh   \targgggghhh     \t\n\t\n  \n\n\n", "A"},
				{" I heart \t  cats  \t \n\t\n  \n\n\n", "B"},
				{"   \t  welcome  \t\t\n\t\n to the\nnew\nworld\n", "C"},
		};
		for (String[] test : tests) {
			assertEquals(test[1], WhitespaceInterpreter.execute(test[0], null));
		}
	}

	@Test
	public void testStack() {
		System.out.println("\n\nTesting stack functionality");
		String[][] tests = {
				{"   \t\t\n   \t\t\n\t\n \t\t\n \t\n\n\n", "33"},
				{"   \t\t\n \n \t\n \t\t\n \t\n\n\n", "33"},
				{"   \t\n   \t \n   \t\t\n \t  \t \n\t\n \t\n\n\n", "1"},
				{"   \t\n   \t \n   \t\t\n \t  \t\n\t\n \t\n\n\n", "2"},
				{"   \t\n   \t \n   \t\t\n \t   \n\t\n \t\n\n\n", "3"},
				{"   \t\t\n   \t \n \n\t\t\n \t\t\n \t\n\n\n", "32"},
				{"   \t\t\n   \t \n \n\t \n\n\t\n \t\n\n\n", "2"},
				{"   \t\t\n   \t \n   \t\n   \t  \n   \t\t \n   \t \t\n   \t\t\t\n \n\t \t\n \t\t\n\t\n \t\t\n \t\t\n \t\t\n \t\n\n\n", "5123"},
		};
		for (String[] test : tests) {
			assertEquals(test[1], WhitespaceInterpreter.execute(test[0], null));
		}
	}
	
	@Test
	public void testArithmetic() {
		System.out.println("\n\nTesting arithmetic functionality");
		String[][] tests = {
				{"  \t\t\n   \t  \n\t   \t\n \t\n\n\n", "3"},
				{"  \t\t \n   \t  \n\t  \t\t\n \t\n\n\n", "-6"},
				{"  \t\t   \n   \t\t\n\t \t \t\n \t\n\n\n", "-3"},
				
				{"   \t \t\n  \t\t \n\t \t\t\t\n \t\n\n\n", "-1"},
				{"   \t \t\n  \t\t\t\n\t \t\t\t\n \t\n\n\n", "-1"},
				{"  \t\t \t\n   \t \n\t \t\t\t\n \t\n\n\n", "1"},
				{"  \t\t \t\n   \t\t\n\t \t\t\t\n \t\n\n\n", "1"},
				{"  \t\t \t\n  \t\t \n\t \t\t\t\n \t\n\n\n", "-1"},
				{"  \t\t \t\n  \t\t\t\n\t \t\t\t\n \t\n\n\n", "-2"},
		};
		int i = 0;
		for (String[] test : tests) {
			assertEquals(++i + ": " + test[1], i + ": " + WhitespaceInterpreter.execute(test[0], null));
		}
	}
	
	@Test
	public void testSubroutine() {
		System.out.println("\n\nTesting subroutines functionality");
		String[][] tests = {
				{"   \t\n   \t\t\n   \n   \t \n   \n   \t\n\n  \n\t\n \t\n\t \n\n\n\n", "123"},
				{"   \t \n\n \t \n   \t\t\n\n \t \n   \t\n\n \t \n\n\n\n\n   \n\t\n \t\n\n\n", "2"},
				{"   \t\n   \t \n   \t\t\n\t\n \t\n \n\n\t\n \t\t\n \t\n  \n   \t\n   \t\n \n\n\n   \n", "err"},
				{"   \t\n\t\n \t\n \t\n\n  \n   \t \n\t\n \t", "err"},
				{"   \t\n   \t \n   \t\t\n\t\n \t\n \n\n\t\n \t\t\n \t\n  \n\n  \n\n\n\n", "err"},
				{"", "err"},
		};
		for (String[] test : tests) {
			if (test[1] == "err") {
				assertThrows(Exception.class, () -> WhitespaceInterpreter.execute(test[0], null));
				continue;
			}
			assertEquals(test[1], WhitespaceInterpreter.execute(test[0], null));
		}
		
	}
}
