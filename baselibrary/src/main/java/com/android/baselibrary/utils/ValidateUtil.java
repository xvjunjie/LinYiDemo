package com.android.baselibrary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件：ExpresssoinValidateUtil.java 描述：使用正则表达式验证web端输入工具类 注释：如果用过HTML5的就知道 常用的
 * 输入验证已经不需 要自己编写方法验证了，HTML5 的类库已经帮我们做了 JDK： 1.6.x或1.7.x(这里使用的是JDK1.6.39)
 * <p/>
 * 验证工具类
 * Date: 2015-12-12
 * Time: 23:21p
 */
public class ValidateUtil {

    private static Pattern pattern = null;
    private static Matcher macher = null;

    /**
     * 字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null == str || str.trim().equals("")) {
            return true;
        }
        return false;
    }

	/*----------常用输入验证------*/

    // 匹配双字节字符(包括汉字在内)：[^x00-xff] ---已验证
    public static boolean isDoubleByteString(String inputString) {
        pattern = Pattern.compile("[^x00-xff]");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配HTML标记的正则表达式：/< (.*)>.*|< (.*) />/ ---未验证：可以实现HTML过滤
    public static boolean isHtmlString(String inputString) {
        pattern = Pattern.compile("/< (.*)>.*|< (.*) />/");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配首尾空格的正则表达式：[\\s*)]+\\w+[\\s*$] ---已验证
    public static boolean isTrimStartAndEndInthisString(String inputString) {
        pattern = Pattern.compile("[\\s*)]+\\w+[\\s*$]");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 邮箱规则：用户名@服务器名.后缀 ---已验证
    // 匹配Email地址的正则表达式：^([a-z0-9A-Z]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}
    public static boolean isEmail(String inputString) {
        pattern = Pattern
                .compile("^([a-z0-9A-Z]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配网址URL的正则表达式：^http://[a-zA-Z0-9./\\s] ---已验证
    public static boolean isUrl(String inputString) {
        pattern = Pattern.compile("^http://[a-zA-Z0-9./\\s]");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 验证用户密码:“^[a-zA-Z]\\w{5,17}$”
    // 正确格式为：以字母开头，长度在6-18 --已验证
    public static boolean isPassword(String inputString) {
        pattern = Pattern.compile("^[a-zA-Z]\\w{5,17}$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 验证密码“.*[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]”
    // 正确格式为：以字母与数字穿插，长度在6-18 --已验证
    public static boolean isPassword2(String inputString) {
        pattern = Pattern.compile(".*[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 验证身份证是否有效15位或18位 ^\\d{15}(\\d{2}[0-9xX])?$ ---已验证<包括对年月日的合法性进行验证>
    public static boolean isIdCard(String inputString) {
        pattern = Pattern.compile("^\\d{15}(\\d{2}[0-9xX])?$");
        macher = pattern.matcher(inputString);

        if (macher.find()) { // 对年月日字符串的验证
            String power = inputString.substring(inputString.length() - 12,
                    inputString.length() - 4);
            pattern = Pattern
                    .compile("^[1-2]+([0-9]{3})+(0[1-9][0-2][0-9]|0[1-9]3[0-1]|1[0-2][0-3][0-1]|1[0-2][0-2][0-9])");
            macher = pattern.matcher(power);
        }
        boolean re = macher.find();
        return macher.find();
    }

    // 验证固定电话号码 ^(([0-9]{3,4})|([0-9]{3,4})-)?[0-9]{7,8}$ ---已验证
    public static boolean isTelePhone(String inputString) {
        pattern = Pattern.compile("^(([0-9]{3,4})|([0-9]{3,4})-)?[0-9]{7,8}$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 验证移动电话号码 ^[1][3-8]+\\d{9} ---已验证  验证全号段的手机号
    public static boolean isMobilePhone(String inputString) {
        //pattern = Pattern.compile("^[1][3-8]+\\d{9}");// ^(1(([35][0-9])|(47)|[8][01236789]))\\d{8}$
        pattern = Pattern.compile("^[1][3-8]+\\d{9}$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 验证移动电话号码 ^[1][3-8]+\\d{9} ---已验证
    public static boolean isMobilePhone_all(String inputString) {
        //pattern = Pattern.compile("^[1][3-8]+\\d{9}");// ^(1(([35][0-9])|(47)|[8][01236789]))\\d{8}$
        pattern = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(288))\\d{8}$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入汉字，匹配中文字符的正则表达式：^[\u4e00-\u9fa5]*$--已验证
    public static boolean isChineseString(String inputString) {
        pattern = Pattern.compile("^[\u4e00-\u9fa5]*$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

	/*--------- 数字操作验证---对于使用过正则表达式的人而言，下面的就太简单了故不再测试--*/

    // 匹配正整数 ^[1-9]d*$　 　
    public static boolean isPositiveInteger(String inputString) {
        pattern = Pattern.compile("^[1-9]d*$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配负整数 ^-[1-9]d*$ 　
    public static boolean isNegativeInteger(String inputString) {
        pattern = Pattern.compile("^-[1-9]d*$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配整数 ^-?[1-9]d*$　　
    public static boolean isInteger(String inputString) {
        pattern = Pattern.compile("^-?[1-9]d*$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配非负整数（正整数 + 0） ^[1-9]d*|0$　
    public static boolean isNotNegativeInteger(String inputString) {
        pattern = Pattern.compile("^[1-9]d*|0$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配非正整数（负整数 + 0） ^-[1-9]d*|0$　
    public static boolean isNotPositiveInteger(String inputString) {
        pattern = Pattern.compile("^-[1-9]d*|0$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配正浮点数 ^[1-9]d*.d*|0.d*[1-9]d*$　　
    public static boolean isPositiveFloat(String inputString) {
        pattern = Pattern.compile("^[1-9]d*.d*|0.d*[1-9]d*$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配负浮点数 ^-([1-9]d*.d*|0.d*[1-9]d*)$　
    public static boolean isNegativeFloat(String inputString) {
        pattern = Pattern.compile("^-([1-9]d*.d*|0.d*[1-9]d*)$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配浮点数 ^-?([1-9]d*.d*|0.d*[1-9]d*|0?.0+|0)$　

    public static boolean isFloat(String inputString) {
        pattern = Pattern.compile("^-?([1-9]d*.d*|0.d*[1-9]d*|0?.0+|0)$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配非负浮点数（正浮点数 + 0）^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$　　
    public static boolean isNotNegativeFloat(String inputString) {
        pattern = Pattern.compile("^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 匹配非正浮点数（负浮点数 + 0）^(-([1-9]d*.d*|0.d*[1-9]d*))|0?.0+|0$
    public static boolean isNotPositiveFloat(String inputString) {
        pattern = Pattern.compile("^(-([1-9]d*.d*|0.d*[1-9]d*))|0?.0+|0$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入数字：“^[0-9]*$”
    public static boolean isNumber(String inputString) {
        pattern = Pattern.compile("^[0-9]*$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入n位的数字：“^d{n}$”
    public static boolean isNumberFormatLength(int length, String inputString) {
        pattern = Pattern.compile("^d{" + length + "}$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入至少n位数字：“^d{n,}$”
    public static boolean isNumberLengthLess(int length, String inputString) {
        pattern = Pattern.compile("^d{" + length + ",}$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入m-n位的数字：“^d{m,n}$”
    public static boolean isNumberLengthBetweenLowerAndUpper(int lower,
                                                             int upper, String inputString) {
        pattern = Pattern.compile("^d{" + lower + "," + upper + "}$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入零和非零开头的数字：“^(0|[1-9][0-9]*)$”
    public static boolean isNumberStartWithZeroOrNot(String inputString) {
        pattern = Pattern.compile("^(0|[1-9][0-9]*)$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入有两位小数的正实数：“^[0-9]+(.[0-9]{2})?$”
    public static boolean isNumberInPositiveWhichHasTwolengthAfterPoint(
            String inputString) {
        pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入有1-3位小数的正实数：“^[0-9]+(.[0-9]{1,3})?$”
    public static boolean isNumberInPositiveWhichHasOneToThreelengthAfterPoint(
            String inputString) {
        pattern = Pattern.compile("^[0-9]+(.[0-9]{1,3})?$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入非零的正整数：“^+?[1-9][0-9]*$”
    public static boolean isIntegerUpZero(String inputString) {
        pattern = Pattern.compile("^+?[1-9][0-9]*$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入非零的负整数：“^-[1-9][0-9]*$”
    public static boolean isIntegerBlowZero(String inputString) {
        pattern = Pattern.compile("^-[1-9][0-9]*$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入由26个英文字母组成的字符串：“^[A-Za-z]+$”
    public static boolean isEnglishAlphabetString(String inputString) {
        pattern = Pattern.compile("^[A-Za-z]+$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入由26个大写英文字母组成的字符串：“^[A-Z]+$”
    public static boolean isUppercaseEnglishAlphabetString(String inputString) {
        pattern = Pattern.compile("^[A-Z]+$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入由26个小写英文字母组成的字符串：“^[a-z]+$”
    public static boolean isLowerEnglishAlphabetString(String inputString) {
        pattern = Pattern.compile("^[a-z]+$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入由数字和26个英文字母组成的字符串：“^[A-Za-z0-9]+$”
    public static boolean isNumberEnglishAlphabetString(String inputString) {
        pattern = Pattern.compile("^[A-Za-z0-9]+$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    // 只能输入由数字、26个英文字母或者下划线组成的字符串：“^w+$”
    public static boolean isNumberEnglishAlphabetWithUnderlineString(
            String inputString) {
        pattern = Pattern.compile("^w+$");
        macher = pattern.matcher(inputString);
        return macher.find();
    }

    /**
     * 是否为生日
     *
     * @param date
     * @return
     */
    public static boolean isBirth(String date) {
        Pattern pattern = Pattern
                .compile("^[1-2]+([0-9]{3})+(0[1-9][0-2][0-9]|0[1-9]3[0-1]|1[0-2][0-3][0-1]|1[0-2][0-2][0-9])");
        return pattern.matcher(date).matches();
    }

    /*
     * validate date(include bissextile)format: yyyy-MM-ddadd by tony
     */
    public static boolean isValidDate(String date) {
        Pattern pattern = Pattern
                .compile("((^((1[8-9]/d{2})|([2-9]/d{3}))([-///._])(10|12|0?[13578])([-///._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]/d{2})|([2-9]/d{3}))([-///._])(11|0?[469])([-///._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]/d{2})|([2-9]/d{3}))([-///._])(0?2)([-///._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-///._])(0?2)([-///._])(29)$)|(^([3579][26]00)([-///._])(0?2)([-///._])(29)$)|(^([1][89][0][48])([-///._])(0?2)([-///._])(29)$)|(^([2-9][0-9][0][48])([-///._])(0?2)([-///._])(29)$)|(^([1][89][2468][048])([-///._])(0?2)([-///._])(29)$)|(^([2-9][0-9][2468][048])([-///._])(0?2)([-///._])(29)$)|(^([1][89][13579][26])([-///._])(0?2)([-///._])(29)$)|(^([2-9][0-9][13579][26])([-///._])(0?2)([-///._])(29)$))");

        return pattern.matcher(date).matches();
    }
}
