package service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActiuneLogger {

    private static final String FILE_NAME = "actiuni_log.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String denumireActiune) {
        String timestamp = LocalDateTime.now().format(formatter);
        String linie = denumireActiune + "," + timestamp;

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(linie + "\n");
        } catch (IOException e) {
            System.out.println("Eroare la scrierea în fișierul de log: " + e.getMessage());
        }
    }
}
