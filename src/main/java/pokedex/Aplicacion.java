package pokedex;

import domain.Habilidad;
import domain.Pokemon;
import domain.Tipo;
import excepciones.PokemonNoEvolucionadoExcepcion;
import excepciones.TipoNoValidoExcepcion;
import lombok.Data;
import org.springframework.stereotype.Component;


import java.util.List;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import java.util.InputMismatchException;

@Data
@Component
public class Aplicacion {
	private List<Pokemon> pokemons;
//	private Map<Pokemon, ArrayList<Evolucion>> pokemonsYEvoluciones = new HashMap<>();
	private Scanner sc = new Scanner(System.in);

	/* Constructor */
	public Aplicacion() {
		this.pokemons = new ArrayList<>();
	}

	public void agregarPokemon(Pokemon pokemon) {
		if (existePokemon(pokemon)){
			eliminarPokemon(pokemon);
			getPokemons().add(pokemon);
		}
		else{
			getPokemons().add(pokemon);
		}
	}

	public void eliminarPokemon(Pokemon pokemon) {
		getPokemons().remove(buscarPokemon(pokemon));
	}

	public Boolean existePokemon(Pokemon pokemon) {
		Boolean existePokemon = false;
		for (Pokemon p: this.getPokemons()) {
			existePokemon = existePokemon || p.getId().equals(pokemon.getId());
		}
		return existePokemon;
	}

	public Pokemon buscarPokemon(Pokemon pokemon) {
		Integer i = 0;
		while(!(this.pokemons.get(i).getId().equals(pokemon.getId()))) {
			i++;
		}
		return pokemons.get(i);
	}



/*	public String mostrarPokemon() {
		Boolean pokemonEsValido;
		String datosPokemon = "";
		
		do {
			System.out.println("Ingrese el nombre del pokemon que desea que se muestre:");
			String nombrePokemon = sc.next();
			
			try {
				datosPokemon = this.datosPokemon(this.buscarPokemon(nombrePokemon));
				pokemonEsValido = false;
			}
			catch (IndexOutOfBoundsException e) {
				System.out.println("El pokemon ingresado no existe");
				pokemonEsValido = true;
			} 
		} while(pokemonEsValido);
		
		return datosPokemon;
	}*/

	
/*	public void agregarPokemon() {
		String nombrePokemon = "";
		Boolean pokemonExistente;
		Integer nivelPokemon = 0;
		List<Tipo> tiposAAgregar = new ArrayList<>();
		List<Habilidad> habilidadesAAgregar = new ArrayList<>();
		
		do {
			System.out.println("Ingrese los datos del pokemon que desea agregar:");
			
			try {
				nombrePokemon = this.insertarNombre();

				Validador.validarPokemon(this, nombrePokemon);
				pokemonExistente = false;
			}
			catch(PokemonYaExisteExcepcion e) {
				System.out.println("El pokemon ingresado ya existe");
				pokemonExistente = true;
			}
		} while(pokemonExistente);
		
		nivelPokemon = this.insertarNivel();
		
		Pokemon pokemon = new Pokemon(nombrePokemon, nivelPokemon);

		tiposYHabilidadesAAgregar(tiposAAgregar, habilidadesAAgregar);
		pokemon.getTipos().addAll(tiposAAgregar);
		pokemon.getHabilidades().addAll(habilidadesAAgregar);
	//	insertarTipoYHabilidades(pokemon, tipoAEditar, habilidadesAEditar);
		
		this.agregarEvolucionesAMap(pokemon);
		
		this.getPokemons().add(pokemon);
		
	}*/

		
/*	public void modificarPokemon() {
		Pokemon pokemon = null;
		Boolean pokemonNoExiste;
		Boolean modificandoPokemon = true;
		
		do {
			System.out.println("Ingrese el nombre del pokemon que desea modificar:");
			String nombre = sc.next();
			
			try {
				pokemon = this.buscarPokemon(nombre);
				pokemonNoExiste = false;
			}
			catch (IndexOutOfBoundsException e) {
				System.out.println("El pokemon ingresado no existe");
				pokemonNoExiste = true;
			}
		} while(pokemonNoExiste);
		
		this.edicionDatosPokemon(pokemon);
		
	}*/
	
