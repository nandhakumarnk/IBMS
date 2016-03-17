package com.rd.strivos.cobby;

public class UserDetails {
	public static String uid, uname, mobile, usergroup;

	public static void setMobile(String mobile) {
		UserDetails.mobile = mobile;
	}

	public static void setUid(String uid) {
		UserDetails.uid = uid;
	}

	public static void setUname(String uname) {
		UserDetails.uname = uname;
	}

	public static void setUsergroup(String usergroup) {
		UserDetails.usergroup = usergroup;
	}

	public static String getMobile() {
		return mobile;
	}

	public static String getUid() {
		return uid;
	}

	public static String getUname() {
		return uname;
	}

	public static String getUsergroup() {
		return usergroup;
	}
}
