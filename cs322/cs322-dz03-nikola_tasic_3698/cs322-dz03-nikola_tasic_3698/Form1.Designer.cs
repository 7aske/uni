namespace cs322_dz03_nikola_tasic_3698 {
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
			this.label1 = new System.Windows.Forms.Label();
			this.button1 = new System.Windows.Forms.Button();
			this.pictureBox1 = new System.Windows.Forms.PictureBox();
			this.button2 = new System.Windows.Forms.Button();
			this.label2 = new System.Windows.Forms.Label();
			((System.ComponentModel.ISupportInitialize) (this.pictureBox1)).BeginInit();
			this.SuspendLayout();
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(30, 24);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(84, 23);
			this.label1.TabIndex = 0;
			this.label1.Text = "Click the button";
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(120, 12);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(84, 42);
			this.button1.TabIndex = 1;
			this.button1.Text = "Click me!";
			this.button1.UseVisualStyleBackColor = true;
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// pictureBox1
			// 
			this.pictureBox1.Location = new System.Drawing.Point(30, 71);
			this.pictureBox1.Name = "pictureBox1";
			this.pictureBox1.Size = new System.Drawing.Size(174, 175);
			this.pictureBox1.TabIndex = 2;
			this.pictureBox1.TabStop = false;
			// 
			// button2
			// 
			this.button2.Location = new System.Drawing.Point(77, 277);
			this.button2.Name = "button2";
			this.button2.Size = new System.Drawing.Size(75, 23);
			this.button2.TabIndex = 3;
			this.button2.Text = "button2";
			this.button2.UseVisualStyleBackColor = true;
			this.button2.Click += new System.EventHandler(this.button2_Click);
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(30, 249);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(165, 25);
			this.label2.TabIndex = 4;
			this.label2.Text = "label2";
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(229, 337);
			this.Controls.Add(this.label2);
			this.Controls.Add(this.button2);
			this.Controls.Add(this.pictureBox1);
			this.Controls.Add(this.button1);
			this.Controls.Add(this.label1);
			this.Name = "Form1";
			this.Text = "Form1";
			((System.ComponentModel.ISupportInitialize) (this.pictureBox1)).EndInit();
			this.ResumeLayout(false);
		}

		private System.Windows.Forms.Label label2;

		private System.Windows.Forms.PictureBox pictureBox1;
		private System.Windows.Forms.Button button2;

		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Button button1;

		#endregion
	}
}