import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

/**
 * This class is to generate the hash value of the data stored in the file
 * @author student
 *
 */


public class MessageDigestForFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			String file = "/home/student/test.txt";
			MessageDigest md = MessageDigest.getInstance("MD5");
			String digest = getDigest(new FileInputStream(file), md, 2048);

			System.out.println("MD5 Digest:" + digest);
			
		}catch(NoSuchAlgorithmException ex){
			System.out.println("no such algm");
		}catch(FileNotFoundException ex1){
			System.out.println("file not found");
		}catch(IOException ex2){
			System.out.println("io");
			
		}
		

	}
	public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)
			throws NoSuchAlgorithmException, IOException {

		md.reset();
		byte[] bytes = new byte[byteArraySize];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			md.update(bytes, 0, numBytes);
		}
		byte[] digest = md.digest();
		String result = new String(Hex.encodeHex(digest));
		return result;
	}

}
