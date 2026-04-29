import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labirinto implements Cloneable{
    private char[][] labirinto;
    private int linhas;
    private int colunas;
    private Coordenada atual;
    private Fila<Coordenada> fila;
    private Pilha<Coordenada> caminho;
    private Pilha<Fila<Coordenada>> possibilidades;

    public Fila<Coordenada> getFila() {
        return fila;
    }

    public void setFila(Fila<Coordenada> fila) {
        this.fila = fila;
    }

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

    public Coordenada getAtual() {
        return atual;
    }

    public void setAtual(Coordenada atual) {
        this.atual = atual;
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
            this.labirinto = new char[getLinhas()][getColunas()];
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

            this.fila = new Fila<Coordenada>();
            this.caminho = new Pilha<Coordenada>(getLinhas()*getColunas());
            this.possibilidades = new Pilha<Fila<Coordenada>>(getLinhas()*getColunas());
        }catch(FileNotFoundException e){
            System.out.println("O arquivo não foi encontrado.");
            e.printStackTrace();
        }
        
    }

    public void procuraEntrada() throws Exception{
        for (int i= 0; i<=getLinhas()-1; i+=getLinhas()-1){
            for (int j = 0; j<getColunas(); j++){
                if (this.labirinto[i][j] == 'E'){
                    Coordenada c = new Coordenada<>(i, j);
                    setAtual(c);
                }
            }
        }
        for (int k= 0; k<=getColunas()-1; k+=getColunas()-1){
            for (int l = 0; l<getLinhas(); l++){
                if (this.labirinto[l][k] == 'E'){
                    Coordenada c = new Coordenada<>(l, k);
                    setAtual(c);
                }
            }
        }

        if (getAtual() == null) throw new Exception("O labirinto não possui entrada.");
    }

    //public char caractere(int lin, int col){
      //  return this.labirinto[lin][col];
    //}

    public Fila<Coordenada> adicionaAFila(){
        try{
            this.fila = new Fila<Coordenada>();
    
            int linhaA = getAtual().getLinha()-1;
            int colunaA = getAtual().getColuna();
            if (linhaA>=0 && (this.labirinto[linhaA][colunaA] == ' ' || this.labirinto[linhaA][colunaA] == 'S')) {
                Coordenada co = new Coordenada<>(linhaA, colunaA);
                fila.guardeUmItem(co);
            }
            linhaA = getAtual().getLinha()+1;
            if (linhaA<getLinhas() && (this.labirinto[linhaA][colunaA] == ' ' || this.labirinto[linhaA][colunaA] == 'S')) {
                Coordenada co = new Coordenada<>(linhaA, colunaA);
                fila.guardeUmItem(co);
            }
            linhaA = getAtual().getLinha();
            colunaA = getAtual().getColuna()-1;
            if (colunaA>=0 && (this.labirinto[linhaA][colunaA] == ' ' || this.labirinto[linhaA][colunaA] == 'S')) {
                Coordenada co = new Coordenada<>(linhaA, colunaA);
                fila.guardeUmItem(co);
            }
            colunaA = getAtual().getColuna()+1;
            if (colunaA<getColunas() && (this.labirinto[linhaA][colunaA] == ' ' || this.labirinto[linhaA][colunaA] == 'S')) {
                Coordenada co = new Coordenada<>(linhaA, colunaA);
                fila.guardeUmItem(co);
            }

            if(fila.isVazia()) return null; 

            
        }catch(Exception e){
            System.out.println("Deu erro nessa bosta aq");
            e.printStackTrace();
        }
        return fila;
    }

    public void andar()throws Exception{

        Fila fil = adicionaAFila();
        Coordenada passo = (Coordenada) fil.recupereUmItem();
        setAtual(passo);

        this.labirinto[passo.getLinha()][passo.getColuna()] = '*';
        this.getCaminho().guardeUmItem(passo);
        this.getPossibilidades().guardeUmItem(fil);
        fil.removaUmItem();
                    
    }

    public void voltar()throws Exception{
        Coordenada passo = (Coordenada) getCaminho().recupereUmItem();
        getCaminho().removaUmItem();
        setAtual(passo);
        this.labirinto[passo.getLinha()][passo.getColuna()] = ' ';

        this.fila = this.possibilidades.recupereUmItem();
        this.possibilidades.removaUmItem();
    }

    public void resolve()throws Exception{
        procuraEntrada();
        do{
            if(adicionaAFila() == null){
                voltar();
            }else{
                andar();
            }
        }
        while(this.labirinto[getAtual().getLinha()][getAtual().getColuna()] != 'S'
                && !this.caminho.isVazia());
        System.out.println(getAtual().getLinha() + ", " + getAtual().getColuna());
        if(this.labirinto[getAtual().getLinha()][getAtual().getColuna()] == 'S'){
            System.out.println("Achou o caminho");
        }
        else{
            System.out.println("O labirinto não possui saída.");
        }
    }
    //metodo de progressao
    //metodo de regressao


    
}
