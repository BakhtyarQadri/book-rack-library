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
  
5. **Create and Populate Database Tables**
   - Ensure you have a running instance of a PostgreSQL database.
   - Execute the following SQL scripts to create and populate the `Library`, `Rack`, and `Book` tables.

### SQL Scripts

#### Create Schema
```sql
CREATE SCHEMA public;
```

#### Create Tables
```sql
-- Create Library Table
CREATE TABLE public.Library (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL
);

-- Create Rack Table
CREATE TABLE public.Rack (
    id SERIAL PRIMARY KEY,
    library_id_fk INT REFERENCES public.Library(id),
    row_number INT NOT NULL,
    column_number INT NOT NULL
);

-- Create Book Table
CREATE TABLE public.Book (
    id SERIAL PRIMARY KEY,
    rack_id_fk INT REFERENCES public.Rack(id),
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Indexes
CREATE INDEX idx_library_name ON public.Library (name);
CREATE INDEX idx_rack_library_id ON public.Rack (library_id_fk);
CREATE INDEX idx_rack_location ON public.Rack (row_number, column_number);
CREATE INDEX idx_book_name ON public.Book (name);
CREATE INDEX idx_book_rack_id ON public.Book (rack_id_fk);
```
#### Insert Data
```sql
-- Insert Data into Library Table
INSERT INTO public.Library (name, start_time, end_time) VALUES 
('Central Library', '08:00:00', '20:00:00'),
('City Library', '09:00:00', '21:00:00');

-- Insert Data into Rack Table
INSERT INTO public.Rack (library_id_fk, row_number, column_number) VALUES 
(1, 1, 1),
(1, 1, 2),
(2, 1, 1),
(2, 1, 2);
```
   
6. **Build and Run the Project**
   - Once the project is opened, let IntelliJ finish indexing and downloading any required dependencies.
   - Click on the `Run` button in IntelliJ or press `Shift + F10` to build and run the project.

7. **Access the Application**
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
  - `{ "error": { "code": "INTERNAL_SERVER_ERROR", "message": "exception message" } }` <br>

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
  - `{ "error": { "code": "MISSING_NAME", "message": "name shouldn't be missing / null / empty" } }` <br>
  - `{ "error": { "code": "INVALID_NAME", "message": "name should only contains alphanumeric character" } }` <br>
  - `{ "error": { "code": "MISSING_DESCRIPTION", "message": "description shouldn't be missing / null / empty" } }` <br>
  - `{ "error": { "code": "INVALID_DESCRIPTION", "message": "description should only contains alphanumeric character" } }` <br>
  - `{ "error": { "code": "MISSING_RACK_ID, "message": "rack id shouldn't be missing / null" } }` <br>
  - `{ "error": { "code": "INVALID_RACK_ID", "message": "rack id shouldn't be less than 1" } }` <br>
  - `{ "error": { "code": "RACK_ID_NOT_EXIST", "message": "rack id does not exist" } }` <br>
  - `{ "error": { "code": "INTERNAL_SERVER_ERROR", "message": "exception message" } }` <br>

# Status Codes

Following are the possible status codes;

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 201 | `CREATED` |
| 400 | `BAD REQUEST` |
| 500 | `INTERNAL SERVER ERROR` |
