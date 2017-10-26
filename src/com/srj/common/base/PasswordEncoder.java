package com.srj.common.base;


public class PasswordEncoder {
	/**
	 * md5加盐后再次md5
	 * 
	 * @author shiruojiang
	 * @2017-04-14
	 */
	public final static String Encoding(String pwd,String salt) {
		return Encrypt.md5(Encrypt.md5(pwd)+salt);
	}
}
