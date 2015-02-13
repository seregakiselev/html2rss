package rss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victoria on 13.02.2015.
 */
public class HtmlParser {
    Document doc;
    RssChannel channel;
    int maxTitleLength = 97;
    public HtmlParser(String url){
        try {
            doc = Jsoup.connect(url).get();
            channel = new RssChannel();
            channel.setLink(url);
            channel.setLastBuildDate(new Date(System.currentTimeMillis()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RssChannel parse(){
        String title = doc.title();
        channel.setTitle(title);
        Elements descriptions = findElements(doc, "meta[name=description]", "#subtitle");
        channel.setDescription(descriptions.isEmpty() ? "RSS feed for ".concat(title) : descriptions.first().text());

        Elements posts = doc.select("div[class~=.*([P|p]ost)(?!s).*]");
        if (!posts.isEmpty()) {
            Element firstItem = posts.first();
            Elements allItems = firstItem.siblingElements();
            allItems.add(firstItem);
            List<ChannelItem> items = new ArrayList<>();
            for (Element elem : allItems) {
                ChannelItem item = new ChannelItem();
                findItemComments(item, elem);
                findItemAuthor(item, elem);
                findItemPubDate(item, elem);
                elem.select("[class~=.*(meta).*]").remove();
                findItemTitle(item, elem);
                findItemDescription(item, elem);
                findItemEnclosure(item, elem);
                findItemLink(item, elem);
                items.add(item);
            }
            channel.setItems(items);
        }
        return channel;
    }

    private String firstTextNode(Element elem){
        if(elem.textNodes().isEmpty()) return "fuck";
        return elem.textNodes().get(0).text();
    }

    private void findItemComments(ChannelItem item, Element elem){
        Elements comments = findElements(elem, "[class~=.*comment.*]",
                "a[text~=.*(([A|a]nswer)|([C|c]omment)|([К|к]оммент)|([О|о]тзыв)|([О|о]твет)).*]");
        processElements(comments, item, "comments", findLink(comments));
    }

    private void findItemPubDate(ChannelItem item, Element elem) {
        Elements dates = elem.select("[class~=.*((date)|(time)).*]");
        processElements(dates, item, "pubDate", dates.text());
    }

    private void findItemAuthor(ChannelItem item, Element elem) {
        Elements authors = findElements(elem, "[class~=.*author.*]", "a[rel=author]");
        processElements(authors, item, "author", findLink(authors));
    }

    private void findItemEnclosure(ChannelItem item, Element elem){
        Elements images = elem.select("img");
        if(!images.isEmpty()){
            String src = images.first().absUrl("src");
            if(src.toLowerCase().endsWith(".jpg"))
            {
                URL url = null;
                long length = 0;
                try {
                    url = new URL(src);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    length = url.openConnection().getContentLength();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                item.setEnclosure(new Enclosure(src, "image/jpg", length));
            }
        }
    }

    private void findItemTitle(ChannelItem item, Element elem){
        Elements titles = elem.select("[class~=.*((title)|(header)).*]");
        processElements(titles, item, "title", titles.isEmpty() ? titleString(firstTextNode(elem)) : titles.first().text());
    }

    private void findItemDescription(ChannelItem item, Element elem) {
        Elements itemDescriptions = elem.select("[class~=.*((((entry)|(excerpt)).*)|content)]");
        addFieldToItem(item, "description",
                itemDescriptions.isEmpty() ? firstTextNode(elem) : itemDescriptions.first().text());
    }

    private void findItemLink(ChannelItem item, Element elem){
        Elements links = findElements(elem, "a[class~=readmore|read-more|more-link]", "a");
        processElements(links, item, "link", links.isEmpty() ? "" : links.first().attr("href"));
    }

    private void processElements(Elements elements, ChannelItem item, String name, String value){
        if(!elements.isEmpty()){
            addFieldToItem(item, name, value);
            elements.remove();
        }
    }

    private void addFieldToItem(ChannelItem item, String name, String value){
        item.addField(new ChannelItemField(name, value));
    }

    private Elements findElements(Element elem, String firstCondition, String secondCondition){
        Elements elements = elem.select(firstCondition);
        if(elements.isEmpty()){
            elements = elem.select(secondCondition);
        }
        return elements;
    }

    private String findLink(Elements elements){
        if(elements.isEmpty()) return "";
        return elements.first().hasAttr("href")
                ? elements.first().attr("href")
                : elements.select("a").first().attr("href");
    }

    private String titleString(String text){
        if(text.length() > maxTitleLength){
            text = text.substring(0, maxTitleLength).concat("...");
        }
        return text;
    }
}
