package tiposFicha;

import static java.lang.Math.abs;

public class Reina extends Ficha{
    public Reina(Color color) {
        super('Q',color);
    }

    public boolean puedeMoverse(int movimientoVertical, int movimientoHorizontal){
        boolean puede = false;
        //Movimiento horizontal
        if(movimientoVertical == 0 && movimientoHorizontal != 0)
            puede = true;
        //vertical
        if(movimientoHorizontal == 0 && movimientoVertical != 0)
            puede = true;
        //Diagonales
        if(abs(movimientoHorizontal) == abs(movimientoVertical) && movimientoHorizontal != 0)
            puede=true;
        return puede;
    }
}
