# LabLog Application Overview

LabLog is an application that aids the management of school/university laboratories via tracking experiments conducted, the lab's inventory, and tasks that needs to be completed by the personnel of the lab. The target user for this app is the lab personnel.

> "No amount of experimentation can ever prove me right; a single experiment can prove me wrong." - Albert Einstein

## Database and port

The port is default (8080) and when the application is run, the database "lablogdb" is automatically created. Here is the application properties file code:

`spring.datasource.url = jdbc:mariadb://localhost:3306/lablogdb?createDatabaseIfNotExist=true`

`spring.datasource.username = root`

`spring.datasource.password = root`

`spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MariaDBDialect`

`spring.datasource.driver-class-name = org.mariadb.jdbc.Driver`

`spring.jpa.hibernate.ddl-auto = update`

`spring.jpa.show-sql = true`

`server.error.whitelabel.enabled = false`

`logging.file.path=logging_file`

## Login & Register

There are 2 roles that can access this application: **ADMIN** and **PERSONNEL**. The difference is only the ADMIN can register an admin or a personnel.
The passwords are, of course, crypted with BCryptPasswordEncoder.

Since only the ADMIN can register new personnel and admin, add an admin to the personnel table via SQL Query to be able to enter the application.
You can use [this website](https://www.browserling.com/tools/bcrypt) to crypt the password of your choice and insert the value into the personnel table.

Or you can just use this SQL Query and save yourself some time.
`INSERT INTO personnel(personnel.personnel_name, personnel.personnel_password, personnel.personnel_role) VALUES ('Awesome Person', '$2a$10$m09GD9HnQTFLklFpGRVHH.24R2PGzeKOh4SvLK9ocvDNVCJt2bohW', 'ADMIN');`

Username: Awesome Person

Password: person1234

## Poluationg the database

You should be populating student, equipment, and task tables before you populate the experiment
table.

Since the student controller has a @RestController annotation, you can utilize PostMan to populate it. As for the rest of the tables, you can use the application itself or SQL.

## Home Page

Just a simple home page that shows certain procedures that needs to be followed by the lab personnel. You change the theme of the website to dark mode by clicking at the moon icon on the navbar.

## Experiment Page

Click **Initialize Experiment** and specify the start date of the experiment to initialize the experiment. Add as many students and equipments to the experiment as you want. You will have to specify student's name, equipment's id, and the quantity of the equipment is to be used while adding these. You can set the supervisor only the first time. When the experiment is over, you can end the experiment by specifying the end date and damage cost (if there are any equipments broken by the students). If the experiment info you have entered is incorrect, you can simply delete it.

## Inventory Page
Add, update, delete, and "search by name" equipments. However, you cannot delete an equipment from the inventory if it is previously used in an experiment.

## Task Page
Add, update, delete, and search by "due date" tasks.


## What I have learned
About a year ago, I could barely write a python script. Now thanks to TEKSystems and Per Scholas, I am able to create web applications from scratch both on the front and back end. I was previously struggling to understand thymeleaf and spring security, but with the educational pressure that I was exposed to by this challenging project, I was able to utilize a myriad of resources to deepen my understanding of the concepts related to the project.

## Additional Features
The style could be much better. Moreover, while adding the equipments to the experiment, I can have the input field as a dropdown list instead of a text field. That way, the users would not need to check the inventory every time they need to add an equipment.
