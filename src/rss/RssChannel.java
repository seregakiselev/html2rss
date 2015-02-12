package rss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sergio on 2/11/2015.
 */
public class RssChannel {
    private String link;
    private Date lastBuildDate;

    private String title = "";
    private String description = "";
    private Image image = null;
    private List<ChannelItem> items = new ArrayList<ChannelItem>();

    public RssChannel( String link, Date lastBuildDate )
    {
        this.link = link;
        this.lastBuildDate = lastBuildDate;
        //a bit of lambda
        //Runnable r2 = () -> System.out.println("Hello world two!");
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getLink()
    {
        return link;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public Date getLastBuildDate()
    {
        return lastBuildDate;
    }

    public List<ChannelItem> getItems()
    {
        return items;
    }

    public Image getImage()
    {
        return image;
    }
}
