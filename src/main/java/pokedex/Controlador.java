package pokedex;

import domain.Pokemon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class Controlador {

    Aplicacion aplicacion = new Aplicacion();

    @GetMapping("/")
    public String inicio(Model model){
        List<Pokemon> pokemons = aplicacion.getPokemons();
    /*    Tipo tipo = null;
        Pokemon pokemon1 = new Pokemon("Charmander", 2);
        pokemon1.agregarTipo(tipo.Fuego);
        Pokemon pokemon2 = new Pokemon("Chikorita", 3);
        pokemon2.agregarTipo(tipo.Roca);
        Pokemon pokemon3 = new Pokemon("Bulbasaur", 4);
        pokemon3.agregarTipo(tipo.Agua);
        Pokemon pokemon4 = new Pokemon("Cristian", 2);
        pokemon4.agregarTipo(tipo.Fuego);

        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(pokemon1);
        pokemons.add(pokemon2);
        pokemons.add(pokemon3);
        pokemons.add(pokemon4);*/
        model.addAttribute("pokemons", pokemons);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Pokemon pokemon){
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(Pokemon pokemon){
        aplicacion.agregarPokemon(pokemon);
        return "redirect:/";
    }

    @GetMapping("/editar/{nombre}")
    public String editar(Pokemon pokemon, Model model){
        pokemon = aplicacion.buscarPokemon(pokemon);
        model.addAttribute("pokemon", pokemon);
        return "modificar";
    }

    @GetMapping("/eliminar/{nombre}")
    public String eliminar(Pokemon pokemon){
        aplicacion.eliminarPokemon(pokemon);
        return "redirect:/";
    }
}
