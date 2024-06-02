package codewars.whitespace;

public class FlowControl extends Expression {
    public FlowControl(Expression parent) {
        super(parent, "ss", "st", "sn", "ts", "tt", "tn", "nn");
    }

    @Override
    public void evaluate(int i, boolean preprocessing) throws Exception {
        int label = 0;
        if (i < 5) label = Parser.parseLabel(context);
        if (preprocessing ^ i == 0) return;
        // System.out.println("        FC");
        switch (i) {
            case 0:
            	context.mark(label);
                break;
            case 1:
                context.call(label);
                break;
            case 2:
                context.jumpTo(label);
                break;
            case 3:
                if (context.pop() == 0) context.jumpTo(label);
                break;
            case 4:
                if (context.pop() < 0) context.jumpTo(label);
                break;
            case 5:
                context.exitSub();
                break;
            case 6:
            	throw new StopExecutionException();
            default:
                throw new Exception("No effect for this Flow Control pattern.");
        }
    }
}
