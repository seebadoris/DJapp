package djapp;

/**
 *
 * @author HenJul
 */
import dbconnect.DbConnect;

public class DJapp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DbConnect db = new DbConnect();
        db.connection();
        db.afficherTable("clients");
        System.out.println("coucou");
    }
    
}
