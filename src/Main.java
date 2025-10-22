import java.sql.SQLException;

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

        System.out.println(db.selectAll());

    }
}