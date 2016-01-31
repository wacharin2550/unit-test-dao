package api.jdbc.mock.spring_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import api.jdbc.mock.exception.ReflectionException;
import api.jdbc.mock.sql.ConcreteMockedResultSet;
import api.jdbc.mock.util.ObjectFactoryUtil;

public class MockNamedParameterJdbcDaoSupport<T extends NamedParameterJdbcDaoSupport> {
	
	private T dao;
	
    @Mock private Connection connection;
    @Mock private DataSource datasource;
    @Mock private Statement statement;
    @Mock private PreparedStatement preparedStatement;
    @Mock private NamedParameterJdbcTemplate jdbcTemplateMock;
	
	public MockNamedParameterJdbcDaoSupport(T dao) throws SQLException, ReflectionException{
		super();
		
		this.dao = dao;
		
		MockitoAnnotations.initMocks(this);		
		dao.setDataSource(datasource);
		
		Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
		Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(datasource.getConnection()).thenReturn(connection);
	}
	
	public void setDaoObjectProperties(Properties prop, String propMethodName) throws ReflectionException{
		Map<Class<?>, Object> paramMap = new LinkedHashMap<Class<?>, Object>();
		paramMap.put(prop.getClass(), prop);
		
		ObjectFactoryUtil.invokeMethod(dao, propMethodName, paramMap);
	}
	
	public void setResultSetData(ConcreteMockedResultSet resultSet) throws SQLException{
		Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
		Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
	}
	
}
