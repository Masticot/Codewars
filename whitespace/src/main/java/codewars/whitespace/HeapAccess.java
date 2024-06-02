package codewars.whitespace;

public class HeapAccess extends Expression {
    public HeapAccess(Expression parent) {
        super(parent, "s", "t");
    }

    @Override
    public void evaluate(int i, boolean preprocessing) throws Exception {
    	if (preprocessing) return;
    	
        int a = context.pop();
        switch (i) {
            case 0:
                context.store(context.pop(), a);
                break;
            case 1:
                context.get(a);
                break;
            default:
                throw new Exception("No effect for this Heap Access pattern.");
        }
    }
}
