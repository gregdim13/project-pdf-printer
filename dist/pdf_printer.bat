@ECHO OFF
chcp 1253 
cd \
java -Dfile.encoding=Windows-1253 -jar C:\Users\gregd\eclipse-workspace\pdf_printer\dist\pdf_printer.jar
::msg * "The pdf has been printed!!"
PAUSE



::java -jar C:\Users\gregd\eclipse-workspace\pdf_printer\dist\pdf_printer.jar
::java -Dfile.encoding=Windows-1253 -jar C:\Users\gregd\eclipse-workspace\pdf_printer\dist\pdf_printer.jar
::java -Dfile.encoding=UTF-8 -jar C:\Users\gregd\eclipse-workspace\pdf_printer\dist\pdf_printer.jar
::start java -jar C:\Users\kondi\OneDrive\Desktop\pdf_printer.jar
::java -jar C:\Users\gregd\eclipse-workspace\pdf_printer\dist\pdf_printer.jar


