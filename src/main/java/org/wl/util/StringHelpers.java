/**
 * Xi'An AccuRad Healthcare Technology Co.,Ltd.
 * Copyright (c) 2001-2015 All Rights Reserved.
 */
package org.wl.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @className: StringHelpers.java
 * @author: wangLei
 * @version: v1.0
 * @date: 2015年5月27日 下午1:59:51
 * @description: 字符处理辅助类：主要处理字符的各种操作：字符追加、字符替换、字符编码转换、各种类型的转化
 */
public class StringHelpers {

	public final static char[] chr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	/**
	 * 将字符串转换成BigDecimal类型
	 * 
	 * @param bigdecimal
	 * @return
	 */
	public static BigDecimal toBigDecimal(final String bigdecimal) {
		if (bigdecimal == null) {
			return null;
		}
		if (bigdecimal.length() < 1) {
			return null;
		}
		try {
			return new BigDecimal(bigdecimal);
		} catch (final Exception e) {
			return null;
		}
	}

	/**
	 * 截取定长的int类型
	 * 
	 * @param i
	 * @param length
	 * @return
	 */
	public static String getNumberString(final int i, final int length) {
		String sret = String.valueOf(i);
		final int lack = length - sret.length();

		if (lack == 0) {
			return sret;
		}
		if (lack > 0) {
			for (int x = 0; x < lack; x++)
				sret = "0" + sret;
		} else {
			sret = sret.substring(0 - lack, sret.length());
		}

		return sret;
	}

	/**
	 * 截取定长的long类型
	 * 
	 * @param i
	 * @param length
	 * @return
	 */
	public static String getNumberString(final long i, final int length) {
		String sret = String.valueOf(i);
		final int lack = length - sret.length();

		if (lack == 0) {
			return sret;
		}

		if (lack > 0) {
			for (int x = 0; x < lack; x++)
				sret = "0" + sret;
		} else {
			sret = sret.substring(0 - lack, sret.length());
		}

		return sret;
	}

	/**
	 * 将字符串转换成符合HTML页面以及其表单显示的字符串
	 * 
	 * @param oldValue
	 * @return
	 */
	public static String toFormInput(final String oldValue) {
		if (oldValue == null) {
			return null;
		}

		String szTemp = "";
		final int len = oldValue.length();

		for (int i = 0; i < len; i++)
			switch (oldValue.charAt(i)) {
				case 34: // '"'
					szTemp = szTemp + "&quot;";
					break;

				default:
					szTemp = szTemp + oldValue.charAt(i);
					break;
			}

		return szTemp;
	}

	/**
	 * 将字符串形势的浮点数转换为浮点数，如："234.7"-->234.7
	 * 
	 * @param value
	 * @return
	 */
	public static float toFloat(final String value) {
		if (value == null) {
			return 0.0F;
		}

		String szTemp = "";

		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != ',') {
				szTemp = szTemp + value.charAt(i);
			}

