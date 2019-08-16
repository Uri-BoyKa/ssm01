package sz.hh.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sz.hh.serivce.UserService;
import sz.hh.util.BalanceLessException;
import sz.hh.util.StoreHouseLessException;

@Controller
@RequestMapping("/sbc")
public class StoreHouseController {
	/*������ʱҪʵ�ֵĲ���
	 * 1.�����鼮���,��ѯ�鼮�������  ��StoreBuyDao���ѯ
	 * 2.���������һ
	 * 3.��ѯ�˻����	��AccountDao���ѯ
	 * 4.�����鼮��ţ���ѯ�鼮�۸�  ͨ��BookDao��ѯ�鼮���Ȼ���ȡ�۸�
	 * 5.�˻�������
	 */
	@Autowired
	private UserService storeBuyImpl;
	
	@RequestMapping(value="buybook",method=RequestMethod.POST)
	public String buybook(Integer bookid,Integer accid,Model model) {
		System.out.println("buybook.....");
		String msg = "����ɹ�...";
		try {
			boolean flag = storeBuyImpl.buybook(bookid, accid);
			
		} catch (StoreHouseLessException e) {
			 msg = e.getMessage();
			
		} catch (BalanceLessException e) {
			msg = e.getMessage();
		}
		//jspҳ��û�а�msg����ȥ,����û����ʾ����ɹ�������ʾ
		model.addAttribute("msg", msg);
		return "pay_info";
	}
}
