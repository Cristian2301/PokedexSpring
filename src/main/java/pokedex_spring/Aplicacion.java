package pokedex_spring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;

@Data
public class Aplicacion {
	@Autowired
	private Validador validador;
	@Autowired
	private List<Pokemon> pokemons;
	private Map<Pokemon, ArrayList<Evolucion>> pokemonsYEvoluciones = new HashMap<Pokemon, ArrayList<Evolucion>>();
	private Scanner sc = new Scanner(System.in);

	/* Constructor */
	public Aplicacion() {
		this.pokemons = new ArrayList<Pokemon>();
	}
	
	public String mostrarPokemon() {
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
	}
	
	
	public void agregarPokemon() {	
		String nombrePokemon = "";
		Boolean pokemonExistente;
		Integer nivelPokemon = 0;
		
		do {
			System.out.println("Ingrese los datos del pokemon que desea agregar:");
			
			try {
				nombrePokemon = this.insertarNombre();
				
				validador.validarPokemon(this, nombrePokemon);
				pokemonExistente = false;
			}
			catch(PokemonYaExisteExcepcion e) {
				System.out.println("El pokemon ingresado ya existe");
				pokemonExistente = true;
			}
		} while(pokemonExistente);
		
		nivelPokemon = this.insertarNivel();
		
		Pokemon pokemon = new Pokemon(nombrePokemon, nivelPokemon);
		
		pokemon.setTipos(this.insertarTiposYHabilidades(pokemon.getHabilidades()));
		
		this.agregarEvolucionesAMap(pokemon);
		
		this.getPokemons().add(pokemon);
		
	}
	
		
	public void modificarPokemon() {
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
		
	}
	