	public void edicionDatosPokemon(Pokemon pokemon) {
		Boolean modificandoPokemon = true;
		Boolean condicionPokemon;
		
		do {
			try {
				while (modificandoPokemon) {
					System.out.println(" Que dato del pokemon desea modificar? (Ingrese: Nivel/TiposYHabilidades/Evoluciones) \n Si no desea modificar ningun dato ingrese 'Salir':");
					String opcion = sc.next();
					switch (opcion) {
					
						case "Nivel":
							Integer nivelPokemon = this.insertarNivel();
							pokemon.setNivel(nivelPokemon);
							break;
							
						case "TiposYHabilidades":	
							this.edicionTiposYHabilidadesPokemon(pokemon);
							break;
							
						case "Evoluciones":
							this.edicionEvoluciones(pokemon);
							break;
							
						case "Salir":
							modificandoPokemon = false;
							break;
					}
				}
				condicionPokemon = false;
			}
			catch (PokemonNoEvolucionadoExcepcion e) {
				System.out.println("El pokemon " + pokemon.getNombre() + " aún no evolucionó \n El pokemon debe haber evolucionado antes para que se pueda eliminar una evolución");
				condicionPokemon = true;
			}
		} while(condicionPokemon);
	}
	
	public void edicionTiposYHabilidadesPokemon(Pokemon pokemon) {
		String opcion;
		Boolean editandoEvolucion = true;
		List<Tipo> tiposAAgregar = new ArrayList<>();
		List<Habilidad> habilidadesAAgregar = new ArrayList<>();
		List<Tipo> tiposAEliminar = new ArrayList<>();
		List<Habilidad> habilidadesAEliminar = new ArrayList<>();

		while (editandoEvolucion) {
			System.out.println("Si desea agregar un tipo ingrese 'A' \n Si desea eliminar algun tipo ingrese 'E' \n Si desea salir ingrese 'Salir'");
			opcion = sc.next();
			switch (opcion) {
			
				case "A":
					tiposYHabilidadesAAgregar(tiposAAgregar, habilidadesAAgregar);
					pokemon.getTipos().addAll(tiposAAgregar);
					pokemon.getHabilidades().addAll(habilidadesAAgregar);
					System.out.println("SE HAN AGREGADO LOS TIPOS Y LAS HABILIDADES");
					break;
					
				case "E":
					tiposYHabilidadesAEliminar(tiposAEliminar, habilidadesAEliminar);
					pokemon.getTipos().removeAll(tiposAEliminar);
					pokemon.getHabilidades().removeAll(habilidadesAEliminar);
					System.out.println("SE HAN ELIMINADO LOS TIPOS Y LAS HABILIDADES");
					break;
					
				case "Salir":
					editandoEvolucion = false;
					break;
			}
		}		
	}


/*	public void eliminarPokemon() {
		Boolean pokemonNoExiste;
		do {
			System.out.println("Ingrese el nombre del pokemon que desea eliminar:");
			String nombre = sc.next();

			try {
				this.getPokemons().remove(this.buscarPokemon(nombre));
				pokemonNoExiste = false;
			}
			catch (IndexOutOfBoundsException e) {
				System.out.println("El pokemon ingresado no existe");
				pokemonNoExiste = true;
			}
		} while(pokemonNoExiste);
	}*/


	public String listarPokemons() {
		StringBuilder strPokemons = new StringBuilder();
		for (Pokemon p: this.getPokemons()) {
			strPokemons.append(this.datosPokemon(p)+"\n");
		}
		return strPokemons.toString();
	}
	
	
	//TODO: HACER QUE LA EXCEPTION DE QUE NO PUEDE EVOLUCIONAR MAS, CUANDO HAGA EL ROLLBACK ME TIRE PARA LA LISTA DE OPCIONES
	//DEL MENU 
/*	public void evolucionarPokemon() {
		Boolean pokemonNoExiste = false;
		Boolean pokemonNoEvoluciona = false;
		do { 
			System.out.println("Ingrese el nombre del pokemon que desea evolucionar:");
			String nombre = sc.next();
			Pokemon pokemon;
			
			try {
				pokemon = this.buscarPokemon(nombre);
				pokemonNoExiste = false;
				
				Validador.validarPokemonEvolucionado(pokemon);
				pokemonNoEvoluciona = false;
				
				this.evolucionDePokemon(pokemon);
			}
			catch(IndexOutOfBoundsException e) {
				System.out.println("El pokemon ingresado no existe");
				pokemonNoExiste = true;
			}
			catch(PokemonNoEvolucionaMasExcepcion e) {
				System.out.println("El pokemon " + nombre + " no puede evolucionar m�s");
				pokemonNoEvoluciona = true;
			}
		} while(pokemonNoExiste || pokemonNoEvoluciona);
	}*/
	
	
	public void eliminarEvolucion(Pokemon pokemon) throws PokemonNoEvolucionadoExcepcion {
		Validador.validarPokemonNoEvolucionado(pokemon);
		if (pokemon.getEvoluciones().size() == 2) {
			pokemon.getEvoluciones().remove(1);
		}
		else {
			pokemon.getEvoluciones().remove(0);
		}
	}
	
