package test.example.java.dao.tdd.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.ParseException;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import test.example.java.dao.tdd.util.ResultSetData;
import api.jdbc.mock.exception.ReflectionException;
import api.jdbc.mock.spring_jdbc.MockNamedParameterJdbcDaoSupport;
import api.jdbc.mock.sql.ConcreteMockedResultSet;
import example.java.dao.tdd.dao.ConcreteDao;
import example.java.dao.tdd.model.data.Book;

public class ConcreteDaoTest {
	private static final String GET_BOOK_QUERY = "getBookQuery";
	private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	
	private ConcreteDao concreteDao = null;
	private MockNamedParameterJdbcDaoSupport<ConcreteDao> mockObject = null;
	
	@Before
	public void setupTest() throws SQLException, ReflectionException{
		concreteDao = new ConcreteDao();
		concreteDao.setQueryProps((Properties) context.getBean("queryProps"));
		Assert.assertNotNull(concreteDao);
		
		mockObject = new MockNamedParameterJdbcDaoSupport<ConcreteDao>(concreteDao);
		Assert.assertNotNull(mockObject);
	}
	
	//@Test
	public void simpleSelectQueryTest() throws UnexpectedInputException, ParseException, Exception{
		LinkedList<LinkedHashMap<String, String>> expectedData = ResultSetData.getBookResultSet(context);
		ConcreteMockedResultSet resultSet = new ConcreteMockedResultSet("BookData", expectedData);
		mockObject.setResultSetData(resultSet);
		
		HashMap<String, Object> sqlQueryParameters = new HashMap<String, Object>();
		Map<String, Book> actualResultSetData  = concreteDao.getSelectQueryModelMap(GET_BOOK_QUERY, sqlQueryParameters);
		
        Assert.assertNotNull(actualResultSetData);
        Assert.assertTrue(actualResultSetData.size()>0);
        Assert.assertEquals(expectedData.size(), actualResultSetData.size());
	}
	
	@Test
	public void selectQueryRowSetTest() throws UnexpectedInputException, ParseException, Exception{
		LinkedList<LinkedHashMap<String, String>> expectedData = ResultSetData.getBookResultSet(context);
		ConcreteMockedResultSet resultSet = new ConcreteMockedResultSet("BookData", expectedData);
		mockObject.setResultSetData(resultSet);
		
		
		SqlRowSet rowSet  = concreteDao.getSelectQueryResultSet(GET_BOOK_QUERY);
        Assert.assertNotNull(rowSet);
	}
}
