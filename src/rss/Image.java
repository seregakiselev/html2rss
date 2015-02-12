package rss;

/**
 * Created by Sergio on 2/11/2015.
 */
public class Image {
    public static final String TYPE_POPUP = "popup";
    public static final String TYPE_GADGET = "gadget";
    public static final String TYPE_PREVIEW = "preview";

    private int height;
    private int width;
    private String url;

    private String type = null;

    public Image( int height, int width, String url )
    {
        this.height = height;
        this.width = width;
        this.url = url;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public String getUrl()
    {
        return url;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }
}
