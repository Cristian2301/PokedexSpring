package pokedex_spring;

import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
public class Evolucion {
	private String nombre;
	private List<Tipo> tipos;
	private Integer nivel;
	private List<Habilidad> habilidades;
	
	/*Constructor*/
	public Evolucion(String nombre, Integer nivel) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.habilidades = new ArrayList<>();
		this.tipos = new ArrayList<>();
	}

//	public void agregarTipo(String tipo) {
		/*agrega el tipo "tipo" a la lista de tipos.*/
//			this.tipos.add(tipo);
//		}

/*	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("*EVOLUCION " + getNombre().toUpperCase()+"*\n");
		str.append("Tipos:" + getTipos()+"\n");
		str.append("Nivel:" + getNivel()+"\n");
		str.append("Habilidades:" + getHabilidades()+"\n");
		return str.toString();
	}*/
}
