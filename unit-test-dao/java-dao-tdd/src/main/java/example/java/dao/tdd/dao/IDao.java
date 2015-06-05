package example.java.dao.tdd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import example.java.dao.tdd.exception.DaoOperationException;


/**
 * The Interface IStarsCommonDao.
 *
 * @author Abhishek Sharma
 * @category DAO
 * @since 10/9/2014
 * @serial exclude
 */
public interface IDao {
    // SQL data access generic functions.
    /**
     * Gets the results.
     *
     * @param sqlQuery the SQL query
     * @return the results
     * @throws DaoOperationException the GMI Stars DAO Operation Exception.
     */
    SqlRowSet getResults(String sqlQuery) throws DaoOperationException;

    /**
     * Gets the object.
     *
     * @param <T> the generic type
     * @param sqlQuery the SQL query
     * @param rowMapper the row mapper
     * @param map the map
     * @return the object
     * @throws DaoOperationException the GMI Stars DAO Operation Exception.
     */
    <T> T getObject(String sqlQuery, RowMapper<T> rowMapper, Map<String, Object> map)
            throws DaoOperationException;

    /**
     * Gets the results.
     *
     * @param <T> the generic type
     * @param sqlQuery the SQL query
     * @param rowMapper the row mapper
     * @return the results
     * @throws DaoOperationException the GMI Stars DAO Operation Exception.
     */
    <T> List<T> getResults(String sqlQuery, RowMapper<T> rowMapper, MapSqlParameterSource parameters)
            throws DaoOperationException;

    /**
     * Gets the results.
     *
     * @param <T> the generic type
     * @param sqlQuery the SQL query
     * @param rowMapper the row mapper
     * @param map the map
     * @return the results
     * @throws DaoOperationException the GMI Stars DAO Operation Exception.
     */
    <T> List<T> getResults(String sqlQuery, RowMapper<T> rowMapper, Map<String, Object> map)
            throws DaoOperationException;

    /**
     * Update.
     *
     * @param sqlQuery the SQL query
     * @param args the arguments
     * @return the integer value
     * @throws DaoOperationException the GMI Stars DAO Operation Exception.
     */
    int update(String sqlQuery, Object[] args) throws DaoOperationException;

    /**
     * Update.
     *
     * @param sqlQuery the SQL query
     * @param args the arguments
     * @param types the types
     * @return the integer value
     * @throws DaoOperationException the GMI Stars DAO Operation Exception.
     */
    int update(String sqlQuery, Object[] args, int[] types) throws DaoOperationException;

    /**
     * Update.
     *
     * @param sqlQuery the SQL query
     * @return the integer value
     * @throws DaoOperationException the GMI Stars DAO Operation Exception.
     */
    int update(String sqlQuery) throws DaoOperationException;

    /**
     * Batch update.
     *
     * @param sqlQuery the SQL query
     * @param batchPreparedStatementSetter the batch prepared statement setter
     * @return the integer array value
     * @throws DaoOperationException the GMI Stars DAO Operation Exception.
     */
    int[] batchUpdate(String sqlQuery, BatchPreparedStatementSetter batchPreparedStatementSetter)
            throws DaoOperationException;
}
