
public class Student {
	private String ime;
	private String prezime;
	private String brojIndeksa;
	private String nazivFakulteta;
	private String grad;
	private int godinaUpisa;

	public Student() {
	}

	public Student(String ime, String prezime, String brojIndeksa, String nazivFakulteta, String grad, int godinaUpisa) {
		this.ime = ime;
		this.prezime = prezime;
		this.brojIndeksa = brojIndeksa;
		this.nazivFakulteta = nazivFakulteta;
		this.grad = grad;
		this.godinaUpisa = godinaUpisa;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getBrojIndeksa() {
		return brojIndeksa;
	}

	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}

	public String getNazivFakulteta() {
		return nazivFakulteta;
	}

	public void setNazivFakulteta(String nazivFakulteta) {
		this.nazivFakulteta = nazivFakulteta;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public int getGodinaUpisa() {
		return godinaUpisa;
	}

	public void setGodinaUpisa(int godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}

	@Override
	public String toString() {
		return ime + " " + prezime + " " + brojIndeksa + " " + godinaUpisa + " " + nazivFakulteta + " " + grad;
	}
}
