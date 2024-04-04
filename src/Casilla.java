import tiposFicha.Ficha;

public class Casilla {
    private char longitud;
    private int altura;
    private Ficha ficha;


    public Casilla(char longitud, int altura) {
        this.longitud = longitud;
        this.altura = altura;
    }

    public Ficha getFicha() {return ficha;}

    public void setFicha(Ficha fichaEntrada) {
        ficha = fichaEntrada;
    }

    public String nombreCasilla(){
        return "" + longitud + altura;
    }

    public String toString(){
        String resultado;
        if(ficha==null)
            resultado = " ";
        else
            resultado = ficha.toString();
        return resultado;
    }
}
