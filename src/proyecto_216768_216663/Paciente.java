
package proyecto_216768_216663;

import java.util.Calendar;
public class Paciente {
    int id;
    int doctorId;
    char edif;
    int habit;
    String nombre;
    char sexo;
    Calendar fechaNacimiento = Calendar.getInstance();
    Calendar fechaIngreso = Calendar.getInstance();
    
    Paciente(){
        
    }
    
    Paciente(int id, String nombre, char sexo){
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
    }
    
    Paciente(ModelManager manager, String nombre, char sexo, String fnacimiento, String fingreso, char edif, int habit, int doctorId){
        this.id = manager.getId();
        manager.increase();
        
        this.nombre = nombre;
        this.sexo = sexo;
        String date[] = fnacimiento.split("-");
        
        this.fechaNacimiento.set(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]) - 1,
                Integer.parseInt(date[2])
        );
        
        date = fingreso.split("-");
        
        this.fechaIngreso.set(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]) - 1,
                Integer.parseInt(date[2])
        );
        
        this.edif = (edif == 'B' ? 'B' : 'A');
        this.habit = habit;
        this.doctorId = doctorId;
    }

    public int getHabit() {
        return habit;
    }

    public void setHabit(int habit) {
        this.habit = habit;
    }

    public char getEdif() {
        return edif;
    }

    public void setEdif(char edif) {
        this.edif = edif;
    }
    
    public String getClave(){
        String clave = "OBN" + this.id;
        return clave;
    }

    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Calendar getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Calendar fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    } 
}
