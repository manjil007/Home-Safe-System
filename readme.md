# Home Safe
This project is a group project assigned by the 
UNM CS 460 - Software Engineering class. The project 
repository represents the software of our Home Safe, and it's
simulation.

## Team
- Ester Aguilera: Team Manager, Frontend Developer
- Raju Nayak: Document Manager, Backend Developer
- Emely Seheon: Frontend Lead
- Jacob Graves: Frontend Developer
- Majil Man Pradhan: Frontend Developer
- Michael Millar: Software Architect, Backend Lead

**Team Roles**

- Ester Aguilera: 
1. West panel buttons
2. Welcome Screen
3. Manage Pin (Admin)
4. Entry Screen (collaboration)
5. Log Screen (Collaboration)

- Raju Nayak
1. Humidity sensor control
2. Temperature sensor control
3. Sensor simulation
4. Other sensors (collaboration)
5. Controller (collaboration)

- Emely Seheon
1. Gold screen
2. Locked and unlocked safe screens
3. GUIUtils and MainGUI
4. Keyboard enter button functionality 
5. SwitchPanel enter button functionality

- Jacob Graves
1. Keyboard
2. Log Screen (collaboration)
3. Testing
4. Entry Screen (collaboration)
5. SwitchPanel (collaboration)

- Manjil Man Pradhan
1. Pop-ups
2. SwitchPanel (collaboration)
3. Log Screen (collaboration)
4. Entry Screen (collaboration)
5. Testing

- Michael Miller
1. SQLite Database
2. Maven setup
3. DAO Setup
4. Controller (collaboration)
5. Events

## Project Usage
**Safe Handle**

- Opens the safe only if the safe is unlocked.

**Safe Keyhole**

- Opens the safe with a key, works if  the safe is locked or unlocked

**Display Screen**

- Allows users to login, manage pins, and views logs.

**Add a User**

- Only works if you are an admin
1. Login as an admin user
2. On the welcome screen, click on "Manage Pin"
3. Click on "ADD"
4. Enter the new user's username and password.

**Modify User**

- As an Admin:
1. Login as an admin user
2. On the welcome screen, click on "Manage PIN"
3. Click on the user you would like to modify
4. Click on "MODIFY"
5. Enter the old pin of the user that you are trying to modify
6. Enter the new pin of the user that you are trying to modify
7. Change the admin status of the user if you wish

- As a non-admin:
1. Login as a non-admin user
2. On the welcome screen, click on "Manage PIN"
3. Enter the old pin of the current user
4. Enter the new pin of the current user

**Delete User**

- Only works if you are an admin
1. Login as an admin user
2. On the welcome screen, click on "Manage PIN"
3. Click on the user you would like to delete
4. Click on "DELETE"
5. Enter the pin of the current user to confirm the deletion

**View Logs**

- An admin user can view all users' logs, a non-admin user can only view their own logs.
1. Login to the safe
2. Click on "View Logs"

**Navigating the UI**

- Pushing the "<--" button on the top left of the screen takes you to the screen before the current screen
Pushing the "exit" button on the top left of the screen takes you to the view of the outside of the safe.

### IDE Usage
This project is runnable within the IntelliJ IDE without 
additional configuration. Simply run the main class:
`src/main/java/homesafe/HomeSafe.java`.

### Maven
This project was built using Apache Maven.

**Remove Build Artifacts:** `mvn clean`

**Install dependencies:** `mvn install`

**Run tests:** `mvn test`

**Compile jar:** `mvn package`

### Known Bugs
- Temprature, humidity, and time readings are not current or connected to the simulation class.
- The GUI looks different according to operating systems, this is because the GUI is made with Java Swing, which has a different Look and Feel for different operating systems.
  - In our opinion, the GUI looks best on a Windows operating system.