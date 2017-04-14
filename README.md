# GCDSoapService
Soap WebService for GCD application reading value from JMS Queue

This SOAP service expose the following method as operations:

public int gcd();

which returns the greatest common divisor (GCD) of the two integers at the head of the queue. These two elements will subsequently be discarded from the queue and the head replaced by the next two in line.

public List<Integer> gcdList();

which returns a list of all the computed greatest common divisors from a database.

public int gcdSum();

which returns the sum of all computed greatest common divisors from a database.

Pre requisite

1) Connection details such as DB Url, username, password should be updated in Application-Context.xml under dataSource

2) Create a table in MySql Database with following command.

CREATE TABLE  sakila.GCD_SOAP_TABLE 
	(FIRST_OPERAND INT(100)  NOT NULL,
	SECOND_OPERAND INT(100) NOT NULL,
    GCD_RESULT INT(100) NOT NULL,
    INSERT_DATE datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
	);
  
3) ActiveMQ details needs to be updated in Application-Context.xml e.g brokerURL - tcp://localhost:61616
4) ActiveMQ should be up and running before making call to GCDSoapService operations


