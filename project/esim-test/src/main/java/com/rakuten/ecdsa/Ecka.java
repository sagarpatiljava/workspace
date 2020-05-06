package com.rakuten.ecdsa;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.ECPointUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;

import com.rakuten.utils.utils;



public class Ecka {

	public final static int pValue = 47;

	public final static int gValue = 71;

	public final static int XaValue = 9;

	public final static int XbValue = 14;

	/*
	public static void main(String[] args) throws Exception {

		//String euiccOtPk = "0456c92fe92d7eebeb2d247572fb615cb6bbc6a0faa6cdf25368fb76b74a636b78789391f22475fe3a6905399caf5f1fee702d92c2a8786b1759a0eac23825fa47";
		//String euiccOtPk = "4dfed4f4694791bf1695cea0307a35b418019695387bb75b7d2447b6b5209f0445ae4e5e521cd13888d75fe07c8580222ae20dbaac1d77cd76304993421bd739";
		//String smdpOtSk  = "72855921798631197769721367615418667901136218718534684580211478125326938535908";
		
		//String euiccOtPk = "04214bdecd4dd5e271837fd43d32180ac090f78be267da28470d63754a02d643dd23d205a8578a605e25b30fbaa0c7c075d778522e938205ee93e5f35217bbf36a";
		//String smdpOtSk  = "81d89b2ef42156780e10486f7cb1e92818a48951566428f7db417d5579f42cb5";
		
		//String euiccOtPk = "0456c92fe92d7eebeb2d247572fb615cb6bbc6a0faa6cdf25368fb76b74a636b78789391f22475fe3a6905399caf5f1fee702d92c2a8786b1759a0eac23825fa47";
		//String smdpOtSk  = "a112fd8c7eae3d6da525e8f340a87827ab46885ee821b77321ec034c9435f7e4";
		
		String smdpOtSk = "93fb33d0584f349b07f8b5d2af93d7c3e354b349a3b913502e6abc070e4d4929";
		String euiccOtPk = "04255d7daf00ef841d76fa7a63d63eb3fe6c9f70492d381923f8f8bd7b24f3c5ad168ebe3b09dd80f29e7ffd24a4d1be747f8323e472928396dde9e0151ceaba18";
		
		//brainpoolP256r1
		//PublicKey  pk = getPublicKeyFromHex(utils.hexStringToByteArray(euiccOtPk),"secp256r1");
		//PrivateKey sk = getPrivateKeyFromHex(smdpOtSk, "secp256r1");
		//testDh1(pk,sk,"secp256r1");
		
		PublicKey  pk = getPublicKeyFromHex(utils.hexStringToByteArray(euiccOtPk),"brainpoolP256r1");
		PrivateKey sk = getPrivateKeyFromHex(smdpOtSk, "brainpoolP256r1");
		testDh1(pk,sk,"brainpoolP256r1");

	}
	*/

	public static String sharedSecret(String euiccOtPk,String smdpOtSk,String curveName) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException {
		
		PublicKey  euiccOtPK = getPublicKeyFromHex(utils.hexStringToByteArray(euiccOtPk),curveName);
		PrivateKey smdpOtSK = getPrivateKeyFromHex(smdpOtSk, curveName);
		
//		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDH", new BouncyCastleProvider());

				
		KeyAgreement agreement =    KeyAgreement.getInstance("ECDH",new BouncyCastleProvider());
		agreement.init(smdpOtSK);
		agreement.doPhase(euiccOtPK, true);
			
		MessageDigest hash = MessageDigest.getInstance("SHA256", new BouncyCastleProvider());
		String shs = utils.byteArrayToHex(agreement.generateSecret());
		System.out.println("Generated Shared Secret:\t"+shs);
		return shs;
	}
	
	public static String testDh1(PublicKey euiccOtPk,PrivateKey smdpOtSk,String curveName) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDH", new BouncyCastleProvider());

				
		KeyAgreement alice_agreement =    KeyAgreement.getInstance("ECDH",new BouncyCastleProvider());
		alice_agreement.init(smdpOtSk);
		alice_agreement.doPhase(euiccOtPk, true);
			
		MessageDigest hash = MessageDigest.getInstance("SHA256", new BouncyCastleProvider());
		String shs = utils.byteArrayToHex(alice_agreement.generateSecret());
		System.out.println("Generated Shared Secret:\t"+shs);
		return shs;
	}
	
	public static void testDh(PublicKey euiccOtPk,PrivateKey smdpOtpk,String curveName) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDH", new BouncyCastleProvider());

		ECGenParameterSpec ecsp;
		//ecsp = new ECGenParameterSpec("secp192r1");
		ecsp = new ECGenParameterSpec(curveName);

		keyGen.initialize(ecsp, new SecureRandom());
		// Generate RSA Assymetric KeyPair
		KeyPair alice_pair = keyGen.generateKeyPair();

		// Extract Public Key
		PublicKey alice_pub =  alice_pair.getPublic();
		// Extract Private Key
		PrivateKey alice_pvt = alice_pair.getPrivate();

		KeyPair bob_pair = keyGen.generateKeyPair();

		// Extract Public Key
		PublicKey bob_pub =  bob_pair.getPublic();
		// Extract Private Key
		PrivateKey bob_pvt = alice_pair.getPrivate();

		
		KeyAgreement alice_agreement =    KeyAgreement.getInstance("ECDH",new BouncyCastleProvider());
		alice_agreement.init(alice_pair.getPrivate());
		alice_agreement.doPhase(bob_pub, true);
		byte[] alice_secret = alice_agreement.generateSecret();
		SecretKeySpec alice_aes = new SecretKeySpec(alice_secret, "AES");

		// Create KeyAgreement for Bob
		KeyAgreement bob_agreement = KeyAgreement.getInstance("ECDH",new BouncyCastleProvider());
		bob_agreement.init(bob_pvt);
		bob_agreement.doPhase(alice_pub, true); 
		
		MessageDigest hash = MessageDigest.getInstance("SHA256", new BouncyCastleProvider());
		//System.out.println(new String(hash.digest(bob_agreement.generateSecret())));
		System.out.println(utils.byteArrayToHex(bob_agreement.generateSecret()));
	}
	
	
	
	public static PrivateKey getPrivateKeyFromHex(String sk, String curveName) {

		BigInteger s = new BigInteger(sk, 16);
		ECParameterSpec ecParameterSpec = ECNamedCurveTable.getParameterSpec(curveName);
		ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(s, ecParameterSpec);
		try {
			//KeyFactory keyFactory = KeyFactory.getInstance(EC);
			KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", new BouncyCastleProvider());
			return keyFactory.generatePrivate(privateKeySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static PublicKey getPublicKeyFromHex(byte[] pubKey,String curveName) throws NoSuchAlgorithmException, InvalidKeySpecException {
		ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec(curveName);

		KeyFactory kf = KeyFactory.getInstance("ECDSA", new BouncyCastleProvider());
		ECNamedCurveSpec params = new ECNamedCurveSpec(curveName, spec.getCurve(), spec.getG(), spec.getN());
		ECPoint point =  ECPointUtil.decodePoint(params.getCurve(), pubKey);
		ECPublicKeySpec pubKeySpec = new ECPublicKeySpec(point, params);
		ECPublicKey pk = (ECPublicKey) kf.generatePublic(pubKeySpec);
		return pk;
	}

}
