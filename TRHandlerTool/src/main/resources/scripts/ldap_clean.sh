#!/bin/bash

#########################################
# Variables
#########################################
BASE_DN="ou=People,dc=onlab,dc=msc,dc=bme,dc=hu"
UID_DN="cn=Users,ou=Groups,dc=onlab,dc=msc,dc=bme,dc=hu"
ENTRIES=""
METHOD=1
PWD=`pwd`

#########################################
# Main
#########################################
echo "($METHOD) Searching for entries to delete."

ENTRIES=`ldapsearch -H "ldap://localhost:389" -D "cn=admin,dc=onlab,dc=msc,dc=bme,dc=hu" \
	-w "rootroot" -b $BASE_DN -s children \
	| grep -i "dn" | awk '{split($0,UID," "); print UID[2]}'`

echo "Search finished, found entries:"

if [[ ! -z "${ENTRIES}" ]]
then
	echo $ENTRIES
	METHOD=$((METHOD+1))
	echo "($METHOD) Deleting found entries."
	ldapdelete -H "ldap://localhost:389" -D "cn=admin,dc=onlab,dc=msc,dc=bme,dc=hu" \
		-c -v -w "rootroot" $ENTRIES
	echo "Done: Entries has been deleted!"
else
	echo "There are no entries to delete!"
fi

METHOD=$((METHOD+1)) 
echo "($METHOD) Deleting uid attributes from: $UID_DN"

ldapmodify -H "ldap://localhost:389" -D "cn=admin,dc=onlab,dc=msc,dc=bme,dc=hu" \
	-w "rootroot" -v -f $PWD/src/main/resources/scripts/ldapmodify.ldif

echo "DONE, exiting..."
exit 0;