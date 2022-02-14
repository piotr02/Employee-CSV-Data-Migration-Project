# Employee CSV Date Migration Project

#### Developed by Aiden Sykes, Antony Ademefun, Monali Dhamale and Piotr Sulek




### Table Of Contents

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#tour">Project Tour</a></li>
    <li><a href="#development">Development</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

### About the Project

This project has been developed as a team of four during the span of a week. We split our workload and co-ordinated our meetings by the use of agile methodology.




The project's functionality includes being able to read, validate and write CSV Files to databases.


<p align="right">(<a href="#top">back to top</a>)</p>

### Built With

* [IntelliJ Community Edition](https://www.jetbrains.com/idea/)
* [GitHub Desktop](https://desktop.github.com)
* [junit-jupiter:5.8.2](https://junit.org/junit5/docs/current/user-guide/)
* MySQL

### Dependencies

* junit.jupiter Version 5.8.2
* apache.logging.log4j Version 2.17.1


<p align="right">(<a href="#top">back to top</a>)</p>


## Getting Started

Simply run this project using IntelliJ Community Edition. Make sure to install the dependencies and implementations listead above.

### Installation

1. Clone the repository below.
   ```sh
   git clone https://github.com/piotr02/Employee-CSV-Data-Migration-Project.git
   ```
2. Either import or run the downloaded file onto IntelliJ Community Edition.


<p align="right">(<a href="#top">back to top</a>)</p>


### Project Tour

* Phase 1 : We Created Employee-CSV-Data-Migration-Project and write code to read data from an Employee CSV file.Corrupt or duplicated data and records with missing fields,              added to a separate collection for analysis i.e TestCorruptedData.csv,TestDuplicatedData.csv files and TestMissingValuesData.csv file and testes the files with the            help JUnit.
* Duplicate Data
             ![DuplicateData](DuplicateData.png)
 * Missing Values
              ![MissingValues](MissingValues.png)
 * CorruptData
               ![CorruptData](CorruptData.png)
 * Duplicate Data
                ![UniqueData](DuplicateData.png)
             
* Phase 2 : Used MySQL Database and create a connection and transefering dates from JAVA to SQL. 
* Create database  from JAVA to Mysql
![Main Method](CreateDatabase.png)
* Create Database
              ![Create Database](CreateDatabase.png)
              
* comparing the execution time 
![Thread Execution Time](Threadexecution.png) 
![Thread Execution Time](graph.png)
* Phase 3 : Implementing multiple threads to write the data and comparing the execution time. 
* Create Database From Java To SQL
 ![Create Database](CreateDatabase.png)
* Phase 4 : Modified code to make use of functional programming concepts – lambdas and streams.

### JUnit Testing

JUnit testing allows us to test the model methods of the project without having to participate in lengthy manual testing.

Effecient use of JUnit Tests will allow us to maintain our program to ensure that it works without any error.

We can find errors by asserting  results and comparing it to the actual result|
![](Screenshot_1.png)

Looking at our test coverage, the average % of code coverage is being dragged down by classes that are deprecated. The classes which see most use within the main methods of our project have exceedingly high coverage


### Future Developments

Outside of the time given for this project, we would like to implement a more user-friendly design. This shall allow users to select what outputs they desire such as the amount of corrupted data rather than getting all data.


## Technologies:

Project is created with:
* IntelliJ IDEA 2021.3.1
* junit-jupiter:5.8.2
* apache.logging.log4j:2.17.1
* Java for IntelliJ
* GitHub Desktop
* GitHub
* MySQL

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Thank you to the Training Staff at Sparta Global for teaching and assisting me during the development of my project.

* [Nishant Mandal]()
* [Paula Kendra]()
* [Neil Weightman]()

<p align="right">(<a href="#top">back to top</a>)</p>