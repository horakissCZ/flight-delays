# flight-delays

### Usage

Run:
1. Clone repository
2. ```mvn clean package``` 
3. ```java -cp target/flight-delays-1.1-SNAPSHOT.jar cz.horak.app.App```

Change working directory:
* ```java -DworkingDir=/tmp -cp target/flight-delays-1.1-SNAPSHOT.jar cz.horak.app.App```

Change origin airport:
* ```java -Dairport=SHO -cp target/flight-delays-1.1-SNAPSHOT.jar cz.horak.app.App```

Change url of sources:
* ```java -Durl=http://stat-computing.org/dataexpo/2009/1989.csv.bz2 -cp target/flight-delays-1.1-SNAPSHOT.jar cz.horak.app.App```
