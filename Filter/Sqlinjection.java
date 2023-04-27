package Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet Filter implementation class Sqlinjection
 */
@WebFilter("/Sqlinjection")
public class Sqlinjection extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Sqlinjection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String ADMINID=request.getParameter("ADMINID");
		String PASSWORD=request.getParameter("PASSWORD");
		String regex="^ad(10[1-9]|[1-9][0-9]{2}|1000)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ADMINID);
		if(matcher.matches()) 
	      {
	         regex="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?!.*\\s).{8,20}$";
	         pattern = Pattern.compile(regex);
	 		 matcher = pattern.matcher(PASSWORD);
	 		if(matcher.matches()) 
		      {
	 			
	          }
	 		else 
	 		{
	 			  HttpSession Session=request.getSession();
		          Session.setAttribute("passfilter",PASSWORD);
		          
	 		}
	      }
		else 
		{
			  HttpSession Session=request.getSession();
	          Session.setAttribute("idfilter",ADMINID);
	          
	         
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
