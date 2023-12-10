package com.br.edu.ifnmg.tcd.comment;

import com.br.edu.ifnmg.tcd.entity.Entity;
import com.br.edu.ifnmg.tcd.reader.Reader;
import com.sun.source.doctree.CommentTree;

import java.time.LocalDate;

public class Comment extends Entity {
    private String content;
    private LocalDate date;
    private Reader reader;
    private Book book;

    public Comment(String content, LocalDate date, Reader reader, Book book) throws Exception {
        setContent(content);
        setDate(date);
        setReader(reader);
        setBook(book);
    }

    public Comment() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) throws Exception {
        if (content.isEmpty()) {
            throw new Exception("Comment cannot be empty");
        } else if (content.length() > 280) {
            throw new Exception("Max comment content is 280 characters");
        } else {
            this.content = content;
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws Exception {
        if (date.isAfter(LocalDate.now())) {
            throw new Exception("Invalid date");
        }

        this.date = date;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
