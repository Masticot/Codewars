package codewars.whitespace;

public class StopExecutionException extends Exception {
	private static final long serialVersionUID = 1L;

	public StopExecutionException() {
        super("Exit Program.");
    }
}
