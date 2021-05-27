package pokedex_spring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

@Data
public class Pokemon {
	private String nombre;
	private List<Tipo> tipos;
	private Integer nivel;
	private List<Habilidad> habilidades;
	private List<Evolucion> evoluciones;

	/*Constructor*/
	public Pokemon(String nombre, Integer nivel) {
		//this.id = id;
		this.nombre = nombre;
		this.nivel = nivel;
		this.tipos = new ArrayList<Tipo>();
		this.habilidades = new ArrayList<Habilidad>();
		this.evoluciones = new ArrayList<Evolucion>();
	}

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
