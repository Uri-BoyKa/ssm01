package sz.hh.pojo;

import java.io.Serializable;

public class StoreHouse implements Serializable {
	
	private Integer bookid;
	private Integer bookCount;
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public Integer getBookCount() {
		return bookCount;
	}
	public void setBookCount(Integer bookCount) {
		this.bookCount = bookCount;
	}
	

}
