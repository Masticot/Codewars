package codewars.whitespace;

public class Subroutine {
    private int callPosition;
    private Context context;

    public Subroutine(int label, Context context) {
    	context.jumpTo(label);
    	callPosition = context.getPosition();
        this.context = context;
    }

    public void exit() {
        context.setPosition(callPosition);
    }
}
