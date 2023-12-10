/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.edu.ifnmg.tcd.book;

import com.br.edu.ifnmg.tcd.comment.Comment;
import com.br.edu.ifnmg.tcd.entity.Entity;
import com.br.edu.ifnmg.tcd.exemplary.Exemplary;
import java.util.List;

public class Book extends Entity {
    private String title;
    private String authors;
    private Short pages;
    private Short year;
    private Byte edition;
    private List<Comment> comments;
    private List<Exemplary> exemplaries;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Exemplary> getExemplaries() {
        return exemplaries;
    }

    public void setExemplaries(List<Exemplary> exemplaries) {
        this.exemplaries = exemplaries;
    }
    
    public void addExemplary(Exemplary exemplary){
        this.exemplaries.add(exemplary);
    }
    
    public void addComment(Comment comment){
        this.comments.add(comment);
    }
    
    
    public Book() {
        
    }

    public Book(Long id, String title, String authors, Short pages, Short year, Byte edition) throws Exception{
        setId(id);
        setTitle(title);
        setAuthors(authors);
        setPages(pages);
        setYear(year);
        setEdition(edition);
    }

    public void setTitle(String title) throws IllegalAccessException{
        if(title.length()<=150) this.title = title;
        else throw new IllegalAccessException("Invalid size!");
    }


    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) throws IllegalAccessException{
        if(authors.length()<=250) this.authors = authors;
        else throw new IllegalAccessException("Invalid size!");
    }

    public Short getPages() {
        return pages;
    }

    public void setPages(Short pages) throws IllegalAccessException{
        if(pages>=1) this.pages = pages;
        else throw new IllegalAccessException("Invalid quantity!");
    }

    public Short getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public void setYear(Short year) throws IllegalAccessException{
        if(year != null) this.year = year;
        else throw new IllegalAccessException("Null value!");
    }

    public Byte getEdition() {
        return edition;
    }

    public void setEdition(Byte edition) throws IllegalAccessException{
        if(edition >= 1) this.edition = edition;
        else throw new IllegalAccessException("Invalid quantity!");
    }
    
}
