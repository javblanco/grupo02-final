package es.cic.bootcamp.grupo02final.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Flujo {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Length(max = 15)
	@NotBlank
	private String nombre;
	
	private LocalDate tiempoInicio;
	
	private LocalDate tiempoFin;

	@ManyToOne
	private Instancia instancia;
	
	@NotNull
	private boolean estado;

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getTiempoInicio() {
		return tiempoInicio;
	}

	public LocalDate getTiempoFin() {
		return tiempoFin;
	}

	public Instancia getInstancia() {
		return instancia;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTiempoInicio(LocalDate tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	public void setTiempoFin(LocalDate tiempoFin) {
		this.tiempoFin = tiempoFin;
	}

	public void setInstancia(Instancia instancia) {
		this.instancia = instancia;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Flujo [id=" + id + ", nombre=" + nombre 
					+ ", tiempoInicio=" + tiempoInicio + ", tiempoFin=" + tiempoFin 
					+ ", instancia=" + (!Objects.isNull(instancia) ? instancia.toString() : null)
					+ ", estado=" + estado + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(estado, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flujo other = (Flujo) obj;
		return estado == other.estado && Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

    public void setConectores(List<Conector> conectores) {
    }

	public Flujo(String nombre, LocalDate tiempoInicio, LocalDate tiempoFin, Instancia instancia, boolean estado) {
		super();
		this.nombre = nombre;
		this.tiempoInicio = tiempoInicio;
		this.tiempoFin = tiempoFin;
		this.instancia = instancia;
		this.estado = estado;
	}

	public Flujo(){
		super();
	}

}
