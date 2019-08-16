package sz.hh.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercepter implements HandlerInterceptor {
/*
 * ����ʵ��ajax û��¼��������ص�¼ҳ���������,��һЩ���Թ��˵�����ַ�Ź�	
 */
	private String [] arr = {"uc/login","","bc/fid","bc/infor"};
	
	public boolean checkUrl(String myurl) {
		if(myurl.endsWith(".jps")||myurl.endsWith(".png")||myurl.endsWith(".css")||myurl.endsWith(".js")) {
			return true;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].equals(myurl)) {
				return true;
			}
		}
		return false;
	}
	
	//��Ŀ�귽��ִ��֮ǰҪ��ɵĲ���
	//�����������true,������Լ���;�����������false,���󽫵���Ϊֹ,����Ŀ�귽��,��������ִ��;
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("myusers");
		
		String path = request.getContextPath();
		String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		System.out.println("basepath:"+basepath);
		if(obj==null) {
			String uri = request.getRequestURI();
			/*�ڻ�ȡ��ַuri��һ�ε�ʱ������uc/uc/loginʱ����Ϊlogin.jspҳ��û��д��ַ��������
			 * <%	String path = request.getContextPath();
					String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				%>
			 * 
			 */
			//System.out.println("uri:"+uri);
			String myurl = uri.substring(path.length()+1);
			//System.out.println("myurl:"+myurl);
			if(this.checkUrl(myurl)) {
				return true;
			}
			response.sendRedirect(basepath+"uc/login");
			return false;
		}
		return true;
	}
	
	//Ŀ�귽��ִ��֮��Ĳ���
	//Ҫ��preHandle�������뷵��true,�Ż�ִ��postHandle����
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}
	
	//�������ִ�еĲ���,һ�������ͷ���Դ
	//Ҫ��preHandle�������뷵��true,afterCompletion��������ִ��
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("�������ʱ�Ĳ���");
	}

}
