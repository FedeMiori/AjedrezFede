package tiposPieza;

import static java.lang.Math.abs;

public class Alfil extends Pieza {
    public Alfil(Color color) {
        super('B',color);
    }

    public boolean puedeMoverse(int movimientoVertical, int movimientoHorizontal){
        boolean puede=false;
        if(abs(movimientoHorizontal) == abs(movimientoVertical) && movimientoHorizontal != 0)
            puede=true;
        return puede;
    }
}