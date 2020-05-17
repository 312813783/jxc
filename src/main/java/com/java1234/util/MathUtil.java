package com.java1234.util;

/**
 * <br>数学工具类
 * @author Administrator
 *
 */
public class MathUtil {

	/**
	 * <br>格式化数字 保留两位
	 * @param n
	 * @return
	 */
	public static float format2Bit(float n){
		return (float)(Math.round(n*100))/100;
	}
}
