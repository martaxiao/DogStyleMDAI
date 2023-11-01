package com.example.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private String nombre;
	private String telefono;
	
	@OneToMany(mappedBy = "usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Cita> citas;
	
	public Usuario() {
		id = 0;
		username = "";
		password = "";
		nombre = "";
		telefono = "";
		citas = new ArrayList<>();
	}
	
	public Usuario(long id, String username, String password, String nombre, String telefono, List<Cita> citas) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.telefono = telefono;
		this.citas = citas;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(citas, id, nombre, password, telefono, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(citas, other.citas) && id == other.id && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(telefono, other.telefono)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", nombre=" + nombre
				+ ", telefono=" + telefono + ", citas=" + citas + "]";
	}
	
}
