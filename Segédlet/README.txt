MSC Onlab 2

OpenLDAP Server -> slapd configuration

-----	LDAP	-----
LDAP reconfigure:
sudo dpkg-reconfigure slapd

LDAP Enable ldaps:
sudo vi /etc/ldap/ldap.conf
sudo vi /etc/default/slapd

-----	IMPORT CERT_FILE eg: LDAP CERT	-----
GEt Java home:
echo $JAVA_HOME

LDAP certificate to JVM truststore:
# Copy the certificate into the directory Java_home\Jre\Lib\Security
# Change your directory to Java_home\Jre\Lib\Security>
# Import the certificate to a trust store.

keytool -import -alias ca -file somecert.cer -keystore cacerts -storepass changeit [Return]

Trust this certificate: [Yes]

If this error happens:
keytool error: java.lang.Exception: Input not an X.509 certificate
java.lang.Exception: Input not an X.509 certificate
        at sun.security.tools.KeyTool.addTrustedCert(KeyTool.java:1913)
        at sun.security.tools.KeyTool.doCommands(KeyTool.java:818)
        at sun.security.tools.KeyTool.run(KeyTool.java:172)
        at sun.security.tools.KeyTool.main(KeyTool.java:166)
        
Check the cert file:
Does your cacerts.pem file hold a single certificate? Since it is a PEM, have a look at it, it should contain
-----BEGIN CERTIFICATE-----

and end with

-----END CERTIFICATE-----

Finally, to check it is not corrupted, get hold of openssl and print its details using

openssl x509 -in cacerts.pem -text

After importing to default trust store, LDAP SSL should work!!



Check out Spring LDAP documentation for connecting to LDAP server over HTTP(S):

As far as self signed certificate is concerned, you can import certificate chain into a truststore and set the following VM arguments:

-Djavax.net.ssl.trustStore="<path to truststore file>"
-Djavax.net.ssl.trustStorePassword="<passphrase for truststore>"

or override the truststore at runtime like:

System.setproperty("javax.net.ssl.trustStore","<path to truststore file>")
System.setproperty("javax.net.ssl.trustStorePassword","<passphrase for truststore>")

Keep in mind that both options will override default JVM truststore. So if you are hitting different sites with different certs, you may want to import all of them into one truststore.
