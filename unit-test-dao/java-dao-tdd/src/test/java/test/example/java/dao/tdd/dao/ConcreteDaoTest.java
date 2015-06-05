package test.example.java.dao.tdd.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.ParseException;

import test.example.java.dao.tdd.util.ResultSetData;
import api.jdbc.mock.exception.ReflectionException;
import api.jdbc.mock.spring_jdbc.MockNamedParameterJdbcDaoSupport;
import api.jdbc.mock.sql.ConcreteMockedResultSet;
import example.java.dao.tdd.dao.ConcreteDao;
import example.java.dao.tdd.model.data.Book;

public class ConcreteDaoTest {
	
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
	
	@Test
	public void bookDataTest() throws UnexpectedInputException, ParseException, Exception{
		ConcreteMockedResultSet resultSet = new ConcreteMockedResultSet("BookData",ResultSetData.getBookResultSet(context));
		mockObject.setResultSetData(resultSet);
		
		HashMap<String, Object> sqlQueryParameters = new HashMap<String, Object>();
		Map<String, Book> data  = concreteDao.getBookData(sqlQueryParameters);
		
        Assert.assertNotNull(data);
        Assert.assertTrue(data.size()>0);

	}
}
