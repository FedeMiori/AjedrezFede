package tiposPieza;
public abstract class Pieza {
    private char letra;
    protected Color color;

    final String parametroVerde = "\u001B[38;2;55;139;71m";
    final String parametroNegrita = "\033[0;1m";
    final String resetTipografia = "\u001B[0m";

    private int numMovimientos;

    public Pieza(char letra, Color color) {
        this.letra = letra;
        this.color = color;
        numMovimientos=0;
    }

    public Color getColor(){
        return color;
    }

    protected int getNumMovimientos(){
        return numMovimientos;
    }

    public String toString(){
        if(esDeColor(Color.negro))
            return parametroVerde + letra + resetTipografia;
        else
            return String.valueOf(letra);
    }

    public void incrementarNumMovimientos(){
        numMovimientos++;
    }

    //Defino este metodo abstracto para obligar a quee todos los hijos implementen este metodo
    public abstract boolean movimientoValido(int movimientoHorizontal, int movimientoVertical);

    public boolean movimientoValido(int[] vectorMovimiento){
        return movimientoValido(vectorMovimiento[0], vectorMovimiento[1]);
    }

    public boolean movimientoValido(int[] vectorMovimiento, Pieza piezaAComer){
        if(this instanceof Peon)
            //Esta linea ejecuta el metodo de la clase hija peon
            return ((Peon) this).movimientoValido(vectorMovimiento, piezaAComer);
        else
            return this.movimientoValido(vectorMovimiento);
    }

    public boolean esDeColor(Color color){
        return this.color == color;
    }

    public boolean mismoColor(Pieza piezaEntrada){
        return piezaEntrada != null && piezaEntrada.getColor() == color;
    }

    public boolean distintoColor(Pieza piezaEntrada){
        return piezaEntrada == null || piezaEntrada.getColor() != color;
    }
}
