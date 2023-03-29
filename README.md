# Spring Kafka Microservice Demo

This repository contains a simple Spring project that demonstrates how to use Kafka to build a data pipeline. The project consists of the following modules:

`producer`: sends messages to Kafka.  
`schema`: defines the schema for the messages sent to Kafka.  
`consumer`: receives messages from Kafka.  
`stream`: processes streams of messages from Kafka.  

## Prerequisites
Before running this demo, ensure that you have the following software installed:

JDK 17 ([link](https://docs.oracle.com/en/java/javase/17/install/installation-jdk-microsoft-windows-platforms.html))  
Maven ([link](https://maven.apache.org/download.cgi))  
Kafka ([link](https://kafka.apache.org/quickstart))  

## Getting Started
To run the demo, follow these steps:

- Clone this repository. [instructions](https://www.perplexity.ai/search?q=how+to+clone+a+repository)  
- Navigate to the root directory of the project. [instructions](https://www.perplexity.ai/search?q=how+to+avigate+to+the+root+directory+of+the+project)   
- Run `mvn clean install` to build the project. [instructions](https://www.perplexity.ai/search?q=hot+to+mvn+clean+install+to+build+the+project)  
- Start Kafka. [instructions](https://www.perplexity.ai/search?q=how+to+start+a+local+kafka+cluster)
- In a new terminal (project root directory), start the producer by running the following command: `java -jar producer/target/producer-1.0-SNAPSHOT.jar`.
- In a new terminal (project root directory), start the stream by running the following command: `java -jar stream/target/stream-1.0-SNAPSHOT.jar`.
- In a new terminal (project root directory), start the consumer by running the following command: `java -jar consumer/target/consumer-1.0-SNAPSHOT.jar`.
- In a new terminal (project root directory), run the following command `curl http://localhost:8080/api/v1/producer`
- Observe the terminals for producer and consumer, notice how only the customer IDs with response times greater than 30ms are present in the consumer terminal

<br>

The flow of data from data source to data sink should be as follows:
- Upon receiving a GET request, the producer will send 100K messages to Kafka, which will be received by the stream module. 
- The stream module will process the messages, filtering out all messages with response times less than 30ms and send the output to a new topic in kafka. 
- The consumer will receive the messages processed by stream module and write them to the console.


## Configuration

The following information is useful if you would like to run these applications and test them manually using the kafka cli 

- Kafka
  - port: 9092
- Producer
  - port: 8080
  - endpoint: http://localhost:8080/api/v1/producer
  - topic: YouBuyyClickStreamData
- Stream
  - port: 8081
  - listening-topic: YouBuyyClickStreamData
  - publishing-topic: CleansedYouBuyyClickStreamData
- Consumer
  - port: 8082
  - listening-topic: CleansedYouBuyyClickStreamData

## Contributing
Contributions to this project are welcome.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE.md) file for details.