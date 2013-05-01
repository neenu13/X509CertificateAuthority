<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ page import="java.io.*" %> 
<%@ page import="Demo.MessageDigestForFile" %>


<%
String userName=request.getParameter("name");
String countryName=request.getParameter("cname");
String stateName=  request.getParameter("sname");
String orgName = request.getParameter("oname");
String orgUnitName = request.getParameter("ouname");
String email = request.getParameter("email");
String pkey  = request.getParameter("pkey");
String dname = request.getParameter("dname");

String filePath="/home/student/inputFile.txt";
File inputFile=new File(filePath);
FileWriter fwriter= new FileWriter(filePath);
//fwriter.write(("Name: "+userName+"\n"+ "Country: "+countryName+"\n"+"State: "+stateName+"\n"+"Organization: "+orgName+"\n"+"OrgUnit: "+orgUnitName+"\n"+"EmailAddress: "+email+"\n"+"Public Key: "+pkey+"\n"+"Domain: "+dname));
fwriter.write(("VERIFIER"+"\n"+"Amrita Certificate Authority"+"\n"+"ENTITY"+"\n"+"Name: "+userName+"\n"+ "Country: "+countryName+"\n"+"State: "+stateName+"\n"+"Organization: "+orgName+"\n"+"OrgUnit: "+orgUnitName+"\n"+"EmailAddress: "+email+"\n"+"Public Key: "+pkey+"\n"+"Domain: "+dname+"\n"+"signature: "+"\n") );
 // PrintWriter pw = new PrintWriter(new FileOutputStream(filePath));
 // pw.println(("Name: "+userName+"\n"+ "Country: "+countryName+"\n"+"State: "+stateName+"\n"+"Organization: "+orgName+"\n"+"OrgUnit: "+orgUnitName+"\n"+"EmailAddress: "+email+"\n"+"Public Key: "+pkey+"\n"+"Domain: "+dname));
 fwriter.close();

MessageDigestForFile msgDigest = new MessageDigestForFile();

msgDigest.callDigest(filePath);


String filename = "inputFile.txt"; 
String filepath = "/home/student/"; 
response.setContentType("APPLICATION/OCTET-STREAM"); 
response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\""); 

java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath + filename);
		  
int i; 
while ((i=fileInputStream.read()) != -1) {
  out.write(i); 
} 
fileInputStream.close(); 

%>


