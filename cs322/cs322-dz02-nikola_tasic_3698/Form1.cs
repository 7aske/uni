using System;
using System.IO;
using System.Windows.Forms;

namespace cs322_dz02_nikola_tasic_3698 {
	public partial class Form1 : Form {
		public Form1() {
			InitializeComponent();
		}

		private void saveButtonClick(object sender, EventArgs e) {
			Button button = (Button) sender;
			string data = "";

			data += "Ime: " + first_name_text.Text + "\n";
			data += "Prezime: " + last_name_text.Text + "\n";
			data += "Indeks: " + index_text.Text + "\n";

			MessageBox.Show(data, "Uneti podaci");

			using (StreamWriter sw = new StreamWriter("out.txt")) {
				sw.Write(data);
			}
		}
	}
}