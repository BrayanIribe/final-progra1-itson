
package proyecto_216768_216663;

import java.util.Calendar;
public class Paciente {
    String clave;
    char sexo;
    Calendar fechaNacimiento = Calendar.getInstance();
    Calendar fechaIngreso = Calendar.getInstance();
    
    Paciente(){
    }
    
    Paciente(String clave, char sexo, int yn, int mn, int dn, int yi, int mi, int di){
        this.clave = clave;
        this.sexo = sexo;
        mn--;
        mi--;
        this.fechaNacimiento.set(yn, mn, dn);
        this.fechaIngreso.set(yi, mi, di);
        
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
    
    
    
    
    
}
