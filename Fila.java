public class Fila<X> implements Cloneable{
    private Object[] elemento;
    private int  j = 0, fim = 0, qtd = 0;
    private Clonador<X> clonador;

    public Fila() throws Exception{
        this.elemento = new Object[3];
        this.clonador = new Clonador<X>();
    }

    public void guardeUmItem(X x)throws Exception{
        if (x == null)
            throw new Exception("Falta o que guardar");

        if (x instanceof Cloneable)
            this.elemento[fim]=this.clonador.clone(x);
        else
            this.elemento[fim] = x;

        this.fim++;
        this.qtd++;
    }

    public void removaUmItem() throws Exception{
        if (this.qtd == 0)
            throw new Exception("Aqui eu acho q tenho q ver se n tiver nada vai ser pq eu bati na parede");
        
    }
}
