# RestFul-Web-Service
A simple restful web service for App Engine using google cloud sql database which supports GET and POST requests.

![screenshots](https://user-images.githubusercontent.com/28942363/39026201-56944b00-4469-11e8-8cf1-06806b875725.png)

# Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
 
 ## Prerequisites
  Have an account on google app engine to host the project and set up a google cloud plugin on the IDE.
  
 ## Set up
 - Download the project using git https://github.com/anishshanbhag/RestFul-Web-Service.git
 - Create an account here https://cloud.google.com/appengine/
 - Create a project with an Instance ID.
 - Create SQL instance on Goodle Cloud Sql Database.
 
 ## Deploy
 Using Terminal
 - cd [your project directory]/project
 - gcloud config set project YOUR_PROJECT_ID
   mvn appengine:deploy
 - gcloud app browse (to view the project)
 
 Using plugin in IDE
 - Right click on project and select deploy to app engine standards.
 - Select the app engine project with instance Id using which you want to deploy.
 
 
 
  
