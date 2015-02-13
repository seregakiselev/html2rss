package rss;

/**
 * Created by Victoria on 13.02.2015.
 */
public class Enclosure {
    public long getLength() {
        return length;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    private long length;
    private String url, type;

    public Enclosure(String url, String type, long length){
        this.url = url;
        this.type = type;
        this.length = length;
    }
}
