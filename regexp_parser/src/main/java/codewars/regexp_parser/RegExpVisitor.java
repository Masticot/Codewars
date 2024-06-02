package codewars.regexp_parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RegExpVisitor {
    private Stack<RegExp> stack = new Stack<>();

    public RegExp getResult() throws Exception {
    	if (stack.isEmpty()) throw new Exception("Illegal access on empty stack.");
    	return stack.pop();
    }
    
    public void merge() {
    	if (stack.size() > 1) {
    		visitSequence();
    	}
    }

    public void visitLiteral(char literal) {
        stack.push(literal == '.' ? new Any() : new Normal(literal));
    	System.out.println("      literal = " + stack.peek().getType());
    	System.out.println("        stack = " + stack);
    }

    public void visitRepetition() throws Exception {
    	System.out.print("      repetition on " + stack.peek().getType());
        if (!stack.isEmpty()) {
        	if (stack.peek() instanceof ZeroOrMore) throw new Exception("More than two '*' characters require parentheses for explicit grouping.");
            stack.push(new ZeroOrMore(stack.pop()));
        	System.out.println(" = " + stack.peek().getType());
        }
    	System.out.println("        stack = " + stack);
    }

    public void visitAlternation() {
    	RegExp rightExp = stack.pop();
        RegExp leftExp = stack.pop();
        stack.push(new Or(leftExp, rightExp));
    	System.out.println("      alternation = " + stack.peek().getType());
    	System.out.println("        stack = " + stack);
    }

    public void visitSequence() {
        List<RegExp> exprs = new ArrayList<>();
        while (!stack.isEmpty()) {
            exprs.add(0, stack.pop()); // Reverse order because stack is LIFO
        }
        stack.push(new Str(exprs));
    	System.out.println("      sequence = " + stack.peek().getType());
    	System.out.println("        stack = " + stack);
    }
    
    public void push(RegExp exp) {
    	stack.push(exp);
    }
}
