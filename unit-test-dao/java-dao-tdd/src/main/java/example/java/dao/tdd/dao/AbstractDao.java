package example.java.dao.tdd.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import example.java.dao.tdd.exception.DaoOperationException;

public abstract class AbstractDao extends NamedParameterJdbcDaoSupport implements IDao {

    private Properties queryProps;

    /**
     * Gets the query props.
     *
     * @return the query props
     */
    protected Properties getQueryProps() {
        return queryProps;
    }

    /**
     * Sets the query props.
     *
     * @param queryProps the query props
     */
    public void setQueryProps(Properties queryProps) {
        this.queryProps = queryProps;
    }

     public SqlRowSet getResults(String sqlQuery) throws DaoOperationException {
        try {
            return this.getJdbcTemplate().queryForRowSet(sqlQuery);
        } catch (DataAccessException exception) {
            throw new DaoOperationException(exception.getMessage(), exception);
        }
    }

    public <T> List<T> getResults(String sqlQuery, RowMapper<T> rowMapper, MapSqlParameterSource parameters)
            throws DaoOperationException {
        try {
            return this.getJdbcTemplate().query(sqlQuery, rowMapper, parameters);
        } catch (DataAccessException exception) {
            throw new DaoOperationException(exception.getMessage(),exception);
        }
    }

    public <T> List<T> getResults(String sqlQuery, RowMapper<T> rowMapper, Map<String, Object> map)
            throws DaoOperationException {
        try {
            return this.getNamedParameterJdbcTemplate().query(sqlQuery, map, rowMapper);
        } catch (DataAccessException exception) {
            throw new DaoOperationException(exception.getMessage(),exception);
        }
    }

    public <T> T getObject(String sqlQuery, RowMapper<T> rowMapper, Map<String, Object> map)
            throws DaoOperationException {
        try {
            return this.getNamedParameterJdbcTemplate().queryForObject(sqlQuery, map, rowMapper);
        } catch (DataAccessException exception) {
            throw new DaoOperationException(exception.getMessage(),exception);
        }
    }
    
    public <T> T getObjectFromQuery(String sqlQuery, RowMapper<T> rowMapper, Map<String, Object> map)
            throws DaoOperationException {
        try {
            List<T> list = this.getNamedParameterJdbcTemplate().query(sqlQuery, map, rowMapper);
            if(list.size()==1) {
                return list.get(0);
            }
        } catch (DataAccessException exception) {
            throw new DaoOperationException(exception.getMessage(),exception);
        }
        return null;
    }

    public int update(String sqlQuery, Object[] args) throws DaoOperationException {
        try {
            return this.getJdbcTemplate().update(sqlQuery, args);
        } catch (DataAccessException exception) {
            throw new DaoOperationException(exception.getMessage(),exception);
        }
    }

    public int update(String sqlQuery, Object[] args, int[] types) throws DaoOperationException {
        try {
            return this.getJdbcTemplate().update(sqlQuery, args, types);
        } catch (DataAccessException exception) {
            throw new DaoOperationException(exception.getMessage(),exception);
        }
    }

    public int update(String sqlQuery) throws DaoOperationException {
        try {
            int output = this.getJdbcTemplate().update(sqlQuery);
            getConnection().commit();
            return output;
        } catch (DataAccessException exception) {
            throw new DaoOperationException(exception.getMessage(),exception);
        } catch (SQLException exception) {
            throw new DaoOperationException(exception.getMessage(),exception);
        }

    }

    public int[] batchUpdate(String sqlQuery, BatchPreparedStatementSetter pss) throws DaoOperationException {
        try {
            
            int[] output =  this.getJdbcTemplate().batchUpdate(sqlQuery, pss);
            getConnection().commit();
            return output;
        } catch (DataAccessException exception) {
            throw new DaoOperationException(exception.getMessage(),exception);
        } catch (SQLException exception) {
            throw new DaoOperationException(exception.getMessage(),exception);
        }

    }
}
