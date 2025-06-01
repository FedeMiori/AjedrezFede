import tiposPieza.Pieza;

public class Casilla {
    private Posicion posicion;
    private Pieza pieza;
    private static final String invisiblePlaceholder = "\u3000"; // Espacio ideográfico


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
            return invisiblePlaceholder;
        else
            return pieza.toString();
    }
}
