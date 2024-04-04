package tiposPieza;

public class Rey extends Pieza {
    public Rey(Color color) {
        super('K',color);
    }

    public boolean puedeMoverse(int movimientoVertical, int movimientoHorizontal){
        boolean puede=false;
        if(movimientoVertical != 0 || movimientoHorizontal != 0)
            if(movimientoVertical <= 1 && movimientoHorizontal <= 1)
                puede=true;
        return puede;
    }

    public static void main(String[] args) {

    }
}
