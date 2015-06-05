package example.java.dao.tdd.dao;

import java.util.Map;

import example.java.dao.tdd.exception.DaoOperationException;
import example.java.dao.tdd.model.data.Book;
import example.java.dao.tdd.row.mapper.BookRowMapper;

public class ConcreteDao extends AbstractDao {
	private static final String GET_BOOK_QUERY = "getBookQuery";

	public Map<String, Book> getBookData(Map<String, Object> sqlQueryParameters)
			throws DaoOperationException {
		String sqlQuery = getQueryProps().getProperty(GET_BOOK_QUERY);

		BookRowMapper mapper = new BookRowMapper();

		getResults(sqlQuery, mapper, sqlQueryParameters);
		Map<String, Book> dataList = mapper.getBookMap();

		return dataList;
	}

}
