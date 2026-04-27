import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labirinto implements Cloneable{
    private char[][] labirinto;
    private int linhas;
    private int colunas;
    private Coordenada atual;
    private Pilha<Coordenada> caminho;
    private Pilha<Fila<Coordenada>> possibilidades;

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) throws Exception{
        if(linhas<=0) throw new Exception("Número de linhas inválido.");
        this.linhas = linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) throws Exception {
        if (colunas<=0) throw new Exception("Número de colunas inválido.");
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

    public Labirinto(String arquivo) throws Exception{
        File arq = new File(arquivo);

        try(Scanner leitor = new Scanner(arq)){
            this.setLinhas(leitor.nextInt()); 
            this.setColunas(leitor.nextInt());
            leitor.nextLine();
            this.labirinto = new char[this.getLinhas()][this.getColunas()];
            int l = 0;
            int c = 0;
            while(leitor.hasNextLine()){
                String dado = leitor.nextLine();
                for (int i = 0; i<dado.length(); i++){
                    labirinto[l][c] = dado.charAt(i);
                    c++;
                }
                c=0;
                l++;
            }

            
            this.caminho = new Pilha<>(getLinhas()*getColunas());
            this.possibilidades = new Pilha<>(getLinhas()*getColunas());
        }catch(FileNotFoundException e){
            System.out.println("O arquivo não foi encontrado.");
            e.printStackTrace();
        }
        
    }

    
}
