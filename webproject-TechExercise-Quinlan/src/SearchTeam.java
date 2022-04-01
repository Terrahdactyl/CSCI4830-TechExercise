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

@WebServlet("/SearchTeam")
public class SearchTeam extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SearchTeam() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "NYL Search Results";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");

      List<Team> listTeams = null;
      if (keyword != null && !keyword.isEmpty()) {
         listTeams = UtilDB.listTeams(keyword);
      } else {
         listTeams = UtilDB.listTeams();
      }
      display(listTeams, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("<a href=/" + projectName + "/" + rankingsName + ">NYL Rankings</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Team> listTeams, PrintWriter out) {
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
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
