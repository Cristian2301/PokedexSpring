package pokedex;

import domain.Pokemon;
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

    @GetMapping("/")
    public String inicio(Model model){
        List<Pokemon> pokemons = aplicacion.getPokemons();
        model.addAttribute("pokemons", pokemons);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Pokemon pokemon){
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Pokemon pokemon, BindingResult resultadoBindeado){
        if(resultadoBindeado.hasErrors()){
            return "modificar";
        }
        aplicacion.agregarPokemon(pokemon);
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

   /* @GetMapping("/evolucionar/{id}")
    public String evolucionar(Pokemon pokemon){
        aplicacion.evolucionarPokemon(pokemon);
        return "redirect:/";
    }*/
}
