package model;

public class Categorie {
    private int id;
    private String tip;
    private double pret;

    public Categorie(int id, String tip, double pret) {
        this.id = id;
        this.tip = tip;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public String getTip() {
        return tip;
    }

    public double getPret() {
        return pret;
    }

    @Override
    public String toString() {
        return "Categorie{id=" + id + ", tip='" + tip + "', pret=" + pret + "}";
    }
}
