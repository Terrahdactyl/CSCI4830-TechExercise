import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Team;
import util.Info;
import util.UtilDB;

@WebServlet("/Rankings")
public class Rankings extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public Rankings() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      String keyword = request.getParameter("keyword").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "National Yakipoo League Standings";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
//      out.println("<ol>");

      List<Team> listTeams = null;
      listTeams = UtilDB.rankTeams();
      
      display(listTeams, out);
//      out.println("</ol>");
      out.println("<br> <a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("<br> <a href=/" + projectName + "/" + insertWebName + ">Insert New Team</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Team> listTeams, PrintWriter out) {
	  int checkWins = listTeams.get(0).getWins();
	  int rank = 1;
      for (Team team : listTeams) {
         System.out.println("[DBG] " + team.getId() + ", " //
               + team.getName() + ", " //
               + team.getOwner() + ", "
               + team.getWins());
         
         if(team.getWins().equals(checkWins)) {
             out.println(rank + ". "
                     + team.getName() + ", " //
                     + team.getOwner() + ", " //
                     + checkWins + "<br>");
         }
         else {
        	 checkWins = team.getWins();
        	 rank++;
             out.println(rank + ". "
                     + team.getName() + ", " //
                     + team.getOwner() + ", " //
                     + checkWins + "<br>");
         }
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
