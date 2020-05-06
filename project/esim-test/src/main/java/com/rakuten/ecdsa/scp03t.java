package com.rakuten.ecdsa;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.params.KeyParameter;

import com.rakuten.utils.utils;

public class scp03t {

	int maxPayloadLength = 1007;
	
	static String icv = "00000000000000000000000000000000";
	String sENC ="";
	String sMAC ="";
	String ICV  ="";
	String initialMACChaningVal ="";
	int count =0;

	private final static String characterEncoding = "UTF-8";
	private final static String cipherTransformation = "AES/CBC/PKCS5Padding";
	private final static String aesEncryptionAlgorithm = "AES";

	public scp03t(String sEnc,String sMac, String macChaingVal) {
		// TODO Auto-generated constructor stub
		this.sENC = sEnc;
		this.sMAC = sMac;
		this.ICV  = icv;
		this.initialMACChaningVal= macChaingVal;
		//int counter =0;
	}

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		
		String test = "000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060711223344556677881122334455667788000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f00010203";
		
		byte[] sEnc  = utils.hexStringToByteArray("2b7e151628aed2a6abf7158809cf4f3c");
		byte[] data  = utils.hexStringToByteArray(test);
		byte[] icv   = utils.hexStringToByteArray("000102030405060708090a0b0c0d0e0f");
		
		String testData = "000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060711223344556677881122334455667788000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708090a0b0c0d0e0f000102030405060708";