		try {
			final float f = Float.parseFloat(szTemp);

			return f;
		} catch (final NumberFormatException e) {
			final float f1 = 0.0F;

			return f1;
		}
	}

	/**
	 * To double.
	 *
	 * @param value the value
	 * @return the double
	 */
	public static double toDouble(final String value) {
		if (value == null) {
			return 0.0D;
		}

		String szTemp = "";

		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != ',') {
				szTemp = szTemp + value.charAt(i);
			}

		try {
			final double d = Double.parseDouble(szTemp);

			return d;
		} catch (final NumberFormatException e) {
			final double d1 = 0.0D;

			return d1;
		}
	}

	/**
	 * 将字符串形式的长整型值转换成长整数， 如："234"-->234 ;可以处理象"12,345"的形式
	 * 
	 * @param value the value
	 * @return the long
	 */
	public static long toLong(final String value) {
		if (value == null) {
			return 0L;
		}

		String szTemp = "";

		for (int i = 0; i < value.length(); i++)
			if (value.charAt(i) != ',') {
				szTemp = szTemp + value.charAt(i);
			}

		try {
			final double dd = Double.parseDouble(szTemp);
			final long l1 = (long) dd;

			return l1;
		} catch (final NumberFormatException e) {
			final long l = 0L;

			return l;
		}
	}

	/**
	 * 将字符串形式的整型值转换成整数，如："234"-->234
	 *
	 * @param value the value
	 * @return the int
	 */
	public static int toInt(final String value) {
		return (int) toLong(value);
	}

	/**
	 * 将对象转换成字符串
	 *
	 * @param obj the obj
	 * @return the string
	 */
	public static String toString(final Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * 将数字转换成用'a'-'z'表示的字符串;1='a'...26='z',27='aa'...52='zz'等 等于将十进制转换成二十六进制
	 *
	 * @param num the num
	 * @return the string
	 */
	public static String toAbcNumber(final int num) {
		int i = num;
		final StringBuffer str = new StringBuffer();

		if (i <= 0) {
			return "";
		}

		do {
			str.insert(0, chr[(i - 1) % chr.length]);
			i = i / chr.length;

			if ((i > 0) && (i < chr.length)) {
				str.insert(0, chr[i - 1]);
			}
		} while (i > chr.length);

		return str.toString();
	}

	/**
	 * 缩简字符串的函数 例如："abcdef,akd;adf" 变成 "abcdef,a..." 可以指定长度和最后的字符串
	 *
	 * @param source the source
	 * @param lststr the lststr
	 * @param length the length
	 * @param iscodelen the iscodelen
	 * @return the string
	 */
	public static String trimString(final String source, final String lststr, final int length, final boolean iscodelen) {
		if ((source == null) || (source.trim().length() == 0) || (length <= 0)) {
			return source;
		}

		final String endStr = (lststr != null) ? lststr : "...";
		String result = source.trim();
		final int len = (endStr.length() < length) ? (length - endStr.length()) : 2;
		final byte[] sbytes = result.getBytes();

		if (length < (iscodelen ? sbytes.length : result.length())) {
			if (iscodelen) {
				if (new String(sbytes, 0, len).length() == 0) {
					result = new String(sbytes, 0, len - 1) + endStr;
				} else {
					result = new String(sbytes, 0, len) + endStr;
				}
			} else {
				result = source.substring(0, len) + endStr;
			}
		}

		return result;
	}

	/**
	 * 将字符串转换成垂直显示的HTML格式代码 在每一个字符的后面加上一个<br>
	 * .
	 *
	 * @param source the source
	 * @param lstline the lstline
	 * @param length the length
	 * @param iscodelen the iscodelen
	 * @return the string
	 */
	public static String toTrimHtmlVerticalString(final String source, final String lstline, final int length,
			final boolean iscodelen) {
		if ((source == null) || (source.trim().length() == 0) || (length <= 0)) {
			return source;
		}

		String dst = source.trim();
		final byte[] sbytes = dst.getBytes();
		boolean istrim = false;

		if (length < (iscodelen ? sbytes.length : dst.length())) {
			istrim = true;

			if (iscodelen) {
				if (new String(sbytes, 0, length).length() == 0) {
					dst = new String(sbytes, 0, length - 1);
				} else {
					dst = new String(sbytes, 0, length);
				}
			} else {
				dst = dst.substring(0, length);
			}
		}

		final int len = dst.length();
		final StringBuffer result = new StringBuffer();

		for (int i = 0; i < (len - 1); i++) {
			result.append(dst.charAt(i)).append("<br>");
		}

		if (istrim && (lstline != null) && (lstline.trim().length() > 0)) {
			result.append(lstline.trim());
		} else {
			result.append(dst.charAt(len - 1));
		}

		return result.toString();
	}

	/**
	 * 将字符串转换成垂直显示的HTML格式代码 在每一个字符的后面加上一个<br>
	 * .
	 *
	 * @param source the source
	 * @return the string
	 */
	public static String toHtmlVerticalString(final String source) {
		if ((source == null) || (source.trim().length() == 0)) {
			return source;
		}

		final String tmp = source.trim();
		final int len = tmp.length();
		final StringBuffer result = new StringBuffer();

		for (int i = 0; i < (len - 1); i++) {
			result.append(tmp.charAt(i)).append("<br>");
		}

		result.append(tmp.charAt(len - 1));

		return result.toString();
	}

	/**
	 * 缩简字符串的函数 例如："abcdef,akd;adf" 变成 "abcdef,a..." 可以指定长度和最后的字符串
	 *
	 * @param source the source
	 * @return the short string
	 */
	public static String getShortString(final String source) {
		final int len = 10;
		final String endStr = "...";
		String result = source;

		if ((source != null) && (source.length() > len)) {
			result = source.substring(0, len) + endStr;
		}

		return result;
	}

	// 指定长度
	/**
	 * Gets the short string.
	 *
	 * @param source the source
	 * @param length the length
	 * @return the short string
	 */
	public static String getShortString(final String source, final int length) {
		final int len = (length > 0) ? length : 10;
		final String endStr = "...";
		String result = source;

		if ((source != null) && (source.length() > len)) {
			result = source.substring(0, len) + endStr;
		}

		return result;
	}

	// 指定长度与最后字符串
	/**
	 * Gets the short string.
	 *
	 * @param source the source
	 * @param length the length
	 * @param lastStr the last str
	 * @return the short string
	 */
	public static String getShortString(final String source, final int length, final String lastStr) {
		final int len = (length > 0) ? length : 10;
		final String endStr = (lastStr != null) ? lastStr : "...";
		String result = source;

		if ((source != null) && (source.length() > len)) {
			result = source.substring(0, len) + endStr;
		}

		return result;
	}

	/**
	 * 计算次方
	 *
	 * @param a the a
	 * @param b the b
	 * @return the double
	 */
	public static double P(final int a, final int b) {
		int result = 1;

		for (int i = 0; i < b; i++)
			result = result * a;

		return result;
	}

	// 将整数值转换成二进制数组，如 13(10)=1101(2)，即1,3,4位为真，第2位为假(假设第一位为1)
	// 输入参数：整数数值value，数组长度length
	/**
	 * Gets the bin array.
	 *
	 * @param value the value
	 * @param length the length
	 * @return the bin array
	 */
	public static int[] getBinArray(final int value, final int length) {
		if ((value < 0) || (length <= 0)) {
			return null;
		}

		final int len = (length > 32) ? 32 : length; // 限定最多32位长度
		int val = value;
		final int[] binbit = new int[len];

		for (int i = 0; i < len; i++) {
			if ((val % 2) == 1) {
				binbit[i] = 1;
			} else {
				binbit[i] = 0;
			}

			val = val >> 1;
		}

		return binbit;
	}

	/**
	 * 换行，空格字符的替换操作.
	 *
	 * @param in 要进行转换的字符串
	 * @return 替换后的字符串
	 */
	public static String replaceNewLine(final String in) {
		if (in == null) {
			return null;
		}
		char ch;
		final char[] input = in.toCharArray();
		final int len = input.length;
		final StringBuffer out = new StringBuffer((int) (len * 1.3));
		for (int index = 0; index < len; index++) {
			ch = input[index];
			if (ch == '\n') {
				out.append("<br>");
			} else if (ch == ' ') {
				out.append("&nbsp;");
			} else {
				out.append(ch);
			}

		}
		return out.toString();
	}

	/**
	 * 把字符串的字符集从ISO转换为gb2312.
	 *
	 * @param in 输入的ISO字符串
	 * @return GB2312字符串
	 */
	public static String convertIso8859ToGb2312(final String in) {
		String out = null;
		byte[] ins = null;
		try {
			ins = in.getBytes("iso-8859-1");
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (ins != null) {
			try {
				out = new String(ins, "gb2312");
			} catch (final UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return out;
	}

	/**
	 * 把字符串的字符集从GB2312转换为ISO.
	 *
	 * @param in 输入的GB2312字符串
	 * @return ISO字符串
	 */
	public static String convertGb2312ToIso8859(final String in) {
		String out = null;
		try {
			final byte[] ins = in.getBytes("gb2312");
			out = new String(ins, "iso-8859-1");
		} catch (final Exception e) {
		}
		return out;
	}

	/**
	 * Convert utf to gbk.
	 *
	 * @param in the in
	 * @return the string
	 */
	public static String convertUtfToGBK(final String in) {
		String out = null;
		try {
			final byte[] ins = in.getBytes("UTF-8");
			out = new String(ins, "ISO-8859-1");
		} catch (final Exception e) {
		}
		return out;
	}

	/**
	 * 去掉字符串两头的空格.
	 *
	 * @param str 待处理的字符串
	 * @return 处理后的字符串
	 */
	public static String convertNullToString(final String str) {

		if (str == null) {
			return "";
		} else {
			final int len = str.length();
			for (int i = 0; i < len; i++) {
				if (str.charAt(i) != ' ')
					break;
			}

			return str.trim();
		}
	}

	/**
	 * 替换字符串某些字符操作.
	 *
	 * @param str 原始的字符串 例如：bluesunny
	 * @param pattern 配备的字符 例如：blue
	 * @param replace 替换为的字符 例如：green
	 * @return 返回处理结果 例如：greensunny
	 */
	public static String replace(final String str, final String pattern, String replace) {

		if (replace == null) {
			replace = "";
		}
		int s = 0, e = 0;

		final StringBuffer result = new StringBuffer(str.length() * 2);
		while ((e = str.indexOf(pattern, s)) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}

	/**
	 * 判断字符串是否为数字类型.
	 *
	 * @param str 待处理的字符串
	 * @return true表示为数字类型 false表示为非数字类型
	 */
	public static boolean isNumber(final String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		String sStr = "";
		int m = 0;
		m = str.indexOf(".");

		for (int j = 0; j < str.length(); j++) {
			if (m != j)
				sStr = sStr + str.charAt(j);
		}

		final byte[] btyeStr = sStr.getBytes();
		for (int i = 0; i < btyeStr.length; i++) {
			if ((btyeStr[i] < 48) || (btyeStr[i] > 57)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 把string型字符串转为整型.
	 *
	 * @param str 待处理的字符串
	 * @return 整数
	 */
	public static int strToInt(final String str) {
		int i = 0;
		if (str != null && str.length() != 0) {
			try {
				i = Integer.parseInt(str.trim());
			} catch (final NumberFormatException nfe) {
				nfe.printStackTrace();
			}
		}
		return i;
	}

	/**
	 * 把string型字符串转为Double.
	 *
	 * @param str 待处理的字符串
	 * @return double
	 */
	public static double strToDouble(final String str) {
		double i = 0;
		if (str != null && str.length() != 0) {
			try {
				i = Double.parseDouble(str.trim());
			} catch (final NumberFormatException nfe) {
				nfe.printStackTrace();
			}
		}
		return i;
	}

	/**
	 * 产生随机字符串.
	 *
	 * @return the string
	 */
	public static String createRandomString() {
		String random = null;
		// 产生随机字符串
		random = RandomStringUtils.randomAlphabetic(10);
		// 随机字符串再加上当前的日期时间 long
		random += DateUtils.getNowDateTimeToLong();
		return random;
	}

	/**
	 * 将Id数组转换成逗号分隔的字符串.
	 *
	 * @param fid the fid
	 * @return the string
	 */
	public static final String comboIdStr(final String[] fid) {
		String IdStr = "";
		for (int i = 0; i < fid.length; i++) {
			IdStr += fid[i];

			if (i != fid.length - 1)
				IdStr += ",";
		}
		return IdStr;
	}

	/**
	 * 将带特殊分隔符的字符串转换为按照指定替换符的字符串.
	 *
	 * @param oldValue the old value
	 * @param separateChar the separate char
	 * @param replaceStr the replace str
	 * @return the tree level value
	 */
	public static final String getTreeLevelValue(final String oldValue, final String separateChar,
			final String replaceStr) {
		final String[] spStr = oldValue.split(separateChar);
		String tmp = "";

		for (int i = 0; i < spStr.length - 1; i++) {
			tmp += replaceStr;
		}

		return tmp;
	}
}
