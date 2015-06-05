package test.example.java.dao.tdd.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.ParseException;

import example.java.dao.tdd.model.data.Book;

public class ResultSetData {
    private static final String BOOK = "bookDataList";

    public static LinkedList<LinkedHashMap<String, String>> getBookResultSet(ApplicationContext context) throws UnexpectedInputException,
            ParseException, Exception {
        LinkedList<LinkedHashMap<String, String>> resultSetData = new LinkedList<LinkedHashMap<String, String>>();

        @SuppressWarnings("unchecked")
        FlatFileItemReader<Book> csvReader = (FlatFileItemReader<Book>) context.getBean(BOOK);
        csvReader.open(new ExecutionContext());
        
        Book data = null;
        LinkedHashMap<String, String> temp;
        while ((data = csvReader.read()) != null) {
            temp = new LinkedHashMap<String, String>();

            temp.put("name".toUpperCase(), data.getName());
            temp.put("author".toUpperCase(), data.getAuthor());

            resultSetData.add(temp);
        }
        return resultSetData;
    }

 
}
