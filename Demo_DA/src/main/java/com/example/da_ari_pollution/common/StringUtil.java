//package com.example.da_ari_pollution.common;
//
//import org.springframework.util.StringUtils;
//
//import java.util.LinkedList;
//
//public class StringUtil extends StringUtils {
//
//    private static LinkedList<Replacer> replacerList = new LinkedList<Replacer>();
//
//    public static final String SPACE = " ";
//
//    public static final Character AND = '&';
//
//    public static final Character STAR = '*';
//
//    public static final Character SLASH = '/';
//
//    public static final Character QUESTION_MASK = '?';
//
//    public static final Character COMMA = ',';
//
//    public static final Character UNDERSCORE = '_';
//
//    public static final Character DOT = '.';
//
//    public static final Character AT = '@';
//
//    public static final Character PLUS = '+';
//
//    public static final String UTF8_BOM = "\uFEFF";
//
//    public static final String CODE = "$.code";
//
//    public static final String MESSAGE = "$.message";
//
//    public static final String DATA = "$.data";
//
//    static {
//        final String aa = "[áàảãạâấầẩẫậăắằẳẵặ]";
//        final String a = "a";
//        replacerList.add(new Replacer(aa, a));
//
//        final String oo = "[óòỏõọôốồổỗộơớờởỡợ]";
//        final String o = "o";
//        replacerList.add(new Replacer(oo, o));
//
//        final String ee = "[éèẻẽẹêếềểễệ]";
//        final String e = "e";
//        replacerList.add(new Replacer(ee, e));
//
//        final String uu = "[uúùủũụưứừửữự]";
//        final String u = "u";
//        replacerList.add(new Replacer(uu, u));
//
//        final String ii = "[iíìỉĩị]";
//        final String i = "i";
//        replacerList.add(new Replacer(ii, i));
//
//        final String yy = "[ýỳỷỹỵ]";
//        final String y = "y";
//        replacerList.add(new Replacer(yy, y));
//
//        final String dd = "[đ]";
//        final String d = "d";
//        replacerList.add(new Replacer(dd, d));
//    }
//
//
//    private static class Replacer {
//
//        public String regex;
//
//        public String replacement;
//
//        public Replacer(String regex, String replacement) {
//            this.regex = regex;
//            this.replacement = replacement;
//        }
//    }
//
//    public static String trim(String value) {
//        if (value == null)
//            return null;
//        int len = value.length();
//        int start = 0;
//        while (start < len) {
//            char c = value.charAt(start);
//            if (Character.isWhitespace(c) || Character.isSpaceChar(c)) {
//                start++;
//                continue;
//            }
//            break;
//        }
//
//        while (len > start) {
//            char c = value.charAt(len - 1);
//            if (Character.isWhitespace(c) || Character.isSpaceChar(c)) {
//                len--;
//                continue;
//            }
//            break;
//        }
//
//        return ((start > 0) || (len < value.length())) ? value.substring(start, len) : value;
//    }
//
//    public static String arrayConcat(Object[] a) {
//        if (a == null) return "";
//
//        if (a.length == 0) return "";
//
//        int iMax = a.length - 1;
//        StringBuilder b = new StringBuilder();
//        for (int i = 0; ; i++) {
//            b.append(a[i]);
//            if (i == iMax) return b.toString();
//            b.append(", ");
//        }
//    }
//}