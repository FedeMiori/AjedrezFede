import java.util.*;
public class Turno {
    private Jugador[] jugadores;
    private int tieneTurno;

    public Turno(Jugador[] jugadores){
        this.jugadores=jugadores;
        tieneTurno=0;
    }

    public Turno(){
        this(crearJugadores());
    }

    private static Jugador[] crearJugadores(){
        Scanner teclado = new Scanner(System.in);
        Jugador[] array = new Jugador[2];
        System.out.println("Inserte el Nombre del 1er jugador: ");
        String nombre=teclado.nextLine();
        array[0] = new Jugador(nombre);
        System.out.println("Inserte el Nombre del 2o jugador: ");
        nombre = teclado.nextLine();
        array[1] = new Jugador(nombre);
        return array;
    }

    public Jugador quienTieneTurno(){
        return jugadores[tieneTurno];
    }

    public void siguienteTurno(){
        tieneTurno = (tieneTurno + 1) % jugadores.length;
    }
}
