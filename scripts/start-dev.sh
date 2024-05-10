JAVA_HOME="/home/ljs/Applications/zulu21.34.19-ca-jdk21.0.3-linux_x64"
SERVER_PORT=8090

cd ..

./gradlew :modoo-report-api:build

fuser -k "$SERVER_PORT"/tcp
nohup /home/ljs/Applications/zulu21.34.19-ca-jdk21.0.3-linux_x64/bin/java -jar -Dserver.port="$SERVER_PORT" -Dspring.profiles.active="$MODOO_REPORT_PROFILE" ./modoo-report-api/build/libs/modoo-report-api-0.0.1-SNAPSHOT.jar &
