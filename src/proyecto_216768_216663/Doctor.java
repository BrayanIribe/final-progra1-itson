
package proyecto_216768_216663;

public class Doctor extends Paciente{
    char especialidad;
    
    //super: hace referencia al constructor de la clase "madre" (en este caso Paciente)
    Doctor(){
    super();
    }
    
    Doctor(int id, String nombre, char sexo, char especialidad){
        super(id, nombre, sexo);
        this.especialidad = especialidad;
    }
    
    public char getEspecialidad() {
        return especialidad;
    }
    
}
