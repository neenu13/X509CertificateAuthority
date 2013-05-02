package Demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

/**
 * This class will generate an encrypted file containing informations collected from the user.The data collected fro
 * user is saved in a file and its location is passed as the first argument.We had already computed the key pair for the certificate 
 * authority.We use that key here to encrypt the data and save it to another file.
 * @author student
 *
 */


public class X509CertificateGenerator{

		
	/**
	 * This method is to read the public/private key of the certificate authority.This key is furhter used to
	 * encrypt the data given by user.
	 * @param keyFileName
	 * @return
	 * @throws IOException
	 */
    static Key readKeyFromFile(String keyFileName) throws IOException {
        InputStream in = new FileInputStream(keyFileName);
        ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(
                in));
        try {
            BigInteger m = (BigInteger) oin.readObject();
            BigInteger e = (BigInteger) oin.readObject();
            KeyFactory fact = KeyFactory.getInstance("RSA");
            if (keyFileName.startsWith("public"))
                return fact.generatePublic(new RSAPublicKeySpec(m, e));
            else
                return fact.generatePrivate(new RSAPrivateKeySpec(m, e));
        } catch (Exception e) {
            throw new RuntimeException("Spurious serialisation error", e);
        } finally {
            oin.close();
            System.out.println("Closed reading file.");
        }
    }

    /**
     * The user data is stored in a file and its location is passed to this method.Content of the file 
     * is encrypted and is stored to another file in the destination.
     * 
     * @param file_loc
     * @param file_des
     * @throws Exception
     */
    
    public static  void rsaEncrypt(String file_loc, String file_des)
            throws Exception {
        byte[] data = new byte[32];
        int i;

        System.out.println("start encyption");

        Key pubKey = readKeyFromFile("/home/student/private.key");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        FileInputStream fileIn = new FileInputStream(file_loc);
        FileOutputStream fileOut = new FileOutputStream(file_des);
        CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher);

        // Read in the data from the file and encrypt it
        while ((i = fileIn.read(data)) != -1) {
            cipherOut.write(data, 0, i);
        }

        // Close the encrypted file
        cipherOut.close();
        fileIn.close();

        System.out.println("encrypted file created");
    }
    
   

}
