package codewars.whitespace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private String sequence;
    private int position;

    private Stack<Integer> stack;
    private Map<Integer, Integer> heap;
    private Map<Integer, Integer> flags;
    private Stack<Subroutine> subroutines;
    private int bogus;

    private BufferedReader reader;
    private OutputStreamWriter writer;
    private String output;

    Context(String sequence) {
        this.sequence = sequence;
        position = 0;
        stack = new Stack<>();
        heap = new HashMap<>();
        flags = new HashMap<>();
        subroutines = new Stack<>();
        bogus = -1;
    	output = "";
    }
    
    public void open(InputStream input, OutputStream stream) {
        if (input != null) {
        	reader = new BufferedReader(new InputStreamReader(input));
        }
    	if (stream != null) {
            writer = new OutputStreamWriter(stream);
        }
    }

    public void updatePosition(String pattern) {
        position += pattern.length();
    }

    public boolean reachedEnd() {
        return position >= sequence.length();
    }

    public String getSequence() {
        return sequence.substring(position);
    }

    public void push(int value) {
    	System.out.println("        push " + value);
        stack.push(value);
    }

    public int pop() {
        return stack.pop();
    }

    public void duplicate(int n) {
        stack.push(stack.get(stack.size() - n - 1));
    }

    public void discard(int n) {
        int temp = stack.pop();
        if (n < 0 || n >= stack.size()) {
            stack.clear();
        }
        else {
            for (int i = 0; i < n; i++) {
                stack.pop();
            }
        }
        stack.push(temp);
    }

    public void swap() {
        int a = stack.pop();
        int b = stack.pop();
        stack.push(a);
        stack.push(b);
    }

    public void store(int address, int value) {
        heap.put(address, value);
    }

    public void get(int address) {
        stack.push(heap.get(address));
    }

    public int readNumber() throws IOException {
    	if (reader == null) throw new IOException("No input stream provided");
    	if (!reader.ready()) throw new IOException("Reached end of input.");
        return Parser.parseInt(reader.readLine());
    }

    public char readChar() throws IOException {
    	if (reader == null) throw new IOException("No input stream provided");
    	if (!reader.ready()) throw new IOException("Reached end of input.");
        return (char) reader.read();
    }

    public String getOutput() {
        return output;
    }

    public void write(String value) throws IOException {
    	System.out.println("        write " + value);
        output += value;
        if (writer != null) {
        	writer.write(value);
        }
    }
    
    public void close() throws Exception {
        if (writer != null) {
            writer.close();
        }
    }

    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
    	this.position = position;
    }

    public void mark(int label) throws Exception {
    	System.out.println("        mark " + position);
    	if (flags.containsKey(label)) throw new Exception("Label already used.");
        flags.put(label, position);
    }

    public void jumpTo(int label) {
    	System.out.println("        jump to " + flags.get(label));
        position = flags.get(label);
    }
    
    public void call(int label) {
    	System.out.println("        call " + flags.get(label));
        Subroutine subroutine = new Subroutine(label, this);
        subroutines.push(subroutine);
    }

    public void exitSub() {
        Subroutine subroutine = subroutines.pop();
        subroutine.exit();
    	System.out.println("        return to " + position);
    }
    
    public boolean reachedBogus() {
    	return position == bogus;
    }
    
    public void setBogus() {
    	bogus = position;
    }
}
