CREATE TABLE books (
 id BIGSERIAL PRIMARY KEY,
 isbn VARCHAR(20) NOT NULL UNIQUE,
 title VARCHAR(200) NOT NULL,
 author VARCHAR(150) NOT NULL,
 available_copies INTEGER NOT NULL CHECK (available_copies >= 0),
 total_copies INTEGER NOT NULL CHECK (total_copies >= 0),
 version BIGINT NOT NULL DEFAULT 0,
 CONSTRAINT copies_valid CHECK (available_copies <= total_copies)
);
CREATE TABLE members (
 id BIGSERIAL PRIMARY KEY,
 name VARCHAR(120) NOT NULL,
 email VARCHAR(254) NOT NULL UNIQUE
);
CREATE TABLE loans (
 id BIGSERIAL PRIMARY KEY,
 book_id BIGINT NOT NULL REFERENCES books(id),
 member_id BIGINT NOT NULL REFERENCES members(id),
 borrowed_at TIMESTAMP WITH TIME ZONE NOT NULL,
 due_at TIMESTAMP WITH TIME ZONE NOT NULL,
 returned_at TIMESTAMP WITH TIME ZONE,
 CONSTRAINT loan_dates_valid CHECK (due_at > borrowed_at)
);
CREATE INDEX idx_loans_member ON loans(member_id);
CREATE INDEX idx_loans_book_active ON loans(book_id) WHERE returned_at IS NULL;
