using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Client
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        public Form2(string title, string description, string comments)
        {
            InitializeComponent();
            textBox1.Text = title;
            textBox2.Text = description;
            textBox3.Text = comments;
        }
    }
}
