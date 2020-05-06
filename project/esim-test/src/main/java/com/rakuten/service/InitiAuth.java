package com.rakuten.service;


import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Service;
import com.rakuten.dto.FunctionExecutionStatus;
import com.rakuten.dto.Header;
import com.rakuten.dto.InitAuthRequest;
import com.rakuten.dto.InitAuthResponse;
import com.rakuten.ecdsa.EcdsaCrypto;
import com.rakuten.sgpConstants.SGPContants;
import com.rakuten.sgpConstants.SMDPConstants;
import com.rakuten.utils.utils;

@Service
public class InitiAuth {

	
	public String getAuthenticate(String usernm,String pwd) {
		if(usernm==null || pwd==null)
		{
			return "fail";
		}
		return "success";
	}
	
	
	
	public InitAuthResponse createInitAuthResonse(InitAuthRequest initRequest,String txid) throws Exception {
		
		System.out.println("<======== Inside Initiate Authenticate Request =======>");
		System.out.println("euiccChallenge:\t"+ initRequest.getEuiccChallenge());
		System.out.println("euiccInfo1:\t"+ initRequest.getEuiccInfo1());
		System.out.println("smdpAddress:\t"+  initRequest.getSmdpAddress());
		
		//1. Check if received address matches its own SM-DP+ address
		if(!checkSmdpAddressFromInitAuthRequest(initRequest.getSmdpAddress()))
			return createErrorResponse("8.8.1","3.8","Invalid SM-DP+ Address.");
		
		//2. check if SMDP+ supports the Specification Version Number indicated by the eUICC
		if(!compareSVNFromEuiccInfo1(initRequest.getEuiccInfo1()))
			return createErrorResponse("8.8.3","3.1","Unsupported SVN");
		
		//TODO
		//3. Check if SMDP+ Can use one of GSMA CI Public key against eUICC Signature
		//extractEuiccCiPKIdListForVerification(initRequest.getEuiccInfo1());
		
		//4. Check if smdp+ Server can provide Cert.DPAuth.ECDSA signed by one of GSMA CI Public key

		
		
		System.out.println("<====== Preparing Initiate Authenticate Response ======>");
		
		//1. Generate Transaction ID
		String TxID = txid;
		System.out.println("Transaction ID in Hex:\t"+TxID);
				
		//2. Euicc Challenge
		String euiccChallenge = utils.byteArrayToHex(Base64.getDecoder().decode(initRequest.getEuiccChallenge()));
		System.out.println("euicc Challenge in Hex:\t"+euiccChallenge);		
		
		
		//3. CiPkIkIdToBeUsed 
		String CiPkIkIdToBeUsed = SMDPConstants.ciKeyID[SMDPConstants.CiPkIdToBeUsedNo][0];
		String Curve = SMDPConstants.ciKeyID[SMDPConstants.CiPkIdToBeUsedNo][1];
		
		
		//4. DPAuth Certificate to be used
		String certDPAuthECDSA = SMDPConstants.certDpAuth[SMDPConstants.DpAuthCertficateType][SMDPConstants.DpAuthCertficateNo];
		
		//5. Private Key Corresponding to DPAuth Certificate
		String skDpAuth = SMDPConstants.SKDpAuth[SMDPConstants.DpAuthCertficateType][SMDPConstants.DpAuthCertficateNo];
		
		String serverChallenge = genearteServerChallenge();
		String serverSigned1   = generateServerSigned1(TxID,
													euiccChallenge, 
													utils.encodeStringToHexString(SMDPConstants.SMDP_PLUS_ADDRESS), 
													serverChallenge);
		String serverSignature1 = EcdsaCrypto.generateSignature(skDpAuth, 
																serverSigned1,
																Curve);
		
		System.out.println("serverChallengein Hex:\t"+ serverChallenge);
		System.out.println("serverSigned1 Vaue in Hex:\t"+serverSigned1);
		System.out.println("serverSignature1 value in Hex:\t"+serverSignature1);
		
		//Test Purpose: verify Signature with Public key
		//System.out.println(EcdsaCrypto.verifySignatureECDSAB64(certDPAuthECDSA, serverSigned1, serverSignature1, Curve));
		
		
		serverSignature1 = utils.generateASN1Signature(serverSignature1);
		System.out.println("serverSignature1 value in ASN.1:\t"+serverSignature1);
		
		/*InitAuthResponse res= new InitAuthResponse(TxID,
				Base64.getEncoder().encodeToString(utils.hexStringToByteArray(serverSigned1)),
				Base64.getEncoder().encodeToString(utils.hexStringToByteArray(serverSignature1)),
				SMDPConstants.SMDP_PLUS_EUICC_CI_PKIdToBeUsed,
				SMDPConstants.CERT_S_SM_DPauth_ECDSA_BRP
				,new Header(new FunctionExecutionStatus("Executed-Success")) );*/
		
		InitAuthResponse res= new InitAuthResponse(TxID,
				Base64.getEncoder().encodeToString(utils.hexStringToByteArray(serverSigned1)),
				Base64.getEncoder().encodeToString(utils.hexStringToByteArray(serverSignature1)),
				CiPkIkIdToBeUsed,
				certDPAuthECDSA
				,new Header(new FunctionExecutionStatus("Executed-Success")) );
		
		return res;
		
	}
	
