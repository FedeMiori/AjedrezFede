import tiposPieza.Color;
import tiposPieza.Pieza;
import tiposPieza.Rey;

import java.util.List;

public class EstadoPartida {
    private Jugador[] jugadores;
    private int tieneTurno;
    private Tablero tablero;
    private Posicion[] movimientoJaqueMate;
    private Color colorGanador;

    public EstadoPartida(Jugador[] jugadores, Tablero tablero){
        this.jugadores = jugadores;
        tieneTurno = 0;

        this.tablero = tablero;
    }

    public Jugador quienTieneTurno(){
        return jugadores[tieneTurno];
    }

    public void siguienteTurno(){
        tieneTurno = (tieneTurno + 1) % jugadores.length;
    }

    public Jugador getJugadorConColor(Color color){
        Jugador encontrado = null;
        for (int i = 0; i < jugadores.length; i++) {
            if(jugadores[i].esDeColor(color))
                encontrado = jugadores[i];
        }
        return encontrado;
    }

    public boolean finPartida(){
        return comprobarGanador(Color.blanco) || comprobarGanador(Color.negro);
    }

    public boolean comprobarGanador(Color color){
        Posicion posicionRey = tablero.buscarPieza(new Rey(color));
        Posicion posicionAmenazante = buscarJaque(posicionRey);
        if(posicionAmenazante != null) {
            colorGanador = tablero.getPiezaEnCasilla(posicionAmenazante).getColor();
            //DEBUG
            System.out.printf("JAQUE: "+colorGanador);
            return jaqueJaguar(posicionAmenazante, posicionRey);
        }
        else
            return false;
    }

    public Jugador quienHaGanado(){
        return getJugadorConColor(colorGanador);
    }

    private Posicion buscarJaque(Posicion posicionRey){
        Posicion posicionUlimaFichaMovida = tablero.getUltimoMovimiento()[1];
        if(posicionUlimaFichaMovida != null && tablero.movimientoPosible(posicionUlimaFichaMovida, posicionRey))
            return posicionUlimaFichaMovida;
        else
            return null;
    }

    //Comprueba Jaque Mate
    private boolean jaqueJaguar(Posicion posicionAmenazante, Posicion posicionRey){
        List<Posicion> listaPosiblesBloqueos = tablero.getListaPosicionesIntermedias(posicionAmenazante,posicionRey);
        Color colorDelRey = tablero.getPiezaEnCasilla(posicionRey).getColor();
        Boolean bloqueoEncontrado = false;
        int i=0, j;
        while(i < tablero.ANCHO_TABLERO){
            j=0;
            while(j < tablero.ALTO_TABLERO && !bloqueoEncontrado){
                Pieza posibleDefensora = tablero.getPiezaEnCasilla(i,j);
                if(posibleDefensora != null && posibleDefensora.esDeColor(colorDelRey))
                    bloqueoEncontrado = puedeDefender(new Posicion(i,j), listaPosiblesBloqueos);
                j++;
            }
            i++;
        }
        return bloqueoEncontrado;
    }

    private boolean puedeDefender(Posicion posicionPosibleDefensora ,List<Posicion> posiblesBloqueos){
        boolean puedeDefender = false;
        for(Posicion bloqueo: posiblesBloqueos)
            if(tablero.movimientoPosible(posicionPosibleDefensora,bloqueo))
                puedeDefender = true;
        return puedeDefender;
    }
}
