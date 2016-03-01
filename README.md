# Payment Tracker

Program that keeps a record of payments. Each payment includes a currency and an amount. 
The program should output a list of all the currency and amounts to the console once per minute. The input can be typed into the command line, and optionally also be loaded from a file when starting up.
The user can then enter more lines into the console by typing a currency and amount and pressing enter. Once per minute, the output showing the net amounts of each currency should be displayed. If the net amount is 0, that currency should not be displayed. 
When the user types "quit", the program should exit.
Currency code may be any uppercase 3 letter code.
When user enters invalid input error message is displayed and program is still running.

### Build application with maven
```
mvn package
```
### Running application
```
java -jar target/payment-tracker-1.0-SNAPSHOT-jar-with-dependencies.jar
```

##### Sample input
```
USD 1000
HKD 100
USD -100
RMB 2000
HKD 200
```
##### Sample output
```
USD 900
RMB 2000
HKD 300
```
