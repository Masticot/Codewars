package codewars.whitespace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	static private String getPattern(Context context) {
		Matcher matcher = Pattern.compile("^[st]*n").matcher(context.getSequence());
        String match = "";
        if (matcher.find()) {
            match = matcher.group(0);
            context.updatePosition(match);
        }
        return match;
	}

    static public int parseInt(Context context) throws Exception {
    	String match = getPattern(context);
        switch(match.length()) {
        	case 0:
	        	throw new Exception("No number found in " + context.getSequence());
	        case 1:
	        	throw new Exception("Empty number value is not valid.");
	        case 2:
	        	return 0;
	    	default:
	    		String binary = match.substring(0, match.length() - 1).replace('s', '0').replace('t', '1');
	            int sign = binary.charAt(0) == '1' ? -1 : 1;
	            return sign * Integer.parseInt(binary.substring(1), 2);
        }
    }

    static public int parseLabel(Context context) throws Exception {
    	String match = getPattern(context);
        switch(match.length()) {
        	case 0:
	        	throw new Exception("No label found in " + context.getSequence());
        	case 1:
        		return 0;
	    	default:
	    		String binary = match.substring(0, match.length() - 1).replace('s', '0').replace('t', '1');
	            return Integer.parseInt(binary, 2) + 1;
        }
    }

    public static int parseInt(String line) {
    	if (line.startsWith("0x")) {
            return Integer.parseInt(line.substring(2), 16);
        } else if (line.startsWith("0b")) {
            return Integer.parseInt(line.substring(2), 2);
        } else if (line.startsWith("0") && line.length() > 1) {
            return Integer.parseInt(line.substring(1), 8);
        } else {
            return Integer.parseInt(line);
        }
    }
}
