package model;

import java.time.LocalDate;

public class Eveniment {
    private int id;
    private String nume;
    private int codCategorie; // FK spre categorii.id
    private int codLocatie;   // FK spre locatii.id
    private LocalDate data;

    public Eveniment(int id, String nume, int codCategorie, int codLocatie, LocalDate data) {
        this.id = id;
        this.nume = nume;
        this.codCategorie = codCategorie;
        this.codLocatie = codLocatie;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public int getCodCategorie() {
        return codCategorie;
    }

    public int getCodLocatie() {
        return codLocatie;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Eveniment{id=" + id + ", nume='" + nume + "', codCategorie=" + codCategorie +
                ", codLocatie=" + codLocatie + ", data=" + data + "}";
    }
}
