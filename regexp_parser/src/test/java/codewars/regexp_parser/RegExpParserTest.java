package codewars.regexp_parser;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;


public class RegExpParserTest {

    @Test
    public void coreDisplay___NOT_A_TEST___Look_Inside___() {
        System.out.println(new Normal('a'));
        
        System.out.println(new ZeroOrMore(new Normal('a')));
        
        System.out.println(new Or( new Any(),
                                   new ZeroOrMore(new Normal('a'))));

        System.out.println(new Str(Arrays.asList(new Normal('a'),new Normal('b'))));
        
        System.out.println(new Str(Arrays.asList(new Normal('b'),
                                                 new Or( new Normal('c'),
                                                         new Normal('d')),
                                                 new ZeroOrMore( new Normal('e')))) );
    }
    
    private void shouldBe(String input, String expected) {

        System.out.println("input: " + input);
        RegExp actual = new RegExpParser(input).parse();
        System.out.println("  actual:");
        System.out.println("    " + actual.getType() + " = \"" + actual + "\"");
        System.out.println("  expected: \"" + expected + "\"");
        assertEquals(String.format("Parsing \"%s\": ", input), expected, actual.toString());
    }
    
    
    @Test
    public void basicTests() {
        List<String[]> parse_Res = Arrays.asList(   new String[] {".", "."},
                                                    new String[] {"a", "a"},
                                                    new String[] {"a|b", "(a|b)"},
                                                    new String[] {"a*", "a*"},
                                                    new String[] {"(a)", "a"},
                                                    new String[] {"(a)*", "a*"},
                                                    new String[] {"(a|b)*", "(a|b)*"},
                                                    new String[] {"a|b*", "(a|b*)"},
                                                    new String[] {"abcd", "(abcd)"},
                                                    new String[] {"ab|cd", "((ab)|(cd))"});
        for (String[] s: parse_Res) shouldBe(s[0], s[1]);
    }
    
    
    @Test
    public void precedenceTests() {
        List<String[]> parse_Res = Arrays.asList(   new String[] {"ab*", "(ab*)"},
                                                    new String[] {"(ab)*", "(ab)*"},
                                                    new String[] {"ab|a", "((ab)|a)"},
                                                    new String[] {"a(b|a)", "(a(b|a))"},
                                                    new String[] {"a|b*", "(a|b*)"},
                                                    new String[] {"(a|b)*", "(a|b)*"});
        for (String[] s: parse_Res) shouldBe(s[0], s[1]);
    }
    
    
    @Test
    public void otherExamples() {
        List<String[]> parse_Res = Arrays.asList(   new String[] {"a", "a"},
                                                    new String[] {"ab", "(ab)"},
                                                    new String[] {"a.*", "(a.*)"},
                                                    new String[] {"(a.*)|(bb)", "((a.*)|(bb))"});
        for (String[] s: parse_Res) shouldBe(s[0], s[1]);
    }
    
    
    @Test
    public void invalidTests() {
        List<String[]> parse_Res = Arrays.asList(   new String[] {"*", ""},
                                                    new String[] {"(", ""},
                                                    new String[] {"(hi!", ""},
                                                    new String[] {")(", ""},
                                                    new String[] {"a|t|y", ""},
                                                    new String[] {"a**", ""});
        for (String[] s: parse_Res) shouldBe(s[0], s[1]);
    }
    

    @Test
    public void complexExamples() {
        List<String[]> parse_Res = Arrays.asList(   new String[] {"((aa)|ab)*|a", "(((aa)|(ab))*|a)"},
                                                    new String[] {"((a.)|.b)*|a", "(((a.)|(.b))*|a)"});
        for (String[] s: parse_Res) shouldBe(s[0], s[1]);
    }
    
    @Test
    public void randomTests() {
        List<String[]> parse_Res = Arrays.asList(   
        		new String[] {"Zp}n=|u{uX0ti)nLZsHVti?+Oy4R)%x}(6lgUBmFfO|P\\`burE^LI,)wU2 jl(g1W<CU2FkSb<QRb4M)8]c|+.uu", ""},
        new String[] {"XcrHy_pL7p0/LCt%,Qjfv.NTLz6t6W:wiy\\%-\"5%*}%", "(XcrHy_pL7p0/LCt%,Qjfv.NTLz6t6W:wiy\\%-\"5%*}%)"});
        for (String[] s: parse_Res) shouldBe(s[0], s[1]);
    }

}
