public class DemoPreAlpha {
    public static void main(String[] args) {
        Tablero miTablero = new Tablero();
        miTablero.inicializar();
        while(true){
            miTablero.printTablero();
            Posicion origen = Posicion.pedirUsuario("Posicion de pieza a mover: ");
            Posicion destino = Posicion.pedirUsuario("Posicion destino de la pieza");
            miTablero.moverPieza(origen, destino);
        }
    }
}
