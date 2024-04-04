package tiposFicha;
public abstract class Ficha {
    private char letra;
    private Color color;

    private int numMovimientos;

    public Ficha(char letra, Color color) {
        this.letra = letra;
        this.color = color;
        numMovimientos=0;
    }

    protected int getNumMovimientos(){
        return numMovimientos;
    }

    public String toString(){
        return Character.toString(letra);
    }

    public void incrementarNumMovimientos(){
        numMovimientos++;
    }

    public abstract boolean puedeMoverse(int movimientoVertical, int movimientoHorizontal);
}
