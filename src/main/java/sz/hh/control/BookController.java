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
	
	//��ѯ����
	@RequestMapping(value="fid",method=RequestMethod.GET)
	public String showAll(@RequestParam(required=true,defaultValue="1") Integer page,Model model){
		PageHelper.startPage(page, 3);
		List<Book> booklist = bookServ.findAll();
		PageInfo<Book> blist = new PageInfo<Book>(booklist);
		model.addAttribute("page", blist);
		model.addAttribute("booklist", booklist);
		return "book_list";
	}
	
	//ֻҪд��ַ����ȥ�� ����a��ǩ  ����ͱ���Ҫ��GET
	//��ѯ��ϸ��Ϣ
	@RequestMapping(value="infor",method=RequestMethod.GET)
	public String findmessage(Integer b,Model model) {
		Book book = bookServ.findId(b);
		model.addAttribute("boo", book);
		//System.out.println(book.getBookid());
		return "information";
	}
	
	//����鼮
	@RequestMapping(value="adbok",method=RequestMethod.POST)
	public String addBooks(Book book,@RequestParam MultipartFile pic,HttpServletRequest request) {
		System.out.println(pic.getOriginalFilename());
		
		try {
			InputStream is = pic.getInputStream();
			//��ȡimages�ļ��еľ���·��
			String realpath = request.getSession().getServletContext().getRealPath("/images");
			
			//�ļ���
			String newfilename = UUID.randomUUID().toString();
			//�õ��ļ��ĺ�׺��
			String endname = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
			//��ʱ�͵õ�һ��������·��
			OutputStream os = new FileOutputStream(new File(realpath+"/"+newfilename+endname));
			
			FileCopyUtils.copy(is,os);
			//book���󱣴浽���ݿ�
			book.setImgPath("images/"+newfilename+endname);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = bookServ.addBooks(book);
		return "redirect:/bc/fid";
	}
	
	
	
	
}	
