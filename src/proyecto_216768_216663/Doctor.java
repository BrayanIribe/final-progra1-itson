
package proyecto_216768_216663;

public class Doctor extends Paciente{
    char especialidad;
    
    //super: hace referencia al constructor de la clase "madre" (en este caso Paciente)
    Doctor(){
    super();
    }
    
    public char getEspecialidad() {
        return especialidad;
    }
    
}
