# hegira-api
Hegira 4Clouds REST API  

## Installation

##### Configuration

After having downloaded the source code two new files should be created under the folder src/main/webapp/WEB-INF/classes:
* queue.properties
* zookeeper.properties

###### queue.properties
Contains the properties to configure RabbitMQ. The only needed option to specify, so far, is the ip address of the broker:

```
host=<ip_address>
```

###### zookeeper.properties
Contains the properties to configure ZooKeeper. The only needed option to specify, so far, is the ip address and the port of one of the ZooKeeper installation:

```
connectString=<ip_address>:<port>
```

##### Build
The project is Maven compliant, hence by executing the command ```mvn clean package``` the proper packages will be created.

##### Deploy
Maven generates a war archive which should be copied in ```webapps``` Tomcat folder.
After starting Tomcat hegira-api component should be available at: ```http://<tomcat-address>:<port>/<war_package_name>```

## Usage
Rest API are described at: [http://deib-polimi.github.io/hegira-api/](http://deib-polimi.github.io/hegira-api/)