	/*
	public InitAuthResponse  createInitSuccessResonse(InitAuthRequest initRequest) throws Exception {
		
		System.out.println("<======== Initiate Authenticate Request =======>");
		
		System.out.println("euiccChallenge:\t"+ initRequest.getEuiccChallenge());
		System.out.println("euiccInfo1:\t"+ initRequest.getEuiccInfo1());
		System.out.println("SMDP address:\t"+  initRequest.getSmdpAddress());
		
		
		if(!checkSmdpAddressFromInitAuthRequest(SMDPConstants.SMDP_PLUS_ADDRESS))
			return createErrorResponse("8.8.1","3.8","Invalid SM-DP+ Address.");
		
		System.out.println("Preparing Init Auth response");
		
		//1.Generate Transaction ID
		String TxID = utils.genearteTransactionId();
		System.out.println("Transaction ID in Hex :"+TxID);
				
		//2. Euicc Challenge
		String euiccChallenge = utils.byteArrayToHex(Base64.getDecoder().decode(initRequest.getEuiccChallenge()));
		System.out.println("EUCC Challenge in Hex :"+euiccChallenge);		
		
		
		String serverChallenge = genearteServerChallenge();
		
		String serverSigned1   = generateServerSigned1(TxID,
													euiccChallenge, 
													utils.encodeStringToHexString(SMDPConstants.SMDP_PLUS_ADDRESS), 
													serverChallenge);
		
		String serverSignature1 = EcdsaCrypto.generateSignatureB64(SMDPConstants.SK_S_SM_DPauth_ECDSA_BRP, 
																serverSigned1,
																"brainpoolP256r1");
		
		System.out.println("serverSignature1 in Hex :"+serverSignature1);
		//System.out.println("serverSignature1 in B64:"+Base64.getEncoder().encode(utils.hexStringToByteArray(serverSignature1)).toString());
		System.out.println("serverSignature1 in B64:"+Base64.getEncoder().encodeToString(utils.hexStringToByteArray(serverSignature1)));
		//myMap.put("body", new InitAuthResponse("11223344556677889900078787878787",

		//		InitAuthResponse res= new InitAuthResponse("11223344556677889900078787878787",
//									"4d453641454245694d3052565a6e65496d514148683465486834654245494845765a6d4e2b347a32434145624d74546874762b44466d567a615730756257397564486c77636d39715a574e306379356a62323245454534706458526c51456770676757736c68724e4b33493d",
//									"XzdAEbMzQaQJJZSl89zMJk+CSebvimqs7JsmsUNMheV5qrz6mExr5m6N4OJtYLHaCwxF2UwxPgdSh4CRLEaXw1j8Eg==",
//									"81370F5125D0B1D408D4C3B232E6D25E795BEBFB",
//									"MIICYjCCAgegAwIBAgIQLQ5lmHZE1zJ2CPtAZglb+DAKBggqhkjOPQQDAjBEMRgwFg"
//									+ "YDVQQKEw9HU00gQXNzb2NpYXRpb24xKDAmBgNVBAMTH0dTTSBBc3NvY2lhdGlvbiAtIFJT"
//									+ "UDIgUm9vdCBDSTEwHhcNMTcwNzIwMDAwMDAwWhcNMjAwNzE5MjM1OTU5WjBIMQswCQYD"
//									+ "VQQGEwJGUjETMBEGA1UECgwKR2VtYWx0byBTQTEkMCIGA1UEAwwbU01EUCBQbHVzI"
//									+ "FRvdXJzIFBsYXRmb3JtIDAyMFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEtwgOHadOHQe"
//									+ "OHxKfnKesTxZDFqTErGT0o/t59oxVUM3IicJXH+vCbUgXQ0z4pEoq1+oroJBn9Kynq4T+bF"
//									+ "hswaOB1jCB0zAXBgNVHSABAf8EDTALMAkGB2eBEgECAQQwTQYDVR0fBEYwRDBCoECgPoY8aHR0cDovL2dzbWEtY3JsLnN5bWF1dGguY29tL29mZmxpbmVjYS9nc21hLXJzcDItcm9vdC1jaTEuY3JsMA4GA1UdDwEB/wQEAwIHgDAZBgNVHREEEjAQiA4rBgEEAYH4AgGBXGRlAjAdBgNVHQ4EFgQURBvO5F1K3NIX35cj0JhaCQiAUXowHwYDVR0jBBgwFoAUgTcPUSXQsdQI1MOyMubSXnlb6/swCgYIKoZIzj0EAwIDSQAwRgIhANeHTHfH0xK0pkWfofuoYUfijXQQlraAlmFYav5PJ+xTAiEAg0f5QlcDvV6lPJ26wm+w1i6+3RZFE7LVu/NxSCUKj20="
//									,new Header(new FunctionExecutionStatus("All-successs")) );
		
		InitAuthResponse res= new InitAuthResponse(TxID,
				Base64.getEncoder().encodeToString(utils.hexStringToByteArray(serverSigned1)),
				Base64.getEncoder().encodeToString(utils.hexStringToByteArray(serverSignature1)),
				SMDPConstants.SMDP_PLUS_EUICC_CI_PKIdToBeUsed,
				SMDPConstants.CERT_S_SM_DPauth_ECDSA_BRP
				,new Header(new FunctionExecutionStatus("Executed-Success")) );
	    
		
		return res;
	}*/
	
	
	
	
	public InitAuthResponse  createInitFailedResonse(InitAuthRequest initRequest) {
		
		System.out.println("Inside Service ");
		System.out.println("EUICC!1 :"+initRequest.getEuiccInfo1());
		System.out.println("EUCC Challenge :"+initRequest.getEuiccChallenge());
		System.out.println("SMDP address :"+initRequest.getSmdpAddress());
		
		//myMap.put("body", new InitAuthResponse("11223344556677889900078787878787",
		Map<String,String> failedValues = new TreeMap<>();
		failedValues.put("subjectCode", "8.2.5");
		failedValues.put("resonCode", "3.7");
		failedValues.put("message", "No More Profile");
		InitAuthResponse res= new InitAuthResponse(new Header(new FunctionExecutionStatus("Failed",failedValues)));
	                                       
		return res;
	}
	
