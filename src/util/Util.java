package util;

import rss.RssBuilder;
import rss.RssChannel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Sergio on 2/12/2015.
 */
public class Util {
    public static void getXml(HttpServletRequest request, HttpServletResponse response) {
        try {
            String link = Misc.norm(request.getParameter("pageUrl"));
            //TODO: parse site html
            RssBuilder builder = new RssBuilder();
            RssChannel channel = new RssChannel();
            channel.setLink(link).setLastBuildDate(new Date(System.currentTimeMillis()));
            builder.setRssChannel(channel);
            PrintWriter out = response.getWriter();
            out.println(builder.buildRss());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
