<?php
$name = $_POST['name'];
$cname = $_POST['cname'];
$sname = $_POST['sname'];
$oname = $_POST['oname'];
$ouname = $_POST['ouname'];
$email = $_POST['email'];
$pkey = $_POST['pkey'];
$dname = $_POST['dname'];
 


if(ISSET($_POST['name']) && ISSET($_POST['cname']) && ISSET($_POST['sname']) && ISSET($_POST['oname']) && ISSET($_POST['ouname']) && ISSET($_POST['email'])&& ISSET($_POST['pkey']) && ISSET($_POST['dname'])) { 

        $data ='User Name =>'. $_POST['name'] ."\n".'Country Name =>' . $_POST['cname'] ."\n". 'State Name =>' . $_POST['sname'] ."\n". 'OrgOrganization Name =>' . $_POST['oname'] ."\n". 'Organization Unit Name =>' . $_POST['ouname'] . "\n".'E-mail =>' . $_POST['email'] ."\n". 'Public key =>' . $_POST['pkey'] ."\n". 'Domain Name =>'. $_POST['dname'] .  "\n";

         
        $stringData= "Subject Name\n\n";
        
        $f = fopen("insert.txt", "w");
        fwrite($f,$dname."\n\n");
        $identity ='Identity Name =>'. $_POST['dname'] ;
        fwrite($f,$identity."\n");
        $verified = "www.amrita.ca";
        $verify ='Verified by =>'.$verified;
        fwrite($f,$verify."\n\n");
        fwrite($f,$stringData);
        fwrite($f, $data); 
        fclose($f);
           
}

else {
       die('No post data to process');
}

    
$connect = mysql_connect('localhost', 'root', 'student123');

if (!$connect) {
    die ("Cannot connect to the 'localhost' using 'root'"); 
    }
else{
    mysql_select_db('cloud', $connect);

    $query = "INSERT INTO details VALUES ('$name','$cname','$sname','$oname','$ouname','$email','$pkey', '$dname')";
   

    print '<br><font size="4" color="blue">';
    if (mysql_query($query, $connect)) {
        print "Insert into 'cloud' was successful!</font>";
    } else {
        print "Insert into 'cloud' failed!</font>";
    }
}
mysql_close ($connect);
?>

