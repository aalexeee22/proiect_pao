package service;

import model.Utilizator;

import java.util.List;
import java.util.Scanner;

public class Autentificare {
    private final UtilizatorRepository utilizatorRepo;

    public Autentificare(UtilizatorRepository utilizatorRepo) {
        this.utilizatorRepo = utilizatorRepo;
    }

    public Utilizator login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Parolă: ");
        String parola = scanner.nextLine();

        List<Utilizator> utilizatori = utilizatorRepo.getUtilizatori();
        for (Utilizator u : utilizatori) {
            if (u.getEmail().equals(email) && u.getParola().equals(parola)) {
                System.out.println("Autentificare reușită!");
                return u;
            }
        }

        System.out.println("Autentificare eșuată.");
        return null;
    }

    public void inregistrare() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Parolă: ");
        String parola = scanner.nextLine();

        utilizatorRepo.adaugaUtilizator(new Utilizator(0, username, email, parola));
        System.out.println("Utilizator înregistrat cu succes.");
    }
}
