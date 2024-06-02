package codewars.whitespace;

public class Whitespace extends Expression {
    public Whitespace(Context context) {
        super(context, "s", "ts", "tt", "tn", "n");
        context.setPosition(0);
    }

    @Override
    public void evaluate(int i, boolean preprocessing) throws Exception {
        switch (i) {
            case 0:
                new SM(this).evaluate(preprocessing);
                break;
            case 1:
                new Arithmetic(this).evaluate(preprocessing);
                break;
            case 2:
                new HeapAccess(this).evaluate(preprocessing);
                break;
            case 3:
                new IO(this).evaluate(preprocessing);
                break;
            case 4:
                new FlowControl(this).evaluate(preprocessing);
                break;
            default:
                throw new Exception("No effect for this IMP.");
        }
    }

    @Override
    public void evaluate(boolean preprocessing) throws Exception {
        if (context.reachedEnd()) throw new PreBufferedEndException();
        while(!context.reachedEnd()) {
            super.evaluate(preprocessing);
        }
    }
}
