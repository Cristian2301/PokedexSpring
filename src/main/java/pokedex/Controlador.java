package pokedex;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import domain.Habilidad;
import domain.Pokemon;
import domain.Tipo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class Controlador {

    @Autowired
    Aplicacion aplicacion;

    public void crearPokemonsValidos(){
        //Pokemons
        Pokemon pokemon1 = new Pokemon("Charmander", 0);
        Pokemon pokemon2 = new Pokemon("Bulbasaur", 0);
        Pokemon pokemon3 = new Pokemon("Chikorita", 0);
        Pokemon pokemon4 = new Pokemon("Squirtle", 0);
        Pokemon pokemon5 = new Pokemon("Cyndaquil", 0);

        //Evoluciones
        Pokemon EvolucionCharmander = new Pokemon("Charmeleon", 16);
        Pokemon EvolucionCharmeleon = new Pokemon("Charizard", 36);
        Pokemon EvolucionBulbasaur = new Pokemon("Ivysaur", 16);
        Pokemon EvolucionIvysaur = new Pokemon("Venusaur", 36);

        EvolucionCharmander.agregarTipo(Tipo.Fuego);
        EvolucionCharmeleon.agregarTipo(Tipo.Fuego);
        EvolucionCharmeleon.agregarTipo(Tipo.Volador);
        EvolucionCharmander.agregarHabilidad(Habilidad.AbsorveFuego);
        EvolucionCharmander.agregarHabilidad(Habilidad.MarLlamas);
        EvolucionCharmeleon.agregarHabilidad(Habilidad.AbsorveFuego);
        EvolucionCharmeleon.agregarHabilidad(Habilidad.MarLlamas);
        EvolucionCharmeleon.agregarHabilidad(Habilidad.RemolinoWhirlwind);
        EvolucionCharmeleon.agregarHabilidad(Habilidad.AlaDeAcero);

        EvolucionBulbasaur.agregarTipo(Tipo.Agua);
        EvolucionIvysaur.agregarTipo(Tipo.Agua);
        EvolucionIvysaur.agregarTipo(Tipo.Volador);
        EvolucionBulbasaur.agregarHabilidad(Habilidad.AbsorveAgua);
        EvolucionBulbasaur.agregarHabilidad(Habilidad.BurbujaBubble);
        EvolucionIvysaur.agregarHabilidad(Habilidad.AbsorveAgua);
        EvolucionIvysaur.agregarHabilidad(Habilidad.BurbujaBubble);
        EvolucionIvysaur.agregarHabilidad(Habilidad.RemolinoWhirlwind);
        EvolucionIvysaur.agregarHabilidad(Habilidad.AlaDeAcero);

        pokemon1.setEvolucion(EvolucionCharmander);
        EvolucionCharmander.setEvolucion(EvolucionCharmeleon);
        //EvolucionCharmeleon.setEvolucion(null);
        pokemon2.setEvolucion(EvolucionBulbasaur);
        EvolucionBulbasaur.setEvolucion(EvolucionIvysaur);
        //EvolucionIvysaur.setEvolucion(null);


        //Se agregan los pokemons a la lista de pokemonsValidos
        aplicacion.getPokemonsValidos().add(pokemon1);
        aplicacion.getPokemonsValidos().add(EvolucionCharmander);
        aplicacion.getPokemonsValidos().add(EvolucionCharmeleon);
        aplicacion.getPokemonsValidos().add(pokemon2);
        aplicacion.getPokemonsValidos().add(EvolucionBulbasaur);
        aplicacion.getPokemonsValidos().add(EvolucionIvysaur);
    }

    @GetMapping("/")
    public String inicio(Model model){
        if (aplicacion.getPokemonsValidos().isEmpty()){
            crearPokemonsValidos();
        }
        List<Pokemon> pokemons = aplicacion.getPokemons();
        model.addAttribute("pokemons", pokemons);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Pokemon pokemon){
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Pokemon pokemon, BindingResult resultadoBindeado, Model model){
        if(resultadoBindeado.hasErrors()){
            return "modificar";
        }
     //   List<Tipo> tipos = pokemon.getTipos();
    //    model.addAttribute("tipos", tipos);
        try {
            aplicacion.agregarPokemon(pokemon);
        }
        catch(IndexOutOfBoundsException e){
            String mensaje = "El pokemon ingresado es invalido";
            model.addAttribute("mensaje", mensaje);
            return "modificar";
        }
      //  System.out.println(pokemon.getTipos());
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editar(Pokemon pokemon, Model model){
        pokemon = aplicacion.buscarPokemon(pokemon);
        model.addAttribute("pokemon", pokemon);
        return "modificar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(Pokemon pokemon){
        aplicacion.eliminarPokemon(pokemon);
        return "redirect:/";
    }

    @GetMapping("/evolucionar/{id}")
    public String evolucionar(Pokemon pokemon, Model model){
        Pokemon evolucion = aplicacion.evolucionarPokemon(aplicacion.buscarPokemon(pokemon));

        try {
            aplicacion.agregarPokemon(evolucion);
        }
        catch(IndexOutOfBoundsException e){
            String mensaje1 = "Este pokemon no se puede evolucionar más";
            model.addAttribute("mensaje1", mensaje1);
            return "redirect:/";
        }

        aplicacion.eliminarPokemon(pokemon);
        return "redirect:/";
    }
}
