package example.java.dao.tdd.row.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import example.java.dao.tdd.model.data.Book;

public class BookRowMapper implements ParameterizedRowMapper<Book> {

    private Map<String, Book> bookDataMap;

    public BookRowMapper() {
        this.bookDataMap = new LinkedHashMap<String, Book>();
    }

    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Book book = new Book();

    	String bookName = rs.getString("name");
    	book.setName(bookName);
    	book.setAuthor(rs.getString("author"));

    	bookDataMap.put(bookName, book);

        return book;
    }

    public Map<String, Book> getBookMap() {
        return bookDataMap;
    }
}
