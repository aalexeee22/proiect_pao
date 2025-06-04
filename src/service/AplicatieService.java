package service;

import model.*;
import java.util.List;
public class AplicatieService {
    public final CategorieRepository categorieRepo = new CategorieRepository();
    public final LocatieRepository locatieRepo = new LocatieRepository();
    public final UtilizatorRepository utilizatorRepo = new UtilizatorRepository();
    public final ClientRepository clientRepo = new ClientRepository();
    public final EvenimentRepository evenimentRepo = new EvenimentRepository();

    public void adaugaCategorie(Categorie c) {
        categorieRepo.adaugaCategorie(c);
    }

    public List<Categorie> getCategorii() {
        return categorieRepo.getCategorii();
    }

    public void adaugaLocatie(Locatie l) {
        locatieRepo.adaugaLocatie(l);
    }

    public List<Locatie> getLocatii() {
        return locatieRepo.getLocatii();
    }

    public void adaugaEveniment(Eveniment e) {
        evenimentRepo.adaugaEveniment(e);
    }

    public List<Eveniment> getEvenimente() {
        return evenimentRepo.getEvenimente();
    }

    public void stergeEveniment(int id) {
        evenimentRepo.stergeEveniment(id);
    }

    public void editeazaEveniment(Eveniment e) {
        evenimentRepo.editeazaEveniment(e);
    }

    public List<Utilizator> getUtilizatori() {
        return utilizatorRepo.getUtilizatori();
    }

    public void adaugaUtilizator(Utilizator u) {
        utilizatorRepo.adaugaUtilizator(u);
    }

    public void adaugaClient(Client c) {
        clientRepo.adaugaClient(c);
    }

    public List<Client> getClienti() {
        return clientRepo.getClienti();
    }
}