	/*	public modificarEvolucion(Pokemon pokemon) {
	System.out.println("Ingrese el nombre de la evolucion que desea modificar:");
	
}*/

//****************************************************************************//
	
	
		
	public String insertarNombre() {
			System.out.println("Nombre:");
			String nombre = sc.next();
			return nombre;
	}
	
	
	//TODO: ESTA TIRANDO ERROR: SE QUEDA LOOPEANDO EN EL nivel = sc.nextInt();
	public Integer insertarNivel() {
		Integer nivel = 0;
		Boolean nivelNoEsNumerico = false;
		do {
			try {
				System.out.println("nivel:");
				nivel = sc.nextInt();
				nivelNoEsNumerico = false;
			}
			catch(InputMismatchException e) {
				System.out.println("el nivel ingresado debe ser un valor numerico");
				nivelNoEsNumerico = true;
			}
		} while(nivelNoEsNumerico);
		
		return nivel;
	}

	
	public void tiposYHabilidadesAAgregar(List<Tipo> tipos, List<Habilidad> habilidades){
		Integer opcion = 1;
		List<String> tiposValidos = Arrays.asList("fuego", "agua", "planta", "roca", "volador", "hielo", "acero");
		System.out.println("*Mensaje: Recuerde que al agregar un tipo, tambien esta agregando la habilidad que corresponde a ese tipo*");
		
		while(opcion == 1) {
			try {
				System.out.println("Los tipos existentes son: 'fuego', 'agua', 'planta', 'roca', 'volador', 'hielo', 'acero' (Debe ingresarlos tal cual estan escritos aqui)");
				System.out.println("Tipo:");
				String tipoStr = sc.next();
				Validador.validarTipos(tipoStr, tiposValidos);
				tiposYHabilidadesIngresados (tipoStr, tipos, habilidades);
			}
			catch(TipoNoValidoExcepcion e) {
				System.out.println("el tipo ingresado es invalido");
			}
			
			System.out.println("Si desea agregar otro tipo presione 1, sino presione 2:");
			opcion = sc.nextInt();
		}
	}

	public void tiposYHabilidadesAEliminar(List<Tipo> tipos, List<Habilidad> habilidades) {
		Integer opcion = 1;
		List<String> tiposValidos = Arrays.asList("fuego", "agua", "planta", "roca", "volador", "hielo", "acero");
		System.out.println("*Mensaje: Recuerde que al eliminar un tipo, tambien esta eliminando la habilidad que corresponde a ese tipo*");

		while (opcion == 1) {
			try {
				System.out.println("Tipo:");
				String tipoStr = sc.next();
				Validador.validarTipos(tipoStr, tiposValidos);
				tiposYHabilidadesIngresados (tipoStr, tipos, habilidades);
			}

			catch(TipoNoValidoExcepcion e) {
				System.out.println("el tipo ingresado es invalido");
			}

			System.out.println("Si desea eliminar otro tipo presione 1, sino presione 2:");
			opcion = sc.nextInt();
		}
	}

	public void tiposYHabilidadesIngresados (String tipoStr, List<Tipo> tipos, List<Habilidad> habilidades) {
		switch(tipoStr) {

			case "fuego":
				tipos.add(Tipo.Fuego);
				habilidades.add(Habilidad.AbsorveFuego);
				habilidades.add(Habilidad.MarLlamas);
				break;

			case "agua":
				tipos.add(Tipo.Agua);
				habilidades.add(Habilidad.AbsorveAgua);
				habilidades.add(Habilidad.BurbujaBubble);
				break;

			case "planta":
				tipos.add(Tipo.Planta);
				habilidades.add(Habilidad.AbsorveRayosSol);
				habilidades.add(Habilidad.DefensaHoja);
				break;

			case "roca":
				tipos.add(Tipo.Roca);
				habilidades.add(Habilidad.ChorroDeArena);
				habilidades.add(Habilidad.RocaAfilada);
				break;

			case "volador":
				tipos.add(Tipo.Volador);
				habilidades.add(Habilidad.RemolinoWhirlwind);
				habilidades.add(Habilidad.TornadoGust);
				break;

			case "hielo":
				tipos.add(Tipo.Hielo);
				habilidades.add(Habilidad.VientoHielo);
				habilidades.add(Habilidad.CantoHelado);
				break;

			case "acero":
				tipos.add(Tipo.Acero);
				habilidades.add(Habilidad.AlaDeAcero);
				habilidades.add(Habilidad.BombaIman);
				break;
		}
	}


/*
	public Evolucion insertarEvolucion() {
		String nombreEvolucion = this.insertarNombre();
		Integer nivelEvolucion = this.insertarNivel();

		Evolucion evolucion = new Evolucion(nombreEvolucion, nivelEvolucion);
		
		//evolucion.setTipos(this.insertarTiposYHabilidades(evolucion.getHabilidades()));
		
		return evolucion;
	}
	

	public void evolucionDePokemon(Pokemon pokemon) {
		if (pokemon.getEvoluciones().size() == 0) {
			pokemon.agregarEvolucion(this.evolucionesDePokemon(pokemon).get(0));
		}
		else {
			pokemon.agregarEvolucion(this.evolucionesDePokemon(pokemon).get(1));
		}
	}*/
	


