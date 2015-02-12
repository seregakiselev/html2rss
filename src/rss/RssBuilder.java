package rss;

import util.Misc;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sergio on 2/11/2015.
 */
public class RssBuilder {
    protected static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    protected static final String XML_TITLE_TAG = "title";
    protected static final String XML_ITEM_TAG = "item";
    protected static final String XML_IMAGE_TAG = "image";
    protected static final String XML_LINK_TAG = "link";
    protected static final String XML_DESCRIPTION_TAG = "description";

    private static final String XML_RSS_VERSION = "<rss version=\"2.0\">";
    private static final String XML_RSS_END = "</rss>";

    private static final String XML_CHANNEL_TAG = "channel";
    protected static final String XML_LAST_BUILD_DATE_TAG = "lastBuildDate";
    private static final String XML_TYPE_TAG = "type";
    protected static final String XML_URL_TAG = "url";
    private static final String XML_HEIGHT_TAG = "height";
    private static final String XML_WIDTH_TAG = "width";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss Z" );

    protected StringBuilder body;
    private long level;
    protected RssChannel channel;

    public void setRssChannel( RssChannel channel )
    {
        this.channel = channel;
    }

    public String buildRss() {
        clear();
        printLn(XML_HEAD);
        printRssVersion();
        openTag(XML_CHANNEL_TAG);
        printRssChannel();
        closeTag( XML_CHANNEL_TAG );
        printLn( XML_RSS_END );
        return body.toString();
    }

    protected void printRssVersion() {
        printLn(XML_RSS_VERSION);
    }

    protected void printRssChannel() {
        if (channel == null) {
            return;
        }
        addTag(XML_TITLE_TAG, channel.getTitle());
        addTag(XML_LINK_TAG, channel.getLink());
        addTag(XML_DESCRIPTION_TAG, channel.getDescription());
        String date = getDateStr(channel.getLastBuildDate());
        addTag(XML_LAST_BUILD_DATE_TAG, date);
        Image image = channel.getImage();
        if (image != null) {
            printImage(image);
        }
        for (ChannelItem channelItem : channel.getItems()) {
            printRssChannelItem(channelItem);
        }
    }

    protected void printRssChannelItem(ChannelItem item) {
        openTag(XML_ITEM_TAG);
        printRssChannelItemBody(item);
        closeTag(XML_ITEM_TAG);
    }

    protected void printRssChannelItemBody(ChannelItem item) {
        for (ChannelItemField field : item.getFields()) {
            addTag(field.getName(), field.getValue());
        }
    }

    protected static String getDateStr( Date date )
    {
        return dateFormat.format(date);
    }

    protected void clear() {
        body = new StringBuilder();
        level = 0;
    }

    protected void printLn(String str) {
        printSpace();
        print(str);
        print("\n");
    }

    private void printSpace() {
        for (int i = 0; i < level; i++) {
            print("\t");
        }
    }

    private void print(String str)
    {
        body.append(str);
    }

    protected void openTag(String name) {
        printLn("<" + name + ">");
        level++;
    }

    protected void closeTag(String name) {
        level--;
        printLn("</" + name + ">");
    }

    protected void addTag(String name, String value) {
        printSpace();
        print("<" + name + ">");
        value = Misc.norm(value);
        value = Misc.encodeTagBodyForXML(value);
        print(value);
        print("</" + name + ">");
        print("\n");
    }

    protected void printImage(Image image) {
        openTag(XML_IMAGE_TAG);
        String type = image.getType();
        if (type != null) {
            addTag(XML_TYPE_TAG, type);
        }
        addTag(XML_URL_TAG, image.getUrl());
        addTag(XML_HEIGHT_TAG, "" + image.getHeight());
        addTag(XML_WIDTH_TAG, "" + image.getWidth());
        closeTag(XML_IMAGE_TAG);
    }
}
