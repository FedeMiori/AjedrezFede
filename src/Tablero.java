import tiposPieza.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tablero {
    public final int ALTO_TABLERO = 8;
    public final int ANCHO_TABLERO = 8;

    private Casilla[][] casillas = new Casilla[ALTO_TABLERO][ANCHO_TABLERO];
    private Posicion[] ultimoMovimiento = new Posicion[2];

    public Tablero(){
        for (int i = 0; i < ALTO_TABLERO; i++) {
            for (int j = 0; j < ANCHO_TABLERO; j++) {
                casillas[i][j] = new Casilla( new Posicion(i,j) );
            }
        }
    }

    public Casilla getCasilla(char letra, int numero){
        String input = String.valueOf(letra) + String.valueOf(numero);
        return getCasilla( new Posicion( input ) );
    }

    public Casilla getCasilla(Posicion posicion){
        Casilla casilla = null;
        if( posicion.dentroLimites(ALTO_TABLERO, ANCHO_TABLERO))
            casilla = casillas[posicion.getPosX()][posicion.getPosY()];
        return casilla;
    }

    public boolean casillaVacia(Posicion posicion){
        return getCasilla(posicion).getPieza() == null;
    }

    public Pieza getPiezaEnCasilla(int posX, int posY){
        return getPiezaEnCasilla(new Posicion(posX,posY));
    }

    public Pieza getPiezaEnCasilla(Posicion posicion){
        if(posicion.dentroLimites(ALTO_TABLERO,ANCHO_TABLERO))
            return getCasilla(posicion).getPieza();
        else
            return null;
    }

    public Posicion[] getUltimoMovimiento() {return ultimoMovimiento;}

    private void setUltimoMovimiento(Posicion origen, Posicion destino){
        ultimoMovimiento[0] = origen;
        ultimoMovimiento[1] = destino;
    }

    /**
     * Metodo que dibuja un tablero muy detallado por la terminal
     */
    public void printTablero(){
        System.out.print("   ");
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            System.out.print("  "+(char) ('a'+i)+" ");
        }
        System.out.println();

        for (int i = ALTO_TABLERO -1; i >=0; i--) {
            System.out.print("   ");
            for (int j = 0; j < ANCHO_TABLERO; j++) {
                System.out.print("+---");
            }
            System.out.println("+");

            System.out.print((i+1)+"  ");
            for (int j = 0; j < ANCHO_TABLERO; j++) {
                System.out.print("| "+casillas[j][i].toString()+" ");
            }
            System.out.println("|  "+(i+1));
        }
        System.out.print("   ");
        for (int j = 0; j < ANCHO_TABLERO; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
        System.out.print("   ");
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            System.out.print("  "+(char) ('a'+i)+" ");
        }
        System.out.println();
    }
    
    public void inicializar(){

        //Colocamos peones
        for (int i = 0; i < ANCHO_TABLERO; i++) {
            casillas[i][1].setPieza( new Peon(Color.blanco) );
            casillas[i][6].setPieza( new Peon(Color.negro) );
        }

        //Colocamos torres:

        getCasilla('a',1).setPieza( new Torre(Color.blanco) );
        getCasilla('h',1).setPieza( new Torre(Color.blanco) );
        getCasilla('a',8).setPieza( new Torre(Color.negro) );
        getCasilla('h',8).setPieza( new Torre(Color.negro) );

        //Colocamos caballos:
        getCasilla('b',1).setPieza( new Caballo(Color.blanco) );
        getCasilla('g',1).setPieza( new Caballo(Color.blanco) );
        getCasilla('b',8).setPieza( new Caballo(Color.negro) );
        getCasilla('g',8).setPieza( new Caballo(Color.negro) );

        //Alfiles
        getCasilla('c',1).setPieza( new Alfil(Color.blanco) );
        getCasilla('f',1).setPieza( new Alfil(Color.blanco) );
        getCasilla('c',8).setPieza( new Alfil(Color.negro) );
        getCasilla('f',8).setPieza( new Alfil(Color.negro) );

        //Reyes
        getCasilla('e',1).setPieza( new Rey(Color.blanco) );
        getCasilla('e',8).setPieza( new Rey(Color.negro) );

        //Reinas
        getCasilla('d',1).setPieza( new Reina(Color.blanco) );
        getCasilla('d',8).setPieza( new Reina(Color.negro) );


    }

    public boolean moverPieza(Posicion posicionOrigen, Posicion posicionDestino, Color color){
        if(getCasilla(posicionOrigen).getPieza().esDeColor(color))
            return moverPieza(posicionOrigen,posicionDestino);
        else {
            System.out.println("\nERROR: El jugador no puede mover esta pieza");
            return false;
        }
    }

    /***
     * OJO! Busca una pieza igual (mismo tipo y color) NO BUSCA LA MISMA INSTANCIA
     */
    public Posicion buscarPieza(Pieza pieza){
        int i=0, j;
        Posicion resultadoBusqueda = null;
        while(i < ANCHO_TABLERO){
            j=0;
            while(j < ALTO_TABLERO && resultadoBusqueda == null){
                Pieza piezaAComprobar = getPiezaEnCasilla(i,j);
                if(pieza.mismoTipoPieza(piezaAComprobar) && pieza.mismoColor(piezaAComprobar))
                    resultadoBusqueda = new Posicion(i,j);
                j++;
            }
            i++;
        }
        return resultadoBusqueda;
    }

    /**
     * Metodo que mueve la pieza situalda en posicionOrigen a la posicionDestino
     * Tiene en cuenta toda la casuistica para ver si se puede realizar ese movimiento
     */
    public boolean moverPieza(Posicion posicionOrigen, Posicion posicionDestino){
        Pieza piezaAMover = getPiezaEnCasilla(posicionOrigen);
        if(movimientoPosible(posicionOrigen,posicionDestino)){
            piezaAMover.incrementarNumMovimientos();
            getCasilla(posicionOrigen).setPieza(null);
            getCasilla(posicionDestino).setPieza(piezaAMover);
            setUltimoMovimiento(posicionOrigen,posicionDestino);
            return true;
        }
        else {
            System.out.println("\nERROR: No se pudo realizar el movimiento");
            return false;
        }
    }

    public boolean movimientoPosible(Posicion posicionOrigen, Posicion posicionDestino){
        boolean puedeMoverse = false;
        Pieza piezaAMover = getPiezaEnCasilla(posicionOrigen);
        Pieza piezaEnDestino = getPiezaEnCasilla(posicionDestino);
        int[] vectorMovimiento = posicionOrigen.getVector( posicionDestino );

        //Comprueba que las posiciones estén dentro del tablero
        if(posicionOrigen.dentroLimites(ALTO_TABLERO, ANCHO_TABLERO) && posicionDestino.dentroLimites(ALTO_TABLERO, ANCHO_TABLERO)){
            //Consulta a la pieza si puede hacer ese movimiento (Aprovechando el polimorfismo)
            if(piezaAMover != null && piezaAMover.movimientoValido(vectorMovimiento)) {
                //Comprueba que no haya ninguna ficha entre las 2 posiciones excepto que sea un caballo
                if (caminoDespejado(posicionOrigen, posicionDestino) || piezaAMover instanceof Caballo)
                    //Comprueba que la casilla destino esté vacia o que contenga una ficha adversaria
                    if (piezaEnDestino == null || piezaEnDestino.distintoColor(piezaAMover))
                        puedeMoverse = true;
            }

            //Aquí se tiene en cuenta la situacion particular en la que el peon puede comer moviendose en diagonal
            else if(piezaAMover instanceof Peon && piezaAMover.movimientoValido(vectorMovimiento, piezaEnDestino))
                puedeMoverse = true;
        }

        return puedeMoverse;
    }

    /**
     * Metodo que indica si las casillas intermedias entre dos posiciones estan vacias
     * sirve para ver si una pieza que no sea un caballo tiene el camino despejado para autorizar el movimiento
     * @param origen posicion origen del movimiento
     * @param destino posicion ddestino del movimiento
     * @return devuelve true si la trayectoria entre los dos puntos no tiene piezas
     */
    public boolean caminoDespejado(Posicion origen, Posicion destino){
        List<Posicion> lista = getListaPosicionesIntermedias(origen,destino);
        boolean sinObstaculos = true;
        for(Posicion posicion : lista)
            if(!casillaVacia(posicion))
                sinObstaculos = false;
        return sinObstaculos;
    }

    public List<Posicion> getListaPosicionesIntermedias(Posicion origen, Posicion destino){
        int[] vector = origen.getVector(destino);
        int[] vectorUnitario = getVectorUnitario(vector);
        List<Posicion> listaPosiciones = new LinkedList<>();

        Posicion posicionIteradora = new Posicion( origen.getPosX(), origen.getPosY() );

        //Aquí se comprueba que el vector sea diagonal, horizontal o diagonal
        if( vector[0]*vector[1] == 0 || Math.abs(vector[0]) == Math.abs(vector[1]) ){
            //a la posicion iteradora le sumo el vector unitario
            posicionIteradora.sumarVector( vectorUnitario );
            while( ! posicionIteradora.equals(destino)
                    && posicionIteradora.dentroLimites(ANCHO_TABLERO,ALTO_TABLERO)){
                listaPosiciones.add(posicionIteradora.generarCopia());
                posicionIteradora.sumarVector(vectorUnitario);
            }
        }
        return listaPosiciones;
    }

    private static int[] getVectorUnitario(int[] vector){
        int[] vectorUnitario= new int[vector.length];
        for (int i = 0; i < vector.length; i++) {
            if(vector[i] < 0)
                vectorUnitario[i] = -1;
            else if(vector[i] > 0)
                vectorUnitario[i] = 1;
            else
                vectorUnitario[i] = 0;
        }
        return vectorUnitario;
    }

    public void inicializarDEBUG(){
        //Reyes
        getCasilla('e',1).setPieza( new Rey(Color.blanco) );
        getCasilla('e',8).setPieza( new Rey(Color.negro) );

        getCasilla('d',6).setPieza( new Torre(Color.negro) );
        getCasilla('d',4).setPieza( new Torre(Color.blanco) );
    }

    public static void main(String[] args) {
        Tablero t = new Tablero();
        t.inicializarDEBUG();
        t.printTablero();
        System.out.println(t.movimientoPosible(new Posicion('d',6),new Posicion('e',6)));
    }
}
