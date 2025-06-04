package model;

public class Utilizator {
    private int id;
    private String username;
    private String email;
    private String parola;

    public Utilizator(int id, String username, String email, String parola) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.parola = parola;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getParola() {
        return parola;
    }

    @Override
    public String toString() {
        return "Utilizator{id=" + id + ", username='" + username + "', email='" + email + "'}";
    }
}
