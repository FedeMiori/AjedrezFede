import tiposPieza.Color;

public class Juego {
    private Jugador[] jugadores;
    private EstadoPartida estadoPartida;
    private Tablero tablero;

    public Juego(){
        tablero = new Tablero();
        jugadores = new Jugador[] {
                new Jugador("Jugador Blancas", Color.blanco, tablero),
                new Jugador("Jugador Negras", Color.negro, tablero)
        };
        estadoPartida = new EstadoPartida(jugadores,tablero);
    }

    public void jugar() {
        tablero.inicializarDEBUG();
        Jugador jugadorConTurno;
        do{
            jugadorConTurno = estadoPartida.quienTieneTurno();
            System.out.println("TURNO DE: "+jugadorConTurno.toString().toUpperCase());
            tablero.printTablero();
            while(!jugadorConTurno.moverPieza());
            estadoPartida.siguienteTurno();
        }while(!finPartida());
        System.out.println("FIN PARTIDA");
        tablero.printTablero();
        Jugador jugadorGanador = estadoPartida.quienHaGanado();
        System.out.println("GANADOR : "+jugadorGanador.toString().toUpperCase());
    }

    private boolean finPartida(){ return estadoPartida.finPartida(); }

    public static void main(String[] args) {
        Juego juego=new Juego();
        juego.jugar();
    }
}
