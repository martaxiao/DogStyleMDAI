package com.example.data.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Cita {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private Date fecha;
	private String hora;
	private boolean libre;
	
	@ManyToOne
	private Usuario usuario;
	
	public Cita() {
		id = 0;
		fecha = new Date();
		hora = "";
		libre = false;
		usuario = new Usuario();
	}
	
	public Cita(long id, Date fecha, String hora, boolean libre, Usuario usuario) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.libre = libre;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public boolean isLibre() {
		return libre;
	}

	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, hora, id, libre, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(hora, other.hora) && id == other.id
				&& libre == other.libre && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Cita [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", libre=" + libre + ", usuario=" + usuario
				+ "]";
	}
}
