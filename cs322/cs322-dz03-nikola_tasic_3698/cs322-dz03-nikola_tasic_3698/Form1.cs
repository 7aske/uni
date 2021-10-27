using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace cs322_dz03_nikola_tasic_3698 {
	public partial class Form1 : Form {
		public Form1() {
			InitializeComponent();
			label1.VisibleChanged += (o, args) => { button1.Text = label1.Visible ? "Hide Label" : "Show Label"; };
		}

		// Toggle prikaza labele
		private void button1_Click(object sender, EventArgs e) {
			label1.Visible = !label1.Visible;
		}

		// Prikaz slike
		private void button2_Click(object sender, EventArgs e) {
			var dialog = new OpenFileDialog();
			var res = dialog.ShowDialog();
			pictureBox1.SizeMode = PictureBoxSizeMode.StretchImage;
			if (res == DialogResult.OK) {
				pictureBox1.Image = Image.FromFile(dialog.FileName);
				label2.Text = dialog.FileName;
			} else {
				pictureBox1.Image = null;
			}
		}
	}
}