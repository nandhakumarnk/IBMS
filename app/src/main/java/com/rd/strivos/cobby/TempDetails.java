package com.rd.strivos.cobby;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TempDetails {
	public static String last_tx_address = "", last_tx_name = "",
			login_time = "",last_tx_data="",seq_id="",inq_details="";
	public static int p_status;
	public static int new_count = 0, update_count = 0;
	

	public static void setLast_tx_address(String lastTxAddress) {
		last_tx_address = lastTxAddress;
	}

	public static void setLogin_time(String loginTime) {
		login_time = loginTime;
	}

	public static String getLogin_time() {
		return login_time;
	}


	public static String getLast_tx_address() {
		if (last_tx_address.length() > 0) {
			return last_tx_name;
		} else {
			return "";
		}
	}

	public static void setLast_tx_name(String lastTxName) {
		last_tx_name = lastTxName;
	}

	public static String getLast_tx_name() {
		if (last_tx_name.length() > 0) {
			return last_tx_name;
		} else {
			return "";
		}

	}
public static void set_seqid(String seqid){
	seq_id=seqid;
	
}
public static String get_seqid(){
	return seq_id;
}	
public static void empty_Temp() {
		last_tx_address = "";
		last_tx_name = "";
	}

	public static String currentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date d = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		String s = sdf.format(d);
		return s + " Hrs";
	}

	public static String currentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		String s = sdf.format(d);
		return s;
	}

	public static String currentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		String s = sdf.format(d);
		return s + " Hrs";
	}

	public static void setNew_count() {
		new_count++;
	}

	public static void setUpdate_count() {
		update_count++;
	}

	public static String getNew_count() {
		return new_count+"";
	}

	public static String getUpdate_count() {
		return update_count+"";
	}

	public static void setlastTransactionData(String lastTxData){
		
		 last_tx_data=lastTxData;
	}
	
	public static String getlastTransactionData(){
		
		return last_tx_data;
	}
	public static void setInquiryDetails(String inqdetails){
		
		 inq_details=inqdetails;
	}
public static String getInquiryDetails(){
		
		return inq_details;
	}
	
}
