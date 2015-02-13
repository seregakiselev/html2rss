package util;

import rss.HtmlParser;
import rss.RssBuilder;
import rss.RssChannel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
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
            HtmlParser parser = new HtmlParser(link);
            RssBuilder builder = new RssBuilder();
            RssChannel channel = parser.parse();
            channel.setLink(link).setLastBuildDate(new Date(System.currentTimeMillis()));
            builder.setRssChannel(channel);
            ByteArrayOutputStream stream = getRssOutputStream(builder);
            response.setContentLength(stream.size());
            response.setContentType("text/xml;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(stream.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static ByteArrayOutputStream getRssOutputStream(RssBuilder builder)
            throws IOException {
        String body = builder.buildRss();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(body.getBytes("UTF-8"));
        stream.flush();
        return stream;
    }
}
