package sz.hh.dao;

import java.util.List;

import sz.hh.pojo.Book;

public interface BookDao {

	public List<Book> findAllBook();
	
	public Book findId(Integer bookid);
	
	public boolean addBooks(Book book);
	
	
}
