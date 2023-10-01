
# Mars Y, to the space and beyond

![](/ressource/marsy-logo.png)
## Team A
### Authors
- [Antoine BUQUET](https://github.com/antoinebqt)
- [Benoit GAUDET](https://github.com/BenoitGAUDET38)
- [Ayoub IMAMI](https://github.com/AyoubIMAMI)
- [Mourad KARRAKCHOU](https://github.com/MouradKarrakchou)
---
## Requirements
- Docker
---
## This project uses
- Maven 4.0.0
- Spring 3.1.3
- Java 17
- JDK 19
---
## Demonstration

#### Commands to execute the project on Linux
```
git clone git@github.com:pns-si5-al-course/soa-marsy-marsy-23-24-team-a.git
cd soa-marsy-marsy-23-24-team-a
checkout tags/delivery-final
./prepare.sh
./run.sh
```

#### Scenarios
- Mission successful
  - Poll to weather and rocket department services
  - Rocket launch
  - Stage splitting and returning to Earth
  - Speed variation through Max Q
  - Payload dropped and monitored


- Mission failed
  - Rocket damaged by sabotage
  - Rocket destroyed to avoid potential ground damage

The two scenarios will automatically be run in sequence using the run.sh script

---
## Services descriptions

#### Services
- *weather-service*: make sure the weather is safe across sites
  - Reply GO or NO-GO to the *mission-service* when it asks for the weather status before launching the rocket


- *mission-service*: overlook the whole mission
  - Ask *weather-service* and *rocket-department-service* for a GO or NO-GO to launch the rocket
  - Send a GO or NO-GO to *rocket-department-service* based on responses from *weather-service* and *rocket-department-service*
  - Inform *payload-service* and *executive-service* when the mission begins
  - Request *telemetry-service* to be notified if the rocket has a severe anomaly
  - Gives the order to destroy the rocket in case of severe anomaly


- *rocket-department-service*: manage the rocket and its systems
  - Reply GO or NO-GO to the *mission-service* when it asks for the rocket status before the launch
  - Launch the rocket after receiving the GO answer from the *mission-service*
  - Request the *telemetry-service* to be notified of certain events during the mission
  - Stage the rocket mid-flight when there is no more fuel in the first stage
  - Slow down the rocket when passing through the Max Q zone to avoid damage to the hardware and payload


- *telemetry-service*: handle the data communications between the flight hardware and the ground
	- Receive event notification requests from other services
  - Receive, monitor and save data from *rocket-hardware-mock-service*, *stage-hardware-mock-service* and *payload-hardware-mock-service*
  - Informs *mission-service*, *executive-service*, *payload-service* and *rocket-department-service* of certain events


- *payload-service*: responsible for the customer’s cargo and the efficient trajectory or orbit insertion
  - Ask the *telemetry-service* to be informed when the rocket reaches the correct height to drop the payload
  - Drop the payload into the correct orbit
  - Receive and save data from *payload-hardware-mock-service* via *telemetry-service*
  - Verify that the altitude of the payload was still correct after it was dropped
  

- *executive-service*: direct and drive strategically the company’s business and objectives
  - Request the *telemetry service* to be informed of the successful return of the rocket's first stage to Earth.

#### Mocks
- *rocket-hardware-mock-service*: fictional representation of the rocket hardware
	- Start sending data to the *telemetry service* after the start of the mission
	- Send data on the altitude, the speed, the fuel of each stage, the status, and acceleration every half second


- *stage-hardware-mock-service*: fictional representation of the stages hardware
	- Start sending data of a specific stage to the *telemetry service* after the stage has been detached
	- Send data on the altitude, the speed, the fuel, and acceleration every second


- *payload-hardware-mock-service*: fictional representation of the payload hardware
	- Start sending data to the *telemetry-service* after being dropped
	- Send data on the current position of the payload to *telemetry-service* every second


#### Database
- *database-telemetry*:
	- Save the rocket data received by the *rocket-hardware-mock-service*
	- Save the stage data received by the *stage-hardware-mock-service*
	- Save notification requests received by other services


- *database-payload*:
	- Save the payload data received by the *payload-hardware-mock-service*


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
|  stage-hardware-mock-service  | 3010 |

---
## Team members implication
|      Members      |  Points  |
|:-----------------:|:--------:|
|  Antoine BUQUET   |   100    |
|   Benoit GAUDET   |   100    |
|    Ayoub IMAMI    |   100    |
| Mourad KARRAKCHOU |   100    |
