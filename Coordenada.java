public class Coordenada<X> {
    private int linha;
    private int coluna;

    public Coordenada(int lin, int col){
        this.linha= lin;
        this.coluna = col;
    }
    public int getLinha() {
        return linha;
    }
    public void setLinha(int linha) {
        this.linha = linha;
    }
    public int getColuna() {
        return coluna;
    }
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    @Override
    public int hashCode() {
        int ret = 3;

        ret = ret*7+((Integer)(this.linha)).hashCode();
        ret = ret*7+((Integer)(this.coluna)).hashCode();

        if (ret<0)
            ret = -ret;

        return ret;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj==this)
            return true;

        if(obj==null)
            return false;

        if(obj.getClass()!=this.getClass())
            return false;

        Coordenada<X> coord = (Coordenada<X>) obj;
        if(this.linha!=coord.linha)
            return false;
        if (this.coluna!=coord.coluna)
            return false;
        
        return true;
    }
    

    @Override
    public String toString(){
        return "Linha: " + this.linha +
                "Coluna: " + this.coluna;
    }
}
