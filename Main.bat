@echo off
java -Xms1036m -Xmx1536m -XX:NewSize=32m -XX:MaxPermSize=128m -XX:+UseConcMarkSweepGC -XX:+ExplicitGCInvokesConcurrent -XX:+AggressiveOpts -cp bin;data/libs/*;data/libs/slf4j/*; org.areillan.Main
pause