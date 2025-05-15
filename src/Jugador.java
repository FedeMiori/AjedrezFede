import tiposPieza.*;

public class Jugador {
    private String nombre;
    private Color color;
    Tablero tablero;

    public Jugador(String nombre, Color color, Tablero tablero){
        this.nombre = nombre;
        this.color = color;
        this.tablero = tablero;
    }

    public Color getColor() {return color;}

    public boolean moverPieza(){
        System.out.println(nombre+" mueve pieza: ");
        Posicion origen = Posicion.pedirUsuario("Posicion de pieza a mover: ");
        Posicion destino = Posicion.pedirUsuario("Posicion destino de la pieza");
        return tablero.moverPieza(origen,destino,this.color);
    }

    public boolean esDeColor(Color color){
        return this.color == color;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
