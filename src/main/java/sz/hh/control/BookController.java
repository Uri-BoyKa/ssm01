package sz.hh.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import sz.hh.pojo.Book;
import sz.hh.serivce.BookService;
@Controller
@RequestMapping("/bc")
public class BookController {

	@Autowired
	private BookService bookServ;
	
	//查询所有
	@RequestMapping(value="fid",method=RequestMethod.GET)
	public String showAll(@RequestParam(required=true,defaultValue="1") Integer page,Model model){
		PageHelper.startPage(page, 3);
		List<Book> booklist = bookServ.findAll();
		PageInfo<Book> blist = new PageInfo<Book>(booklist);
		model.addAttribute("page", blist);
		model.addAttribute("booklist", booklist);
		return "book_list";
	}
	
	//只要写地址传过去的 就像a标签  这里就必须要用GET
	//查询详细信息
	@RequestMapping(value="infor",method=RequestMethod.GET)
	public String findmessage(Integer b,Model model) {
		Book book = bookServ.findId(b);
		model.addAttribute("boo", book);
		//System.out.println(book.getBookid());
		return "information";
	}
	
	//添加书籍
	@RequestMapping(value="adbok",method=RequestMethod.POST)
	public String addBooks(Book book,@RequestParam MultipartFile pic,HttpServletRequest request) {
		System.out.println(pic.getOriginalFilename());
		
		try {
			InputStream is = pic.getInputStream();
			//获取images文件夹的绝对路径
			String realpath = request.getSession().getServletContext().getRealPath("/images");
			
			//文件名
			String newfilename = UUID.randomUUID().toString();
			//得到文件的后缀名
			String endname = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
			//这时就得到一个完整的路径
			OutputStream os = new FileOutputStream(new File(realpath+"/"+newfilename+endname));
			
			FileCopyUtils.copy(is,os);
			//book对象保存到数据库
			book.setImgPath("images/"+newfilename+endname);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = bookServ.addBooks(book);
		return "redirect:/bc/fid";
	}
	
	
	
	
}	
