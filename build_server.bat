@echo off
javac -d bin -sourcepath src src/com/znow/attendance_mng_system/server/ServerMain.java
java -cp bin com.znow.attendance_mng_system.server.ServerMain
pause