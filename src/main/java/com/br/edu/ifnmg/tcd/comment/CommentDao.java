package com.br.edu.ifnmg.tcd.comment;

import com.br.edu.ifnmg.tcd.reader.Reader;
import com.br.edu.ifnmg.tcd.reader.ReaderDao;
import com.br.edu.ifnmg.tcd.repository.Dao;
import com.br.edu.ifnmg.tcd.comment.Comment;
import com.br.edu.ifnmg.tcd.comment.CommentDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


public class CommentDao extends Dao<Comment> {
    public static final String TABLE = "comment";

    @Override
    public String getSaveStatement() {
        return "insert into " + TABLE + "(content,date,reader_id, book_id) values (?,?,?,?)";
    }

    @Override
    public String getUpdateStatement() {
        return "update " + TABLE + " set content = ?, date = ? where id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "select content, date, reader_id, book_id from " + TABLE + " where id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "select content, date, reader_id, book_id from " + TABLE;
    }

    public String getFindByBookStatement() {
        return "select content, date, reader_id from " + TABLE + " where book_id = ?";
    }

    @Override
    public String getDeleteStatement() {
        return "delete from " + TABLE + " where id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Comment e) {
        try {
            if (e.getContent() != null) {
                pstmt.setObject(1, e.getContent(), Types.VARCHAR);
            }
            if (e.getContent() != null) {
                pstmt.setObject(2, e.getDate(), Types.DATE);
            }
            if (e.getContent() != null) {
                pstmt.setObject(3, e.getReader().getId(), Types.BIGINT);
            }
            if (e.getContent() != null) {
                pstmt.setObject(4, e.getBook().getId(), Types.BIGINT);
            }
            if (e.getContent() != null) {
                pstmt.setObject(3, e.getId(), Types.BIGINT);
            }
        } catch (SQLException ex) {
            System.out.println("Exception in compose Save or Update: " + ex);
        }
    }

    @Override
    public Comment extractObject(ResultSet rs) {

        Comment queryComment = null;

        try {
            queryComment = new Comment();

            queryComment.setId(rs.getLong("id"));
            queryComment.setContent(rs.getString("content"));
            queryComment.setDate(rs.getDate("date").toLocalDate());
            Reader reader = new ReaderDao().findById(rs.getLong("reader_id"));
            queryComment.setReader(reader);
            Book book = new BookDao().findById(rs.getLong("book_id"));
            queryComment.setBook(book);

        } catch (Exception ex) {
            System.out.println("Exception in extractObject: " + ex);
        }

        return queryComment;
    }
}
