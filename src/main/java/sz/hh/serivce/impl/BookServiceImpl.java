package sz.hh.serivce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.hh.dao.BookDao;
import sz.hh.pojo.Book;
import sz.hh.serivce.BookService;
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	public List<Book> findAll() {
		
		
		return bookDao.findAllBook();
	}

	public Book findId(Integer bookid) {
		
		return bookDao.findId(bookid);
	}

	public boolean addBooks(Book book) {
		
		return bookDao.addBooks(book);
	}

}
