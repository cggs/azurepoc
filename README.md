# java-spring-2.5-backend
This is a standard spring java back-end project that can be deployed straight to an existing Azure App Service.
When you create the app service it is important you create with  

To compile
$mvn compile

To run the code
$mvn spring-boot:run 

To test the code
http://localhost:8080/swagger-ui.html

To deploy to Azure
$mvn azure-webapp:deploy

To test if your code is working on Azure use

https://{your app name}.azure.websistes.net/swagger-ui.html


