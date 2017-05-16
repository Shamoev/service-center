package ua.com.sourceit.jdbc.dao;

import ua.com.sourceit.jdbc.exceptions.GenericDaoException;
import ua.com.sourceit.jdbc.model.BaseEntity;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;

public class GenericDaoImpl<T extends BaseEntity<PK>, PK extends Serializable> implements GenericDao<T, PK> {
    private DataSource dataSource;
    protected Class<T> type;

    public GenericDaoImpl(DataSource dataSource, Class<T> type) {
        this.dataSource = dataSource;
        this.type = type;
    }

    @Override
    public PK create(T objectToCreate) {
        try (Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            PreparedStatement stmt = con.prepareStatement(objectToCreate.getCreateSql(), Statement.RETURN_GENERATED_KEYS); // ???
            objectToCreate.prepareCreateStatement(stmt);
            stmt.execute();
            ResultSet resultSet = stmt.getGeneratedKeys(); // ???
            if (resultSet.next()) {
                PK id = (PK) new Long(resultSet.getLong(1)); //Don't do such way.
                objectToCreate.setId(id);                    //PK could be String e.g.
                con.commit();
                return id;
            }
        } catch (SQLException e) {
            throw new GenericDaoException("Error saving "
                    + objectToCreate.getClass().getSimpleName()
                    + " to data base", e);
        }
        throw new GenericDaoException("Error saving "
                + objectToCreate.getClass().getSimpleName()
                + " to data base");
    }

    @Override
    public T read(PK id) {
        T result = null;
        try (Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            result = type.newInstance();
            PreparedStatement stmt = con.prepareStatement(result.getReadSql());
            result.prepareReadStatement(stmt, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                result.setId(id);
                result.setDataFromResultSet(resultSet);
                con.commit();
                return result;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            throw new GenericDaoException("Error reading "
                    + result == null
                    ? "unknown"
                    : result.getClass().getSimpleName().toLowerCase()
                    + " from data base", e);
        }
        String type = result == null
                ? "unknown"
                : result.getClass().getSimpleName().toLowerCase();
        throw new GenericDaoException("Error reading "
                + type + " from data base - no " + type + "s were found with id = " + id);
    }

    @Override
    public void update(T objectToUpdate) {
        try (Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            PreparedStatement stmt = con.prepareStatement(objectToUpdate.getUpdateSql(), Statement.RETURN_GENERATED_KEYS);
            objectToUpdate.prepareUpdateStatement(stmt);
            int countOfUpdateRows = stmt.executeUpdate();
            if (countOfUpdateRows == 1) {
                con.commit();
                return;
            } else {
                con.rollback();
                String type = objectToUpdate.getClass().getSimpleName();
                throw new GenericDaoException("Error updating" +
                        type + ". 0 or more than 1" +
                        type + "s were found. ROLLBACK!");
            }
        } catch (SQLException e) {
            throw new GenericDaoException("Error updating " + objectToUpdate.getClass().getSimpleName(), e);
        }
    }

    @Override
    public void delete(T objectToDelete) {
        try (Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            PreparedStatement stmt = con.prepareStatement(objectToDelete.getDeleteSql(), Statement.RETURN_GENERATED_KEYS);
            objectToDelete.prepareDeleteStatement(stmt);
            int countOfDeletedRows = stmt.executeUpdate();
            if (countOfDeletedRows == 1) {
                con.commit();
                return;
            } else {
                con.rollback();
                String type = objectToDelete.getClass().getSimpleName();
                throw new GenericDaoException("Error deleting " +
                        type + " from data base. 0 or more than 1 " +
                        type + "s were found. ROLLBACK");
            }
        } catch (SQLException e) {
            throw new GenericDaoException("Error updating " + objectToDelete.getClass().getSimpleName(), e);
        }
    }

    public void prepareConnection(Connection con) throws SQLException {
        con.setAutoCommit(false);
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }
}
