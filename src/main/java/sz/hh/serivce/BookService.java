package sz.hh.serivce;

import java.util.List;

import sz.hh.pojo.Book;

public interface BookService {

	public List<Book> findAll();
	
	public Book findId(Integer bookid);
	
	public boolean addBooks(Book book);
}
