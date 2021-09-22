package es.cic.bootcamp.grupo02final.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Conector {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Length(max = 15)
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String lenguaje;
	
	@NotBlank
	private String tipoServicio;
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 476574c05a452fdd899b41ee3aa661fd6bce8899

	public Conector() {
		super();
	}

	public Conector(@Length(max = 15) @NotBlank String nombre, @NotBlank String lenguaje,
			@NotBlank String tipoServicio) {
		super();
		this.nombre = nombre;
		this.lenguaje = lenguaje;
		this.tipoServicio = tipoServicio;
	}
<<<<<<< HEAD
>>>>>>> 8b60d04f282c14916663aeed094eb681b31444fb
=======
>>>>>>> 476574c05a452fdd899b41ee3aa661fd6bce8899

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	@Override
	public String toString() {
		return "Conector [id=" + id + ", nombre=" + nombre + ", lenguaje=" + lenguaje + ", tipoServicio=" + tipoServicio
				+ ", instancia=" + "]";
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
		Conector other = (Conector) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}
	
	

}
