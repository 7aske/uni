namespace cs322_dz02_nikola_tasic_3698 {
	partial class Form1 {
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing) {
			if (disposing && (components != null)) {
				components.Dispose();
			}

			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent() {
			this.first_name_text = new System.Windows.Forms.TextBox();
			this.last_name_text = new System.Windows.Forms.TextBox();
			this.index_text = new System.Windows.Forms.TextBox();
			this.save_btn = new System.Windows.Forms.Button();
			this.first_name_label = new System.Windows.Forms.Label();
			this.last_name_label = new System.Windows.Forms.Label();
			this.index_label = new System.Windows.Forms.Label();
			this.SuspendLayout();
			// 
			// first_name_text
			// 
			this.first_name_text.Location = new System.Drawing.Point(103, 12);
			this.first_name_text.Name = "first_name_text";
			this.first_name_text.Size = new System.Drawing.Size(100, 20);
			this.first_name_text.TabIndex = 0;
			// 
			// last_name_text
			// 
			this.last_name_text.Location = new System.Drawing.Point(103, 38);
			this.last_name_text.Name = "last_name_text";
			this.last_name_text.Size = new System.Drawing.Size(100, 20);
			this.last_name_text.TabIndex = 1;
			// 
			// index_text
			// 
			this.index_text.Location = new System.Drawing.Point(103, 64);
			this.index_text.Name = "index_text";
			this.index_text.Size = new System.Drawing.Size(100, 20);
			this.index_text.TabIndex = 2;
			// 
			// save_btn
			// 
			this.save_btn.Location = new System.Drawing.Point(56, 101);
			this.save_btn.Name = "save_btn";
			this.save_btn.Size = new System.Drawing.Size(75, 23);
			this.save_btn.TabIndex = 3;
			this.save_btn.Text = "Sacuvaj";
			this.save_btn.UseVisualStyleBackColor = true;
			this.save_btn.Click += new System.EventHandler(this.saveButtonClick);
			// 
			// first_name_label
			// 
			this.first_name_label.Location = new System.Drawing.Point(12, 15);
			this.first_name_label.Name = "first_name_label";
			this.first_name_label.Size = new System.Drawing.Size(85, 15);
			this.first_name_label.TabIndex = 4;
			this.first_name_label.Text = "Ime";
			// 
			// last_name_label
			// 
			this.last_name_label.Location = new System.Drawing.Point(12, 41);
			this.last_name_label.Name = "last_name_label";
			this.last_name_label.Size = new System.Drawing.Size(85, 15);
			this.last_name_label.TabIndex = 5;
			this.last_name_label.Text = "Prezime";
			// 
			// index_label
			// 
			this.index_label.Location = new System.Drawing.Point(12, 67);
			this.index_label.Name = "index_label";
			this.index_label.Size = new System.Drawing.Size(85, 15);
			this.index_label.TabIndex = 6;
			this.index_label.Text = "Indeks";
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(800, 450);
			this.Controls.Add(this.index_label);
			this.Controls.Add(this.last_name_label);
			this.Controls.Add(this.first_name_label);
			this.Controls.Add(this.save_btn);
			this.Controls.Add(this.index_text);
			this.Controls.Add(this.last_name_text);
			this.Controls.Add(this.first_name_text);
			this.Name = "Form1";
			this.Text = "Form1";
			this.ResumeLayout(false);
			this.PerformLayout();
		}

		private System.Windows.Forms.TextBox first_name_text;
		private System.Windows.Forms.TextBox last_name_text;
		private System.Windows.Forms.TextBox index_text;
		private System.Windows.Forms.Button save_btn;
		private System.Windows.Forms.Label first_name_label;
		private System.Windows.Forms.Label last_name_label;
		private System.Windows.Forms.Label index_label;

		#endregion
	}
}