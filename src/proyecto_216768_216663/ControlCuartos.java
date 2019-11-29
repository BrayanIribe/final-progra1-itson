package proyecto_216768_216663;

import java.util.Scanner;
import java.util.Vector;

public class ControlCuartos {
    Scanner tec = new Scanner(System.in);
    Doctor doctor = new Doctor();
    String n = doctor.getNombre();
    Paciente[] A = new Paciente[15];
    Paciente[] B = new Paciente[10];
    
    
    //precios
    float tarifaA = 1100;
    float tarifaB = 1990;
    
    //chars para el dibujo de cajas
    // esi = esquina superior izquierda     | esd = esquina superior derecha
    char esi = '\u2554';
    char esd = '\u2557';
        
    // esp = espacio, linea horizontal  | esv = espacio, linea vertical
    char esh = '\u2500';
    char esv = '\u2502';
      
    // us = union superior              | ui = union inferior
    // uli = union laterarl izquierda   | uld = union lateral derecha
    char us = '\u2566';
    char ui = '\u2569';
    char uli = '\u2560';
    char uld = '\u2563';
        
    // eii = esquina inferior izquierda | eid = esquina inferior derecha
    char eii = '\u255A';
    char eid = '\u255D';
    
    // ce1 = caracter especial 1
    char ce1 = '\u2592';
    
    public void menu(){
        //constructores para el edificio A
        this.A[1] = new Paciente("OBN13", "Jesus Urrego", 'H', 1990,10,12,2019,10,8);
        this.A[5] = new Paciente("OBN25", "Sara Perez", 'F', 1997,3,28,2019,10,18);
        this.A[9] = new Paciente("OBN08", "Diego Garcia", 'H', 2004,7,17,2019,11,20);
        this.A[13] = new Paciente("OBN16", "Daniela Flores", 'F', 1999,10,27,2019,11,28);
        //constructores para el edificio B
        this.B[6] = new Paciente("OBN03", "Pedro Zapata", 'H', 1957,5,13,2019,8,19);
        int opcion = 0;
        while(true){
            System.out.println("SISTEMA DE RESERVACION DE CUARTOS");
            System.out.println("1. Registrar un paciente");
            System.out.println("2. Buscar paciente");
            System.out.println("3. Porcentaje de ocupacion de cada edificio");
            System.out.println("4. Listado de pacientes de determinado doctor");
            System.out.println("5. Imprimir el mapa de cuartos disponibles y ocupados");
            System.out.println("6. Hacer corte");
            System.out.println("7. Terminar operacion");
            System.out.println("");
            System.out.print("> ");
            opcion = this.tec.nextInt();
            System.out.println("");
            this.tec.nextLine();
            if (opcion == 7)
                break;
            
            switch(opcion){
                case 1: 
                    //registrar paciente
                    registrarPaciente();
                    break;
                case 2:
                    // buscar paciente
                    buscarPacientes();
                    break;
                case 3:
                    // porcentaje de ocupacion de cada edificio
                    porcentajeOcupacion();
                    break;
                case 4:
                    // listado de pacientes de determinado doctor
                    break;
                case 5:
                    // imprimir el mapa de cuartos disponibles y ocupados
                    imprimirMapaA();
                    imprimirMapaB();
                    break;
                case 6:
                    // hacer corte
                    break;
                default:
                    System.out.println("Opcion no valida, ingrese una del 1 al 7.");
            }
        }
        
    }
    //brayan, aqui no entiendo muy bien que esta pasando
    //pero parece que solo esta buscando en el edifico A
    public void buscarPacientes(){
        System.out.printf("Escribe el nombre o clave del paciente: ");
        String keyword = this.tec.nextLine();
        System.out.println("");
        
        //aqui hay que hacer que al momento de buscar se ignoren mayusculas o minusculas
        Vector<Paciente> pacientes = this.buscarPacientesA(keyword);
        if (pacientes == null || pacientes.size() == 0){
            System.out.println("");
            System.out.println("No se encontraron pacientes.");
            System.out.println("");
            return;
        }
        //aqui se imprime la informacion del paciente encontrado 
        for(int i = 0; i < pacientes.size(); i++){
            Paciente paciente = pacientes.get(i);
            System.out.println("");
            
            System.out.printf("%1s", esi);
            for(int b = 0 ;b < 54; b++){
                System.out.printf("%1s",esh);
            }
            System.out.printf("%1s%n", esd);
        
            System.out.printf("%-2s%-22s%-30s%2s%n",esv,"Nombre:",paciente.getNombre(),esv);
            System.out.printf("%-2s%-22s%-30s%2s%n",esv,"Clave:",paciente.getClave(),esv);
            System.out.printf("%-2s%-22s%-30s%2s%n",esv,"Sexo:",paciente.getSexo(),esv);
            
            //aqui hay que agregar la habitacion actual, tampoco supe ponerla xd
            //System.out.printf("%-2s%-22s%-30s%2s%n",esv,"Habitacion:",esv);
            
            //aqui no supe agregar la fecha
            /*
            System.out.printf("%-2s%-20s%-18s%2s%n",esv,"Fecha de nacimiento:",paciente.getFechaNacimiento(),esv);
            System.out.printf("%-2s%-20s%-18s%2s%n",esv,"Fecha de ingreso:", paciente.getFechaIngreso(),esv);
            */            

            System.out.printf("%1s", eii);
            for(int b = 0 ;b < 54; b++){
                System.out.printf("%1s",esh);
            }
            System.out.printf("%1s%n", eid);
            
            System.out.println("");
        }
    }
    
