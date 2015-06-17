/**  
 * Copyright © 2013武汉天喻通讯技术有限公司. All rights reserved.
 *
 * @Title: CheckIDCardTools.java
 * @Prject: PoliceTong
 * @Package: com.cmcc.wificity.police.utils
 * @Description: TODO
 * @author: Mr.Gdp  
 * @date: 2013年9月27日 上午9:31:54
 * @version: V1.0  
 */
package com.hh.kit.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName: CheckIDCardTools
 * @Description: TODO
 * @author: Mr.Gdp
 * @date: 2013年9月27日 上午9:31:54
 */
public class CheckIDCardTools {
	// 位权值数组
	private static byte[] Wi = new byte[17];
	// 身份证前部分字符数
	private static final byte fPart = 6;
	// 身份证算法求模关键值
	private static final byte fMod = 11;
	// 旧身份证长度
	private static final byte oldIDLen = 15;
	// 新身份证长度
	private static final byte newIDLen = 18;
	// 新身份证年份标志
	private static final String yearFlag = "19";
	// 校验码串
	private static final String CheckCode = "10X98765432";
	// 最小的行政区划码
	private static final int minCode = 150000;
	// 最大的行政区划码
	private static final int maxCode = 700000;

	// 旧身份证号码
	// private String oldIDCard="";
	// 新身份证号码
	// private String newIDCard="";
	// 地区及编码

	// private String Area[][2] =
	private static void setWiBuffer() {
		for (int i = 0; i < Wi.length; i++) {
			int k = (int) Math.pow(2, (Wi.length - i));
			Wi[i] = (byte) (k % fMod);
		}
	}

	// 获取新身份证的最后一位:检验位
	private static String getCheckFlag(String idCard) {
		int sum = 0;
		// 进行加权求和
		for (int i = 0; i < 17; i++) {
			sum += Integer.parseInt(idCard.substring(i, i + 1)) * Wi[i];
		}
		// 取模运算，得到模值
		byte iCode = (byte) (sum % fMod);
		return CheckCode.substring(iCode, iCode + 1);
	}

	// 判断串长度的合法性
	private static boolean checkLength(final String idCard, boolean newIDFlag) {
		boolean right = (idCard.length() == oldIDLen)
				|| (idCard.length() == newIDLen);
		newIDFlag = false;
		if (right) {
			newIDFlag = (idCard.length() == newIDLen);
		}
		return right;
	}

	// 获取时间串
	private static String getIDDate(final String idCard, boolean newIDFlag) {
		String dateStr = "";
		if (newIDFlag)
			dateStr = idCard.substring(fPart, fPart + 8);
		else
			dateStr = yearFlag + idCard.substring(fPart, fPart + 6);
		return dateStr;
	}

	// 判断时间合法性
	private static boolean checkDate(final String dateSource) {
		String dateStr = dateSource.substring(0, 4) + "-"
				+ dateSource.substring(4, 6) + "-" + dateSource.substring(6, 8);
		System.out.println(dateStr);
		DateFormat df = DateFormat.getDateInstance();
		df.setLenient(false);
		try {
			Date date = df.parse(dateStr);
			return (date != null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

//	// 旧身份证转换成新身份证号码
//	public static String getNewIDCard(final String oldIDCard) {
//		// 初始化方法
//		CheckIDCardTools.setWiBuffer();
//		if (!checkIDCard(oldIDCard)) {
//			return oldIDCard;
//		}
//		String newIDCard = oldIDCard.substring(0, fPart);
//		newIDCard += yearFlag;
//		newIDCard += oldIDCard.substring(fPart, oldIDCard.length());
//		String ch = getCheckFlag(newIDCard);
//		newIDCard += ch;
//		return newIDCard;
//	}
}
