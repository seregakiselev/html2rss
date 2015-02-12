import rss.RssBuilder;
import rss.RssChannel;
import util.Misc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Sergio on 2/11/2015.
 */
public class RssServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = Misc.norm(request.getParameter("pageUrl"));
        //TODO: parse site html
        RssBuilder builder = new RssBuilder();
        RssChannel channel = new RssChannel("www.google.com", new Date(System.currentTimeMillis()));
        builder.setRssChannel(channel);
        PrintWriter out = response.getWriter();
        out.println(builder.buildRss());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<h1>azazazaza</h1>");
    }
}
