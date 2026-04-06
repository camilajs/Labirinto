public class Fila<X> implements Cloneable{
    private Object[] elemento;
    private int tamanhoInicial;
    private int qtd;
    private Clonador clonador;

    public Fila(int tamanhoInicial) throws Exception{
        if(tamanhoInicial<=0)
            throw new Exception("Tamanho inválido.");
    }
}
