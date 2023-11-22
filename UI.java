import java.io.IOException;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) throws IOException {
        menu();
    }
    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        do {
            try {
                System.out.println();
                System.out.println("**********************");
                System.out.println("Svømmeklubben Delfinen");
                System.out.println("**********************");
                System.out.println();
                System.out.println("1. Medlemsadministration");
                System.out.println("1.1 Opret medlem");
                System.out.println("1.2 Opdater stamoplysninger for medlem");
                System.out.println("1.3 Generer oversigt over nuværende medlemmer");
                System.out.println();
                System.out.println("2. Trænermenu");
                System.out.println("2.1 Tilføj svømmere til hold");
                System.out.println("2.2 Registrer discipliner");
                System.out.println("2.3 Registrer konkurrencedeltagere");
                System.out.println("2.4 Generer top 5 oversigt");
                System.out.println();
                System.out.println("3. Kasserermenu");
                System.out.println("3.1 Vis medlemmer i restance");
                System.out.println("3.2 Opdater kontigentbetaling");
                System.out.println();
                System.out.println("4. Afslut program\n");
                System.out.print("Indtast venligst et tal mellem 1-4 \n");
                choice = scanner.nextInt();
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println("An error has occurred " + e.getMessage());
                scanner.nextLine();
            }
        }while (choice != 4) ;
        scanner.close();
    }
}
