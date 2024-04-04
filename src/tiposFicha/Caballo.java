package tiposFicha;

import static java.lang.Math.abs;

public class Caballo extends Ficha{
    public Caballo(Color color) {
        super('N',color);
    }

    public boolean puedeMoverse(int movimientoVertical, int movimientoHorizontal){
        boolean puede=false;
        if(abs(movimientoVertical) == 2 && abs(movimientoHorizontal) == 1)
            puede = true;
        if(abs(movimientoHorizontal) == 2 && abs(movimientoVertical) == 1)
            puede=true;
        return puede;
    }
}
