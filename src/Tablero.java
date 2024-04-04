import tiposPieza.*;

public class Tablero {
    private final int altoTablero=8;
    private final int anchoTablero=8;

    Casilla[][] casillas = new Casilla[altoTablero][anchoTablero];

    public Tablero(){
        char caracter;
        for (int i = 0; i < altoTablero; i++) {
            for (int j = 0; j < anchoTablero; j++) {
                caracter = (char)('a'+j);
                casillas[i][j] = new Casilla(caracter,i+1);
            }
        }
    }

    public Casilla getCasilla(char letraColumna, int numAlto){
        Casilla casilla = null;
        int numLargo = (int) letraColumna - 'a';
        numAlto--;
        if( numAlto >= 0 && numAlto < altoTablero && numLargo >= 0 && numLargo < anchoTablero)
            casilla = casillas[numAlto][numLargo];
        return casilla;
    }

    public void printTablero(){
        for (int i = altoTablero-1 ; i >=0; i--) {
            System.out.print("   ");
            for (int j = 0; j < anchoTablero; j++) {
                System.out.print("+---");
            }
            System.out.println("+");

            System.out.print(" "+(i+1)+" ");
            for (int j = 0; j < anchoTablero; j++) {
                System.out.print("| "+casillas[i][j].toString()+" ");
            }
            System.out.println("|");
        }
        System.out.print("   ");
        for (int j = 0; j < anchoTablero; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
        System.out.print("   ");
        for (int i = 0; i < anchoTablero; i++) {
            System.out.print("  "+(char) ('a'+i)+" ");
        }
    }

    /**
     *
     */
    public void printTableroSimple(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("|"+casillas[i][j].toString());
            }
            System.out.println("|");
        }
    }

    public void inicializar(){

        //Colocamos peones
        for (int i = 0; i < anchoTablero; i++) {
            casillas[1][i].setFicha( new Peon(Color.blanco) );
            casillas[6][i].setFicha( new Peon(Color.negro) );
        }

        //Colocamos torres:

        getCasilla('a',1).setFicha( new Torre(Color.blanco) );
        getCasilla('h',1).setFicha( new Torre(Color.blanco) );
        getCasilla('a',8).setFicha( new Torre(Color.negro) );
        getCasilla('h',8).setFicha( new Torre(Color.negro) );

        //Colocamos caballos:
        getCasilla('b',1).setFicha( new Caballo(Color.blanco) );
        getCasilla('g',1).setFicha( new Caballo(Color.blanco) );
        getCasilla('b',8).setFicha( new Caballo(Color.negro) );
        getCasilla('g',8).setFicha( new Caballo(Color.negro) );

        //Alfiles
        getCasilla('c',1).setFicha( new Alfil(Color.blanco) );
        getCasilla('f',1).setFicha( new Alfil(Color.blanco) );
        getCasilla('c',8).setFicha( new Alfil(Color.negro) );
        getCasilla('f',8).setFicha( new Alfil(Color.negro) );

        //Reyes
        getCasilla('e',1).setFicha( new Rey(Color.blanco) );
        getCasilla('e',8).setFicha( new Rey(Color.negro) );

        //Reinas
        getCasilla('d',1).setFicha( new Reina(Color.blanco) );
        getCasilla('d',8).setFicha( new Reina(Color.negro) );


    }

    public static void main(String[] args) {
        Tablero tablero=new Tablero();
        tablero.inicializar();
        Pieza piezaAMover =tablero.getCasilla('a',1).getFicha();
        tablero.getCasilla('a',3).setFicha(piezaAMover);
        tablero.getCasilla('a',1).setFicha(null);
        tablero.printTablero();
    }
}
