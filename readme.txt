How to run this program:


- Recommend using Eclipse IDE to run this program

   if you can not compile these java file, please use Eclipse IDE or others to import this project to run


- in Windows command prompt please follow these steps:

1. go to AP_Ozlympic current folder

   cd ..\AP_Ozlympic\

2. set the path of java compiler if it is not configured yet

   set path=%path%;C:\Program Files\Java\jdk1.8.0_131\bin

3. compile all java files

   javac -cp src; src\main\*.java -Xlint

   javac -cp src; src\main\views\*.java

   javac -cp src; src\exceptions\*.java

4. run the Ozlympic

   java -classpath .;src;lib\sqlite-jdbc-3.16.1.jar; main.Ozlympic


- in Linux environment please follow these steps:

1. go to AP_Ozlympic current folder

   cd ../AP_Ozlympic/

2. set the path of java compiler if it is not configured yet

   export PATH=${PATH}: your java bin path;

3. compile all java files

   javac -cp src src/main/*.java -Xlint

   javac -cp src src/main/views/*.java

   javac -cp src src/exceptions/*.java

4. run the Ozlympic

   java -classpath .:src:lib\sqlite-jdbc-3.16.1.jar main.Ozlympic