package tp.pr3.control;

import tp.pr3.logica.Partida;

public class ComandoJugar extends Comando {

	/**
	 * Ejecuta el comando para cambiar el tipo de juego
	 */
	@Override
	public void ejecuta(Partida p, String[] args) throws Exception{
		try
		{
			if (args[0].equalsIgnoreCase("c4")){
				new ComandoJugarConecta4().ejecuta(p, args);
			}else if (args[0].equalsIgnoreCase("co")){
				new ComandoJugarComplica().ejecuta(p, args);
			}else if (args[0].equalsIgnoreCase("gr")){
				new ComandoJugarGravity().ejecuta(p, args);
			}else if (args[0].equalsIgnoreCase("rv")){
				new ComandoJugarReversi().ejecuta(p, args);
			}else{
				throw new Exception("No te entiendo");
			}
		}catch (Exception e){
			throw new Exception("No te entiendo");
		}
	}
}
