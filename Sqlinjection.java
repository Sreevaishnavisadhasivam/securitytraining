import webgoattraining.connect;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * Servlet implementation class Sqlinjection
 */
@WebServlet("/Sqlinjection")
public class Sqlinjection extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	public Connection con=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sqlinjection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			con=connect.getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		String ADMINID=request.getParameter("ADMINID");
		String PASSWORD=request.getParameter("PASSWORD");
		int option=Integer.parseInt(request.getParameter("NUMBER"));
		System.out.println(option);
		if(option!=1)
		{
			
			
		String query1="select * from login where adminid='"+ADMINID+"'and password='"+PASSWORD+"'";
		System.out.println(query1);
		Statement state = null;
		try {
			state = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet result = null;
		try {
			result = state.executeQuery(query1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		if(result!=null)
		{
		try {
			if(result.next()) {
				try {
					System.out.println(result.getString(1)+" "+result.getString(2));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.setAttribute("passed", ADMINID);
				response.sendRedirect("home.jsp");
				System.out.println("loggined");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else 
		{
			System.out.println("empty result set found");
		}
		}
		else 
		{
			
			
			
			String query = "SELECT * FROM login WHERE adminid = ? AND password = ?";
			PreparedStatement pstmt = null;
			try {
				pstmt = con.prepareStatement(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.setString(1, ADMINID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.setString(2, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(pstmt);
			ResultSet rs=null;
			try {
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(rs.next())
				{
					HttpServletRequest session = null;
					session.setAttribute("passed", ADMINID);
					System.out.println("logiined");
					response.sendRedirect("home.jsp");
				}
				else 
				{
					System.out.println("invalid login");
					RequestDispatcher rd=request.getRequestDispatcher("Sqlinjection.jsp");  
			 	    rd.forward(request, response); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		
	
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		        
	}

}
