
# Steps to run the project
- install jdk 21
- install intellij
- open terminal, run the command "git clone https://github.com/BakhtyarQadri/book-rack-library.git"
- open the project in Intellij, build and run it
- application will start and server will listen at port `8080`

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
