CREATE DATABASE training_db;

CREATE SCHEMA public;

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
CREATE INDEX idx_book_name ON public.Book (name);
CREATE INDEX idx_book_rack_id ON public.Book (rack_id_fk);

-- Insert Data into Library Table
INSERT INTO public.Library (name, start_time, end_time) VALUES
('Central Library', '08:00:00', '20:00:00'),
('City Library', '09:00:00', '21:00:00');

-- Insert Data into Rack Table
INSERT INTO public.Rack (library_id_fk, row_number, column_number) VALUES
(1, 1, 1),
(1, 1, 2),
(1, 1, 3),
(1, 1, 4),
(1, 1, 5),
(2, 1, 1),
(2, 1, 2),
(2, 1, 3),
(2, 1, 4),
(2, 1, 5);