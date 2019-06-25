For building use maven with follow command
mvn clean install

For running application use

java -jar .\log-parser.jar --accesslog=access.log --startDate=2017-01-01.15:00:00 --duration=hourly --threshold=200