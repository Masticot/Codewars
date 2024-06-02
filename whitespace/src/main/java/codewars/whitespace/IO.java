package codewars.whitespace;

public class IO extends Expression {

    public IO(Expression parent) {
        super(parent, "ss", "st", "ts", "tt");
    }

    @Override
    public void evaluate(int i, boolean preprocessing) throws Exception {
    	if (preprocessing) return;
    	
        int addr = 0;
        if (i >= 2) addr = context.pop();
        switch (i) {
            case 0:
                context.write(Character.toString((char) context.pop()));
                break;
            case 1:
                context.write(Integer.toString(context.pop()));
                break;
            case 2:
                context.store(addr, context.readChar());
                break;
            case 3:
                context.store(addr, context.readNumber());
                break;
            default:
                throw new Exception("No effect for this I/O pattern.");
        }
    }
}
