Project Name: Big4ETLPipeline

Project overview on Pipeline:
- This a complete ETL pipeline using Scala and Apache Spark which is integrated with PostgreSQL for storage.
- Extracts financial data from a CSV dataset obtained from Kaggle based on the Big 4 firms
- Transform the data using Spark SQL for aggregation and filtering
- Load the cleansed data into PostgreSQL DB.
- Further querying actioned within PostgreSQL after receiving the cleansed data for analyst.

Technologies used:
- Scala Version 2.12.17 :  Programming Language
- SBT Version 1.10.11 : Scala Build Tool
- Apache Spark Version 3.5.0 : Used for data processing and transformations
- PostgreSQL Version 17 : Databased used to load the cleansed data
- IntelliJ IDEA Community Edition Version 2024.3.5 : Development Environmnet

Setup Instructions:
1. Clone the Repository
In your CLI:
git clone https://github.com/Damianleenair/Big4ETLPipeline.git
cd Big4ETLPipeline

2.Open Project in IntelliJ IDEA Community Edition
- Scala Plugin and SBT plugin needs to be installed
- IntelliJ will automatically detect the build.sbt file

3. Configuring PostgreSQL and Scala code:
- Create a new database and give it a name, password and assign user rights
- Within the scala code input your DATABASE NAME, USERNAME and PASSWORD:
    val jdbcUrl = "jdbc:postgresql://localhost:5432/DATABASE NAME"
    val connectionProperties = new Properties()
    connectionProperties.put("user", "USERNAME")
    connectionProperties.put("password", "PASSWORD*")
    connectionProperties.put("driver", "org.postgresql.Driver")

4. scala code and build.sbt has been commented to provide a clear understanding of the following:
   - Steps taken to intialize SparkSession, Extract data from the csv file, transform the data, load data to PostgreSQL and Stop the Spark Session
   - scala version used
   - version of project
   - external library dependencies
  

  
