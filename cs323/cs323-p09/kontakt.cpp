using namespace std;

class Kontakt {
private:
    string ime;
    string prezime;
    string broj;
    string adresa;
public:
    Kontakt(string &ime, string &prezime, string &broj, string &adresa);

    Kontakt(Kontakt &k) {
        cout << "ovo je copy constr\n";
    }

    ~Kontakt();

    friend ostream &operator<<(ostream &output, const Kontakt &k) {
        output << "Ime: " + k.getIme() + " Prezime: " + k.getPrezime() << endl;
        return output;
    }

    const string &getIme() const {
        return ime;
    }

    void setIme(const string &ime) {
        Kontakt::ime = ime;
    }

    const string &getPrezime() const {
        return prezime;
    }

    void setPrezime(const string &prezime) {
        Kontakt::prezime = prezime;
    }

    const string &getBroj() const {
        return broj;
    }

    void setBroj(const string &number) {
        Kontakt::broj = number;
    }

    const string &getAdresa() const {
        return adresa;
    }

    void setAdresa(const string &adresa) {
        Kontakt::adresa = adresa;
    }
};


Kontakt::Kontakt(string &ime, string &prezime, string &broj, string &adresa) {
    this->ime = ime;
    this->prezime = prezime;
    this->broj = broj;
    this->adresa = adresa;
}

Kontakt::~Kontakt() {}
