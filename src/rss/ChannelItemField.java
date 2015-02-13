package rss;

/**
 * Created by Sergio on 2/11/2015.
 */
public class ChannelItemField {
    private String name;
    private String value;

    public ChannelItemField(String name, String value){
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
