public class Pilha<X> {
    private Object[] elemento;
    private int tamanhoInicial;
    private int ultimo = -1;
    private Clonador clonador;

    public <X>Pilha()throws Exception{
        this.elemento = new Object[10];

        this.clonador = new Clonador();
    }

    public <X>Pilha(int tamIni) throws Exception{
        if(tamIni <=0)throw new Exception("Tamanho inválido.");
        this.elemento = new Object[tamIni];

        this.tamanhoInicial = tamIni;
    }
}