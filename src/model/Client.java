package model;

import model.Utilizator;
public class Client extends Persoana {
    private int id;
    private Utilizator utilizator; // compoziție

    public Client(int id, Utilizator utilizator, String nume, String prenume) {
        super(nume, prenume);
        this.id = id;
        this.utilizator = utilizator;
    }

    public int getId() {
        return id;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    @Override
    public String toString() {
        return "Client{id=" + id +
                ", utilizator=" + utilizator +
                ", nume=" + nume +
                ", prenume=" + prenume + '}';
    }
}
