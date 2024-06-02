package codewars.whitespace;

public class SM extends Expression {
    public SM(Expression parent) {
        super(parent, "s", "ts", "tn", "ns", "nt", "nn");
    }

    @Override
    public void evaluate(int i, boolean preprocessing) throws Exception {
        int number = 0;
        if (i < 3) number = Parser.parseInt(context);
        if (preprocessing) return;
        	
        switch (i) {
            case 0:
                context.push(number);
                break;
            case 1:
                context.duplicate(number);
                break;
            case 2:
                context.discard(number);
                break;
            case 3:
                context.duplicate(0);
                break;
            case 4:
                context.swap();
                break;
            case 5:
                context.pop();
                break;
            default:
                throw new Exception("No effect for this Stack Management pattern.");
        }
    }
}
