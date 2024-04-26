Added the guide to get the code from a Git repository and run a Java Spring Boot application on their local machine using IntelliJ IDEA, Please follow the below steps:

# Clone the Git Repository

1. Clone the Repository:

- Open your terminal or command prompt.
- Navigate to the directory where you want to clone the repository:

### `cd /path/to/destination/directory`

2. Clone the Git repository using git clone:

### `git clone https://github.com/sivasankarp/Retrospective`

Open the Project in IntelliJ IDEA

1. Open IntelliJ IDEA:
   - Launch IntelliJ IDEA on your local machine.
2. Import the Project:
   - In IntelliJ IDEA, click on File > Open.
   - Navigate to the directory where you cloned the repository.
   - Select the project directory and click Open.
3. Configure Project SDK:
   - If prompted, configure the project SDK (Java Development Kit - JDK) in IntelliJ IDEA.
   - Go to File > Project Structure.
   - Under Project SDK, select your installed JDK (JDK 17).

# Run the Spring Boot Application

1. Run the Application:
   - Locate the main class of your Spring Boot application (usually annotated with @SpringBootApplication).
   - Right-click on the main class file.
   - Select Run <MainClassName> to start the Spring Boot application.
2. Alternatively, use the Run Configuration:
   - Click on Run in the menu bar.
   - Select Edit Configurations....
   - Click on + to add a new configuration (e.g., Spring Boot).
   - Specify the main class and other configuration settings.
   - Click OK to save the configuration, then click Run to start the application.

# Access the Application

1. Open a Web Browser:
   - Once the Spring Boot application is running, open a web browser.
2. Navigate to the Application URL:
   - Enter the URL of your Spring Boot application in the web browser.
   - By default, the application may run on http://localhost:6666.
