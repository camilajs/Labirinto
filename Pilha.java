public class Pilha<X> {
    private Object[] elemento;
    private int ultimo = -1;
    private Clonador clonador;

    public <X>Pilha(int tamanho)throws Exception{
        if (tamanho<=0) throw new Exception("Tamanho inválido.");
        this.elemento = new Object[tamanho];

        this.clonador = new Clonador();
    }

    public void guardeUmItem(X x)throws Exception{
        this.ultimo++;
        if (x instanceof Cloneable){
            this.elemento[this.ultimo] = this.clonador.clone(x);
        }else{
            this.elemento[this.ultimo]= x;
        }
        
    }

    
    public X recupereUmItem () throws Exception // LIFO
    {
        if (this.ultimo==-1) // vazia
            throw new Exception ("Nada a recuperar");

        X ret=null;
        if (this.elemento[this.ultimo] instanceof Cloneable)
            ret = (X)this.clonador.clone((X)this.elemento[this.ultimo]);
            ret = (X)this.elemento[this.ultimo];

        return ret;
    }

    public void removaUmItem()throws Exception{
        if(this.ultimo == -1) 
            throw new Exception("Acho q aq vai ver se tem mais possibilidades ou nao");
        this.elemento[this.ultimo] = null;
        this.ultimo--;
    }

    public Boolean isVazia(){
        if(this.ultimo == -1)
            return true;
        return false;
    }

    @Override
    public String toString(){
        return "Vetor elementos: "+
        this.elemento + 
        "O último elemento é: " + this.elemento[ultimo];
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;
        if(obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;

        Pilha<X> pil = (Pilha<X>) obj;

        if(this.ultimo != pil.ultimo)
            return false;

        for (int i=0; i<=this.ultimo; i++)
            if(!this.elemento[i].equals(pil.elemento[i]))
                return false;
        
        return true;
    }

    @Override 
    public int hashCode(){
        int ret = 2;

        ret = ret*7 + ((Integer)(this.ultimo)).hashCode();
        
        for(int i=0; i<=this.ultimo;i++)
            ret = ret*7 + this.elemento[i].hashCode();

        if (ret<0) 
            ret = -ret;

        return ret;
    }


}