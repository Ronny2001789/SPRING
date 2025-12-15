# 1. Start with a base image that has Java 21 and Maven installed
FROM maven:3.9.6-eclipse-temurin-21

  # 2. Set the working directory inside the container
  #    (This creates a folder inside the Docker "virtual machine")
WORKDIR /bezkoder-app

  # 3. Copy your project files from your computer into the container
  #    (The first '.' is your local folder, the second '.' is the container folder)
COPY . .

  # 4. Build the application
  #    (We skip tests to speed up the build time)
RUN mvn clean install -DskipTests

  # 5. The command to run when the container starts
CMD mvn spring-boot:run