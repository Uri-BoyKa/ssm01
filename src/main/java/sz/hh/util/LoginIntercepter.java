package sz.hh.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercepter implements HandlerInterceptor {
/*
 * 这是实现ajax 没登录购买就跳回登录页面的拦截器,把一些可以过滤掉的网址放过	
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
	
	//在目标方法执行之前要完成的操作
	//如果方法返回true,请求可以继续;如果方法返回false,请求将到此为止,包括目标方法,都将不再执行;
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("myusers");
		
		String path = request.getContextPath();
		String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		System.out.println("basepath:"+basepath);
		if(obj==null) {
			String uri = request.getRequestURI();
			/*在获取地址uri第一次的时候会出现uc/uc/login时是因为login.jsp页面没有写地址请求配置
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
	
	//目标方法执行之后的操作
	//要求preHandle方法必须返回true,才会执行postHandle方法
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}
	
	//请求最后执行的操作,一般用来释放资源
	//要求preHandle方法必须返回true,afterCompletion方法才能执行
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("请求完成时的操作");
	}

}
