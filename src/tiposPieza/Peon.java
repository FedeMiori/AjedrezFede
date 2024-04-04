package tiposPieza;

public class Peon extends Pieza {
    public Peon(Color color) {
        super('P',color);
    }
    public boolean puedeMoverse(int movimientoVertical, int movimientoHorizontal){
        boolean puede=false;
        if(movimientoHorizontal==0 && movimientoVertical == 1)
            puede =true;
        if(getNumMovimientos()==0 && movimientoHorizontal==0 && movimientoVertical == 2)
            puede=true;
        return puede;
    }
}
