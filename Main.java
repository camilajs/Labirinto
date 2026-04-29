import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws IOException, Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo  = scanner.nextLine();
        
        Labirinto lab = new Labirinto(nomeArquivo);
        lab.resolve();
    }
}
