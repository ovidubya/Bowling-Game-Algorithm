import java.util.Scanner;

public class BowlingAppV2
{
    public static void main(String[] args) {
        int a;
        int f;
        Scanner in = new Scanner(System.in);
        BowlingV2 bw = new BowlingV2("Ovadia");
        System.out.println("Welcome " + bw.returnName() + " to bowling!");
        System.out.println("Enter how many pins you want to knock down");
        
        while(bw.returnFrame() != 12 || bw.returnRoll() != 2) {
           a = in.nextInt();
           bw.shot(a);
           System.out.println("");
           System.out.println("");
           System.out.println("");
           bw.updateInfo();
        }
        System.out.println("Thank you for playing");
    }
        
}