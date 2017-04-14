# GCDSoapService
Soap WebService for GCD application reading value from JMS Queue

This SOAP service expose the following method as operations:

public int gcd();

which returns the greatest common divisor (GCD) of the two integers at the head of the queue. These two elements will subsequently be discarded from the queue and the head replaced by the next two in line.

public List<Integer> gcdList();

which returns a list of all the computed greatest common divisors from a database.

public int gcdSum();

which returns the sum of all computed greatest common divisors from a database.
