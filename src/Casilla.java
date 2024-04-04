import tiposPieza.Pieza;

public class Casilla {
    private Posicion posicion;
    private Pieza pieza;


    public Casilla(Posicion posicion) {
        this.posicion=posicion;
    }

    public Pieza getPieza() {return pieza;}

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public String posicionCasilla(){
        return posicion.getNotacionAlgebraica();
    }

    public String toString(){
        if(pieza ==null)
            return " ";
        else
            return pieza.toString();
    }
}