	public void edicionDatosPokemon(Pokemon pokemon) {
		Boolean modificandoPokemon = true;
		Boolean condicionPokemon = false;
		
		do {
			try {
				while (modificandoPokemon) {
					System.out.println(" �Que dato del pokemon desea modificar? (Ingrese: Nivel/TiposYHabilidades/Evoluciones) \n Si no desea modificar ning�n dato ingrese 'Salir':");
					String opcion = sc.next();
					switch (opcion) {
					
						case "Nivel":
							Integer nivelPokemon = this.insertarNivel();
							pokemon.setNivel(nivelPokemon);
							break;
							
						case "TiposYHabilidades":	
							this.edicionTiposYHabilidadesPokemon(pokemon);
				//			pokemon.getTipos().removeAll(pokemon.getTipos());
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
				System.out.println("El pokemon " + pokemon.getNombre() + " a�n no evolucion� \n El pokemon debe haber evolucionado antes para que se pueda eliminar una evoluci�n");
				condicionPokemon = true;
			}
		} while(condicionPokemon);
	}
	
	public void edicionTiposYHabilidadesPokemon(Pokemon pokemon) {
		String opcion;
		Boolean editandoEvolucion = true;
		while (editandoEvolucion) {
			System.out.println("Si desea agregar un tipo ingrese 'A' \n Si desea eliminar algun tipo ingrese 'E' \n Si desea salir ingrese 'Salir'");
			opcion = sc.next();
			switch (opcion) {
			
				case "A":
					pokemon.setTipos(this.insertarTiposYHabilidades(pokemon.getHabilidades()));
					System.out.println("SE HAN AGREGADO LOS TIPOS Y LAS HABILIDADES");
					break;
					
				case "E":
					this.eliminarTiposYHabilidades(pokemon.getTipos(), pokemon.getHabilidades());
					System.out.println("SE HAN ELIMINADO LOS TIPOS Y LAS HABILIDADES");
					break;
					
				case "Salir":
					editandoEvolucion = false;
					break;
			}
		}		
	}
	
	
	public void eliminarTiposYHabilidades(List<Tipo> tipos, List<Habilidad> habilidades) {
		Integer opcion = 1;
		List<String> tiposValidos = Arrays.asList("fuego", "agua", "planta", "roca", "volador", "hielo", "acero");
		Tipo tipo = null;
		Habilidad habilidad = null;
		System.out.println("*Mensaje: Recuerde que al eliminar un tipo, tambien esta eliminando la habilidad que corresponde a ese tipo*");
		while(opcion == 1) {
			try {
				System.out.println("Tipo:");
				String tipoStr = sc.next();
				validador.validarTipos(tipoStr, tiposValidos);
				switch(tipoStr) {
					   
					case "fuego":
						tipos.remove(tipo.Fuego);
						habilidades.remove(habilidad.AbsorveFuego);
						habilidades.remove(habilidad.MarLlamas);
						break;
					
					case "agua":
						tipos.remove(tipo.Agua);
						habilidades.remove(habilidad.AbsorveAgua);
						habilidades.remove(habilidad.BurbujaBubble);
						break;
						
					case "planta":
						tipos.remove(tipo.Planta);
						habilidades.remove(habilidad.AbsorveRayosSol);
						habilidades.remove(habilidad.DefensaHoja);
						break;
				     
					case "roca":
						tipos.remove(tipo.Roca);
						habilidades.remove(habilidad.ChorroDeArena);
						habilidades.remove(habilidad.RocaAfilada);
						break;
						
					case "volador":
						tipos.remove(tipo.Volador);
						habilidades.remove(habilidad.RemolinoWhirlwind);
						habilidades.remove(habilidad.TornadoGust);
						break;
						
					case "hielo":
						tipos.remove(tipo.Hielo);
						habilidades.remove(habilidad.VientoHielo);
						habilidades.remove(habilidad.CantoHelado);
						break;
					
					case "acero":
						tipos.remove(tipo.Acero);
						habilidades.remove(habilidad.AlaDeAcero);
						habilidades.remove(habilidad.BombaIman);
						break;
				}
			}
			catch(TipoNoValidoExcepcion e) {
				System.out.println("el tipo ingresado es invalido");
			}
			
			System.out.println("Si desea eliminar otro tipo presione 1, sino presione 2:");
			opcion = sc.nextInt();
		}
	}
	
	
	public void eliminarPokemon() {
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
	}
	
	
	public String listarPokemons() {
		StringBuilder strPokemons = new StringBuilder();
		for (Pokemon p: this.getPokemons()) {
			strPokemons.append(this.datosPokemon(p)+"\n");
		}
		return strPokemons.toString();
	}
	
	
	//TODO: HACER QUE LA EXCEPTION DE QUE NO PUEDE EVOLUCIONAR MAS, CUANDO HAGA EL ROLLBACK ME TIRE PARA LA LISTA DE OPCIONES
	//DEL MENU 
	public void evolucionarPokemon() {
		Boolean pokemonNoExiste = false;
		Boolean pokemonNoEvoluciona = false;
		do { 
			System.out.println("Ingrese el nombre del pokemon que desea evolucionar:");
			String nombre = sc.next();
			Pokemon pokemon;
			
			try {
				pokemon = this.buscarPokemon(nombre);
				pokemonNoExiste = false;
				
				validador.validarPokemonEvolucionado(pokemon);
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
	}
	
	
	public void eliminarEvolucion(Pokemon pokemon) throws PokemonNoEvolucionadoExcepcion {
		validador.validarPokemonNoEvolucionado(pokemon);
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
	
	
	public List<Tipo> insertarTiposYHabilidades(List<Habilidad> habilidades){
		List<Tipo> tipos = new ArrayList<Tipo>();
		List<String> tiposValidos = Arrays.asList("fuego", "agua", "planta", "roca", "volador", "hielo", "acero");
		Integer opcion = 1;
		Tipo tipo = null;
		Habilidad habilidad = null;
		System.out.println("*Mensaje: Recuerde que al agregar un tipo, tambien esta agregando la habilidad que corresponde a ese tipo*");
		
		while(opcion == 1) {
			try {
				System.out.println("Los tipos existentes son: 'fuego', 'agua', 'planta', 'roca', 'volador', 'hielo', 'acero' (Debe ingresarlos tal cual estan escritos aqui)");
				System.out.println("Tipo:");
				String tipoStr = sc.next();
				validador.validarTipos(tipoStr, tiposValidos);
				switch(tipoStr) {
					   
					case "fuego":
						tipos.add(tipo.Fuego);
						habilidades.add(habilidad.AbsorveFuego);
						habilidades.add(habilidad.MarLlamas);
						break;
					
					case "agua":
						tipos.add(tipo.Agua);
						habilidades.add(habilidad.AbsorveAgua);
						habilidades.add(habilidad.BurbujaBubble);
						break;
						
					case "planta":
						tipos.add(tipo.Planta);
						habilidades.add(habilidad.AbsorveRayosSol);
						habilidades.add(habilidad.DefensaHoja);
						break;
				     
					case "roca":
						tipos.add(tipo.Roca);
						habilidades.add(habilidad.ChorroDeArena);
						habilidades.add(habilidad.RocaAfilada);
						break;
						
					case "volador":
						tipos.add(tipo.Volador);
						habilidades.add(habilidad.RemolinoWhirlwind);
						habilidades.add(habilidad.TornadoGust);
						break;
						
					case "hielo":
						tipos.add(tipo.Hielo);
						habilidades.add(habilidad.VientoHielo);
						habilidades.add(habilidad.CantoHelado);
						break;
					
					case "acero":
						tipos.add(tipo.Acero);
						habilidades.add(habilidad.AlaDeAcero);
						habilidades.add(habilidad.BombaIman);
						break;
				}
			}
			catch(TipoNoValidoExcepcion e) {
				System.out.println("el tipo ingresado es invalido");
			}
			
			System.out.println("Si desea agregar otro tipo presione 1, sino presione 2:");
			opcion = sc.nextInt();
		}
		return tipos;
	}

	
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
	}
	
	public Boolean existePokemon(String nombre) {
		Boolean existePokemon = false;
		for (Pokemon p: this.getPokemons()) {
			existePokemon = existePokemon || p.getNombre().equals(nombre);
		}
		return existePokemon;
	}	

	public Pokemon buscarPokemon(String nombre) {
		Integer i = 0;
		while(!(this.pokemons.get(i).getNombre().equals(nombre))) {
			i++;
		}
		return pokemons.get(i);
	}
	
	
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
	
	
	public void agregarEvolucionesAMap(Pokemon pokemon) {
		ArrayList<Evolucion> evoluciones = new ArrayList<Evolucion>();
		if (!this.existePokemonConEvoluciones(pokemon.getNombre())) {
			System.out.println("Est� agregando un pokemon nuevo, a continuacion agregue sus respectivas evoluciones:");
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
	}
	
/*	public List<Evolucion> evolucionesDePokemon(Pokemon pokemon) {
		List<Evolucion> evoluciones = new ArrayList<Evolucion>();
		for (Map.Entry<Pokemon, ArrayList<Evolucion>> entry : this.getPokemonsYEvoluciones().entrySet()) {
			if(entry.getKey().equals(pokemon)) {
				evoluciones.addAll(entry.getValue());
			}
		}
		return evoluciones;
	}*/
	
	public List<Evolucion> evolucionesDePokemon(Pokemon pokemon) {
		List<Evolucion> evoluciones = new ArrayList<Evolucion>();
		for (Map.Entry<Pokemon, ArrayList<Evolucion>> entry : this.getPokemonsYEvoluciones().entrySet()) {
			if(entry.getKey().getNombre().equals(pokemon.getNombre())) {
				evoluciones.addAll(entry.getValue());
			}
		}
		return this.asignacionDeTipos(evoluciones, pokemon);
	}
	
	public List<Evolucion> asignacionDeTipos(List<Evolucion> evoluciones, Pokemon pokemon){
		List<Evolucion> evolucionesConTipos = new ArrayList<Evolucion>();
		for (Evolucion e: evoluciones) { 
			e.setTipos(pokemon.getTipos());
			e.setHabilidades(pokemon.getHabilidades());
			evolucionesConTipos.add(e);
		}
		return evolucionesConTipos;
	}
	
}
