@echo off
javac -d bin -sourcepath src src/com/znow/attendance_mng_system/client/ClientMain.java
java -cp bin com.znow.attendance_mng_system.client.ClientMain localhost 2534 0001
pause