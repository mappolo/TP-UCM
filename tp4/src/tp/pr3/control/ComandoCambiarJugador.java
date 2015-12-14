package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Partida;

public class ComandoCambiarJugador extends Comando{

	/**
	 * Ejecuta el comando para cambiar el tipo de jugador
	 */
	public void ejecuta(Partida p, String[] args) {
		Ficha color = Ficha.VACIA;
		boolean automatico = false;
		if (args[0].equalsIgnoreCase("blancas")){
			color = Ficha.BLANCA;
		}else if (args[0].equalsIgnoreCase("negras")){
			color = Ficha.NEGRA;
		}else{
			throw new IllegalArgumentException ("Color de jugador no válido");
		}
		
		if (args[1].equalsIgnoreCase("humano")){
			automatico = false;
		}else if (args[1].equalsIgnoreCase("aleatorio")){
			automatico = true;
		}else{
			throw new IllegalArgumentException ("Tipo de jugador no válido");
		}
		
		p.setAutomatico(color, automatico);
	}

}
