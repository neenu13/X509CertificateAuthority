We are submitting the full code here.
1)User data Collection(Front End-index.html.main.html,insert.php,frontpage.html)
__________________________________

   This is done using html and jsp.The screen shots of the front page is uploaded.

2)Generate public/private key pair for
__________________________________________

   This is done in KeyGeneration.java file.Generate Private/Public key pair using RSA algorithm and it is stored in two separate files public.key and private.key

3)Signing the certificate.

_____________________________________________

   This is done in X509CertificateGenerator.java.First we will find out the hash of the user input collected using the MD5 and then encrypt the hash using private key of the Certificate Authority.

4)Delivery of the digital certificate
_______________________________________________

   The client can either save or view the digital certificate provided by the certificate authority.The screen shot os attached here.

