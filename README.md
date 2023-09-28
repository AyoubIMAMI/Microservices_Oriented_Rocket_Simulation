# To MarsY and Beyond
![](/ressource/marsy-logo.png)
## Team A

---
### Authors
- [Antoine Buquet](https://github.com/antoinebqt)
- [Benoit Gaudet](https://github.com/BenoitGAUDET38)
- [Ayoub IMAMI](https://github.com/AyoubIMAMI)
- [Mourad Karrakchou](https://github.com/MouradKarrakchou)
---
## Requirements
- Maven 4.0.0
- Spring 3.1.3
- Java 17
- JDK 19
---
## Demonstration

#### Commands to execute the project
```
$ git clone git@github.com:pns-si5-soa/marsy-23-24-team-a.git
$ cd marsy-23-24-team-a
$ git checkout tags/delivery-final
$ ./prepare.sh
$ ./run.sh
```

#### Scenarios
- Mission successful
- Mission could not start
- Mission failed: rocket destroyed
---
## Services descriptions

#### Departments
- *weather-service*: check the weather status
  - send a GO or NO GO answer to the *mission-service*

  <br>
- *mission-service*: overlook the whole mission
  - perform a poll to *weather-service* and *rocket-service*
  - send a GO or NO GO answer, after the poll, to the *rocket-department-service*
  - destroy the flight hardware in case of a severe anomaly

  <br>
- *rocket-department-service*: overlooking the rocket and its
  systems
  - send a GO NO GO answer to the *mission-service*
  - launch the rocket after receiving the GO answer from the *mission-service*
  - ask the *telemetry-service* for warnings
  - stage the rocket mid-flight
  - slow down the rocket to go through Max Q

  <br>
- *telemetry-service*: handling the data
  - receive, store and monitor data
  - warn the *rocket-department-service* and the *payload-service*

  <br>
- *payload-service*: responsible for the payload orbit insertion
  - ask the *telemetry-service* for warnings
  - ask the *telemetry-service* for payload data
  - deliver the payload on the right orbit or trajectory
  
  <br>
- *executive-service*: strategically directing and driving the
  company’s business and objectives
  - ask the telemetry for warnings

#### Hardware
- *rocket-hardware-mock-service*: representation of the rocket
- *payload-hardware-mock-service*: representation of the payload

#### Database
- *database-telemetry*: rocket database
- *database-payload*: payload database

#### Used ports
|           Services            | Port |
|:-----------------------------:|:----:|
|        weather-service        | 3000 |
|        mission-service        | 3001 |
|   rocket-department-service   | 3002 |
|       telemetry-service       | 3003 |
|        payload-service        | 3004 |
| rocket-hardware-mock-service  | 3005 |
|      database-telemetry       | 3006 |
|       database-payload        | 3007 |
|       executive-service       | 3008 |
| payload-hardware-mock-service | 3009 |

## Team members implication
|      Members      |  Points  |
|:-----------------:|:--------:|
|  Antoine BUQUET   |   100    |
|   Benoit GAUDET   |   100    |
|    Ayoub IMAMI    |   100    |
| Mourad KARRAKCHOU |   100    |