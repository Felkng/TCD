package com.br.edu.ifnmg.tcd.repository;

import com.br.edu.ifnmg.tcd.entity.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Dao<E> implements IDao<E> {

    public static final String DB = "library";

    @Override
    public Long saveOrUpdate(E e) {
        Long id = 0L;

        if (((Entity) e).getId() == null
                || ((Entity) e).getId() <= 0) {

            // Insert a new register
            // try-with-resources
            try (PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(
                            getSaveStatement(),
                            Statement.RETURN_GENERATED_KEYS)) {

                // Assemble the SQL statement with the data (->?)
                composeSaveOrUpdateStatement(preparedStatement, e);

                // Show the full sentence
//                 System.out.println(">> SQL: " + preparedStatement);
                // Performs insertion into the database
                preparedStatement.executeUpdate();

                // Retrieve the generated primary key
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                // Moves to first retrieved data
                if (resultSet.next()) {

                    // Retrieve the returned primary key
                    id = resultSet.getLong(1);
                }

            } catch (Exception ex) {
                System.out.println("Exception in SaveDao: " + ex);
            }

        } else {
            // Update existing record
            try (PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(
                            getUpdateStatement())) {

                // Assemble the SQL statement with the data (->?)
                composeSaveOrUpdateStatement(preparedStatement, e);

                // Show the full sentence
                // System.out.println(">> SQL: " + preparedStatement);
                // Performs the update on the database
                preparedStatement.executeUpdate();

                // Keep the primary key
                id = ((Entity) e).getId();

            } catch (Exception ex) {
                System.out.println("Exception in UpdateDao: " + ex);
            }
        }

        return id;
    }

    @Override
    public E findById(Long id) {
        try (PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(
                        getFindByIdStatement())) {

            // Assemble the SQL statement with the id
            preparedStatement.setLong(1, id);

            // Show the full sentence
//            System.out.println(">> SQL: " + preparedStatement);
            // Performs the query on the database
            ResultSet resultSet = preparedStatement.executeQuery();

            // Returns the respective object if exists
            if (resultSet.next()) {
                return extractObject(resultSet);
            }

        } catch (Exception ex) {
            System.out.println("Exception im findByIdDao: " + ex);
        }

        return null;
    }

    @Override
    public ArrayList<E> findAll() {
        try (PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(
                        getFindAllStatement())) {

            // Show the full sentence
            // System.out.println(">> SQL: " + preparedStatement);
            // Performs the query on the database
            ResultSet resultSet = preparedStatement.executeQuery();

            // Returns the respective object
            return extractObjects(resultSet);

        } catch (Exception ex) {
            System.out.println("Exception in findAllDao: " + ex);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(getDeleteStatement())) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Exception in deleteDao: " + ex);
        }

    }

    @Override
    public ArrayList<E> extractObjects(ResultSet rs) {
        ArrayList<E> objectList = new ArrayList<>();
        try {
            while (rs.next()) {
                E e = extractObject(rs);
                objectList.add(e);
            }
        } catch (Exception ex) {
            System.out.println("Exception in extractObjects DAO: " + ex);
        }

        return objectList;
    }
}
