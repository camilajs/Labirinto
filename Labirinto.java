import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labirinto implements Cloneable{
    private char[][] labirinto;
    private int linhas;
    private int colunas;
    private Pilha<Coordenada> caminho;
    private Pilha<Fila<Coordenada>> possibilidades;

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public Pilha<Coordenada> getCaminho() {
        return caminho;
    }

    public void setCaminho(Pilha<Coordenada> cam) {
        this.caminho = cam;
    }

    public Pilha<Fila<Coordenada>> getPossibilidades() {
        return possibilidades;
    }

    public void setPossibilidades(Pilha<Fila<Coordenada>> possibilidades) {
        this.possibilidades = possibilidades;
    }

    public Labirinto(String arquivo){
        File arq = new File(arquivo);

        try(Scanner leitor = new Scanner(arq)){
            while(leitor.hasNextLine()){
                String dado = leitor.nextLine();
                System.out.println(dado);
            }
        }catch(FileNotFoundException e){
            System.out.println("O arquivo não foi encontrado.");
            e.printStackTrace();
        }
    }

    
}
