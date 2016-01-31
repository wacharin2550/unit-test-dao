package example.java.dao.tdd.dao;

import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import example.java.dao.tdd.exception.DaoOperationException;
import example.java.dao.tdd.model.data.Book;
import example.java.dao.tdd.row.mapper.BookRowMapper;

public class ConcreteDao extends AbstractDao {

	public Map<String, Book> getSelectQueryModelMap(String query, Map<String, Object> sqlQueryParameters)
			throws DaoOperationException {
		String sqlQuery = getQueryProps().getProperty(query);

		BookRowMapper mapper = new BookRowMapper();

		getResults(sqlQuery, mapper, sqlQueryParameters);
		Map<String, Book> dataList = mapper.getBookMap();

		return dataList;
	}
	
	public SqlRowSet getSelectQueryResultSet(String query)
			throws DaoOperationException {
		String sqlQuery = getQueryProps().getProperty(query);

		return getResults(sqlQuery);
	}

}
