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
    private List<ChannelItem> items = new ArrayList<ChannelItem>();


    public RssChannel setLink(String link) {
        this.link = link;
        return this;
    }

    public RssChannel setLastBuildDate(Date lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
        return this;
    }

    public String getTitle()
    {
        return title;
    }

    public RssChannel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLink()
    {
        return link;
    }

    public String getDescription()
    {
        return description;
    }

    public RssChannel setDescription( String description )
    {
        this.description = description;
        return this;
    }

    public Date getLastBuildDate()
    {
        return lastBuildDate;
    }

    public List<ChannelItem> getItems()
    {
        return items;
    }

    public RssChannel setItems(List<ChannelItem> items) {
        this.items = items;
        return this;
    }
}
