package com.rakuten.service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Service;

import com.rakuten.dto.AuthClientRequest;
import com.rakuten.dto.AuthClientResponse;
import com.rakuten.dto.FunctionExecutionStatus;
import com.rakuten.dto.GetBoundProfilePackageRequest;
import com.rakuten.dto.GetBoundProfilePackageResponse;
import com.rakuten.dto.Header;
import com.rakuten.ecdsa.EcdsaCrypto;
import com.rakuten.ecdsa.Ecka;
import com.rakuten.ecdsa.scp03t;
import com.rakuten.sgpConstants.RspGlobal;
import com.rakuten.sgpConstants.SGPContants;
import com.rakuten.sgpConstants.SMDPConstants;
import com.rakuten.utils.utils;

@Service
public class GetBoundProfilePackage {

	
	//public static String metaData 			= "";
	public static String rspTxID 			= "";
	public static String sKSmdpPb 			= "";
	public static String euiccPKCert 		= "";
	public static String skDPbpEcdsa 		= "";
	public static String pKEuiccEcdsa 		= "";
	public static String euiccSigned2 		= "";
	public static String otSKSmdpECKA 		= "";
	public static String otPKSmdpECKA 		= "";
	public static String otPKEuiccECKA 		= "";
	public static String smdpSignature2 	= "";
	public static String prepareDwnldRes 	= "";
	public static String euiccSignature2 	= "";
	public static String txIDFromAuthClient = "";
	public static String metaData = "vyU1WgoRERERIiIiIjMzkRVNb250eSBUZWxlY29tIE5ldHdvcmuSDU1vbnR5IFRlbGVjb22VAQI=";
	
	public GetBoundProfilePackage() {
		super();
	}
	
	
	public String genControlRefTemplate() {
		String ControlRefTemplateValue = SGPContants.TAG_CONTROL_REF_TEMPLATE + 
										getLVValue(SGPContants.TAG_CONTROL_REF_TEMPLATE_KEY_TYPE+ getLVValue(SMDPConstants.KEY_TYPE)+
												   SGPContants.TAG_CONTROL_REF_TEMPLATE_KEY_LEN + getLVValue(SMDPConstants.KEY_LEN)+
												   SGPContants.TAG_CONTROL_REF_TEMPLATE_HOST_ID + getLVValue(SMDPConstants.HOST_ID));

		return ControlRefTemplateValue;
	}
	
	public String generateECKAKeyPairs() {
		return "";
	}
	
