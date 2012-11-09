@echo off
set cp=
for %%i in (".\lib\*.jar") do call setenv.bat %%i

start javaw -client -Dsun.java2d.opengl=true -Dcom.sun.management.jmxremote -classpath %cp% ip.IPSearch ipsearch.conf
