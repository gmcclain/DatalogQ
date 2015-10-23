java -classpath .\bin\jflex-1.4.3\lib\JFlex.jar; JFlex.Main dq.flex
java -jar .\bin\java-cup-11a.jar dq.cup
move *.java src
echo "Don't forget to add 'package src;' in first line of each produced java file or it won't compile"
pause