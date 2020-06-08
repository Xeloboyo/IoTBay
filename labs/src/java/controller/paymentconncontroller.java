import uts.isd.model.CustomerBean;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;
 
 

    import java.io.IOException;
    import java.sql.Connection;
    import java.sql.SQLException;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import javax.servlet.http.HttpSession;
    import uts.isd.model.dao.*;
    import uts.isd.model.payment;
/**
 *
 * @author 10847
 */

public class paymentconncontroller extends HttpServlet {
    private DBConnector db;
    private DBManager manager;
    private Connection conn;

    @Override //Add the DBConnector, DBManager, Connection instances to the session
       protected void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
           response.setContentType("text/html;charset=UTF-8");       
           HttpSession session = request.getSession();
           conn = db.openConnection();       
           try {
               manager = new DBManager(conn);
           } catch (SQLException ex) {
               Logger.getLogger(paymentconncontroller.class.getName()).log(Level.SEVERE, null, ex);
           }

           //export the DB manager to the view-session (JSPs)
           session.setAttribute("manager", manager);           
       }   
          @Override 
    public void init() {
        try {
            db = new DBConnector();
            conn = db.openConnection();
            manager = new DBManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(paymentconncontroller.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }

    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
    public void destroy() {
       try {
           db.closeConnection();
       } catch (SQLException ex) {
           Logger.getLogger(paymentconncontroller.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
}