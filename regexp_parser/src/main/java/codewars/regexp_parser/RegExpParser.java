package codewars.regexp_parser;

public class RegExpParser {
    private String regex;
    private RegExpVisitor visitor;
    private int pos = 0;

    public RegExpParser(String regex) {
        this.regex = regex;
        this.visitor = new RegExpVisitor();
    }
    
    public RegExp parse() {
    	try {
    		return parseRecursive();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return new Void();
    	}
    }

    private RegExp parseRecursive() throws Exception {
        while (pos < regex.length()) {
            char ch = regex.charAt(pos);
            switch (ch) {
                case '(':
                    pos++;  // Skip the '('
                    handleGroup();
                    break;
                case '|':
                    if (pos == 0 || pos == regex.length() - 1 || regex.charAt(pos + 1) == '|') {
                        throw new Exception("Syntax Error: '|' cannot be used at the start, end, or between another '|'");
                    }
                    pos++;  // Skip the '|'
                    handleAlternation();
                    break;
                case '*':
                    if (pos == 0) {
                        throw new Exception("Syntax Error: '*' cannot be applied without a preceding valid expression");
                    }
                    pos++;  // Skip the '*'
                    visitor.visitRepetition();
                    break;
                case ')':
                	throw new Exception("Unbalanced parentheses in regex");
                default:
                    visitor.visitLiteral(ch);
                    pos++;  // Move to the next character
                    break;
            }
        }
        
        visitor.merge();
        return visitor.getResult();
    }

    private void handleGroup() throws Exception {
        // This method handles a sub-expression until it finds a closing parenthesis
        int depth = 1;
        int startPos = pos;
        while (pos < regex.length() && depth > 0) {
            char ch = regex.charAt(pos);
            if (ch == '(') {
                depth++;
            } else if (ch == ')') {
                depth--;
                if (depth == 0) {
                    // Process the content of the group
                    String subRegex = regex.substring(startPos, pos);
                    RegExpParser subParser = new RegExpParser(subRegex);
                    RegExp subExpression = subParser.parseRecursive();
                    visitor.push(subExpression);  // Push the parsed sub-expression onto the stack
                    pos++;  // Skip the closing ')'
                    return;
                }
            }
            pos++;
        }
        if (depth != 0) {
            throw new RuntimeException("Unbalanced parentheses in regex");
        }
    }

    private void handleAlternation() throws Exception {
        // Handles alternation by processing the right-hand side as a separate sub-expression
    	visitor.merge();
        RegExpParser subParser = new RegExpParser(regex.substring(pos));
        System.out.println("      right exp: " + regex.substring(pos));
        RegExp rightExpression = subParser.parseRecursive();
        if (rightExpression instanceof Or) throw new Exception("More than two '|' characters require parentheses for explicit grouping."); 
        visitor.push(rightExpression);  // Push the right-hand side expression
        visitor.visitAlternation();  // Assume there's a leftExpression already on the stack
        pos = regex.length();
    }
}
