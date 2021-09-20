package es.cic.bootcamp.grupo02final.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Instancia {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Length(max = 10)
	@NotBlank
	private String nombre;
	
	@OneToOne(mappedBy = "id")
	private Flujo flujo;
	
	@OneToMany(mappedBy = "id")
	private List<Conector> conectores;

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Flujo getFlujo() {
		return flujo;
	}

	public List<Conector> getConectores() {
		return conectores;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFlujo(Flujo flujo) {
		this.flujo = flujo;
	}

	public void setConectores(List<Conector> conectores) {
		this.conectores = conectores;
	}

	@Override
	public String toString() {
		return "Instancia [id=" + id + ", nombre=" + nombre + ", flujo=" + flujo + ", conectores=" + conectores + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instancia other = (Instancia) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	

}
