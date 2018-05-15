package com.carrental.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.carrental.interfaces.IConstants;

public class Util {
	public static boolean isNullOrBlank(String s) {
		if (s == null || "".equals(s)) {
			return true;
		}
		return false;
	}
	
	public static Date converStringtoDate(String strDate) throws ParseException {
		if (Util.isNullOrBlank(strDate)) {
			return null;
		}
		
		Date date = new SimpleDateFormat(IConstants.DATE_FORMAT).parse(strDate);
		return date;
	}
}
