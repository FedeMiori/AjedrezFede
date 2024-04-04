import tiposPieza.Pieza;

public class Casilla {
    private char longitud;
    private int altura;
    private Pieza pieza;


    public Casilla(char longitud, int altura) {
        this.longitud = longitud;
        this.altura = altura;
    }

    public Pieza getFicha() {return pieza;}

    public void setFicha(Pieza piezaEntrada) {
        pieza = piezaEntrada;
    }

    public String nombreCasilla(){
        return "" + longitud + altura;
    }

    public String toString(){
        String resultado;
        if(pieza ==null)
            resultado = " ";
        else
            resultado = pieza.toString();
        return resultado;
    }
}
