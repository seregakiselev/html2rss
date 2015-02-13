package rss;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 2/11/2015.
 */
public class ChannelItem {
    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    private Enclosure enclosure;
    private List<ChannelItemField> fields = new ArrayList<ChannelItemField>();

    public List<ChannelItemField> getFields() {
        return fields;
    }

    public void addField(ChannelItemField field) {
        if(field.getValue() != "") {
            fields.add(field);
        }
    }
}
