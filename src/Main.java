import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // JAVA
        // MySQL, MariaDB non a TPSIT
        // utilizzeremo SQLite (+Andorid), database salvato su unico file, + leggero,
        // tra JAVA e DB c'è il JDBC (Api di sistema)   Java <-> JDBC <-> DB

        Database db = null;
        try{
            db = new Database();
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            System.exit(-1);
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci il nome del piatto da inserire: ");
        String nomePiatto = scan.nextLine();
        System.out.println("Inserisci il prezzo del piatto da inserire: ");
        float prezzo = scan.nextFloat();
        scan.nextLine();
        System.out.println("Inserisci la quanità del piatto da inserire: ");
        int quantita = scan.nextInt();
        scan.nextLine();



        if(db.insert(nomePiatto, prezzo, quantita))
            System.out.println("Piatto inserito con successo");



        System.out.println(db.selectAll());
        /* per prox volta:
            completare esercizio con menu a scelta che esegua le operazioni CRUD
            consegnare  propria repository

        */
    }
}