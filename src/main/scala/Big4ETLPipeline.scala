import org.apache.spark.sql.SparkSession
import java.util.Properties

object Big4ETLPipeline {

  def main(args: Array[String]): Unit = {

    // Step 1: Create SparkSession
    val spark = SparkSession.builder()
      .appName("Big4 Financial Risk Compliance ETL")
      .master("local[*]") // Use all cores locally
      .getOrCreate()

    // Step 2: Read CSV
    val inputFilePath = "big4_financial_risk_compliance.csv"
    val rawDF = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(inputFilePath)

    println("=== Raw Data Preview ===")
    rawDF.show(5)

    // Step 3: Transform data with Spark SQL
    rawDF.createOrReplaceTempView("financial_data")

    val transformedDF = spark.sql(
      """
        |SELECT
        |  Firm_Name,
        |  Industry_Affected,
        |  ROUND(AVG(Audit_Effectiveness_Score), 2) AS Avg_Audit_Effectiveness_Score,
        |  ROUND(AVG(Client_Satisfaction_Score), 2) AS Avg_Client_Satisfaction_Score
        |FROM financial_data
        |WHERE Year >= 2021
        |  AND AI_Used_for_Auditing = 'Yes'
        |GROUP BY Firm_Name, Industry_Affected
        |ORDER BY Avg_Audit_Effectiveness_Score DESC
        |""".stripMargin)

    println("=== Transformed Data Preview ===")
    transformedDF.show()

    // Step 4: Write to PostgreSQL
    val jdbcUrl = "jdbc:postgresql://localhost:5432/ETL"
    val connectionProperties = new Properties()
    connectionProperties.put("user", "postgres")
    connectionProperties.put("password", "Dut931015*")
    connectionProperties.put("driver", "org.postgresql.Driver")

    transformedDF.write
      .mode("overwrite") // or "append"
      .jdbc(jdbcUrl, "big4_transformed_data", connectionProperties)

    println("Data successfully written to PostgreSQL!")

    // Step 5: Stop Spark
    spark.stop()
  }
}
