package codewars.whitespace;

import java.io.InputStream;
import java.io.OutputStream;

public class WhitespaceInterpreter {
	private Context context;
	
    public static String unbleach(String code) {
        return code != null ? code.replace(' ', 's').replace('\t', 't').replace('\n', 'n') : null;
    }

    public static String unbleach2(String code) {
        return code != null ? "\"" + code.replace("\t", "\\t").replace("\n", "\\n") + "\"" : null;
    }
    
    public void preprocess(String code) {
    	context = new Context(unbleach(code.replaceAll("[^ \t\n]", "")));
    	System.out.println("\n    Code: " + unbleach(code) + "  length = " + context.getSequence().length());
    	Expression whitespace = new Whitespace(context);
    	try {
    		whitespace.evaluate(true);
    	}
    	catch (Exception e) {
    		context.setBogus();
    	}
    }
    
    public String execute(InputStream input, OutputStream output) {
    	context.open(input, output);
    	Expression whitespace = new Whitespace(context);
        String res;
        
        try {
        	if (output != null) {
        		output.flush();
        	}
            whitespace.evaluate(false);
            if (context.reachedEnd()) throw new Exception("Unclean Termination.");
        }
        catch (StopExecutionException ignored) {}
        catch (Exception e) {
            throw new RuntimeException("An error occurred during execution: " + e.getMessage(), e);
        }
        finally {
            res = context.getOutput();
            System.out.println("res: " + res);
            try {
            	if (output != null) {
            		output.flush();
            	}
                context.close();
            }
            catch (Exception e) {
                throw new RuntimeException("Error closing context: " + e.getMessage(), e);
            }
        }

        return res;
    }

    public static String execute(String code, InputStream input) {
        return execute(code, input, null);
    }
  
    public static String execute(String code, InputStream input, OutputStream output) {
        WhitespaceInterpreter interpreter = new WhitespaceInterpreter();
    	interpreter.preprocess(code);
    	
        return interpreter.execute(input, output);
    }
}
