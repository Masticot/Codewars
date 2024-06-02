package codewars.whitespace;

public class PreBufferedEndException extends Exception {
	private static final long serialVersionUID = 2L;

	public PreBufferedEndException() {
        super("Code sequence is empty.");
    }
}
