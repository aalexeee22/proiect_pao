package service;

import model.Eveniment;
public class AplicatieService {
    public final CategorieRepository categorieRepo = new CategorieRepository();
    public final LocatieRepository locatieRepo = new LocatieRepository();
    public final UtilizatorRepository utilizatorRepo = new UtilizatorRepository();
    public final ClientRepository clientRepo = new ClientRepository();
    public final EvenimentRepository evenimentRepo = new EvenimentRepository();

    public void stergeEveniment(int id) {
        evenimentRepo.stergeEveniment(id);
    }

    public void editeazaEveniment(Eveniment e) {
        evenimentRepo.editeazaEveniment(e);
    }


}
