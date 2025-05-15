import tiposPieza.Color;
import tiposPieza.Pieza;
import tiposPieza.Rey;

import java.util.List;

public class EstadoPartida {
    private Jugador[] jugadores;
    private int tieneTurno;
    private Tablero tablero;
    private Posicion[] ultimoJaque;
    private Color colorGanador;

    public EstadoPartida(Jugador[] jugadores, Tablero tablero){
        this.jugadores = jugadores;
        tieneTurno = 0;

        this.tablero = tablero;
        this.ultimoJaque = new Posicion[2];
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
            System.out.println("DEBUG: JAQUE "+colorGanador.toString().toUpperCase());
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
        Posicion posicionFichaJaqueAnterior = ultimoJaque[1];
        if(posicionFichaJaqueAnterior != null)
            System.out.println("DEBUG POSICION JAQUE ANTERIOR: "+posicionFichaJaqueAnterior.getNotacionAlgebraica().toUpperCase());
        if(posicionUlimaFichaMovida != null && tablero.movimientoPosible(posicionUlimaFichaMovida, posicionRey)) {
            ultimoJaque = tablero.getUltimoMovimiento();
            return posicionUlimaFichaMovida;
        }
        else if(posicionFichaJaqueAnterior != null && tablero.movimientoPosible(posicionFichaJaqueAnterior, posicionRey)){
            return posicionFichaJaqueAnterior;
        }
        else
            return null;
    }

    //Comprueba Jaque Mate
    private boolean jaqueJaguar(Posicion posicionAmenazante, Posicion posicionRey){
        List<Posicion> listaPosiblesBloqueos = tablero.getListaPosicionesIntermedias(posicionAmenazante,posicionRey);

        for(Posicion p : listaPosiblesBloqueos)
            System.out.println(p.getNotacionAlgebraica());

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
        System.out.println("BLOQUEO ENCONTRADO = "+bloqueoEncontrado);
        return !bloqueoEncontrado;
    }

    private boolean puedeDefender(Posicion posicionPosibleDefensora ,List<Posicion> posiblesBloqueos){
        boolean puedeDefender = false;
        for(Posicion bloqueo: posiblesBloqueos)
            if(tablero.movimientoPosible(posicionPosibleDefensora,bloqueo))
                puedeDefender = true;
        System.out.println("PUEDE DEFENDER = "+puedeDefender);
        return puedeDefender;
    }
}