    public void porcentajeOcupacion(){
        int sum = 0;
        float porcentajeA = 0;
        float porcentajeB = 0;
        for(int i = 0; i < this.A.length; i++){
            if(this.A[i] == null){
                continue;
            }
            sum ++;
        }
        porcentajeA  = ((float)sum / this.A.length)*100;
        
        sum = 0;
        for(int i = 0; i < this.B.length; i++){
            if(this.B[i] == null){
                continue;
            }
            sum ++;
        }
        porcentajeB  = ((float)sum / this.B.length)*100;
        int A = (int)porcentajeA;
        int B = (int)porcentajeB;
        
        System.out.println("El porcentaje de ocupacion de los edificios es:");
        
        // edificio A
        System.out.printf("%1s",esi);
        for(int i = 0 ; i < 102; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n",esd);
        
        System.out.printf("%-2s",esv);
        for(int i = 0 ; i < A; i++){
            System.out.printf("%1s",ce1);
        }
        int C = 101 - A;
        for(int i = 0 ; i < C ; i++){
            System.out.printf("%1s","");
        }
        System.out.printf("%1s%n",esv);
        
        System.out.printf("%1s",eii);
        for(int i = 0 ; i < 102; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n",eid);
        
        System.out.printf("%-12s%3.0f%c%n","Edificio A:",porcentajeA,'%');
        
        System.out.println("");
        
        // edificio B
        System.out.printf("%1s",esi);
        for(int i = 0 ; i < 102; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n",esd);
        
        System.out.printf("%-2s",esv);
        for(int i = 0 ; i < B; i++){
            System.out.printf("%1s",ce1);
        }
        C = 101 - B;
        for(int i = 0 ; i < C ; i++){
            System.out.printf("%1s","");
        }
        System.out.printf("%1s%n",esv);
        
        System.out.printf("%1s",eii);
        for(int i = 0 ; i < 102; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n",eid);
        
        System.out.printf("%-12s%3.0f%c%n","Edificio B:",porcentajeB,'%');
        
        System.out.println("");
    }
    
    public Vector<Paciente> buscarPacientesA(String keyword){
        // crear un vector de objetos de pacientes
        Vector<Paciente> results = new Vector<Paciente>();
        // recorrer el edificio A en busca de pacientes segun
        // el criterio de busqueda
        if (this.A.length > 0)
        for(int i = 0; i < this.A.length; i++){
            if (this.A[i] == null)
                continue;
                        System.out.println(this.A[i]);
            Paciente paciente = this.A[i];
            if (paciente.getNombre().indexOf(keyword) == -1 &&
                    paciente.getClave().indexOf(keyword) == -1)
                continue;
            //agregar al vector el paciente encontrado
            results.add(paciente);
        }
        
        return results;
    }
    
    public void registrarPaciente(){
        System.out.printf("%1s", esi);
        for(int i = 0 ;i < 80; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n", esd);
        
        System.out.printf("%-2s%-78s%2s%n",esv,"El edifico A cuenta con cuartos sencillos que tienen solo una cama y un baño.",esv);
        
        System.out.printf("%1s", uli);
        for(int i = 0 ;i < 80; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n", uld);
        
        System.out.printf("%-2s%-28s%-7.2f%-43s%2s%n",esv,"El costo por cuarto es de  $",this.tarifaA," pesos al dia.",esv);
        
        System.out.printf("%1s", eii);
        for(int i = 0 ;i < 80; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n", eid);
        
        System.out.println("");
        
        System.out.printf("%1s", esi);
        for(int i = 0 ;i < 80; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n", esd);
        
        System.out.printf("%-2s%-78s%2s%n",esv,"El edificio B cuenta con cuartos mas equipados, esos tienen aparte de su cama:",esv);
        System.out.printf("%-2s%-78s%2s%n",esv,"Baño  completo, caja  de  seguridad, closet,  internet  inalambrico,  llamadas",esv);
        System.out.printf("%-2s%-78s%2s%n",esv,"locales ilimitadas, mesa desayunador, frigobar y cafeteria, sillon reclinable,",esv);
        System.out.printf("%-2s%-78s%2s%n",esv,"sala para visitas, reproductor DVD, sofa cama y television de paga.", esv);      
        
        System.out.printf("%1s", uli);
        for(int i = 0 ;i < 80; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n", uld);
        
        System.out.printf("%-2s%-28s%-7.2f%-43s%2s%n",esv,"El costo por cuarto es de  $",this.tarifaB," pesos al dia.",esv);
        
        System.out.printf("%1s", eii);
        for(int i = 0 ;i < 80; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n", eid);
        
        System.out.println("");
        
        System.out.printf("Seleccione un edificio (A/b): ");
        char edif = tec.nextLine().toUpperCase().charAt(0);
        Paciente[] edificio = (edif == 'B' ? this.B: this.A);
        
        if(edificio.length <=0){
            System.out.println("No hay capacidad.");
            return;
        }
        
        int yi, mi, di, yn, mn, dn;
        
        System.out.printf("Escribe el nombre: ");
        String nombre = this.tec.nextLine();
        
        System.out.printf("Escribe la clave: ");
        String clave = this.tec.nextLine().toUpperCase();  
        
        System.out.printf("Escribe el sexo (M/f): ");
        String sexoStr = this.tec.nextLine().toUpperCase();
        char sexo = (sexoStr.charAt(0) == 'F' ? 'F' : 'M');
        
        while(true){
        System.out.printf("Escribe la fecha de nacimiento (YYYY-mm-dd): ");
        String[] date = this.tec.nextLine().split("-");
        
        if (date.length != 3)
            continue;
        
        yn = Integer.parseInt(date[0]);
        mn = Integer.parseInt(date[1]);
        dn = Integer.parseInt(date[2]);
        
        if (mn >= 1 && mn <= 12 && dn >= 1 && dn <= 31)
            break;
        }
        
        while(true){
        System.out.printf("Escribe la fecha de ingreso (YYYY-mm-dd): ");
        String[] date = this.tec.nextLine().split("-");
        
        if (date.length != 3)
            continue;
        
        yi = Integer.parseInt(date[0]);
        mi = Integer.parseInt(date[1]);
        di = Integer.parseInt(date[2]);
        
        if (yi >= 2019 && mi >= 1 && mi <= 12 && di >= 1 && di <= 31)
            break;
        }
        
        // IMPRIMIR MAPA DE UBICACION
        System.out.println("");
        if(edif == 'A'){
            imprimirMapaA();
        }
        else if(edif == 'B'){
            imprimirMapaB();
        }
        System.out.println("");
        int ind = 0;
        
        while(true){
            System.out.print("Seleccione la habitacion: ");
            ind = this.tec.nextInt();
            this.tec.nextLine();
            if (ind <= 0 || ind > edificio.length)
                continue;
                        ind--;
            if (edificio[ind] != null){
                System.out.println("La habitacion seleccionada esta ocupada.");
                continue;
            }
            break;
        }  
        Paciente paciente = new Paciente(clave, nombre, sexo, yn, mn, dn, yi, mi, di);
        
        edificio[ind] = paciente;   
    }
    
    public void imprimirMapaA(){
        
        //Edificio A
        //numeros
        System.out.printf("%3s","");
        for(int i = 1 ; i < 15 ; i++){
        System.out.printf("%3.2s%1s", i,"");
        }
        System.out.printf("%3s%n", "15");
        
        //linea superior
        System.out.printf("%3s%1s","",esi);
        for(int i = 0 ; i < 14 ; i++){
        System.out.printf("%1s%1s%1s%1s",esh,esh,esh,us);
        }
        System.out.printf("%1s%1s%1s%1s%n",esh,esh,esh,esd);
        
        
        //linea del medio
        System.out.printf("%1s%-2s%1s","","A",esv);
        for(int i = 0 ; i < 15 ; i++){
        System.out.printf("%-1s%1s%2s","",(this.A[i] == null ? " " : "X"),esv);
        }
        System.out.println("");
        
        
        //linea inferior
        System.out.printf("%3s%1s","",eii);
        for(int i = 0 ; i < 14 ; i++){
        System.out.printf("%1s%1s%1s%1s",esh,esh,esh,ui);
        }
        System.out.printf("%1s%1s%1s%1s%n",esh,esh,esh,eid);
        System.out.println("");
        
        
    }
    
    public void imprimirMapaB(){
        //Edificio B
        //numeros
        System.out.printf("%3s","");
        for(int i = 1 ; i < 10 ; i++){
        System.out.printf("%3.2s%1s", i,"");
        }
        System.out.printf("%3s%n", "10");
      
        //linea superior
        System.out.printf("%3s%1s","",esi);
        for(int i = 0 ; i < 9 ; i++){
        System.out.printf("%1s%1s%1s%1s",esh,esh,esh,us);
        }
        System.out.printf("%1s%1s%1s%1s%n",esh,esh,esh,esd);
        
        
        //linea del medio
        
        System.out.printf("%1s%-2s%1s","","B",esv);
        for(int i = 0 ; i < 10 ; i++){
        System.out.printf("%-1s%1s%2s","",(this.B[i] == null ? " " : "X"),esv);
        }
        System.out.println();
        
        
        //linea inferior
        System.out.printf("%3s%1s","",eii);
        for(int i = 0 ; i < 9 ; i++){
        System.out.printf("%1s%1s%1s%1s",esh,esh,esh,ui);
        }
        System.out.printf("%1s%1s%1s%1s%n",esh,esh,esh,eid);    
        System.out.println("");
    }
    
    
}
