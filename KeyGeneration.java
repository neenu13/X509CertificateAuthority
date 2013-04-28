import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * This class is to generate the public/private key pair for the certificate autority.Certificate Authority uses 
 * private key to encrypt the hash of the user input data.This key pair will be generated only once.
 * @author student
 *
 */


public class KeyGeneration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		 try {
			generateKeys();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method generates the public/private key pair using RSA algorithm and the generated key pair is stored in 
	 * two separate files.
	 * @throws Exception
	 */
	
	  public static void generateKeys() throws Exception {
	        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
	        kpg.initialize(2048);
	        KeyPair kp = kpg.genKeyPair();
	        PublicKey publicKey = kp.getPublic();
	        PrivateKey privateKey = kp.getPrivate();

	        System.out.println("keys created");

	        KeyFactory fact = KeyFactory.getInstance("RSA");
	        RSAPublicKeySpec pub = (RSAPublicKeySpec)fact.getKeySpec(publicKey,
	                RSAPublicKeySpec.class);
	        RSAPrivateKeySpec priv = (RSAPrivateKeySpec)fact.getKeySpec(privateKey,
	                RSAPrivateKeySpec.class);

	        saveToFile("public.key", pub.getModulus(), pub.getPublicExponent());
	        saveToFile("private.key", priv.getModulus(), priv.getPrivateExponent());

	        System.out.println("keys saved");
	    }
	 
	  /**
	   * In this method the modulus and exponent of each key will be stored in separate files named "public.key" and "private.key"
	   * @param fileName
	   * @param mod
	   * @param exp
	   * @throws IOException
	   */

	    public static void saveToFile(String fileName, BigInteger mod,
	            BigInteger exp) throws IOException {
	        ObjectOutputStream fileOut = new ObjectOutputStream(
	                new BufferedOutputStream(new FileOutputStream(fileName)));
	        try {
	            fileOut.writeObject(mod);
	            fileOut.writeObject(exp);
	        } catch (Exception e) {
	            throw new IOException("Unexpected error");
	        } finally {
	            fileOut.close();
	            System.out.println("Closed writing file.");
	        }
	    }

}
