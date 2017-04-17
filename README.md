## Setup
From the [Spring Cloud Data low](http://cloud.spring.io/spring-cloud-dataflow/) website.

### Downloads
```bash
wget http://repo.spring.io/release/org/springframework/cloud/spring-cloud-dataflow-server-local/1.1.4.RELEASE/spring-cloud-dataflow-server-local-1.1.4.RELEASE.jar
wget http://repo.spring.io/release/org/springframework/cloud/spring-cloud-dataflow-shell/1.1.4.RELEASE/spring-cloud-dataflow-shell-1.1.4.RELEASE.jar
```

### Brew Setup
Installs the project dependencies
```bash
brew bundle
```

### Brew startup
```bash
brew start services zookeeper
brew start services kafka
```

### Start Local Server
```bash
java -jar spring-cloud-dataflow-server-local-1.1.3.RELEASE.jar
```

### Start CLI shell
```bash
java -jar spring-cloud-dataflow-shell-1.1.3.RELEASE.jar
```

## Compile
From the project directory
```bash
mvn clean install -T2C
```

### Install apps
```bash
app import --uri http://bit.ly/Avogadro-SR1-stream-applications-kafka-10-maven
app register --name temperature-filter --type processor --uri file://Users/karun/.m2/repository/me/karun/spikes/temperature-filter/0.0.1-SNAPSHOT/temperature-filter-0.0.1-SNAPSHOT.jar
app register --name temperature-processor --type processor --uri file://Users/karun/.m2/repository/me/karun/spikes/temperature-processor/0.0.1-SNAPSHOT/temperature-processor-0.0.1-SNAPSHOT.jar
```

### Create Stream
```bash
stream create --name temperature-converter --definition "read: file --directory=/Users/karun/Downloads/inputs --filename-pattern=*.txt --mode=lines | filter: temperature-filter | processor: temperature-processor | write: log"
```

### Deploy Stream
```bash
stream deploy --name temperature-converter
```