package domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.UUID;
import java.util.List;

@Data
@Component
public class Pokemon {

	private String id;

	@NotEmpty
	private String nombre;

	private List<Tipo> tipos;

	@Positive
	private Integer nivel;

	private List<Habilidad> habilidades;
	private List<Evolucion> evoluciones;

	public Pokemon() {
		this.id = UUID.randomUUID().toString();
	}

	/*Constructor*/
/*	public Pokemon(String nombre, Integer nivel) {
		this.id = id++;
		this.nombre = nombre;
		this.nivel = nivel;
		this.tipos = new ArrayList<>();
		this.habilidades = new ArrayList<>();
		this.evoluciones = new ArrayList<>();
	}*/

	public void agregarTipo(Tipo tipo) {
		/*agrega el tipo "tipo" a la lista de tipos.*/
		this.tipos.add(tipo);
	}

//	public void eliminarTipo(String tipo) {
	/*elimina el tipo "tipo" de la lista de tipos.*/
//		this.tipos.remove(tipo);
//	}

	public void agregarEvolucion(Evolucion evolucion) {
	/*agrega la evolucion "evolucion" a la lista de evoluciones.*/
		this.evoluciones.add(evolucion);
	}

	public String listarEvoluciones() {
		StringBuilder datos = new StringBuilder();
		for (Evolucion e : this.getEvoluciones()) {
			datos.append("\n");
			datos.append(e.toString());
		}
		return (datos.toString().equals(""))?"El pokemon aún no ha evolucionado":datos.toString();
	}
	/*
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("----------------------------------------------------------------------------------------------------------\n");
		str.append("*POKEMON " + getNombre().toUpperCase()+"*\n");
		str.append("Tipos:" + getTipos()+"\n");
		str.append("Nivel:" + getNivel()+"\n");
		str.append("\nEvoluciones: " + listarEvoluciones()+"\n");
		str.append("Habilidades:" + getHabilidades()+"\n");
		return str.toString();
	}*/
}
