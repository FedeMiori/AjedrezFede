import tiposPieza.Color;

public class Juego {
    private Jugador[] jugadores;
    private Turno turno;
    private Tablero tablero;

    public Juego(){
        tablero = new Tablero();
        jugadores = new Jugador[] {
                new Jugador("Jugador Blancas", Color.blanco, tablero),
                new Jugador("Jugador Negras", Color.negro, tablero)
        };
        turno = new Turno(jugadores);
    }

    public void jugar() {
        tablero.inicializar();
        Jugador jugadorConTurno;
        do{
            jugadorConTurno = turno.quienTieneTurno();
            tablero.printTablero();
            while(!jugadorConTurno.moverPieza());
            turno.siguienteTurno();
        }while(!finPartida());
        Color colorGanador = tablero.quienHaGanado();
        System.out.println("GANADOR : "+colorGanador.toString().toUpperCase());
    }

    private boolean finPartida(){ return tablero.finPartida(); }

    public static void main(String[] args) {
        Juego juego=new Juego();
        juego.jugar();
    }
}
