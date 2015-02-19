using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Serialization;

namespace Client
{
    [XmlRootAttribute("rss")]
    public class RssFeed
    {
        [XmlElement("channel")]
        public Channel channel;

        [Serializable()]
        public class Channel
        {
            [XmlElement("link")]
            public string link;

            [XmlElement("title")]
            public string title;

            [XmlElement("description")]
            public string description;

            [XmlElement("item")]
            public Item[] item;

        }
 
    }

    [Serializable()]
    public class Item
    {
        [XmlElement("title")]
        public string title;

        [XmlElement("description")]
        public string description;

        [XmlElement("link")]
        public string link;

        [XmlElement("comments")]
        public string comments;

        [XmlElement("enclosure")]
        public Enclosure enclosure;
    }

    [Serializable()]
    public class Enclosure
    {
        [XmlElement("type")]
        public string type;

        [XmlElement("url")]
        public string url;

        [XmlElement("length")]
        public int length;
    }
}