		//System.out.println(generateICVValue());
		//generateSCP03tEnc()
		byte[] enc = encrypt(data,sEnc,icv);
		System.out.println("Enc data::"+utils.byteArrayToHex(enc));
		//String str = utils.byteArrayToHex(getMAC(utils.hexStringToByteArray(key),utils.hexStringToByteArray(plainText1)));
//		System.out.println(utils.byteArrayToHex(str));
//		System.out.println(str);
	}

	public String generateMsgForEnc(String msg) {
		
		String msgForEnc ="";
		int rem = 0;
		int offset = 0;
		String remMsg = "";
		System.out.println("Actual data:\t"+msg);
		// Reference::SGP.22_v2.2 :: Section 2.5.3     
        // From the 1020 bytes of each data segment, only 1008 bytes are usable for payload (deducted the 1 byte tag, 3 bytes length field and 8 bytes MAC).
        // Considering the necessary padding during encryption (16 bytes length block encryption and necessary '80' byte padding), then each data segment can
        // only contain 1007 bytes of the PPP data block
		
		if ((msg.length()/2)>maxPayloadLength) {
			System.out.println("Incorrect Payload Length!!");
			return "";
		}
		else if((msg.length()/2)==maxPayloadLength) {
			//payload = payload + "80"
		    msgForEnc = msg + "80";
		}
		else {
			
			rem    = (32 - (msg.length()%32))/2;
			offset = msg.length()/32;
			msg = msg + "80";
			//remMsg = msg.substring((32*offset),(32*offset+(34-rem*2))) //.ljust(32, "0") #Append 80 and remaining 00 to make it complete 16 bytes
			remMsg = rightPadZeros(msg.substring((32*offset),(32*offset+(34-rem*2))), 32); 
			msgForEnc = msg.substring(0,((offset*32)))+ remMsg; //Total payload for 1020 Bytes
		}
		return msgForEnc;
	}
	
	public static String rightPadZeros(String str, int num) {
	    return String.format("%1$-" + num + "s", str).replace(' ', '0');
	  }
	
	public String generateICVValue() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		System.out.println("Encryption counter:\t"+count);
        String msg   = "0000000000000000000000000000000" + Integer.toHexString(count);
        byte[] sEnc  = utils.hexStringToByteArray(this.sENC);
        byte[] data  = utils.hexStringToByteArray(msg);
        byte[] icv   = utils.hexStringToByteArray(this.ICV);
		
        return (String) utils.byteArrayToHex(encrypt(data,sEnc,icv)).subSequence(0, 32);
		
	}
	
	public String generateSCP03tEnc(String msg) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		String icvVal = generateICVValue().substring(0, 32);
		System.out.println("Genarted ICV Value:\t"+icvVal);
		
		System.out.println("Data Len:"+msg.length()/2);
		//if(msg.length()/2 != 0)
		//	return "";
		
		String paddedMsg = generateMsgForEnc(msg);
		System.out.println("PaddedMessage:\t"+paddedMsg);
		
		int padDataLen = paddedMsg.length();
		
		byte[] encData = encrypt(utils.hexStringToByteArray(paddedMsg),
									utils.hexStringToByteArray(sENC),
										utils.hexStringToByteArray(icvVal));
		return (String) utils.byteArrayToHex(encData).subSequence(0, padDataLen);
		        
	}
	
	public String generateCMacAndCEnc(String msg) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		System.out.println("<=========== Enc ====================>");
        String encrypted= generateSCP03tEnc(msg);
        System.out.println("Enc Value(Hex):\t"+encrypted);
        System.out.println("<=========== Cmac ===================>");
        String encAndMac = generateSCP03tCmac(encrypted);
        System.out.println("Enc Value and Mac(Hex):\t"+encAndMac);
        System.out.println("<===================================>");
        return encAndMac;
	}
	
	public String generateCMac(String msgData) {
		return generateSCP03tCmac(msgData);
	}
	
	public String generateSCP03tCmac(String msgData) {
		
		//1. Increment the counter
		count += 1;
		
		String msg = initialMACChaningVal + msgData;
		System.out.println("Counter Value:"+count);
		System.out.println("Total msg for MAc:"+msg);
		
		initialMACChaningVal = getMacInString(utils.hexStringToByteArray(sMAC),
												utils.hexStringToByteArray(msg));
		System.out.println("New Mac Chaining Value::"+initialMACChaningVal);
		return msgData + initialMACChaningVal.substring(0,16);
	
	}
	
	public static String getMacInString(byte[] key, byte[] data) {
		BlockCipher cipher = new AESEngine();
		Mac mac = new CMac(cipher, 128);
		KeyParameter keyP = new KeyParameter(key);
		mac.init(keyP);
		mac.update(data, 0, data.length);
		byte[] out = new byte[16];
		mac.doFinal(out, 0);
		return utils.byteArrayToHex(out);
	}
	
	public static byte[] getMAC(byte[] key, byte[] data) {
		BlockCipher cipher = new AESEngine();
		Mac mac = new CMac(cipher, 128);
		KeyParameter keyP = new KeyParameter(key);
		mac.init(keyP);
		mac.update(data, 0, data.length);
		byte[] out = new byte[16];
		mac.doFinal(out, 0);
		return out;
	}
	
	public static byte[] encrypt(byte[] plainText, byte[] key, byte [] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher = Cipher.getInstance(cipherTransformation);
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithm);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		plainText = cipher.doFinal(plainText);
		return plainText;
	}
	
	public  byte[] decrypt(byte[] cipherText, byte[] key, byte [] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher = Cipher.getInstance(cipherTransformation);
		SecretKeySpec secretKeySpecy = new SecretKeySpec(key, aesEncryptionAlgorithm);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy, ivParameterSpec);
		cipherText = cipher.doFinal(cipherText);
		return cipherText;
	}

	

	private static byte[] getKeyBytes(String key) throws UnsupportedEncodingException {
		byte[] keyBytes= new byte[16];
		byte[] parameterKeyBytes= key.getBytes(characterEncoding);
		System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
		return keyBytes;
	}

	public static String unHex(String arg) {        

	    String str = "";
	    for(int i=0;i<arg.length();i+=2)
	    {
	        String s = arg.substring(i, (i + 2));
	        int decimal = Integer.parseInt(s, 16);
	        str = str + (char) decimal;
	    }       
	    return str;
	}

}
