# Description

You can run rescalecrawler by compiling the project with the given commands below.

You can specify the starting url, you want the crawler to start crawling from. You can also specify the depth of the
levels you want the crawler to crawl.

# Instruction to Run

mvn clean install 

mvn package

cd target

java -jar crawler-1.0-SNAPSHOT.jar <starting-url: string> <depth: int>

# Example Command
java -jar crawler-1.0-SNAPSHOT.jar https://www.rescale.com/ 2

