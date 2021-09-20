package es.cic.bootcamp.grupo02final.DTO;

import java.time.LocalDate;

public class FlujoDTO {
    
    private Long id;
    private String nombre;
    private LocalDate tiempoInicio;
    private LocalDate tiempoFin;
    private boolean estado;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public LocalDate getTiempoInicio() {
        return tiempoInicio;
    }
    public void setTiempoInicio(LocalDate tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }
    public LocalDate getTiempoFin() {
        return tiempoFin;
    }
    public void setTiempoFin(LocalDate tiempoFin) {
        this.tiempoFin = tiempoFin;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (estado ? 1231 : 1237);
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((tiempoFin == null) ? 0 : tiempoFin.hashCode());
        result = prime * result + ((tiempoInicio == null) ? 0 : tiempoInicio.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FlujoDTO other = (FlujoDTO) obj;
        if (estado != other.estado)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (tiempoFin == null) {
            if (other.tiempoFin != null)
                return false;
        } else if (!tiempoFin.equals(other.tiempoFin))
            return false;
        if (tiempoInicio == null) {
            if (other.tiempoInicio != null)
                return false;
        } else if (!tiempoInicio.equals(other.tiempoInicio))
            return false;
        return true;
    }

    
}
