# AP_Ozlympic
Advanced Programming Assignment 2 - Ozlympic game

It is a sport game called Ozlympic Game, which a player to select one of 3 different types of game: swimming, cycling, and running to play. After the game started, this program will display the process of each game, and it will show the result immediately as well.

Main class: Ozlympic.class
Database: SQLite, with 2 tables - participant and gameresult.
JUnit: tests

The main menu system is provided in main class - Ozlympic, which user could jsut simply clicking the buttons to execute functions. The main procedure of this application is:
1. User clicks the create a new game button, then select the game type, 5~8 athletes and 1 referee. 
2. After these choices are made, click the run game button, then the animation of competition will start.
3. In the end of the game, the result will display right away.
4. After the game finished, user could click the button back to the menu and check the history result or athlete points



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
