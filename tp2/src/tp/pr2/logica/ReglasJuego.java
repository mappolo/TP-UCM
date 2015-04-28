package tp.pr2.logica;

public interface ReglasJuego {
	public abstract Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t); //Permite averiguar si en la partida ya tenemos un ganador o no devuelve el color del ganador (si lo hay)
	public abstract Tablero iniciaTablero(); //Construye el tablero que hay que utilizar para la partida
	public abstract Ficha jugadorInicial(); //Devuelve el color del jugador que comienza la partida
	public abstract Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t); //Devuelve el color del jugador al que le toca poner
	public abstract boolean tablas(Ficha ultimoEnPoner, Tablero t); //Devuelve true si, con el estado del tablero dado, la partida ha terminado en tablas
}
