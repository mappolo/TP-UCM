package tp.pr3.control;

public class FactoriaComandos {
	
	/**
	 * Establece el comando a ejecutar
	 * @param nombreComando nombre del comando introducido
	 * @return comando a ejecutar
	 * @throws Exception a capturar
	 */
	public static Comando parse(String nombreComando) throws Exception{
		Comando res = null;
		
		if (nombreComando.equalsIgnoreCase("poner")){
			res = new ComandoPoner();
		}else if (nombreComando.equalsIgnoreCase("deshacer")){
			res = new ComandoDeshacer();
		}else if (nombreComando.equalsIgnoreCase("reiniciar")){
			res = new ComandoReiniciar();
		}else if (nombreComando.equalsIgnoreCase("jugar")){
			res = new ComandoJugar();
		}else if (nombreComando.equalsIgnoreCase("jugador")){
			res = new ComandoCambiarJugador();
		}else if (nombreComando.equalsIgnoreCase("salir")){
			res = new ComandoSalir();
		}else if (nombreComando.equalsIgnoreCase("ayuda")){
			res = new ComandoAyuda();
		}else{
			throw new Exception("No te entiendo");
		}
		return res;
	}
}
