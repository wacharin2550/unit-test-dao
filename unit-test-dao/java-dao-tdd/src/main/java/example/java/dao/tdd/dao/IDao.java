package example.java.dao.tdd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import example.java.dao.tdd.exception.DaoOperationException;


public interface IDao {

    SqlRowSet getResults(String sqlQuery) throws DaoOperationException;

    <T> T getObject(String sqlQuery, RowMapper<T> rowMapper, Map<String, Object> map)
            throws DaoOperationException;

    <T> List<T> getResults(String sqlQuery, RowMapper<T> rowMapper, MapSqlParameterSource parameters)
            throws DaoOperationException;

    <T> List<T> getResults(String sqlQuery, RowMapper<T> rowMapper, Map<String, Object> map)
            throws DaoOperationException;

    int update(String sqlQuery, Object[] args) throws DaoOperationException;

    int update(String sqlQuery, Object[] args, int[] types) throws DaoOperationException;

    int update(String sqlQuery) throws DaoOperationException;

    int[] batchUpdate(String sqlQuery, BatchPreparedStatementSetter batchPreparedStatementSetter)
            throws DaoOperationException;
}
