MSC Onlab 2

LDAP reconfigure:
sudo dpkg-reconfigure slapd

LDAP Enable ldaps:
sudo vi /etc/ldap/ldap.conf
sudo vi /etc/default/slapd

LDAP SSL/TLS config:
Enable SSL in OpenLDAP Server

Using encrypted sessions we can secure LDAP communication.  Transport Layer Security (TLS) is used for this purpose. Recent releases of  slapd  in Ubuntu is compiled with support for GnuTLS instead of OpenSSL, there for we need to install following packages now.
$sudo apt-get install gnutls-bin
After that we need to create certificate authority(CA) for this purpose.
#certtool --generate-privkey > /etc/ssl/private/cakey.pem
After that create a template file(/etc/ssl/ca.info) to assist the creation of self-sign CA.
cn = Example Company
ca
cert_signing_key
Now sign the generated CA.
#certtool --generate-self-signed \
--load-privkey /etc/ssl/private/cakey.pem \ 
--template /etc/ssl/ca.info \
--outfile /etc/ssl/certs/cacert.pem


Now create the key for slapd and sign it using generated CA.

#certtool --generate-privkey \
--outfile /etc/ssl/private/slapd01_key.pem
Create a template file.(/etc/ssl/slapd01.info)
organization = Example
cn = ldap01.example.com
tls_www_server
encryption_key
signing_key
expiration_days = 3650
Create a certificate and sign it with previously created CA.
#certtool --generate-certificate \
--load-privkey /etc/ssl/private/slapd01_key.pem \
--load-ca-certificate /etc/ssl/certs/cacert.pem \
--load-ca-privkey /etc/ssl/private/cakey.pem \
--template /etc/ssl/slapd01.info \
--outfile /etc/ssl/certs/slapd01.pem

Once the certificate is generated , now we need to tell LDAP about the created SSL certificate, for that we  need to create a LDIF file as below. (/etc/ssl/certinfo.ldif)
dn: cn=config
add: olcTLSCACertificateFile
olcTLSCACertificateFile: /etc/ssl/certs/cacert.pem
-
add: olcTLSCertificateFile
olcTLSCertificateFile: /etc/ssl/certs/slapd01.pem
-
add: olcTLSCertificateKeyFile
olcTLSCertificateKeyFile: /etc/ssl/private/slapd01_key.pem
Now add it to the LDAP.
$sudo ldapmodify -Y EXTERNAL -H ldapi:/// -f /etc/ssl/certinfo.ldif
Also make sure to grant read access to openldap user to each of below files and locations.
$chown openldap:openldap /etc/ssl/private/cakey.pem \
/etc/ssl/private/slapd01_key.pem  /etc/ssl/certs/cacert.pem   /etc/ssl/certs/slapd01.pem 
$chown -R openldap:openldap /etc/ssl/private 
Now enable SSL in /etc/default/slapd as below and restart the slapd daemon.(add ldaps:/// entry additionally.)
SLAPD_SERVICES="ldap:/// ldapi:/// ldaps:///"
/etc/init.d/slapd  restart

Now lets see how to access the secured LDAP server.

Configure DHCP server to access secured LDAP 

In http://mageconfig.blogspot.com/2014/10/configure-isc-dhcp-server-with-openldap.html  post I have already configured ISC DHCP to communicate with OpenLDAP server, therefor in here I will only show the extra steps required to communicate with secured OpenLDAP server.

In the DHCP server configuration file, change as below and restart the DHCP server.
/etc/dhcp/dhcpd.conf
ldap-server                 "localhost";
ldap-port 636;
ldap-ssl ldaps;
ldap-tls-reqcert never; #Telling Not to verify certificates as we have used self sign certs
ldap-base-dn                "ou=dhcp,dc=example,dc=com";
ldap-method                 static;
ldap-debug-file             "/var/log/dhcp-ldap-startup.log";
ldap-dhcp-server-cn         "server"

Configure Ubuntu Server to Authenticate with Secured LDAP

As I have explained the procedure above I will only show you the extra configurations needed. As I have used self sign certificates in here also I will disable certificate checks.

/etc/ldap.conf

.
.
uri ldaps://IpAddressOfLDAPServer:636/
.
.
ssl on
.
.
tls_checkpeer no
.
.
  
If you want to use ldapsearch  tool  for browsing , you have to follow below steps.
vim /etc/ldap/ldap.conf
TLS_REQCERT allow #Which tells not to validate self sign certs

$ldapsearch -x  -H ldaps://ldap.example.com -b dc=example,dc=com

