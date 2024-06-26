package UTNFRGP.TP4_GRUPO8;

import java.time.LocalDate;
import java.util.List;

import dao.daoEspecialidad;
import dao.daoMedico;
import dao.daoPaciente;
import dao.daoTurno;
import dao.daoUsuario;
import entidad.Especialidad;
import entidad.EstadoTurno;
import entidad.Medico;
import entidad.Paciente;
import entidad.Turno;
import entidad.Usuario;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	daoEspecialidad daoesp = new daoEspecialidad();
    	for (int i = 1; i < 13; i++) {
    	    Especialidad espe = new Especialidad();
    	    espe.setNombre("Gastroenterologia_" + i);
    	    daoesp.Add(espe);
    	}
    	
    	daoUsuario daouser = new daoUsuario();
    	for (int i = 1; i < 13; i++) {
    	    Usuario user = new Usuario();
    	    user.setNombre("Dr.Jaime_" + i);
    	    user.setPassword("inicio01."+i);
    	    daouser.Add(user);
    	}
    	

    	daoMedico daomed = new daoMedico();
    	List<Especialidad> especialidades = daoesp.ReadAll();
    	List<Usuario> usuarios = daouser.ReadAll();

    	for (int i = 1; i < 13; i++) {
    		
    	    Medico med1 = new Medico();
    	    med1.setNombre("Medico_Nom_" + i);
    	    med1.setApellido("Medico_Ape_" + i);
    	    med1.setSexo('M');
    	    med1.setFNac(LocalDate.parse("1988-05-01"));
    	    med1.setDireccion("Calle Nula " + i);
    	    med1.setLocalidad("Localidad_" + i);
    	    med1.setCorreo("medico_" + i + "_@intramed.net");
    	    med1.setTeléfono("telefono_" + i);
    	    
    	    // Asignacion de una especialidad al médico y usuario 
    	    // asignamos una especialidad y el usuario a cada médico de forma secuencial
    	    Especialidad especialidad = especialidades.get(i - 1); // con i-1 porque el indice arranca en 0
    	    Usuario usuario = usuarios.get(i-1);
    	    
    	    med1.setUsuario(usuario);
    	    med1.setEspecialidad(especialidad);
    	    
    	    daomed.Add(med1);
    	}
    	
    	daoPaciente daopac = new daoPaciente();
    	for (int i = 1; i < 13; i++) {
    		
    	    Paciente pac1 = new Paciente();
    	    
    	    pac1.setNombre("Pedrito_" + i);
    	    pac1.setApellido("Ramirez_"+i);
    	    pac1.setCorreoElectronico("paciente_90@gmail.com_"+i);
    	    pac1.setDireccion("La plata 403_"+i);
    	    pac1.setDni("2588632_"+i);
    	    pac1.setFechaNacimiento("1988-05-01");
    	    pac1.setLocalidad("CABA_"+i);
    	    pac1.setProvincia("Buenos Aires_"+i);
    	    pac1.setTelefono("215585_"+i);
    	    
    	    daopac.Add(pac1);
    	}
    	
    	//Alta de turnos 
    	
    	daoTurno daotur = new daoTurno();
    	List<Medico> medicos = daomed.ReadAll();
    	List<Paciente> pacientes = daopac.ReadAll();

    	
    	for (int i = 1; i < 13; i++) {
    		
    	    Turno turn1 = new Turno();
    	    
    	    turn1.setEstado(EstadoTurno.PENDIENTE);
    	    turn1.setFecha("1988-05-01");
    	    turn1.setHora("20:0"+i);
    	    turn1.setObservacion("Descompuesto"+i);
    	    
    	    Medico medico = medicos.get(i-1);
    	    Paciente paciente = pacientes.get(i-1);
    	    
    	    turn1.setMedico(medico);
    	    turn1.setPaciente(paciente);
    	    
    	    
    	    daotur.Add(turn1);
    	}
    	
    	//ABML TP4 
  
        
        //Alta, modificacion y borrado de paciente
        
        Paciente pac2 = new Paciente();
	    
        pac2.setNombre("PRUEBA_ABML");
        pac2.setApellido("Ramirez");
        pac2.setCorreoElectronico("paciente_90@gmail.com");
        pac2.setDireccion("La plata 403");
        pac2.setDni("2588632");
        pac2.setFechaNacimiento("1988-05-01");
        pac2.setLocalidad("CABA");
        pac2.setProvincia("Buenos Aires");
        pac2.setTelefono("215585");
	    
	    daopac.Add(pac2);
	    
	    daopac.Delete(pac2);
	    
	    
	      Paciente pac3 = new Paciente();
		    
	        pac3.setNombre("PRUEBA_ABML2");
	        pac3.setApellido("Fernandez");
	        pac3.setCorreoElectronico("paciente_90@gmail.com");
	        pac3.setDireccion("La plata 403");
	        pac3.setDni("2588632");
	        pac3.setFechaNacimiento("1988-05-01");
	        pac3.setLocalidad("CABA");
	        pac3.setProvincia("Buenos Aires");
	        pac3.setTelefono("215585");
		    
		    daopac.Add(pac3);
		    
		    pac3.setDni("11111111");
		    pac3.setCorreoElectronico("CORREOMOFICADO POR ABML");
		    pac3.setProvincia("MENDOZA");
		    daopac.Update(pac3);
	    
	    // Listado de turnos con sus relaciones 
	  	
    	List<Turno> todosLosTurnos = daotur.ReadAll();
        for (Turno turno : todosLosTurnos) {
            System.out.println("Id Turno: " + turno.getId() + ", Fecha: " + turno.getFecha() + ", Médico: " + turno.getMedico().getNombre());
            
            Usuario usuarioDelMedico = turno.getMedico().getUsuario();
            System.out.println("   Usuario del Médico: " + usuarioDelMedico.getNombre());
        }
        
        
      //Alta, Modificacion y borrado de turno
        
        Turno turn1 = new Turno();
	   
	    turn1.setEstado(EstadoTurno.PENDIENTE);
	    turn1.setFecha("1988-05-18");
	    turn1.setHora("20:00");
	    turn1.setObservacion("N/A");
	    
	    Medico medico = medicos.get(1);
	    Paciente paciente = pacientes.get(1);
	    
	    turn1.setMedico(medico);
	    turn1.setPaciente(paciente);
	    
	    daotur.Add(turn1);
	    
	    //Modificacion
        turn1.setEstado(EstadoTurno.PRESENTE);
        turn1.setObservacion("Paciente atendido por control");
        daotur.Update(turn1);
        
        System.out.println(turn1.toString());
        
        //Baja
        daotur.Delete(turn1);
        
     // mostrar los datos bidireccionados de un medico con su usuario
        
        for (Medico medico1 : medicos) {
            System.out.println(medico1);
            System.out.println(medico1.getUsuario());               
            int length = medico1.toString().length();
        
            for (int i = 0; i < length; i++) {
                System.out.print("-");
            }
            System.out.println(); 
        }
        
      //Alta, Modificacion y borrado de turno

        daoMedico daomed1 = new daoMedico();
        List<Medico> medicos1 = daomed1.ReadAll();
        daoTurno daotur1 = new daoTurno();
        daoPaciente daopac1 = new daoPaciente();
        List<Paciente> pacientes1 = daopac1.ReadAll();
        
        //Agrego turnos 01-01-2025 para prueba
        Turno turn2 = new Turno();
        turn2.setEstado(EstadoTurno.PENDIENTE);
        turn2.setFecha("2025-01-01");
        turn2.setHora("20:00");
        turn2.setObservacion("N/A");

        Medico medico1 = medicos1.get(2);
        Paciente paciente1 = pacientes1.get(2);

        turn2.setMedico(medico1);
        turn2.setPaciente(paciente1);

        daotur1.Add(turn2);
        
        Turno turn3 = new Turno();
        turn3.setEstado(EstadoTurno.PENDIENTE);
        turn3.setFecha("2025-01-01");
        turn3.setHora("15:00");
        turn3.setObservacion("N/A");
        turn3.setMedico(medico1);
        turn3.setPaciente(paciente1);
        
        daotur1.Add(turn3);
        
        Turno turn4 = new Turno();
        turn4.setEstado(EstadoTurno.PENDIENTE);
        turn4.setFecha("2025-01-01");
        turn4.setHora("18:00");
        turn4.setObservacion("N/A");
        turn4.setMedico(medico1);
        turn4.setPaciente(paciente1);
        
        daotur1.Add(turn4);
         
        Turno turn5 = new Turno();
        turn5.setEstado(EstadoTurno.PRESENTE);
        turn5.setFecha("2025-01-01");
        turn5.setHora("18:00");
        turn5.setObservacion("N/A");
        turn5.setMedico(medico1);
        turn5.setPaciente(paciente1);
        
        daotur1.Add(turn5);
        
        Turno turn6 = new Turno();
        turn6.setEstado(EstadoTurno.PRESENTE);
        turn6.setFecha("2025-01-01");
        turn6.setHora("18:00");
        turn6.setObservacion("N/A");
        turn6.setMedico(medico1);
        turn6.setPaciente(paciente1);
        
        daotur1.Add(turn6);
        
        Turno turn7 = new Turno();
        turn7.setEstado(EstadoTurno.AUSENTE);
        turn7.setFecha("2025-01-01");
        turn7.setHora("18:00");
        turn7.setObservacion("N/A");
        turn7.setMedico(medico1);
        turn7.setPaciente(paciente1);
        
        daotur1.Add(turn7);
    }
}