	public static String getLVValue(String data) {
		if(data.length()%2!=0) {
			System.out.println("Incorrect Hex String!!");
			return "!!";
		}
		else if( (data.length()/2) < 127) {
			return utils.getLV(data);
		}
		else if((data.length()/2) >= 127){
			if ((data.length()/2) < 255 ) {
				return "81" + utils.getLV(data);
			}
			else {
				return "820" + utils.getLV(data); 
			}
		}
		return "";
		
	}
	

	            		
	public String generateMessageHashSHA256(String message) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		//md.digest(message.getBytes(StandardCharsets.UTF_8));
		return utils.byteArrayToHex(md.digest(message.getBytes(StandardCharsets.UTF_8)));
	}
	
	public String decode_PrivateKey(String InputSKKey) {
		return "";
	}
	
	public String generateSMDPSignature() {
		return "";
	}
	
	public String generateMessageForInitialiseSecureChannelRequestTLV(String remoteOpId,
																		String transactionId,
																		String ControlRefTemplateValue,
																		String skDPbpEcdsa) {
		return "";
	}
	
	
	
	public String  generateSessionKeySEnc(String ShS) throws NoSuchAlgorithmException {
		String counter = "00000001";
		String D = ShS+counter;
		return generateMessageHashSHA256(D);	
	}
	
	public String  generateSessionKeySMac(String ShS) throws NoSuchAlgorithmException {
		String counter = "00000002";
		String D = ShS+counter;
		return generateMessageHashSHA256(D);
	}
	

	public Map<String, String> genarateSessionKeyFromSharedSecretandSharedInfo(String Shs,String ShInfo) throws NoSuchAlgorithmException {
		
		Map<String,String> keyMap = new HashMap<>();
		String counter = "00000001";
		String totalMsg = Shs + counter + ShInfo;
		System.out.println("\nMessage for Hash1:"+ totalMsg);

		String keyData1 = generateMessageHashSHA256(totalMsg);
		System.out.println("\nHash1:"+ keyData1);

		counter = "00000002";
		totalMsg = Shs + counter + ShInfo;
		System.out.println("\nMessage for Hash2:"+ totalMsg);
		 
		String keyData2 = generateMessageHashSHA256(totalMsg);
		System.out.println("\nHash2:"+ keyData2);

		String finalKeyData = keyData1 + keyData2;

		keyMap.put("iMCV", finalKeyData.substring(0, 32));
		keyMap.put("sEnc", finalKeyData.substring(32,64));
		keyMap.put("sMac", finalKeyData.substring(64,96));
		
		return keyMap;
		
	}
	
	
	public Map<String,String> extractValFromPrepareDownloadResponse(String prpareDownloadRes) {
		String euiccSigned2 ="";
		String euiccSignature2 = "";
		int length=0,offset =0;
		int length1 =0,offset1=0;
		
		Map<String,String> pdResponse = new HashMap<>();
		
		
		String prpareDownloadResponse = utils.byteArrayToHex(Base64.getDecoder().decode(prpareDownloadRes));
		if(!prpareDownloadResponse.substring(0, 4).equalsIgnoreCase("BF21")) {
			System.out.println("1");
			return pdResponse;
		}
		
		length = utils.calculateLengthFromLVString(prpareDownloadResponse.substring(4));
		offset = utils.calculateOffset(length/2);
		offset = offset + utils.len("BF21");
		
		if(!prpareDownloadResponse.substring(offset, offset + utils.len("A0")).equalsIgnoreCase("A0")) {
			System.out.println("1");
			return pdResponse;
		}
		length1 = utils.calculateLengthFromLVString(prpareDownloadResponse.substring(offset + utils.len("A0")));
		offset1 = utils.calculateOffset(length1/2);
		offset = offset + utils.len("A0") + offset1;

		//extract euiccSigned2
		if(!prpareDownloadResponse.substring(offset, offset + utils.len("30")).equalsIgnoreCase("30")) {
			System.out.println("2");
			return pdResponse;
		}
		
		length1 = utils.calculateLengthFromLVString(prpareDownloadResponse.substring(offset + utils.len("30")));
		offset1 = utils.calculateOffset(length1/2);
		
		euiccSigned2 = prpareDownloadResponse.substring(offset,offset + utils.len("30") + offset1 + length1);
		offset = offset + utils.len("30") + offset1 + length1;
		
		//extract euiccSignature2
		if(!prpareDownloadResponse.substring(offset, offset + utils.len("5F37")).equalsIgnoreCase("5F37")) {
			System.out.println("3");
			return pdResponse;
		}

		length1 = utils.calculateLengthFromLVString(prpareDownloadResponse.substring(offset + utils.len("5F37")));
		offset1 = utils.calculateOffset(length1/2);
		//offset1 = offset + utils.len("5F37");
		//euiccSignature2 = prpareDownloadRes.substring(offset,offset + /*utils.len("5F37") */+ offset1 + length1);
		euiccSignature2 = prpareDownloadResponse.substring(offset);
		
		pdResponse.put("euiccSigned2", euiccSigned2);
		pdResponse.put("euiccSignature2", euiccSignature2);
		return pdResponse;
	}
	
	public String extractOtPkEuiccFromEuiccSigned2(String eiccInfo2) {
		int length=0,offset =0;
		int length1 =0,offset1=0;
		if(!eiccInfo2.substring(0, 2).equalsIgnoreCase("30")) {
			System.out.println("1");
			return "";
		}
		
		length = utils.calculateLengthFromLVString(eiccInfo2.substring(4));
		offset = utils.calculateOffset(length/2);
		offset = offset + utils.len("30");
		
		//1. Extract txID from EuiccSigned2
        if (!eiccInfo2.substring(offset,offset + utils.len("80")).equalsIgnoreCase("80")) {
        	System.out.println("2");
			return "";
        }
		
		length1 = utils.calculateLengthFromLVString(eiccInfo2.substring(offset + utils.len("80")));
		offset1 = utils.calculateOffset(length1/2);
		String transactionID = eiccInfo2.substring(offset,offset + utils.len("80") + offset1 + length1);
		System.out.println("TransactionID extracted from EuiccSigned2(Hex)::"+ transactionID);
	    offset = offset + utils.len("80") + offset1 + length1;	
		
	    //2. Extract euiccOtPk from EuiccSigned2
	    if (!eiccInfo2.substring(offset,offset + utils.len("5F49")).equalsIgnoreCase("5F49")) {
        	System.out.println("3");
			return "";
        }
	    length1 = utils.calculateLengthFromLVString(eiccInfo2.substring(offset + utils.len("5F49")));
		offset1 = utils.calculateOffset(length1/2);
	    
		String euiccOtpk =  eiccInfo2.substring(offset + utils.len("5F49") + offset1,offset + utils.len("5F49") + offset1 + length1);
		
		return  euiccOtpk;
	}
	
	public boolean verifyEuiccSignature1() {
		
		return true;
	}
	
	public String genIscTlvData(String remoteOpId,String transactionId,
								String ControlRefTemplateValue,String skDPbpEcdsa) throws InvalidKeyException, NoSuchAlgorithmException, 
																						  NoSuchProviderException, SignatureException, UnsupportedEncodingException {
		
		String signedData = "82" + getLVValue(remoteOpId) +
                				"80" + getLVValue(transactionId) +
                				ControlRefTemplateValue +
                				"5F49" + getLVValue("04" + otPKSmdpECKA) +
                				"5F49" + getLVValue(otPKEuiccECKA); 

		String smdpSignature = EcdsaCrypto.generateSignatureB64(skDPbpEcdsa, signedData,"brainpoolP256r1");
		System.out.println("smdpSignature::"+smdpSignature);
		
		return "BF23" +
                getLVValue("82" + getLVValue(remoteOpId) +
                           "80" + getLVValue(transactionId) +
                           ControlRefTemplateValue +
                           "5F49" + getLVValue(
                           "04" + otPKSmdpECKA) +  // Check if this 04 is required before one time public key of smdp
                           smdpSignature);
	}

	
	public String generateFirstSequenceOf87(String ConfigureISDPData,scp03t scp) throws InvalidKeyException, 
																						NoSuchAlgorithmException, 
																						NoSuchPaddingException, 
																						InvalidAlgorithmParameterException, 
																						IllegalBlockSizeException, 
																						BadPaddingException {
		// 1. Generate sMac and sEnc over configureISDP data
        // Append A0 + Len + 87 + Len + ENC/Mac(Configure ISDP data)
        // encData = self.generateCMacAndCEncOverProfileData(scp03tSegmentConfigureISDPData,Senc,SCmac,ICV)
        // encData = scp.generateCMacAndCEnc(scp03tSegmentConfigureISDPData)
		String encData = scp.generateSCP03tEnc(ConfigureISDPData);
		String SequenceOf87 = "87" + getLVValue(encData);
		return "A0" + getLVValue(scp.generateCMac(SequenceOf87));
	}
	
	public String generateSequenceOf88(String StoreMetaData,scp03t scp) {

		String SequenceOf88 = "88" + getLVValue(StoreMetaData);
		System.out.println("First Sequence of 88:\t"+ SequenceOf88);
		return "A1" + getLVValue(scp.generateCMac(SequenceOf88));
	}
	
	public String generateSecondSequenceOf87(String ReplaceSessionKeyData,scp03t scp) throws InvalidKeyException, 
																							 NoSuchAlgorithmException, 
																							 NoSuchPaddingException, 
																							 InvalidAlgorithmParameterException, 
																							 IllegalBlockSizeException, 
																							 BadPaddingException{
		//1. Generate sMac and sEnc over Replace Session Key data
        //2. A2+ Len+ 87 + len + ENC/Mac(ReplaceSessionKey data)
        //:: rsk = scp.generateCMacAndCEnc(scp03tSegmentReplaceSessionKeyData)
        //:: SequenceOfSecond87 = "87" +getLVValue(rsk)
        //:: return "A2" + getLVValue(SequenceOfSecond87)
		String rsk = scp.generateCMacAndCEnc(ReplaceSessionKeyData);
		String SequenceOfSecond87 = "87" +getLVValue(rsk);
		return "A2" + getLVValue(SequenceOfSecond87);
	}
	
	public String generateSequenceOf86(String scp03tSegmentPayload,scp03t scp) throws InvalidKeyException, 
																					  NoSuchAlgorithmException, 
																					  NoSuchPaddingException, 
																					  InvalidAlgorithmParameterException, 
																					  IllegalBlockSizeException, 
																					  BadPaddingException {
		//1. Prepare 1008 Byteof Data and Generate sMac and sEnc over 
        //2. Append 86 + Len + generateAbove Data
        //3. Repeat step1 and step2 untill complete payload is completed
        //4. A3 + len + complete waraped payload generated in step 1,2 and 3
        //:: PBPP = scp.generateCMacAndCEnc(scp03tSegmentPayload)
        //:: return "A3" + getLVValue(PBPP)
		
		String PBPP = scp.generateCMacAndCEnc(scp03tSegmentPayload);
		return "A3" + getLVValue(PBPP);
	}
	
	//createBppResonse
	public GetBoundProfilePackageResponse createBppResonse(GetBoundProfilePackageRequest getBppRequest,
														   String txId,
														   RspGlobal rsp) throws Exception {
		
		
		System.out.println(" === Inside GetBound Profile Package Processing=== ");
		System.out.println("TransactionId :"+ getBppRequest.getTransactionID());
		System.out.println("prepareDownloadResponse :"+getBppRequest.getPrepareDownloadResponse());
		
		//1. Compare Ongoing Transaction Id with Transaction Id coming in GetBoundProfilePackage Request
		
		// decode Prepare Download response and extract euiccSigned2 and euiccSignature2
		Map<String,String> extractedValue =  extractValFromPrepareDownloadResponse(getBppRequest.getPrepareDownloadResponse());
		euiccSigned2    = extractedValue.get("euiccSigned2");
		euiccSignature2 = extractedValue.get("euiccSignature2");
		
		System.out.println("euiccSigned2:\t"    +euiccSigned2);
		System.out.println("euiccSignature2:\t" +euiccSignature2);
		
		//Verify euiccSignature2 from euiccpublickey and euiccSigned2
		
		//Extract euiccOtPk from euicc signed1
		otPKEuiccECKA = extractOtPkEuiccFromEuiccSigned2(extractedValue.get("euiccSigned2"));
		System.out.println("otPKEuiccECKA:\t"+otPKEuiccECKA);
		
		
		String euiccPublicKey = rsp.getEuiccPk();
		
		//Verify euiccSignature2
		//System.out.println(EcdsaCrypto.verifySignatureUsingPK(euiccPublicKey, euiccSigned2, euiccSignature2,"secp256r1"));
		//System.out.println(EcdsaCrypto.verifySignatureUsingPK(euiccPublicKey, euiccSigned2, euiccSignature2,"brainpoolP256r1"));


		//Generate smdpOtPk,smdpOtsk
		//Map<String,String> keyPairs = EcdsaCrypto.generateECKeys("secp256r1");
		Map<String,String> keyPairs = EcdsaCrypto.generateECKeys("brainpoolP256r1");
		otSKSmdpECKA = keyPairs.get("PrivateKeyStr");
		otPKSmdpECKA = keyPairs.get("PublicKeyStr");
		System.out.println("otSKSmdpECKA:\t"+ otSKSmdpECKA);
		System.out.println("otPKSmdpECKA :\t"+ otPKSmdpECKA);
		
		//otSKSmdpECKA = "e66c897aa93adf05e957c61102daa9ff8da7106cd2551af7bf1b17ac060f000f";
		//otPKEuiccECKA = "047e999c873a924e9403b5b70d6469039b432d267d392e336848acffdb13faa1c2af0801b344bf86fcf3140686514e12cfda88aee8e013ab51349c450976e16966";
		
		String Shs = Ecka.sharedSecret(otPKEuiccECKA,otSKSmdpECKA,"brainpoolP256r1");
		
		//Private key of SMDpPb
		String skDPbpEcdsa = EcdsaCrypto.extractPrivateKey(SMDPConstants.SK_S_SM_DPpb_ECDSA_BRP);
		
		
		//Generate Shared Info
		String ShInfo = SMDPConstants.KEY_TYPE + SMDPConstants.KEY_LEN + getLVValue(SMDPConstants.HOST_ID)+ SMDPConstants.EID_VAL;
		
		
		//Generate Session Keys
		Map<String,String> KeyMap = genarateSessionKeyFromSharedSecretandSharedInfo(Shs,ShInfo);
		
		String iMacChainVal = KeyMap.get("iMCV");
		String sMacKey      = KeyMap.get("sMac");
		String sEncKey		= KeyMap.get("sEnc");
		
		System.out.println("\niMacChainVal::"+ iMacChainVal);
		System.out.println("\nsMacKey::"+ sMacKey);
		System.out.println("\nsEncKey::"+ sEncKey);
		
		//SCP03t object
		scp03t scp = new scp03t(sEncKey, sMacKey, iMacChainVal);
		
		
		System.out.println("===============================================================");
        String bppIscData = genIscTlvData(SMDPConstants.remoteOpIdValue,
        										getBppRequest.getTransactionID(),
                                          		genControlRefTemplate(),
                                                skDPbpEcdsa);
		
        
        String smdpoid = "B805800388370A";  // This OID need to be chnage(SMDP OID: 2.999.10= 800388370A(Hex Value))
        String configureISDPData = "BF24" + getLVValue(smdpoid);
        
        String bppConfigureISDP = generateFirstSequenceOf87(configureISDPData, scp);
        System.out.println("\nbppConfigureISDP::"+ bppConfigureISDP);
        
        
        System.out.println("===============================================================");
        String bppSMDataData = generateSequenceOf88(metaData, scp);
        System.out.println("bppSMDataData::\n:"+ bppSMDataData);
        
        
		GetBoundProfilePackageResponse gBPPResponse  = new GetBoundProfilePackageResponse("01", 
																						  "02", 
																						  new Header(new FunctionExecutionStatus("Executed-Success")));
		return gBPPResponse;
	}

	
}
