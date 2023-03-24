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
- Start the producer by running the following command: `java -jar producer/target/producer.jar`.
- Start the consumer by running the following command: `java -jar consumer/target/consumer.jar`.
- Start the stream by running the following command: `java -jar stream/target/stream.jar`.   

<br>

The flow of data from data source to data sink should be as follows:
- The producer will send messages to Kafka, which will be received by the stream. 
- The stream will process the messages and send the output to kafka. 
- The consumer will receive the messages processed by stream and write them to the console.


## Configuration

The Kafka broker has default configurations as set in the how to, and the Kafka topics are configured in the KafkaProducerConfig class of the Producer module.

## Contributing
Contributions to this project are welcome.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE.md) file for details.