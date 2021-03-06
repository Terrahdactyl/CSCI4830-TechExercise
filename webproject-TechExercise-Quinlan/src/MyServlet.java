import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Team;
import util.UtilDB;

@WebServlet("/MyServletHibernateDB")
public class MyServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServlet() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

      // #1
      UtilDB.createTeams("user3", "33", "402-111-1111");
      UtilDB.createTeams("user4", "44", "402-222-2222");
      
      // #2
      retrieveDisplayData(response.getWriter());
   }

   void retrieveDisplayData(PrintWriter out) {
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<Team> listTeams = UtilDB.listTeams();
      for (Team team : listTeams) {
         System.out.println("[DBG] " + team.getId() + ", " //
               + team.getName() + ", " //
               + team.getOwner()
               + team.getWins());

         out.println("<li>" + team.getId() + ", " //
               + team.getName() + ", " //
               + team.getOwner() + ", " //
               + team.getWins() + "</li>");
      }
      out.println("</ul>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
