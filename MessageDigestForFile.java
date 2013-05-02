package Demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import Demo.X509CertificateGenerator;

/**
 * This class is to generate the hash value of the data stored in the file
 * @author student
 *
 */


public class MessageDigestForFile {

	/**
	 * @param args
	 */
	public void callDigest(String filePath) {
		// TODO Auto-generated method stub
		 String digest=null;
		try{
			String file = "/home/student/test.txt";
			String destnFile="/home/student/encrypted.txt";
			MessageDigest md = MessageDigest.getInstance("MD5");
		    digest = getDigest(new FileInputStream(filePath), md, 2048);

			System.out.println("MD5 Digest:" + digest);
			
			File digestFile=new File(file);
			FileWriter fwriter= new FileWriter(file, true);
			fwriter.write(digest);
			
			X509CertificateGenerator.rsaEncrypt(file, destnFile);
			System.out.println("hello");
			
			BufferedReader in = new BufferedReader(new FileReader(destnFile));
			
			BufferedWriter out = new BufferedWriter(
					new FileWriter(filePath, true));
			
			String str;
			while ((str = in.readLine()) != null) {
			out.write(str);
			}
			in.close();
			out.close();
			
			
			
			
			System.out.println("The digital certificate is");
			
			 BufferedReader inOut = new BufferedReader(
					    new FileReader("/home/student/inputFile.txt"));

					    String inputLine;
					    while ((inputLine = inOut.readLine()) != null)
					        System.out.println(inputLine);
					    inOut.close();
			
		}catch(NoSuchAlgorithmException ex){
			System.out.println("no such algm");
		}catch(FileNotFoundException ex1){
			System.out.println("file not found");
		}catch(IOException ex2){
			System.out.println("io");
			
		}
		//return digest;
 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	public  String getDigest(InputStream is, MessageDigest md, int byteArraySize)
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
