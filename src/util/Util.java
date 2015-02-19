package util;

import rss.HtmlParser;
import rss.RssBuilder;
import rss.RssChannel;

import javax.servlet.ServletOutputStream;
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
            String body = getRssBody(link);
            ByteArrayOutputStream stream = getRssOutputStream(body);
            response.setContentLength(stream.size());
            response.setContentType("text/xml;charset=utf-8");
            ServletOutputStream writer = response.getOutputStream();
            writer.write(stream.toByteArray());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRssBody(String link) {
        HtmlParser parser = new HtmlParser(link);
        RssBuilder builder = new RssBuilder();
        RssChannel channel = parser.parse();
        channel.setLink(link).setLastBuildDate(new Date(System.currentTimeMillis()));
        builder.setRssChannel(channel);
        return builder.buildRss();
    }

    protected static ByteArrayOutputStream getRssOutputStream(String body) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(body.getBytes("UTF-8"));
        stream.flush();
        return stream;
    }
}
