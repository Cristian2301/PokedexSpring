package pokedex;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import domain.Habilidad;
import domain.Pokemon;
import domain.Tipo;
import excepciones.PokemonEsEvolucionExcepcion;
import excepciones.PokemonExistenteExcepcion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
        Pokemon pokemon6 = new Pokemon ("Pichu", 0);

        //Evoluciones
        Pokemon EvolucionCharmander = new Pokemon("Charmeleon", 16);
        Pokemon EvolucionCharmeleon = new Pokemon("Charizard", 36);
        Pokemon EvolucionBulbasaur = new Pokemon("Ivysaur", 16);
        Pokemon EvolucionIvysaur = new Pokemon("Venusaur", 32);
        Pokemon EvolucionChikorita = new Pokemon("Bayleef", 16);
        Pokemon EvolucionBayleef = new Pokemon("Meganium", 32);
        Pokemon EvolucionSquirtle = new Pokemon("Wartortle", 16);
        Pokemon EvolucionWartortle = new Pokemon("Blastoise", 36);
        Pokemon EvolucionCyndaquil = new Pokemon("Quilava", 14);
        Pokemon EvolucionQuilava = new Pokemon("Typhlosion", 36);
        Pokemon EvolucionPichu = new Pokemon("Pikachu", 16);
        Pokemon EvolucionPikachu = new Pokemon("Raichu", 36);


        EvolucionCharmander.agregarTipo(Tipo.Fuego);
        EvolucionCharmeleon.agregarTipo(Tipo.Fuego);
        EvolucionCharmeleon.agregarTipo(Tipo.Volador);
        EvolucionCharmander.agregarHabilidad(Habilidad.AbsorveFuego);
        EvolucionCharmander.agregarHabilidad(Habilidad.MarLlamas);
        EvolucionCharmeleon.agregarHabilidad(Habilidad.AbsorveFuego);
        EvolucionCharmeleon.agregarHabilidad(Habilidad.MarLlamas);
        EvolucionCharmeleon.agregarHabilidad(Habilidad.RemolinoWhirlwind);
        EvolucionCharmeleon.agregarHabilidad(Habilidad.AlaDeAcero);
    /*    EvolucionCharmeleon.getEvolucion().setNombre("");
        EvolucionCharmeleon.getEvolucion().setNivel(0);
        EvolucionCharmeleon.getEvolucion().setTipos(new ArrayList<>());
        EvolucionCharmeleon.getEvolucion().setHabilidades(new ArrayList<>());
        EvolucionCharmeleon.getEvolucion().setEvolucion(null);*/


        EvolucionBulbasaur.agregarTipo(Tipo.Planta);
        EvolucionIvysaur.agregarTipo(Tipo.Planta);
        EvolucionIvysaur.agregarTipo(Tipo.Veneno);
        EvolucionBulbasaur.agregarHabilidad(Habilidad.AbsorveRayosSol);
        EvolucionBulbasaur.agregarHabilidad(Habilidad.DefensaHoja);
        EvolucionIvysaur.agregarHabilidad(Habilidad.AbsorveRayosSol);
        EvolucionIvysaur.agregarHabilidad(Habilidad.DefensaHoja);
        EvolucionIvysaur.agregarHabilidad(Habilidad.PolvoVeneno);
  /*      EvolucionIvysaur.getEvolucion().setNombre("");
        EvolucionIvysaur.getEvolucion().setNivel(0);
        EvolucionIvysaur.getEvolucion().setTipos(new ArrayList<>());
        EvolucionIvysaur.getEvolucion().setHabilidades(new ArrayList<>());
        EvolucionIvysaur.getEvolucion().setEvolucion(null);*/

   /*     EvolucionChikorita.agregarTipo(Tipo.Planta);
        EvolucionBayleef.agregarTipo(Tipo.Planta);
        EvolucionChikorita.agregarHabilidad(Habilidad.AbsorveRayosSol);
        EvolucionChikorita.agregarHabilidad(Habilidad.DefensaHoja);
        EvolucionBayleef.agregarHabilidad(Habilidad.AbsorveRayosSol);
        EvolucionBayleef.agregarHabilidad(Habilidad.DefensaHoja);
        EvolucionBayleef.getEvolucion().setNombre("");
        EvolucionBayleef.getEvolucion().setNivel(0);
        EvolucionBayleef.getEvolucion().setTipos(new ArrayList<>());
        EvolucionBayleef.getEvolucion().setHabilidades(new ArrayList<>());
        EvolucionBayleef.getEvolucion().setEvolucion(null);

        EvolucionSquirtle.agregarTipo(Tipo.Agua);
        EvolucionWartortle.agregarTipo(Tipo.Agua);
        EvolucionSquirtle.agregarHabilidad(Habilidad.AbsorveAgua);
        EvolucionSquirtle.agregarHabilidad(Habilidad.BurbujaBubble);
        EvolucionWartortle.agregarHabilidad(Habilidad.AbsorveAgua);
        EvolucionWartortle.agregarHabilidad(Habilidad.BurbujaBubble);
        EvolucionWartortle.getEvolucion().setNombre("");
        EvolucionWartortle.getEvolucion().setNivel(0);
        EvolucionWartortle.getEvolucion().setTipos(new ArrayList<>());
        EvolucionWartortle.getEvolucion().setHabilidades(new ArrayList<>());
        EvolucionWartortle.getEvolucion().setEvolucion(null);

        EvolucionCyndaquil.agregarTipo(Tipo.Fuego);
        EvolucionQuilava.agregarTipo(Tipo.Fuego);
        EvolucionCyndaquil.agregarHabilidad(Habilidad.AbsorveFuego);
        EvolucionCyndaquil.agregarHabilidad(Habilidad.MarLlamas);
        EvolucionQuilava.agregarHabilidad(Habilidad.AbsorveFuego);
        EvolucionQuilava.agregarHabilidad(Habilidad.MarLlamas);
        EvolucionQuilava.getEvolucion().setNombre("");
        EvolucionQuilava.getEvolucion().setNivel(0);
        EvolucionQuilava.getEvolucion().setTipos(new ArrayList<>());
        EvolucionQuilava.getEvolucion().setHabilidades(new ArrayList<>());
        EvolucionQuilava.getEvolucion().setEvolucion(null);

        EvolucionPichu.agregarTipo(Tipo.Electrico);
        EvolucionPikachu.agregarTipo(Tipo.Electrico);
        EvolucionPichu.agregarHabilidad(Habilidad.BolaVoltio);
        EvolucionPichu.agregarHabilidad(Habilidad.OndaTrueno);
        EvolucionPikachu.agregarHabilidad(Habilidad.BolaVoltio);
        EvolucionPikachu.agregarHabilidad(Habilidad.OndaTrueno);
        EvolucionPikachu.getEvolucion().setNombre("");
        EvolucionPikachu.getEvolucion().setNivel(0);
        EvolucionPikachu.getEvolucion().setTipos(new ArrayList<>());
        EvolucionPikachu.getEvolucion().setHabilidades(new ArrayList<>());
        EvolucionPikachu.getEvolucion().setEvolucion(null);*/


        pokemon1.setEvolucion(EvolucionCharmander);
        EvolucionCharmander.setEvolucion(EvolucionCharmeleon);
        pokemon2.setEvolucion(EvolucionBulbasaur);
        EvolucionBulbasaur.setEvolucion(EvolucionIvysaur);
   /*     pokemon3.setEvolucion(EvolucionChikorita);
        EvolucionChikorita.setEvolucion(EvolucionBayleef);
        pokemon4.setEvolucion(EvolucionSquirtle);
        EvolucionSquirtle.setEvolucion(EvolucionWartortle);
        pokemon5.setEvolucion(EvolucionCyndaquil);
        EvolucionCyndaquil.setEvolucion(EvolucionQuilava);
        pokemon6.setEvolucion(EvolucionPichu);
        EvolucionPichu.setEvolucion(EvolucionPikachu);*/


        //Se agregan los pokemons a la lista de pokemonsValidos
        aplicacion.getPokemonsValidos().add(pokemon1);
        aplicacion.getPokemonsValidos().add(EvolucionCharmander);
        aplicacion.getPokemonsValidos().add(EvolucionCharmeleon);
        aplicacion.getPokemonsValidos().add(pokemon2);
        aplicacion.getPokemonsValidos().add(EvolucionBulbasaur);
        aplicacion.getPokemonsValidos().add(EvolucionIvysaur);
    /*    aplicacion.getPokemonsValidos().add(pokemon3);
        aplicacion.getPokemonsValidos().add(EvolucionChikorita);
        aplicacion.getPokemonsValidos().add(EvolucionBayleef);
        aplicacion.getPokemonsValidos().add(pokemon4);
        aplicacion.getPokemonsValidos().add(EvolucionSquirtle);
        aplicacion.getPokemonsValidos().add(EvolucionWartortle);
        aplicacion.getPokemonsValidos().add(pokemon5);
        aplicacion.getPokemonsValidos().add(EvolucionCyndaquil);
        aplicacion.getPokemonsValidos().add(EvolucionQuilava);
        aplicacion.getPokemonsValidos().add(pokemon6);
        aplicacion.getPokemonsValidos().add(EvolucionPichu);
        aplicacion.getPokemonsValidos().add(EvolucionPikachu);*/
    }


    @GetMapping("/")
    public String inicio(Model model){
        if (aplicacion.getPokemonsValidos().isEmpty()){
            crearPokemonsValidos();
        }
        List<Pokemon> pokemons = aplicacion.getPokemons();
        System.out.println(pokemons);
        model.addAttribute("pokemons", pokemons);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Pokemon pokemon){
        return "modificar";
    }

    @PostMapping("/guardar")
    //@ExceptionHandler
    public String guardar(@Valid Pokemon pokemon, BindingResult resultadoBindeado, Model model){
        if(resultadoBindeado.hasErrors()){
            return "modificar";
        }
        try {
            aplicacion.agregarPokemon(pokemon);
        }
        catch(IndexOutOfBoundsException e){
            String mensaje = "El pokemon ingresado es invalido";
            model.addAttribute("mensaje", mensaje);
            return "modificar";
        }
/*        catch (PokemonExistenteExcepcion e) {
            String mensaje = "El pokemon ingresado ya existe";
            model.addAttribute("mensaje", mensaje);
            return "modificar";
        }
        catch (PokemonEsEvolucionExcepcion e) {
            String mensaje = "El pokemon ingresado no puede ser una evolucion";
            model.addAttribute("mensaje", mensaje);
            return "modificar";
        }*/
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
        try {
            aplicacion.evolucionarPokemon(pokemon);
        }
        catch(IndexOutOfBoundsException e){
            String mensaje1 = "Este pokemon no se puede evolucionar más";
            model.addAttribute("mensaje1", mensaje1);
            return "redirect:/";
        }
        return "redirect:/";
    }
}
