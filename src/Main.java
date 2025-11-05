/*
__________        _____                           ___________            .__              
\______   \ _____/ ____\____  ______ ____  ____   \_   _____/ ___________|__| ____  ____  
 |       _// __ \   __\/  _ \/  ___// ___\/  _ \   |    __)_ /    \_  __ \  |/ ___\/  _ \ 
 |    |   \  ___/|  | (  <_> )___ \\  \__(  <_> )  |        \   |  \  | \/  \  \__(  <_> )
 |____|_  /\___  >__|  \____/____  >\___  >____/  /_______  /___|  /__|  |__|\___  >____/ 
        \/     \/                \/     \/                \/     \/              \/       
Classe 5BII
Informatica
*/

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
            // design pattern = soluzione logica a un problema ricorrente
            // esempi: builder, factory, odserver (23)
            // useremo Singleton
            db = Database.getIstance();
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            System.exit(-1);
        }

        Scanner scan = new Scanner(System.in);

        System.out.println(db.selectAll());
        /* per prox volta:
            completare esercizio con menu a scelta che esegua le operazioni CRUD (create read update delete)
            es premo 1 inserisco, 2 update, 3 cancello ecc.
            consegnare  propria repository

        */
        System.out.println("MENU Operazioni CRUD\n1. Insert\n2. Update\n3. Delete\n4. Select\n5. Exit");
		System.out.print("Scelta (numero): ");
		int choice = scan.nextInt();
		System.out.println("-----------------------------------------");

        String nomePiatto;
        float prezzo = 0;
        int quantita = 0;
		switch(choice){
			case 1:
                System.out.println("1.Inserisci Dato");
				System.out.println("Inserisci il nome del piatto da inserire: ");
                scan.nextLine();
                nomePiatto = scan.nextLine();
                System.out.println("Inserisci il prezzo del piatto da inserire: ");
                prezzo = scan.nextFloat();
                scan.nextLine();
                System.out.println("Inserisci la quanità del piatto da inserire: ");
                quantita = scan.nextInt();
                scan.nextLine();
 
				if(db.insert(nomePiatto, prezzo, quantita))
                    System.out.println("Piatto inserito con successo");
 
				break;
			case 2:
				System.out.println("2.Aggiorna Dato");
 
				System.out.println("Nome del piatto da aggiornare: ");
                scan.nextLine();
                nomePiatto = scan.nextLine();
                System.out.println("Nuovo prezzo: ");
                prezzo = scan.nextFloat();
                scan.nextLine();
                System.out.println("Nuova quantità: ");
                quantita = scan.nextInt();
                scan.nextLine();
 
				if(db.update(nomePiatto, prezzo, quantita))
                    System.out.println("Piatto aggiornato con successo");
 
				break;
			case 3:
				System.out.println("3. Cancella Dato");
 
				System.out.println("Inserisci nome: ");
                scan.nextLine();
				nomePiatto = scan.nextLine();
 
				if(db.delete(nomePiatto))
                    System.out.println("Piatto cancellato con successo");
 
				break;
			case 4:
				System.out.println("4. Stampa tutti i Record");
				if(db.print())
                    System.out.println("Stampa eseguita con successo");

				break;
			case 5:
				System.out.println("Uscita. Grazie.");
				System.exit(0);
				break;
			default:
				System.out.println("Selezione non valida");
				break;
			}
			System.out.println("-----------------------------------------");
    }
}