/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.edu.ifnmg.tcd.book;

import com.br.edu.ifnmg.tcd.repository.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class BookDao extends Dao<Book> {

    public static final String TABLE = "book";

    @Override
    public String getFindByIdStatement() {
        return "select id, title, authors, pages, year, edition from " + TABLE + " where id = ?";
    }

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(title, authors, pages, year, edition)" + " values(?,?,?,?,?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set title = ?, authors = ?, pages = ?, year = ?, edition = ?"
                + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select * from " + TABLE;

    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Book e) {
        try {
            if (e.getTitle() != null) {
                pstmt.setObject(1, e.getTitle(), Types.VARCHAR);
            }

            if (e.getAuthors() != null) {
                pstmt.setObject(2, e.getAuthors(), Types.VARCHAR);
            }

            if (e.getPages() != null) {
                pstmt.setObject(3, e.getPages(), Types.SMALLINT);
            }
            if (e.getYear() != null) {
                pstmt.setObject(4, e.getYear(), Types.SMALLINT);
            }

            if (e.getEdition() != null) {
                pstmt.setObject(5, e.getEdition(), Types.TINYINT);
            }

            if (e.getId() != null) {
                pstmt.setObject(6, e.getId(), Types.BIGINT);
            }

        } catch (Exception ex) {
            System.out.println("Exception in composeSave or Update: " + ex);
        }

    }

    @Override
    public Book extractObject(ResultSet rs) {

        Book queryBook = null;

        try {
            queryBook = new Book();

            queryBook.setId(rs.getLong("id"));
            queryBook.setTitle(rs.getString("title"));
            queryBook.setAuthors(rs.getString("authors"));
            queryBook.setPages(rs.getShort("pages"));
            queryBook.setYear(rs.getShort("year"));
            queryBook.setEdition(rs.getByte("edition"));

        } catch (Exception ex) {
            System.out.println("Exception in extractObject: " + ex);
        }

        return queryBook;
    }
}
