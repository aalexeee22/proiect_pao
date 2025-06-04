package service;

import model.*;

import java.time.LocalDate;
import java.util.*;

public class Meniu {

    public static void start(AplicatieService service, Autentificare autentificare) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Bine ai venit ---");
            System.out.println("1. Utilizator");
            System.out.println("2. Client");
            System.out.println("0. Ieșire");
            System.out.print("Alege opțiunea: ");
            String opt = scanner.nextLine();

            switch (opt) {
                case "1" -> meniuUtilizator(scanner, service, autentificare);
                case "2" -> meniuClient(scanner, service);
                case "0" -> {
                    ActiuneLogger.log("iesire_aplicatie");
                    running = false;
                }
                default -> System.out.println("Opțiune invalidă.");
            }
        }

        System.out.println("La revedere!");
    }

    private static void meniuUtilizator(Scanner scanner, AplicatieService service, Autentificare auth) {
        boolean active = true;
        Utilizator utilizatorLogat = null;

        while (active) {
            if (utilizatorLogat == null) {
                System.out.println("\n--- Meniu Utilizator ---");
                System.out.println("1. Autentificare");
                System.out.println("2. Creează cont");
                System.out.println("0. Înapoi");
                System.out.print("Alege opțiunea: ");
                String opt = scanner.nextLine();

                switch (opt) {
                    case "1" -> {
                        utilizatorLogat = auth.login();
                        if (utilizatorLogat != null) ActiuneLogger.log("autentificare_utilizator");
                    }
                    case "2" -> {
                        auth.inregistrare();
                        ActiuneLogger.log("creare_utilizator");
                        utilizatorLogat = auth.login();
                    }
                    case "0" -> active = false;
                    default -> System.out.println("Opțiune invalidă.");
                }
            } else {
                System.out.println("\n--- Meniu Utilizator Autentificat ---");
                System.out.println("1. Vizualizează categorii");
                System.out.println("2. Vizualizează locații");
                System.out.println("3. Vizualizează evenimente");
                System.out.println("0. Delogare");
                System.out.print("Alege opțiunea: ");
                String opt = scanner.nextLine();

                switch (opt) {
                    case "1" -> {
                        afiseazaCategoriiSortate(service);
                        ActiuneLogger.log("vizualizare_categorii");
                    }
                    case "2" -> {
                        afiseazaLista("locații", service.locatieRepo.getLocatii());
                        ActiuneLogger.log("vizualizare_locatii");
                    }
                    case "3" -> {
                        afiseazaLista("evenimente", service.evenimentRepo.getEvenimente());
                        ActiuneLogger.log("vizualizare_evenimente");
                    }
                    case "0" -> {
                        utilizatorLogat = null;
                        ActiuneLogger.log("delogare_utilizator");
                        System.out.println("Delogat cu succes.");
                    }
                    default -> System.out.println("Opțiune invalidă.");
                }
            }
        }
    }

    private static void meniuClient(Scanner scanner, AplicatieService service) {
        boolean active = true;
        Client clientLogat = null;

        while (active) {
            if (clientLogat == null) {
                System.out.println("\n--- Meniu Client ---");
                System.out.println("1. Autentificare (email + parolă)");
                System.out.println("2. Creează client nou (cu ID utilizator existent)");
                System.out.println("0. Înapoi");
                System.out.print("Alege opțiunea: ");
                String opt = scanner.nextLine();

                switch (opt) {
                    case "1" -> {
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Parolă: ");
                        String parola = scanner.nextLine();

                        Utilizator utilizator = service.utilizatorRepo.getUtilizatori().stream()
                                .filter(u -> u.getEmail().equals(email) && u.getParola().equals(parola))
                                .findFirst().orElse(null);

                        if (utilizator == null) {
                            System.out.println("Autentificare eșuată.");
                        } else {
                            clientLogat = service.clientRepo.getClienti().stream()
                                    .filter(c -> c.getUtilizator().getId() == utilizator.getId())
                                    .findFirst().orElse(null);
                            if (clientLogat == null) {
                                System.out.println("Nu există client asociat acestui utilizator.");
                            } else {
                                System.out.println("Autentificat ca: " + clientLogat.getNume() + " " + clientLogat.getPrenume());
                                ActiuneLogger.log("autentificare_client");
                            }
                        }
                    }
                    case "2" -> {
                        System.out.print("ID utilizator existent: ");
                        int uid = Integer.parseInt(scanner.nextLine());
                        Utilizator utilizator = service.utilizatorRepo.getUtilizatori().stream()
                                .filter(u -> u.getId() == uid)
                                .findFirst().orElse(null);
                        if (utilizator == null) {
                            System.out.println("ID-ul de utilizator nu există.");
                            break;
                        }
                        System.out.print("Nume: ");
                        String nume = scanner.nextLine();
                        System.out.print("Prenume: ");
                        String prenume = scanner.nextLine();
                        service.clientRepo.adaugaClient(new Client(0, utilizator, nume, prenume));
                        ActiuneLogger.log("creare_client");
                        System.out.println("Client creat.");
                    }
                    case "0" -> active = false;
                    default -> System.out.println("Opțiune invalidă.");
                }
            } else {
                System.out.println("\n--- Meniu Client Autentificat ---");
                System.out.println("1. Adaugă categorie");
                System.out.println("2. Adaugă locație");
                System.out.println("3. Adaugă eveniment");
                System.out.println("4. Vizualizează categorii");
                System.out.println("5. Vizualizează locații");
                System.out.println("6. Vizualizează utilizatori");
                System.out.println("7. Vizualizează clienți");
                System.out.println("8. Vizualizează evenimente");
                System.out.println("9. Șterge eveniment");
                System.out.println("10. Editează eveniment");
                System.out.println("0. Delogare");
                System.out.print("Alege opțiunea: ");
                String opt = scanner.nextLine();

                switch (opt) {
                    case "1" -> {
                        System.out.print("Tip: ");
                        String tip = scanner.nextLine();
                        System.out.print("Preț: ");
                        double pret = Double.parseDouble(scanner.nextLine());
                        service.categorieRepo.adaugaCategorie(new Categorie(0, tip, pret));
                        ActiuneLogger.log("adauga_categorie");
                    }
                    case "2" -> {
                        System.out.print("Nume locație: ");
                        String nume = scanner.nextLine();
                        System.out.print("Adresă: ");
                        String adresa = scanner.nextLine();
                        service.locatieRepo.adaugaLocatie(new Locatie(0, nume, adresa));
                        ActiuneLogger.log("adauga_locatie");
                    }
                    case "3" -> {
                        System.out.print("Nume eveniment: ");
                        String nume = scanner.nextLine();
                        System.out.print("ID categorie: ");
                        int cat = Integer.parseInt(scanner.nextLine());
                        System.out.print("ID locație: ");
                        int loc = Integer.parseInt(scanner.nextLine());
                        System.out.print("Dată (YYYY-MM-DD): ");
                        LocalDate data = LocalDate.parse(scanner.nextLine());
                        service.evenimentRepo.adaugaEveniment(new Eveniment(0, nume, cat, loc, data));
                        ActiuneLogger.log("adauga_eveniment");
                    }
                    case "4" -> {
                        afiseazaCategoriiSortate(service);
                        ActiuneLogger.log("vizualizare_categorii");
                    }
                    case "5" -> {
                        afiseazaLista("locații", service.locatieRepo.getLocatii());
                        ActiuneLogger.log("vizualizare_locatii");
                    }
                    case "6" -> {
                        afiseazaLista("utilizatori", service.utilizatorRepo.getUtilizatori());
                        ActiuneLogger.log("vizualizare_utilizatori");
                    }
                    case "7" -> {
                        afiseazaLista("clienți", service.clientRepo.getClienti());
                        ActiuneLogger.log("vizualizare_clienti");
                    }
                    case "8" -> {
                        afiseazaLista("evenimente", service.evenimentRepo.getEvenimente());
                        ActiuneLogger.log("vizualizare_evenimente");
                    }
                    case "9" -> {
                        System.out.print("ID eveniment de șters: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        service.stergeEveniment(id);
                        ActiuneLogger.log("sterge_eveniment");
                    }
                    case "10" -> {
                        System.out.print("ID eveniment de editat: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Eveniment existent = service.evenimentRepo.getEvenimente().stream()
                                .filter(e -> e.getId() == id).findFirst().orElse(null);
                        if (existent == null) {
                            System.out.println("Evenimentul nu există.");
                            break;
                        }
                        System.out.print("Nume nou (" + existent.getNume() + "): ");
                        String nume = scanner.nextLine();
                        if (nume.isBlank()) nume = existent.getNume();
                        System.out.print("ID categorie: ");
                        String catStr = scanner.nextLine();
                        int cat = catStr.isBlank() ? existent.getCodCategorie() : Integer.parseInt(catStr);
                        System.out.print("ID locație: ");
                        String locStr = scanner.nextLine();
                        int loc = locStr.isBlank() ? existent.getCodLocatie() : Integer.parseInt(locStr);
                        System.out.print("Dată nouă: ");
                        String dataStr = scanner.nextLine();
                        LocalDate data = dataStr.isBlank() ? existent.getData() : LocalDate.parse(dataStr);
                        service.editeazaEveniment(new Eveniment(id, nume, cat, loc, data));
                        ActiuneLogger.log("editeaza_eveniment");
                    }
                    case "0" -> {
                        clientLogat = null;
                        System.out.println("Delogat.");
                        ActiuneLogger.log("delogare_client");
                    }
                    default -> System.out.println("Opțiune invalidă.");
                }
            }
        }
    }

    public static <T> void afiseazaLista(String descriere, List<T> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nu există " + descriere + " înregistrate.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    public static void afiseazaCategoriiSortate(AplicatieService service) {
        List<Categorie> categorii = service.categorieRepo.getCategorii();
        if (categorii.isEmpty()) {
            System.out.println("Nu există categorii înregistrate.");
            return;
        }

        PriorityQueue<Categorie> pq = new PriorityQueue<>(Comparator.comparingDouble(Categorie::getPret));
        pq.addAll(categorii);
        System.out.println("Categorii (ordonate după preț):");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
