package pokedex_spring;

import org.springframework.stereotype.Component;

import java.util.List;

public class Validador {
	
	public static void validarPokemonEvolucionado(Pokemon pokemon) throws PokemonNoEvolucionaMasExcepcion {
		if (pokemon.getEvoluciones().size() >= 2) throw new PokemonNoEvolucionaMasExcepcion();
	}
	
	public static void validarPokemon(Aplicacion app, String nombrePokemon) throws PokemonYaExisteExcepcion {
		if (app.existePokemon(nombrePokemon)) throw new PokemonYaExisteExcepcion();
	}
	
	
	public static void validarPokemonNoEvolucionado(Pokemon pokemon) throws PokemonNoEvolucionadoExcepcion {
		if (pokemon.getEvoluciones().isEmpty()) throw new PokemonNoEvolucionadoExcepcion();
	}
	
	public static void validarOpciones(List<Integer> opciones, Integer opcion) throws OpcionNoValidaExcepcion {
		if (!opciones.contains(opcion)) throw new OpcionNoValidaExcepcion();
	}
	
	public static void validarTipos(String tipo, List<String> tipos) throws TipoNoValidoExcepcion {
		if(!tipos.contains(tipo)) throw new TipoNoValidoExcepcion();	
	}
}