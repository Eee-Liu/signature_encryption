package com.baj.encryption;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @Author: LiuYi
 * @Description:
 * @Date: Created in 2018/10/12日 11:27:32
 */
public class StringUtil {

	public final static String BASE_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String nullParser(String str) {
		return str == null ? "" : str;
	}

	public static String toLowerCase(String str) {
		if (isEmpty(str)) {
			return str;
		}
		return str.toLowerCase();
	}

	public static String toTrimAndLowerCase(String str) {
		if (null == str) {
			return null;
		}
		return str.trim().toLowerCase();
	}

	public static boolean equals(String str1, String str2) {
		if (null == str1 || null == str2) {
			return false;
		}
		return str1.equals(str2);
	}

	/**
	 * 判断是不是手机号
	 *
	 * @param keyword
	 * @return
	 */
	public static boolean isMobileNumber(String keyword) {
		return null != keyword && keyword.matches("^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
	}

	/**
	 * 把字符串切分成字符串列表
	 *
	 * @param str
	 * @param separator
	 * @return
	 * @throws Exception
	 */
	public static List<String> spiltToString(String str, String separator) throws Exception {
		if (null == str) {
			throw new Exception("str is null.");
		}
		List<String> l = new ArrayList<String>();
		if (null == separator) {
			l.add(str);
			return l;
		}
		String[] splitArray = str.trim().split(separator);
		for (String subStr : splitArray)
			l.add(subStr);
		return l;
	}

	public static String[] spiltToStringArray(String str, String separator) throws Exception {
		if (null == str) {
			throw new Exception("str is null.");
		}
		String[] splitArray = str.trim().split(separator);
		return splitArray;
	}

	public static List<Long> spiltToLong(String str, String separator) throws Exception {
		if (null == str) {
			throw new Exception("str is null.");
		}
		List<Long> l = new ArrayList<Long>();
		if (null == separator) {
			l.add(Long.parseLong(str));
			return l;
		}
		String[] splitArray = str.trim().split(separator);
		for (String numStr : splitArray) {
			l.add(Long.parseLong(trim(numStr)));
		}
		return l;
	}

	public static List<Integer> spiltToInteger(String str, String separator) throws Exception {
		if (isEmpty(str)) {
			throw new Exception("str is empty.");
		}
		List<Integer> l = new ArrayList<Integer>();
		if (null == separator) {
			l.add(Integer.parseInt(str.trim()));
			return l;
		}
		String[] splitArray = str.trim().split(separator);
		for (String numStr : splitArray)
			l.add(Integer.parseInt(numStr.trim()));
		return l;
	}

	public static List<Double> spiltToDouble(String str, String separator) throws Exception {
		if (null == str) {
			throw new Exception("str is null.");
		}
		List<Double> l = new ArrayList<Double>();
		if (null == separator) {
			l.add(Double.parseDouble(str));
			return l;
		}
		String[] splitArray = str.trim().split(separator);
		for (String numStr : splitArray)
			l.add(Double.parseDouble(numStr));
		return l;
	}

	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static boolean isNotEmpty(CharSequence cs) {
		return !isEmpty(cs);
	}

	/**
	 * 去除空格,换行符后是否是空字符串
	 *
	 * @param str
	 * @return
	 */
	public static boolean isTrimEmpty(String str) {
		return isEmpty(trim(str));
	}

	public static boolean isNotTrimEmpty(String str) {
		return !isTrimEmpty(str);
	}

	/**
	 * 判断js字符串是否为空
	 *
	 * @param str
	 * @return
	 */
	public static boolean isJsEmpty(String str) {
		return isTrimEmpty(str) || "undefined".equals(str) || "null".equals(str);
	}

	/**
	 * 判断js字符串是否为非空
	 *
	 * @param str
	 * @return
	 */
	public static boolean isJsNotEmpty(String str) {
		return !isJsEmpty(str);
	}

	/**
	 * 切割类似"price:asc,sell:desc"这样的字符串成为map
	 *
	 * @param str
	 * @param mainSeparator 类似上面的“,”
	 * @param subSeparator  类似上面的“:”
	 * @return
	 */
	public static Map<String, String> splitToMap(String str, String mainSeparator, String subSeparator)
			throws Exception {
		if (isEmpty(str)) {
			return new HashMap<String, String>();
		}
		String[] strArray = str.trim().split(mainSeparator);
		Map<String, String> map = new HashMap<String, String>(strArray.length);

		for (String kv : strArray) {
			String[] kvArray = kv.trim().split(subSeparator);
			if (kvArray.length != 2 || isEmpty(kvArray[0]) || isEmpty(kvArray[1])) {
				throw new Exception("the format of str is error, error: \"" + kv + "\" in " + str);
			}
			map.put(kvArray[0], kvArray[1]);
		}
		return map;
	}

	public static String toListStatement(String pre, List<?> list, String splitTag, String end) {
		if (null == list || 0 >= list.size() || null == splitTag) {
			return null;
		}
		pre = (null == pre) ? "" : pre;
		end = (null == end) ? "" : end;

		StringBuilder builder = new StringBuilder();
		builder.append(pre);
		boolean isFirst = true;

		for (Object obj : list) {
			if (isFirst) {
				isFirst = false;
			} else {
				builder.append(splitTag);
			}
			builder.append(obj);
		}
		builder.append(end);
		return builder.toString();
	}

	public static String toSetStatement(String pre, Set<?> set, String splitTag, String end) {
		if (null == set || 0 >= set.size() || null == splitTag) {
			return null;
		}
		pre = (null == pre) ? "" : pre;
		end = (null == end) ? "" : end;

		StringBuilder builder = new StringBuilder();
		builder.append(pre);
		boolean isFirst = true;

		for (Object obj : set) {
			if (isFirst) {
				isFirst = false;
			} else {
				builder.append(splitTag);
			}
			builder.append(obj);
		}
		builder.append(end);
		return builder.toString();
	}

	// 将str转为list
	public static List<String> toList(String str, String splitChar) {
		if (isEmpty(str)) {
			return null;
		}
		String[] array = str.split(splitChar);
		List<String> list = new ArrayList<String>(array.length);
		list.addAll(Arrays.asList(array));
		return list;
	}

	// 将a-b转为[a b]
	public static String toRangeStr(String str, String splitChar) {
		if (isEmpty(str)) {
			return null;
		}
		String[] array = str.split(splitChar);
		return "[" + array[0] + " " + array[1] + "]";
	}

	public static String toRangeStr(Object val1, String splitChar, Object val2) {
		if (null == val1 || null == val2) {
			return null;
		}
		String str1 = null == val1 ? "" : val1 + "";
		String str2 = null == val2 ? "" : val2 + "";
		return str1 + splitChar + str2;
	}

	public static String join(String splitStr, String... strArray) {
		if (null == splitStr || null == strArray) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for (String obj : strArray) {
			if (null == obj)
				continue;
			if (!first) {
				builder.append(splitStr);
			} else {
				first = false;
			}
			builder.append(obj);
		}
		return builder.toString();
	}

	/**
	 * 将类似“0001010011001”中为1的索引存到int型的数组 中
	 *
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Integer> strIndexToIntList(String str, char indexStr) throws Exception {
		if (null == str) {
			return Collections.EMPTY_LIST;
		}
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < str.length(); i++) {
			if (indexStr == str.charAt(i)) {
				list.add(i);
			}
		}
		return list;
	}

	/**
	 * 后期将一些其他的字符做为trim的空字符列表中
	 *
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (null == str)
			return null;
		return str.trim();
	}

	public static String trim(String str, String tag) {
		if (null == str)
			return null;
		if (str.equals(tag)) {
			return "";
		}
		int indexStart = 0;
		int indexEnd = str.length();
		if (str.startsWith(tag)) {
			indexStart = tag.length();
		}
		if (str.endsWith(tag)) {
			indexEnd = str.length() - tag.length();
		}
		if (indexEnd >= indexStart) {
			str = str.substring(indexStart, indexEnd);
		}
		return str;
	}

	/**
	 * 短信编码防止乱码
	 */
	public static String encoding(String content) throws Exception {
		if (isEmpty(content)) {
			return "";
		}
//        String msg = new String(content.getBytes("ISO-8859-1"), "UTF-8");
////        BASE64Encoder base64 = new BASE64Encoder();
////        String msg = base64.encode(content.getBytes("UTF-8"));
		String msg = URLEncoder.encode(content, "ISO-8859-1");
		return msg;
	}

	/**
	 * 长度补齐，如果长度不够，会在前面补"0"
	 *
	 * @param field
	 * @param requiredLength
	 * @return
	 */
	public static String paddingFieldLength(String field, Integer requiredLength) {
		if (field.length() < requiredLength) {
			StringBuffer newId = new StringBuffer();
			int diff = requiredLength - field.length();
			for (int i = 0; i < diff; i++) {
				newId.append("0");
			}
			newId.append(field);
			return newId.toString();
		}
		return field;
	}

	/**
	 * 给定的字符串,按照从前往后截取指定编码格式的固定长度的字符串,不足则不截取
	 *
	 * @param str--被截取的字符串
	 * @param encoder--编码格式，默认为"UTF-8"
	 * @param fixLen--固定长度
	 * @return
	 * @throws Exception
	 */
	public static String extractStrWithFixLength(String str, String encoder, int fixLen) throws Exception {
		if (isEmpty(encoder)) {
			encoder = "UTF-8";
		}
		int len = str.getBytes(encoder).length;
		if (len > fixLen) {
			str = str.substring(0, str.length() - 1);
			str = extractStrWithFixLength(str, encoder, fixLen);
		}
		return str;
	}

	public static String formatPhone(String phone) {
		if (isEmpty(phone)) {
			return "";
		}
		char[] nums = phone.toCharArray();
		for (int i = 3; i < nums.length - 4; i++) {
			nums[i] = '*';
		}
		return new String(nums);
	}

	/**
	 * 按长度生产随机字符串
	 *
	 * @param length 表示生成字符串的长度
	 * @return
	 */
	public static String getRandomString(int length) {

		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(BASE_CHARS.length());
			sb.append(BASE_CHARS.charAt(number));
		}
		return sb.toString();
	}

}
