package codewars.whitespace;

public class Arithmetic extends Expression {
    public Arithmetic(Expression parent) {
        super(parent, "ss", "st", "sn", "ts", "tt");
    }

    @Override
    public void evaluate(int i, boolean preprocessing) throws Exception {
    	if (preprocessing) return;
    	
        int a = context.pop();
        int b = context.pop();
        int value = 0;
        switch (i) {
            case 0:
            	value = b + a;
                break;
            case 1:
            	value = b - a;
                break;
            case 2:
            	value = b * a;
                break;
            case 3:
                if (a == 0) throw new Exception("Denominator cannot be zero");
                value = Math.floorDiv(b, a);
                break;
            case 4:
                if (a == 0) throw new Exception("Modulo cannot be zero");
                value = b - Math.floorDiv(b, a) * a;
                break;
            default:
                throw new Exception("No effect for this Arithmetic pattern.");
        }
        context.push(value);
    }
}
