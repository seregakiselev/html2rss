package util;

import java.text.StringCharacterIterator;

/**
 * Created by Sergio on 2/11/2015.
 */
public class Misc {
    public static String xmlCharCodePrefixEncoded = "&#" + (int) '&' + ";" + "&#" + (int) '#' + ";";
    public static String xmlCharCodePrefix = "&#";

    public static String norm( String s )
    {
        return s == null ? "" : s;
    }

    public static String encodeTagBodyForXML(String text) {
        text = Misc.norm(text);
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator( text );
        char ch = iterator.current();
        while( ch != java.text.CharacterIterator.DONE )
        {
            if(ch == '<' || ch == '>' || ch == '&') {
                result.append(xmlCharCodePrefix + (int) ch + ";");
            } else {
                result.append(ch);
            }
            ch = iterator.next();
        }
        String resultStr =  result.toString();
        return resultStr.replace(xmlCharCodePrefixEncoded, xmlCharCodePrefix);
    }
}
