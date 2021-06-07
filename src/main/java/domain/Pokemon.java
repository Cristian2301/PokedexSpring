package domain;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Data
public class Pokemon {

	private String id;

	@NotEmpty
	private String nombre;
	private List<Tipo> tipos = new ArrayList<>();

	@NotNull
	@Min(1)
	@Max(100)
	private Integer nivel;
	private List<Habilidad> habilidades;
	private Pokemon evolucion;

	public Pokemon() {
		this.id = UUID.randomUUID().toString();
	}

	public Pokemon(String nombre, Integer nivel) {
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.nivel = nivel;
		this.tipos = new ArrayList<>();
		this.habilidades = new ArrayList<>();
		this.evolucion = new Pokemon();
	}

	public void agregarTipo(Tipo tipo) {
		/*agrega el tipo "tipo" a la lista de tipos.*/
		this.tipos.add(tipo);
	}

	public Boolean existeTipo(Tipo tipo){
		Boolean existe = false;
		for(Tipo t: getTipos()){
			existe = existe || t.toString().equals(tipo.toString());
		}
		return existe;
	}

	public void agregarHabilidad(Habilidad habilidad){
		this.habilidades.add(habilidad);
	}



	/*
	public String listarEvoluciones() {
		StringBuilder datos = new StringBuilder();
		for (Pokemon e : this.getEvoluciones()) {
			datos.append("\n");
			datos.append(e.toString());
		}
		return (datos.toString().equals(""))?"El pokemon aún no ha evolucionado":datos.toString();
	}*/

	//Constructor
/*	public Pokemon(String nombre, Integer nivel) {
		this.id = id++;
		this.nombre = nombre;
		this.nivel = nivel;
		this.tipos = new ArrayList<>();
		this.habilidades = new ArrayList<>();
		this.evoluciones = new ArrayList<>();
	}

		public void eliminarTipo(String tipo) {
	//elimina el tipo "tipo" de la lista de tipos.
		this.tipos.remove(tipo);
	}*/
}
