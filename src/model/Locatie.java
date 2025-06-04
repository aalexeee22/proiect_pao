package model;

public class Locatie {
    private int id;
    private String nume;
    private String adresa;

    public Locatie(int id, String nume, String adresa) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    @Override
    public String toString() {
        return "Locatie{id=" + id + ", nume='" + nume + "', adresa='" + adresa + "'}";
    }
}
