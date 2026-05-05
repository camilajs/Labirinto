import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o nome do arquivo: ");
            String nomeArquivo  = scanner.nextLine();
            
            Labirinto lab = new Labirinto(nomeArquivo);
            lab.resolve();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

/// adicionei o try/catch para tratar exceções e evitar que o programa mostre aquele erro grande do Java.
/// agora, quando der problema (ex: sem entrada ou saída), aparece só a mensagem simples para o usuário.
/// removi throws IOException, Exception
/// adicionei  try { ... } catch (Exception e)