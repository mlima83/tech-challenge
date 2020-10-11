# Healthcare

This API is responsible for healthcare institution registration as well as the exams ingest.


#### Technologies used

 * JDK 1.8
 * Apache maven 3.6.2
 * Springboot 2.1.6.RELEASE
 * Springfox Swagger 2 2.9.2
 * Junit 4.12
 * Mockito 2.23

#### Prerequisites before running the application

 * Have previously installed and configured Git, Java and Maven in the versions mentioned above.
  
 * Clone this repository in your workspace.
 
 * After cloning the project, navigate to the main application directory:
 
$ cd ~/healthcare

 * Run the following command for maven to download the project's dependencies and compile the jar:

$ mvn install

 
#### Running the application with maven

 * To start the application with maven, execute this command:
 
$ mvn spring-boot: run

 * The application will be running on port 3000 and you can easily access it through the swagger2 interface at:
 
[http://localhost:3000/swagger-ui.html] (http://localhost:3000/swagger-ui.html)

 
#### Running the application with Docker
 
 * Navigate to the main application directory:
 
 $ cd ~/healthcare

 * To build the application with docker, execute this command:
 
 $ docker build -t pixeon/healthcare .

 * After success start de applicaton with this command:
 
 $ docker run -p 3000:3000 pixeon/healthcare 

 * The application will be running on port 3000 and you can easily access it through the swagger2 interface at:
 
[http://localhost:3000/swagger-ui.html] (http://localhost:3000/swagger-ui.html)

#### Important endpoints for the test

 * <b>Create and change an institution:</b>
 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; URL: http://localhost:3000/institutions
 	
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Request type: POST 
 	
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Request body to create: {"cnpj": "11111111111111", "name": "11111111111111"} 
 	
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Request body to update: {"id": 0, "cnpj": "11111111111111", "name": "institution name", "version": 0} 
 	
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; NOTE: To change a record it is necessary to send the current version for concurrency control.

 * <b>Create and change an exam:</b>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; URL: http://localhost:3000/exams

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Request type: POST

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Request body to create:
{
  "institutionId": 0,
  "patientAge": 0,
  "patientGender": ["MALE" | "FEMALE"],
  "patientName": "string",
  "physicianCRM": "string",
  "physicianName": "string",
  "procedureName": "string",
  "version": 0
}

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Request body to update:
{
 "id": 0,
  "institutionId": 0,
  "patientAge": 0,
  "patientGender": ["MALE" | "FEMALE"],
  "patientName": "string",
  "physicianCRM": "string",
  "physicianName": "string",
  "procedureName": "string",
  "version": 0
}

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; NOTE: To change a record it is necessary to send the current version for concurrency control.

 * <b>Delete an exam:</b>
 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; URL: http://localhost:3000/exams/{institutionId}/{examId}

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Request type: DELETE

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Request body: Not required
  
 * <b>Get an exam:</b>
 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; URL: http://localhost:3000/exams/{institutionId}/{examId}

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Request type: GET

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Request body: Not required

#### Improvement points

* Friendly messages would be a point of improvement, however this is usually dealt with in the frontend.
* The data models must be normalized, thus extracting the patient, doctor and procedure that make up the exam.
* In order to run more than one application instance, the connection to the bank that is currently using H2 in memory must be changed.

#### References

 * [DDD - https://martinfowler.com/bliki/BoundedContext.html▪(https://martinfowler.com/bliki/BoundedContext.html)
 * [Spring data - https://spring.io/guides/gs/accessing-data-jpa/201(https://spring.io/guides/gs/accessing-data-jpa)
 * [Spring with docker - https://spring.io/guides/gs/spring-boot-dockerÔ(https://spring.io/guides/gs/spring-boot-docker/)
 * Código Limpo: Habilidades Práticas do Agile Software (Robert C. Martin, 2011)