	public static InitAuthResponse createErrorResponse(String subjectCode,String reasonCode,String errorMessage) {
		Map<String,String> failedValues = new LinkedHashMap<>();
		failedValues.put("subjectCode", subjectCode);
		failedValues.put("resonCode", reasonCode);
		failedValues.put("message", errorMessage);
		InitAuthResponse res= new InitAuthResponse(new Header(new FunctionExecutionStatus("Failed",failedValues)));
	                                       
		return res;
		
	}
	

	
	private boolean compareSVNFromEuiccInfo1(String EuiccInfo1) {
		byte[] euiccInfo1 = Base64.getDecoder().decode(EuiccInfo1); 
		byte[][] extractedSVN = utils.readTLV(utils.byteArrayToHex(euiccInfo1), Integer.parseInt("82", 16));
		//System.out.println(utils.byteArrayToHex(extractedSVN[0]));
		if(utils.byteArrayToHex(extractedSVN[0]).equalsIgnoreCase(SMDPConstants.SMDP_PLUS_SUPPORTED_SVN))
			return true;
		
		return false;
	}

	private boolean checkSmdpAddressFromInitAuthRequest(String InitAuthSmdpAdress) {
		if(InitAuthSmdpAdress.equalsIgnoreCase(SMDPConstants.SMDP_PLUS_ADDRESS)) {
			return true;
		}
		else
			return false;
	}
	
	private String genearteServerChallenge() {
		return utils.genearteChallenge();
	}
	
	/*
	 * Generate serverSigned1 Data
	 */
	public String generateServerSigned1(String txID, String euiccChallnge,
										String serverAddress, String ServerChallnge) {
		
		String serverSigned1 = SGPContants.TAG_SERVER_SIGNED1+ 
										utils.getLV(SGPContants.TAG_SERVER_SIGNED1_TXID + utils.getLV(txID)+
												   SGPContants.TAG_SERVER_SIGNED1_EUICC_CHALLENGE + utils.getLV(euiccChallnge)+
												   SGPContants.TAG_SERVER_SIGNED1_SERVER_ADDRESS + utils.getLV(serverAddress)+
												   SGPContants.TAG_SERVER_SIGNED1_SERVER_CHALLENGE + utils.getLV(ServerChallnge));
						
		return serverSigned1;
	}

	

	public String extractEuiccCiPKIdListForVerification(String euiccInfo1) {
		byte[] Euiccinfo1 = Base64.getDecoder().decode(euiccInfo1); 
		byte[][] extractedCiPKIdListForVerification = utils.readTLV(utils.byteArrayToHex(Euiccinfo1), Integer.parseInt("A9", 16));
		System.out.println(extractedCiPKIdListForVerification[0]);
		return utils.byteArrayToHex(extractedCiPKIdListForVerification[0]);
		
	}
	
	public String extractEuiccCiPKIdListForSigning(String euiccInfo1) {
		return "";
	}
	
	
	
}
