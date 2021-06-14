package pokedex;

import domain.Habilidad;
import domain.Pokemon;
import domain.Tipo;
import excepciones.PokemonEsEvolucionExcepcion;
import excepciones.PokemonExistenteExcepcion;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.List;

import java.util.ArrayList;

@Data
@Component
@Slf4j
public class Aplicacion {
	private List<Pokemon> pokemons;
	private List<Pokemon> pokemonsValidos;

	/* Constructor */
	public Aplicacion() {
		this.pokemons = new ArrayList<>();
		this.pokemonsValidos = new ArrayList<>();
	}

	public void agregarPokemon(Pokemon pokemon) throws PokemonExistenteExcepcion, PokemonEsEvolucionExcepcion {
		pokemon.setEvolucion(buscarPokemonValido(pokemon).getEvolucion());

		if (existePokemon(pokemon)) {
			modificarPokemon(pokemon);
		} else {
			validarPokemonConNombre(pokemon);
			validarPokemonEvolucion(pokemon);
			getPokemons().add(pokemon);
		}
	}

	public void validarPokemonConNombre(Pokemon pokemon) throws PokemonExistenteExcepcion {
		if (existePokemonConMismoNombre(pokemon)) throw new PokemonExistenteExcepcion();
	}

	public void validarPokemonEvolucion(Pokemon pokemon) throws PokemonEsEvolucionExcepcion {
		if (esNombreDeEvolucion(pokemon)) throw new PokemonEsEvolucionExcepcion();
	}

	public Boolean esNombreDeEvolucion(Pokemon pokemon) {
		Boolean nombreDeEvolucion = false;
		for (Pokemon p : this.getPokemonsValidos()) {
			nombreDeEvolucion = nombreDeEvolucion || p.getEvolucion().getNombre().equals(pokemon.getNombre());
		}
		return nombreDeEvolucion;
	}

	public Boolean existePokemonConMismoNombre(Pokemon pokemon) {
		Boolean existePokemon = false;
		for (Pokemon p : this.getPokemons()) {
			existePokemon = existePokemon || p.getNombre().equals(pokemon.getNombre());
		}
		return existePokemon;
	}

	public void modificarPokemon(Pokemon pokemon) {
		Pokemon pokemonAEditar = buscarPokemon(pokemon);
		List<Tipo> tiposPokemon = pokemon.getTipos();
		Integer nivelPokemon = pokemon.getNivel();
		List<Habilidad> habilidadesPokemon = pokemon.getHabilidades();
		Pokemon evolucionPokemon = pokemon.getEvolucion();
		pokemonAEditar.setTipos(tiposPokemon);
		pokemonAEditar.setNivel(nivelPokemon);
		pokemonAEditar.setHabilidades(habilidadesPokemon);
		pokemonAEditar.setEvolucion(evolucionPokemon);
	}

	public void eliminarPokemon(Pokemon pokemon) {
		getPokemons().remove(buscarPokemon(pokemon));
	}

	public Boolean existePokemon(Pokemon pokemon) {
		Boolean existePokemon = false;
		for (Pokemon p : this.getPokemons()) {
			existePokemon = existePokemon || p.getId().equals(pokemon.getId());
		}
		return existePokemon;
	}

	public Pokemon buscarPokemon(Pokemon pokemon) {
		return this.pokemons.stream()
				.filter(poke -> pokemon.getId().equals(poke.getId()))
				.findAny()
				.orElse(null);
	}

	public void evolucionarPokemon(Pokemon pokemon) {
		Pokemon pokemonAEvolucionar = buscarPokemon(pokemon);
		Pokemon evolucion = evolucionDePokemon(pokemonAEvolucionar);

		validarPuedeEvolucionar(evolucion);

		getPokemons().add(getPokemons().indexOf(pokemonAEvolucionar), evolucion);
		getPokemons().remove(pokemonAEvolucionar);
	}

	public Pokemon evolucionDePokemon(Pokemon pokemon) {
		return pokemon.getEvolucion();
	}

	public void validarPuedeEvolucionar(Pokemon evolucion) {
		if (evolucion.getNombre() == null || evolucion.getNombre() == "") throw new IndexOutOfBoundsException();
	}


	public Pokemon buscarPokemonValido(Pokemon pokemon) {
		return this.pokemonsValidos.stream()
				.filter(poke -> pokemon.getNombre().equals(poke.getNombre()))
				.findAny()
				.orElse(null);
	}

}