	/*public Pokemon buscarPokemon(String nombre) {
		Integer i = 0;
		while(!(this.pokemons.get(i).getNombre().equals(nombre))) {
			i++;
		}
		return pokemons.get(i);
	}*/
	
	
	public void edicionEvoluciones(Pokemon pokemon) throws PokemonNoEvolucionadoExcepcion {
		String opcion;
		Boolean editandoEvolucion = true;
		while (editandoEvolucion) {
			System.out.println("Si desea modificar una evolucion ingrese 'M' /n Si desea eliminar una evolucion ingrese 'E' \n Si desea salir ingrese 'Salir'");
			opcion = sc.next();
			switch (opcion) {
			
				case "M":
	//				this.modificarEvolucion(pokemon);
					System.out.println("Evolucion modificada");
					break;
					
				case "E":
					this.eliminarEvolucion(pokemon);
					System.out.println("Evolucion eliminada");
					break;
					
				case "Salir":
					editandoEvolucion = false;
					break;
			}
		}
	}

	
	public String datosPokemon(Pokemon pokemon) {
		return pokemon.toString();
	}
	
/*
	public void agregarEvolucionesAMap(Pokemon pokemon) {
		ArrayList<Evolucion> evoluciones = new ArrayList<Evolucion>();
		if (!this.existePokemonConEvoluciones(pokemon.getNombre())) {
			System.out.println("Está agregando un pokemon nuevo, a continuacion agregue sus respectivas evoluciones:");
			System.out.println("primerEvolucion:");
			evoluciones.add(this.insertarEvolucion());
			System.out.println("SegundaEvolucion:");
			evoluciones.add(this.insertarEvolucion());
			
			this.getPokemonsYEvoluciones().put(pokemon, evoluciones);	
		}
	}
	
	public Boolean existePokemonConEvoluciones(String nombre) {
		List<Pokemon> pokemons = new ArrayList<Pokemon>(this.getPokemonsYEvoluciones().keySet());
		Boolean existePokemon = false;
		for (Pokemon p : pokemons) {
			existePokemon = existePokemon || p.getNombre().equals(nombre);
		}
		return existePokemon;
	}
	
	
	
	public Pokemon buscarPokemonConEvoluciones(Pokemon pokemon) {
		List<Pokemon> pokemons = new ArrayList<Pokemon>(this.getPokemonsYEvoluciones().keySet());
		Integer i = 0;
		while(!(pokemons.get(i).equals(pokemon))) {
			i++;
		}
		return pokemons.get(i);
	}*/
	
/*	public List<Evolucion> evolucionesDePokemon(Pokemon pokemon) {
		List<Evolucion> evoluciones = new ArrayList<Evolucion>();
		for (Map.Entry<Pokemon, ArrayList<Evolucion>> entry : this.getPokemonsYEvoluciones().entrySet()) {
			if(entry.getKey().equals(pokemon)) {
				evoluciones.addAll(entry.getValue());
			}
		}
		return evoluciones;
	}*/
	
/*	public List<Evolucion> evolucionesDePokemon(Pokemon pokemon) {
		List<Evolucion> evoluciones = new ArrayList<>();
		for (Map.Entry<Pokemon, ArrayList<Evolucion>> entry : this.getPokemonsYEvoluciones().entrySet()) {
			if(entry.getKey().getNombre().equals(pokemon.getNombre())) {
				evoluciones.addAll(entry.getValue());
			}
		}
		return this.asignacionDeTipos(evoluciones, pokemon);
	}
	
	public List<Evolucion> asignacionDeTipos(List<Evolucion> evoluciones, Pokemon pokemon){
		List<Evolucion> evolucionesConTipos = new ArrayList<>();
		for (Evolucion e: evoluciones) { 
			e.setTipos(pokemon.getTipos());
			e.setHabilidades(pokemon.getHabilidades());
			evolucionesConTipos.add(e);
		}
		return evolucionesConTipos;
	}*/
	
}
