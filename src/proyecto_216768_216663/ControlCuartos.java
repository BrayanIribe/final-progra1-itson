package proyecto_216768_216663;

import java.util.Scanner;
import java.util.Vector;

public class ControlCuartos {
    Scanner tec = new Scanner(System.in, "ISO-8859-1");
    Doctor[] doctor = new Doctor[3];
    Paciente[] A = new Paciente[15];
    Paciente[] B = new Paciente[10];
    ModelManager manager = new ModelManager();
    
    
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
    // udc = union de cuatro lados
    char us = '\u2566';
    char ui = '\u2569';
    char uli = '\u2560';
    char uld = '\u2563';
    char udc = '\u256C';
        
    // eii = esquina inferior izquierda | eid = esquina inferior derecha
    char eii = '\u255A';
    char eid = '\u255D';
    
    // ce1 = caracter especial 1 | ce2 = caracter especial 2
    char ce1 = '\u2592';
    char ce2 = '\u259A';
    
    public void menu(){
        this.doctor[0] = new Doctor(1, "Mario Aguilar", 'H', 'G');
        this.doctor[1] = new Doctor(2, "Daniel Sanchez", 'H', 'O');
        this.doctor[2] = new Doctor(3, "Laura Bozo", 'F', 'G');
        
        //constructores para el edificio A
        this.A[1] = new Paciente(this.manager, "Jesus Urrego", 'H', "1990-10-12","2019-10-08", 'A', 1, 1);
        this.A[5] = new Paciente(this.manager, "Sara Perez", 'F', "1997-03-28","2019-10-18", 'A', 5, 2);
        this.A[9] = new Paciente(this.manager, "Diego Garcia", 'H', "2004-07-17","2019-11-20", 'A', 9, 3);
        this.A[13] = new Paciente(this.manager, "Daniela Flores", 'F', "1999-10-27","2019-11-28", 'A', 13, 1);
        //constructores para el edificio B
        this.B[0] = new Paciente(this.manager, "Pedro Zapata", 'H',"57-05-03","2019-08-19",'B', 0, 1);
        this.B[1] = new Paciente(this.manager, "Camilo Zuñiga", 'H', "2001-02-23","2019-10-20", 'B', 1, 2);
        this.B[2] = new Paciente(this.manager, "Laura Camello", 'F', "1932-01-25","2019-11-28", 'B', 2, 3);
        this.B[3] = new Paciente(this.manager, "Bruce Banner", 'H', "1969-12-18","2019-11-10", 'B', 3, 1);
        this.B[4] = new Paciente(this.manager, "Peter Parker", 'H', "1962-08-10","2019-10-19", 'B', 4, 2);
        this.B[5] = new Paciente(this.manager, "Hermione Granger", 'F', "1979-09-19","2019-11-25", 'B', 5, 2);
        this.B[6] = new Paciente(this.manager, "Homero Simpson", 'H', "1956-05-12","2019-10-09", 'B', 6, 3);
        this.B[7] = new Paciente(this.manager, "El Rubius", 'H', "1990-02-13","2019-11-24", 'B', 7, 3);
        this.B[8] = new Paciente(this.manager, "Ariana Grande", 'F', "1993-06-26","2019-10-31", 'B', 8, 1);
        this.B[9] = new Paciente(this.manager, "La Rosalia", 'H', "1993-09-25","2019-11-29", 'B', 9, 2);
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
                    pacientesDoctor();
                    break;
                case 5:
                    // imprimir el mapa de cuartos disponibles y ocupados
                    imprimirMapaA();
                    imprimirOcupantesA();
                    imprimirMapaB();
                    imprimirOcupantesB();
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
        Vector<Paciente> pacientes = this.buscarPacientesW(keyword, 0);
        
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
            System.out.printf("%-2s%-22s%-30s%2s%n",esv,"Edificio:",paciente.getEdif(),esv);
            System.out.printf("%-2s%-22s%-30s%2s%n",esv,"Habitacion:",paciente.getHabit() + 1,esv);
            
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
    
    public Vector<Paciente> buscarPacientesW(String keyword, int doctorId){
        // crear un vector de objetos de pacientes
        Vector<Paciente> results = new Vector<Paciente>();
        // recorrer el edificio A en busca de pacientes segun
        // el criterio de busqueda5
        char tipoBusqueda = 'N'; //N = nombre, C = clave
        
        if (keyword.toLowerCase().contains("obn")) //se encontro
            tipoBusqueda = 'C';
        
        if(doctorId > 0){
            tipoBusqueda = 'D';
        }
        
        keyword = keyword.toLowerCase();
        
        if (this.A.length > 0)
        for(int i = 0; i < this.A.length; i++){
            if (this.A[i] == null)
                continue;
            
            Paciente paciente = this.A[i];
            if(tipoBusqueda == 'N'){
                if (!paciente.getNombre().toLowerCase().contains(keyword))
                    continue;
            }
            else if(tipoBusqueda == 'D'){
                if(paciente.getDoctorId() != doctorId){
                    continue;
                }
            }
            else{
                if (paciente.getClave().toLowerCase().compareTo(keyword) != 0)
                    continue;
            }
            //agregar al vector el paciente encontrado
            results.add(paciente);
        }
        
        if (this.B.length > 0)
        for(int i = 0; i < this.B.length; i++){
            if (this.B[i] == null)
                continue;
            
            Paciente paciente = this.B[i];
            if(tipoBusqueda == 'N'){
                if (!paciente.getNombre().toLowerCase().contains(keyword))
                    continue;
            }
            else if(tipoBusqueda == 'D'){
                if(paciente.getDoctorId() != doctorId){
                    continue;
                }
            }
            else{
                if (paciente.getClave().toLowerCase().compareTo(keyword) != 0)
                    continue;
            }
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
        
        System.out.printf("Seleccione un edificio (A/B): ");
        char edif = tec.nextLine().toUpperCase().charAt(0);
        Paciente[] edificio = (edif == 'B' ? this.B: this.A);
        
        int cont = 0;
        
        if(edif == 'A'){
            for(int i = 0 ; i < 15 ; i++){
                if(A[i]!=null){
                    cont++;
                }
            }
            if(cont >=15){
                System.out.println("No hay capacidad.");
                System.out.println("");
                return;
            }
            
        }
        else if(edif == 'B'){
            for(int i = 0 ; i < 10 ; i++){
                if(B[i]!=null){
                    cont++;
                }
            }
            if(cont >=10){
                System.out.println("No hay capacidad.");
                System.out.println("");
                return;
            }
        }
        
        //cambie esto por las la lineas que escribi arriba porque no estaba validando el edificio B
        /*
        if(edificio.length <= 0){
            System.out.println("No hay capacidad.");
            return;
        }
        */
        
        int yi, mi, di, yn, mn, dn;
        
        System.out.printf("Escribe el nombre: ");
        String nombre = this.tec.nextLine();
        
        System.out.printf("Escribe el sexo (M/f): ");
        String sexoStr = this.tec.nextLine().toUpperCase();
        char sexo = (sexoStr.charAt(0) == 'F' ? 'F' : 'M');
        
        int doctorId = 1;
        
        for(int i = 0; i < this.doctor.length; i++){
            if (this.doctor == null)
                continue;
            
            System.out.printf("Nombre: %s\n", this.doctor[i].getNombre());
        }
        
        while(true){
        System.out.println("Escribe el ID del doctor que desea seleccionar");
        System.out.println("(tome en cuenta la especialidad a la hora de elegir)");
        doctorId = tec.nextInt(); 
        tec.nextLine();
        Doctor doctor = this.obtenerDoctor(doctorId);
        if(doctor != null){
                break;
            }
        System.out.println("Seleccion incorrecta");
        }
        
        String fingreso;
        String fnacimiento;
        
        while(true){
        System.out.printf("Escribe la fecha de nacimiento (YYYY-mm-dd): ");
        fnacimiento = this.tec.nextLine();
        String[] date = fnacimiento.split("-");
        
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
        fingreso = this.tec.nextLine();
        String[] date = fingreso.split("-");
        
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
        System.out.println("");
        System.out.println("El paciente se ha registrado correctamente.");
        System.out.println("");
        
        Paciente paciente = new Paciente(this.manager, nombre, sexo, fnacimiento, fingreso, edif, ind, doctorId);
      
        edificio[ind] = paciente;   
    }
    
    public Doctor obtenerDoctor(int id){
        for(int i = 0 ; i < this.doctor.length ; i++){
            if(this.doctor[i].getId()==id)
                return this.doctor[i];
        }
        return null;
    }
    
    public void pacientesDoctor(){
           int doctorId = 1;
        
        for(int i = 0; i < this.doctor.length; i++){
            if (this.doctor == null)
                continue;
            
            System.out.printf("Nombre: %s\n", this.doctor[i].getNombre());
        }
        
        while(true){
        System.out.println("Escribe el ID del doctor que desea seleccionar");
        System.out.println("(tome en cuenta la especialidad a la hora de elegir)");
        doctorId = tec.nextInt(); 
        tec.nextLine();
        Doctor doctor = this.obtenerDoctor(doctorId);
        if(doctor != null){
                break;
            }
        System.out.println("Seleccion incorrecta");
        }
        Vector<Paciente> pacientes = this.buscarPacientesW("", doctorId);
        
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
            System.out.printf("%-2s%-22s%-30s%2s%n",esv,"Edificio:",paciente.getEdif(),esv);
            System.out.printf("%-2s%-22s%-30s%2s%n",esv,"Habitacion:",paciente.getHabit() + 1,esv);
            
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
    
    public void imprimirOcupantesA(){
        int val = 0;
        
        //linea superior
        System.out.printf("%4s",esi);
        for(int i = 0 ; i < 14 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",us);
        for(int i = 0 ; i < 22 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",us);
        for(int i = 0 ; i < 7 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n",esd);
        
        //linea nombre
        System.out.printf("%4s%1s",esv,"");
        for(int i = 0 ; i < 12 ; i++){
            System.out.printf("%1s",ce2);
        }
        System.out.printf("%1s%-9s%-14s%-2s%5s%2s%n","",esv,"Nombre",esv,"Clave",esv);
        
        //linea divisora
        System.out.printf("%4s",uli);
        for(int i = 0 ; i < 14 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",udc);
        for(int i = 0 ; i < 22 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",udc);
        for(int i = 0 ; i < 7 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n",uld);
        
        //contenido
        for(int i = 0 ; i < 15 ; i++){
            val = (this.A[i] == null ? 0 : 1);
            if(val > 0){
                System.out.printf("%4s%1s%-10s%2s%1s%1s%2s%-19s%2s%6s%2s%n",esv,"","Cuarto Nº",i+1,"",esv,"", A[i].getNombre(),esv, A[i].getClave(),esv);
            }
        }
        
        //linea inferior
        System.out.printf("%4s",eii);
        for(int i = 0 ; i < 14 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",ui);
        for(int i = 0 ; i < 22 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",ui);
        for(int i = 0 ; i < 7 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n",eid);
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
    
    public void imprimirOcupantesB(){
           int val = 0;
        
        //linea superior
        System.out.printf("%4s",esi);
        for(int i = 0 ; i < 14 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",us);
        for(int i = 0 ; i < 22 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",us);
        for(int i = 0 ; i < 7 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n",esd);
        
        //linea nombre
        System.out.printf("%4s%1s",esv,"");
        for(int i = 0 ; i < 12 ; i++){
            System.out.printf("%1s",ce2);
        }
        System.out.printf("%1s%-9s%-14s%-2s%5s%2s%n","",esv,"Nombre",esv,"Clave",esv);
        
        //linea divisora
        System.out.printf("%4s",uli);
        for(int i = 0 ; i < 14 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",udc);
        for(int i = 0 ; i < 22 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",udc);
        for(int i = 0 ; i < 7 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n",uld);
        
        //contenido
        for(int i = 0 ; i < 10 ; i++){
            val = (this.B[i] == null ? 0 : 1);
            if(val > 0){
                System.out.printf("%4s%1s%-10s%2s%1s%1s%2s%-19s%2s%6s%2s%n",esv,"","Cuarto Nº",i+1,"",esv,"", B[i].getNombre(),esv, B[i].getClave(),esv);
            }
        }
        
        //linea inferior
        System.out.printf("%4s",eii);
        for(int i = 0 ; i < 14 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",ui);
        for(int i = 0 ; i < 22 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%s",ui);
        for(int i = 0 ; i < 7 ; i++){
            System.out.printf("%1s",esh);
        }
        System.out.printf("%1s%n",eid);
        System.out.println("");
    }
}
