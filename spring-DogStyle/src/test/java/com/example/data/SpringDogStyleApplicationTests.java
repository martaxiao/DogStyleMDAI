package com.example.data;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.data.model.Cita;
import com.example.data.model.Usuario;
import com.example.data.repository.CitaRepository;
import com.example.data.repository.UsuarioRepository;

@SpringBootTest
class SpringDogStyleApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CitaRepository citaRepository;
	
	@Test
	void contextLoads() {
		crearUsuariosyCitas();
		mostrarUsuarioConCitas();
		eliminarCita();
		mostrarUsuarioConCitas();
		eliminarUsuario();
		mostrarUsuarioConCitas();
	}
	
	void crearUsuariosyCitas() {
		Usuario usuario = new Usuario();
		usuario.setUsername("juan");
		usuario.setPassword("123");
		usuario.setNombre("Juan");
		usuario.setTelefono("123-45-67-89");

		Cita cita11 = new Cita();
		cita11.setFecha(new Date());
		cita11.setHora("10:00");
		cita11.setLibre(true);
		cita11.setUsuario(usuario);

		Cita cita12 = new Cita();
		cita12.setFecha(new Date());
		cita12.setHora("10:30");
		cita12.setLibre(true);
		cita12.setUsuario(usuario);

		Cita cita13 = new Cita();
		cita13.setFecha(new Date());
		cita13.setHora("11:00");
		cita13.setLibre(true);
		cita13.setUsuario(usuario);

		Usuario usuario2 = new Usuario();
		usuario2.setUsername("lauu");
		usuario2.setPassword("456");
		usuario2.setNombre("Laura");
		usuario2.setTelefono("123-65-49-87");

		Cita cita21 = new Cita();
		cita21.setFecha(new Date());
		cita21.setHora("17:00");
		cita21.setLibre(true);
		cita21.setUsuario(usuario2);

		Cita cita22 = new Cita();
		cita22.setFecha(new Date());
		cita22.setHora("17:30");
		cita22.setLibre(true);
		cita22.setUsuario(usuario2);

		usuario.setCitas(List.of(cita11, cita12, cita13));
		usuario2.setCitas(List.of(cita21, cita22));

		// Guardamos los usuarios
		usuarioRepository.saveAll(List.of(usuario, usuario2));

		// Guardamos las citas
		citaRepository.saveAll(List.of(cita11, cita12, cita13, cita21, cita22));
	}

	void mostrarUsuarioConCitas() {
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
		int numUsuario = 1;
		for (Usuario usuario : usuarios) {
			System.out.println("--------------------------");
			System.out.println("Información del usuario " + numUsuario + ": ");
			System.out.println("--------------------------");
			System.out.println("- ID: " + usuario.getId());
			System.out.println("- Nombre: " + usuario.getNombre());
			System.out.println("- Username: " + usuario.getUsername());
			System.out.println("- Password: " + usuario.getPassword());
			System.out.println("- Teléfono: " + usuario.getTelefono());
			System.out.println("-------");
			System.out.println("Citas: ");
			System.out.println("-------");

			for (Cita cita : usuario.getCitas()) {
				System.out.println("- ID de la Cita: " + cita.getId());
				System.out.println("- Fecha: " + cita.getFecha());
				System.out.println("- Hora: " + cita.getHora());
				System.out.println("- Libre: " + cita.isLibre());
				System.out.println("_________________________________");
			}
			
			numUsuario++;
		}
	}

	void eliminarCita() {
		System.out.println("-------------");
		System.out.println("Eliminar cita");
		System.out.println("--------------");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ingresa el ID del usuario: ");
		Long userId = scanner.nextLong();
		scanner.nextLine();

		Usuario usuario = usuarioRepository.findById(userId).orElse(null);
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();

		if (usuario != null || usuarios.contains(usuario)) {
			System.out.print("Ingrese la hora de la cita a borrar: ");
			String hora = scanner.nextLine();

			List<Cita> citas = usuario.getCitas();
			boolean citaEncontrada = false;
			
			for (Cita cita : citas) {
				if (cita.getHora().equals(hora)) {
					citaRepository.delete(cita); // Eliminamos la cita de la BD
					citas.remove(cita); // Eliminamos la cita de la lista del usuario
					usuarioRepository.save(usuario); // Actualizamos el usuario en la BD
					System.out.println("La cita ha sido eliminada con éxito");
					citaEncontrada = true;
					System.out.println();
					break;
				} 
			}
			if(!citaEncontrada) {
				System.out.println("La hora de la cita no existe para el usuario " + userId);
				System.out.println();
			}
		} else {
			System.out.println("El usuario con el ID " + userId + " no se encuentra en la BD");
			System.out.println();
		}
	}
	
	void eliminarUsuario() {
		System.out.println("----------------");
		System.out.println("Eliminar usuario");
		System.out.println("----------------");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ingresa el ID del usuario: ");
		Long userId = scanner.nextLong();
		scanner.nextLine();

		Usuario usuario = usuarioRepository.findById(userId).orElse(null);
		
		if(usuario != null) {
			//Obtenemos todas las citas del usuario
			List<Cita> citas = usuario.getCitas();
			
			//Elimino cada cita del usuario
			for(Cita cita : citas) {
				citaRepository.delete(cita); //elimin la cita de la BD
			}
			
			//Elimino al usuario después de eliminar todas sus citas
			usuarioRepository.delete(usuario);
			System.out.println("El usuario con ID " + userId + " y sus citas han sido eliminados con éxito");
			System.out.println();
		} else {
			System.out.println("El usuario con el ID " + userId + " no se encuentra en la BD");
			System.out.println();
		}
	}

}
