public class Juego {
    private Jugador[] jugadores;
    private Turno turno;
    private Tablero tablero;

    public Juego(){
        jugadores = new Jugador[] {new Jugador("Jugador Blancas"), new Jugador("Jugador Negras")};
        turno = new Turno(jugadores);
        tablero = new Tablero();
    }

    public void jugar() {
        //tablero.inicializar();
        //tablero.printTablero();
        System.out.println("TO DO");
    }

    public static void main(String[] args) {
        Juego juego=new Juego();
        juego.jugar();
    }
}
