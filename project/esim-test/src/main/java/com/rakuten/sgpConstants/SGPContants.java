package com.rakuten.sgpConstants;

public class SGPContants {
	
	public final static String TAG_EUICCINFO1 									= "BF20";
	public final static String TAG_EUICCINFO1_SVN 								= "80";
	public final static String TAG_EUICCINFO1_EUICC_CIPKIdLISTFORVERIFICATION 	= "81";		
	public final static String TAG_EUICCINFO1_EUICC_CIPKIdLISTFOR_SIGNINIG    	= "82";
	
	public final static String TAG_SERVER_SIGNED1 								= "30";
	public final static String TAG_SERVER_SIGNED1_TXID 							= "80";
	public final static String TAG_SERVER_SIGNED1_EUICC_CHALLENGE 				= "81";
	public final static String TAG_SERVER_SIGNED1_SERVER_ADDRESS 				= "82";
	public final static String TAG_SERVER_SIGNED1_SERVER_CHALLENGE 				= "83";
	
	
	public final static String TAG_SMDP_SIGNED2 								= "30";
	public final static String TAG_SMDP_SIGNED2_TXID							= "80";
	public final static String TAG_SMDP_SIGNED2_CC_REQ_FLAG						= "01";
	public final static String TAG_SMDP_SIGNED2_BPP_EUICC_OTPK 					= "5F54";
	
	public final static String TAG_SMDP_OTPK 									= "5F49";
//			smdpSignTag = "5F37"
//			remoteOpIdTag = "82"
//			transactionIdTag = "80"

	public final static String TAG_CONTROL_REF_TEMPLATE							= "A6";
	public final static String TAG_CONTROL_REF_TEMPLATE_KEY_TYPE				= "80";
	public final static String TAG_CONTROL_REF_TEMPLATE_KEY_LEN					= "81";
	public final static String TAG_CONTROL_REF_TEMPLATE_HOST_ID					= "84";
}
