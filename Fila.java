public class Fila<X> implements Cloneable {
    private Object[] elemento;
    private int  inicio = 0, fim = 0, qtd = 0;
    private Clonador<X> clonador;

    public Fila() throws Exception {
        this.elemento = new Object[3];
        this.clonador = new Clonador<X>();
    }

    public void guardeUmItem(X x) throws Exception {
        if (x == null)
            throw new Exception("Falta o que guardar");

        if (x instanceof Cloneable)
            this.elemento[fim] = this.clonador.clone(x);
        else
            this.elemento[fim] = x;
        this.fim = this.fim == this.elemento.length - 1 ? 0 : this.fim + 1;
        this.qtd++;
    }

    public void removaUmItem() throws Exception {
        if (this.qtd == 0)
            throw new Exception("Nada a remover");

        this.elemento[this.inicio] = null;
        this.inicio = this.inicio == this.elemento.length - 1 ? 0 : this.inicio + 1;
        this.qtd--;
    }

    public X recupereUmItem() throws Exception {
        if (this.qtd == 0)
            throw new Exception("Nada a recuperar");

        X ret = null;
        if (this.elemento[this.inicio] instanceof Cloneable)
            ret = this.clonador.clone((X)this.elemento[this.inicio]);
        else
            ret = (X)this.elemento[this.inicio];

        return ret;
    }

    public boolean isVazia() {
        return this.qtd == 0;
    }

    @Override
    public String toString() {
        String ret = this.qtd + " elementos";
        if (this.qtd > 0) ret += ", primeiro: " + this.elemento[this.inicio];
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Fila<X> f = (Fila<X>) obj;
        if (this.qtd != f.qtd) return false;
        
        int t = this.inicio, o = f.inicio;
        for (int i = 0; i < this.qtd; i++) {
            if (!this.elemento[t].equals(f.elemento[o])) return false;
            t = t == this.elemento.length - 1 ? 0 : t + 1;
            o = o == f.elemento.length - 1 ? 0 : o + 1;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int ret = 666;
        ret = ret * 7 + Integer.valueOf(this.qtd).hashCode();
        int atual = this.inicio;
        for (int i = 0; i < this.qtd; i++) {
            ret = ret * 7 + this.elemento[atual].hashCode();
            atual = atual == this.elemento.length - 1 ? 0 : atual + 1;
        }
        return ret < 0 ? -ret : ret;
    }

    public Fila(Fila<X> modelo) throws Exception {
        if (modelo == null) throw new Exception("Modelo ausente");
        this.qtd = modelo.qtd;
        this.inicio = 0;
        this.fim = modelo.qtd;
        this.clonador = modelo.clonador;
        this.elemento = new Object[modelo.elemento.length];
        int am = modelo.inicio;
        for (int i = 0; i < modelo.qtd; i++) {
            this.elemento[i] = modelo.elemento[am];
            am = am == modelo.elemento.length - 1 ? 0 : am + 1;
        }
    }

    @Override
    public Object clone() {
        Fila<X> ret = null;
        try { ret = new Fila<X>(this); } catch (Exception e) {}
        return ret;
    }
}