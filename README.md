# Steps to Run the Project

1. **Install JDK 21**
   - Ensure you have JDK 21 installed on your machine. You can download it from the [official JDK website](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html).

2. **Install IntelliJ IDEA**
   - Download and install IntelliJ IDEA from the [official website](https://www.jetbrains.com/idea/download/).

3. **Clone the Repository**
   - Open your terminal and run the following command:
     ```sh
     git clone https://github.com/BakhtyarQadri/book-rack-library.git
     ```

4. **Open the Project in IntelliJ IDEA**
   - Launch IntelliJ IDEA.
   - Navigate to `File` > `Open` and select the cloned repository folder.
   
5. **Build and Run the Project**
   - Once the project is opened, let IntelliJ finish indexing and downloading any required dependencies.
   - Click on the `Run` button in IntelliJ or press `Shift + F10` to build and run the project.

6. **Access the Application**
   - The application will start, and the server will listen on port `8080`.
   - Open your browser and navigate to `http://localhost:8080` to access the application.

# Endpoints
Following are the REST APIs of the book-rack-library application;

## Show all books
Returns json data about all books.

- URL `/api/book` <br>

- Method `GET` <br>

- Request 'http://localhost:8080/api/book' <br>

- Success Response
  - `{ "status": "success", "data": "No book exist" }` <br>
  - `{ "status": "success", "data": [ { "bookId": 1, "bookName": "Chemistry", "bookDescription": "Matric class", "rackId": 1, "libraryName": "Quaid e Azam Library" } ] }` <br>

- Error Response
  - `{ "error": { "code": 500, "message": "exception message" } }` <br>

## Add a book
Returns message as text.

- URL `/api/book` <br>

- Method `POST` <br>

- Request 'http://localhost:8080/api/book' <br>

- Request Body
  - `{ "name": "Chemistry", "description": "Matric class", "rackId": 1 }`

- Success Response
  - `{ "status": "success", "data": "Book added" }`

- Error Response
  - `{ "error": { "code": 400, "message": "name shouldn't be missing / null / empty" } }` <br>
  - `{ "error": { "code": 400, "message": "description shouldn't be missing / null" } }` <br>
  - `{ "error": { "code": 400, "message": "rack id shouldn't be missing / null" } }` <br>
  - `{ "error": { "code": 400, "message": "rack id shouldn't be less than 1" } }` <br>
  - `{ "error": { "code": 400, "message": "rack id does not exist" } }` <br>
  - `{ "error": { "code": 500, "message": "exception message" } }` <br>

## Status Codes

Following are the possible status codes;

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 201 | `CREATED` |
| 400 | `BAD REQUEST` |
| 500 | `INTERNAL SERVER ERROR` |
