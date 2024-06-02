package codewars.whitespace;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Expression {
    protected Context context;
    protected Expression parent;
    protected List<String> patterns;

    public Expression(Context context, String... patterns) {
        this.context = context;
        this.patterns = Arrays.stream(patterns).collect(Collectors.toList());
    }

    public Expression(Expression parent, String... patterns) {
        this(parent.context, patterns);
        this.parent = parent;
    }

    protected abstract void evaluate(int i, boolean preprocessing) throws Exception;

    public void evaluate(boolean preprocessing) throws Exception {
    	if (context.reachedBogus()) throw new Exception("Preprocessing encountered error at " + context.getPosition());
        int i = select();
        if (i == -1) throw new Exception("No function found in " + context.getSequence());
        evaluate(i, preprocessing);
    }

    private int select() {
        for (int i = 0; i < patterns.size(); i++) {
            Matcher matcher = Pattern.compile(String.format("^(%s)", patterns.get(i))).matcher(context.getSequence());
            if (matcher.find()) {
                context.updatePosition(patterns.get(i));
                return i;
            }
        }
        return -1;
    }
}
