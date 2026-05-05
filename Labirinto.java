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
      /// VITÓRIA COMEÇA AQUI/// (ProcuraEntrada e validaSaida) no procuraentrada ante ele so pegava uma entrada ai eu ajustei para ele validar se realmente tem uma ///
      /// lançando exceção em caso de erro //// 
      public void procuraEntrada() throws Exception {
    int contador = 0;
    Coordenada entrada = null;

    for (int i = 0; i < getLinhas(); i++) {
        for (int j = 0; j < getColunas(); j++) {
            if (this.labirinto[i][j] == 'E') {
                contador++;
                entrada = new Coordenada<>(i, j);
            }
        }
    }

    if (contador == 0)
        throw new Exception("O labirinto não possui entrada.");

    if (contador > 1)
        throw new Exception("O labirinto possui mais de uma entrada.");

    setAtual(entrada);
}

    /// validaSaida: percorre o labirinto, conta quantas 'S' tem e garante q exista só 1. Se tiver 0 ou mais de 1, lança exceção.
public void validaSaida() throws Exception {
    int contador = 0;

    for (int i = 0; i < getLinhas(); i++) {
        for (int j = 0; j < getColunas(); j++) {
            if (this.labirinto[i][j] == 'S') {
                contador++;
            }
        }
    }

    if (contador == 0)
        throw new Exception("O labirinto não possui saída.");

    if (contador > 1)
        throw new Exception("O labirinto possui mais de uma saída.");
}
// VITÓRIA ENCERRA AQUI///
    public Fila<Coordenada> adicionaAFila() throws Exception{
        try{
            this.fila = new Fila<Coordenada>();
    
            int linhaA = getAtual().getLinha()-1;
            int colunaA = getAtual().getColuna();
            if (linhaA>=0 && (this.labirinto[linhaA][colunaA] == ' ' || this.labirinto[linhaA][colunaA] == 'S')) {
                Coordenada co = new Coordenada<>(linhaA, colunaA);
                fila.guardeUmItem(co);
            }
            linhaA = getAtual().getLinha()+1;
            colunaA = getAtual().getColuna();
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
            linhaA = getAtual().getLinha();
            colunaA = getAtual().getColuna()+1;
            if (colunaA<getColunas() && (this.labirinto[linhaA][colunaA] == ' ' || this.labirinto[linhaA][colunaA] == 'S')) {
                Coordenada co = new Coordenada<>(linhaA, colunaA);
                fila.guardeUmItem(co);
            }

            
        }catch(Exception e){
            System.out.println("Deu erro: ");
            e.printStackTrace();
        }
        return this.fila;
    }

    public void andar(Coordenada p, Fila<Coordenada> f)throws Exception{
        setAtual(p);

        this.labirinto[p.getLinha()][p.getColuna()] = '*';
        this.getCaminho().guardeUmItem(p);
        this.getPossibilidades().guardeUmItem(f);
    }

    public void voltar()throws Exception{
        Coordenada novoAtual = (Coordenada) getCaminho().recupereUmItem();
        setAtual(novoAtual);
        getCaminho().removaUmItem();
        this.labirinto[getAtual().getLinha()][getAtual().getColuna()] = ' ';
        
        this.fila = this.possibilidades.recupereUmItem();
        this.possibilidades.removaUmItem();
    }

    public void resolve()throws Exception{
        procuraEntrada();
        validaSaida(); // (Vitória) Aqui estav faltando adicionar a validação de saida 
        adicionaAFila();

        while(this.labirinto[getAtual().getLinha()][getAtual().getColuna()] != 'S'){
            if (!this.fila.isVazia()) {
                Coordenada passo = this.fila.recupereUmItem();
                this.fila.removaUmItem();

                if(this.labirinto[passo.getLinha()][passo.getColuna()] == 'S'){
                    System.out.println("Saída encontrada.");
                    mostraCaminho();
                    return;
                }else{
                    andar(passo, this.fila);
                    adicionaAFila();
                }
            }
            else{
                if(!getCaminho().isVazia()){
                    voltar();
                }else{
                    System.out.println("O labirinto não possui saída.");
                    return;
                }
                    
            }
        }
    }

    public void mostraCaminho()throws Exception{
        Pilha<Coordenada> inverso = new Pilha<Coordenada>(getLinhas()*getColunas());

        while (!this.getCaminho().isVazia()) {
            inverso.guardeUmItem(getCaminho().recupereUmItem());
            getCaminho().removaUmItem();
        }

        while (!inverso.isVazia()) {
            System.out.println(inverso.recupereUmItem());
            inverso.removaUmItem();
        }
        
    }
    //metodo de progressao
    //metodo de regressao

    
}
