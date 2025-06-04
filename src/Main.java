import service.*;

public class Main {
    public static void main(String[] args) {
        ActiuneLogger.log("pornire_aplicatie");
        AplicatieService service = new AplicatieService();
        Autentificare autentificare = new Autentificare(service.utilizatorRepo);
        Meniu.start(service, autentificare);
    }
}
