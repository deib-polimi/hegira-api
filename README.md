# hegira-api
Hegira 4Clouds REST API  

## Installation

##### Configuration

After having downloaded the source code two new files should be created under the folder src/main/webapp/WEB-INF/classes:
* queue.properties
* zookeeper.properties

###### queue.properties
Contains the properties to configure RabbitMQ. Currently, the only needed property is the ip address of the broker:

```java
host=<ip_address>
```

###### zookeeper.properties
Contains the properties to configure ZooKeeper. Currently, the only needed property is the ip address and the port of one of the ZooKeeper installation:

```java
connectString=<ip_address>:<port>
```

##### Build
The project is Maven compliant, hence by executing the command ```mvn clean package``` the proper packages will be created.

##### Deploy
Maven generates a war archive which should be copied in ```webapps``` Tomcat folder.
After starting Tomcat hegira-api component should be available at: ```http://<tomcat-address>:<port>/<war_package_name>```

## Usage
Rest API are described at: [http://deib-polimi.github.io/hegira-api/](http://deib-polimi.github.io/hegira-api/).

In particular, Rest API for data migration are described [here](http://deib-polimi.github.io/hegira-api/resource_API.html).


hegira-api component also exposes a set of [Rest API on top of Apache ZooKeeper](http://deib-polimi.github.io/hegira-api/resource_ZKservice.html), in order to allow PaaS applications to: 

1. request new unique ids that allow Hegira 4Cloud to synchronize data across two databases. 
2. Check the synchronization status.

The same services are obtainable by IaaS applications by means of [zkWrapper-client](https://github.com/deib-polimi/hegira-zkWrapper-client) library.
