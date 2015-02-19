using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Client.ServiceReference1;
using System.Xml.Serialization;
using System.IO;

namespace Client
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string url = textBox1.Text;
            RssServiceClient client = new RssServiceClient();
            string result = client.getXML(url);
            XmlSerializer serializer = new XmlSerializer(typeof(RssFeed));
            RssFeed feed = (RssFeed)serializer.Deserialize(GenerateStreamFromString(result));
            Parse(feed);
        }

        public Stream GenerateStreamFromString(string s)
        {
            MemoryStream stream = new MemoryStream();
            StreamWriter writer = new StreamWriter(stream);
            writer.Write(s);
            writer.Flush();
            stream.Position = 0;
            return stream;
        }

        public void Parse(RssFeed feed)
        {
            listView1.View = View.Details;
           
            // Select the item and subitems when selection is made.
            listView1.FullRowSelect = true;
            // Display grid lines.
            listView1.GridLines = true;
            // Sort the items in the list in ascending order.
            listView1.Sorting = SortOrder.Ascending;
            foreach (Item item in feed.channel.item)
            {
                ListViewItem item1 = new ListViewItem(item.title);
                // Place a check mark next to the item.
                item1.SubItems.Add(item.description);
                item1.SubItems.Add(item.link);
                item1.SubItems.Add(item.comments);
                listView1.Items.Add(item1);
            }

            
            listView1.Columns.Add("Title", -2, HorizontalAlignment.Left);
            listView1.Columns.Add("Description", -2, HorizontalAlignment.Left);
            listView1.Columns.Add("Link", -2, HorizontalAlignment.Left);
            listView1.Columns.Add("Comments", -2, HorizontalAlignment.Center);
            listView1.Show();
        }

        private void listView1_ItemSelectionChanged(object sender, ListViewItemSelectionChangedEventArgs e)
        {
            
        }

        private void listView1_SelectedIndexChanged(object sender, EventArgs e)
        {
            ListViewItem viewItem = ((ListView)sender).SelectedItems[0];
            string title = viewItem.SubItems[0].Text;
            string description = viewItem.SubItems[1].Text;
            string comments = viewItem.SubItems[3].Text;
            Form2 form = new Form2(title, description, comments);
            form.Show();
        }

    }
}
