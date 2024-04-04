package tiposPieza;

public class Rey extends Pieza {
    public Rey(Color color) {
        super('K',color);
    }

    @Override
    public boolean movimientoValido(int movimientoHorizontal, int movimientoVertical){
        boolean puede=false;
        if(movimientoVertical != 0 || movimientoHorizontal != 0)
            if(movimientoVertical <= 1 && movimientoHorizontal <= 1)
                puede=true;
        return puede;
    }
}
