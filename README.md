# Event Planner

#### _An event planning application, 9/9/2016_

#### By _**Timothy Herold**_

## Description

_This application is designed to ask the user a series of questions. Once the user specifies the size and scope of their event, the application will calculate that event's total cost. The total cost is determined by an arbitrary formula._

## Setup/Installation Requirements

_Download Java and build source code from [GitHub](https://github.com/therold/event-planner)._
* _$ git clone https://github.com/therold/event-planner.git_
* _$ cd event-planner_
* _$ gradle compileJava_
* _$ cd build/classes/main_
* _$ java App_

## Specification
* The program should store the number of people attending the event.
  * **Example Input**: 6
  * **Example Output**: [6 stored]
* The program should display the number of people attending the event.
  * **Example Input**: [request number of people attending]
  * **Example Output**: 6
* The program should store the type of food for the event.
  * **Example Input**: [select full dinner]
  * **Example Output**: [full bar stored]
* The program should display the number of people attending the event.
  * **Example Input**: [request type of food for event]
  * **Example Output**: [full dinner displayed]
* The program should store other services requested for the event.
  * **Example Input**: [select full bar]
  * **Example Output**: [full bar stored]
* The program should display the other services requested for the event.
  * **Example Input**: [request other services for event]
  * **Example Output**: [full bar displayed]
* The program should calculate the total cost of the event.
  * **Example Input**: [request total cost]
  * **Example Output**: $540.00

## Technologies Used

* _Java_
* _JUnit_
* _Gradle_

### License

*GPL*

Copyright (c) 2016 **_Timothy Herold_**
