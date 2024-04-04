package tiposPieza;

public class Torre extends Pieza {
    public Torre(Color color) {
        super('R',color);
    }

    public boolean puedeMoverse(int movimientoVertical, int movimientoHorizontal){
        boolean puede = false;
        if(movimientoVertical == 0 && movimientoHorizontal != 0)
            puede = true;
        if(movimientoHorizontal == 0 && movimientoVertical != 0)
            puede = true;
        return puede;
    }
}
