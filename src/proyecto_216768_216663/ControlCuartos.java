package proyecto_216768_216663;

import java.util.Scanner;
import java.util.Calendar;

public class ControlCuartos {
    Scanner tec = new Scanner(System.in);
    
    Paciente[] A = new Paciente[15];
    Paciente[] B = new Paciente[10];
    
    float tarifaA = 1100;
    float tarifaB = 1990;
    
    public void menu(){
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
                    break;
                case 3:
                    // porcentaje de ocupacion de cada edificio
                    break;
                case 4:
                    // listado de pacientes de determinado doctor
                    break;
                case 5:
                    // imprimir el mapa de cuartos disponibles y ocupados
                    imprimirMapa();
                    break;
                case 6:
                    // hacer corte
                    break;
                default:
                    System.out.println("Opcion no valida, ingrese una del 1 al 7.");
            }
        }
        
    }
    
    public void registrarPaciente(){
        System.out.println("El edifico A cuenta con cuartos sencillos");
        System.out.println("que tienen solo una cama y un baño.");
        System.out.printf("El costo por cuarto es de %.2f al dia.",this.tarifaA);
        System.out.println("======");
        System.out.println("El edifico B cuenata con cuartos mas equipados");
        System.out.println("esos tiene aprte de su cama: baño completo,");
        System.out.println("caja de seguridad, closet, internet inalambrico,");
        System.out.println("llamadas locales ilimitadas, mesa desayunador,");
        System.out.println("frigobar y cafeteria, sillon reclinable,");
        System.out.println("sala para visitas, reproductor de DVD, sofa cama,");
        System.out.println("y television de paga.");
        System.out.printf("Este cuesta %.2f por dia.%n", this.tarifaB);
        System.out.println("======");
        System.out.printf("Seleccione un edificio (A/b): ");
        char edif = tec.nextLine().toUpperCase().charAt(0);
        Paciente[] edificio = (edif == 'B' ? this.B: this.A);
        
        if(edificio.length <=0){
            System.out.println("No hay capacidad.");
            return;
        }
        
        int yi, mi, di, yn, mn, dn;
        
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
        
        while(true){
        System.out.printf("Escribe la fecha de nacimiento (YYYY-mm-dd): ");
        String[] date = this.tec.nextLine().split("-");
        
        if (date.length != 3)
            continue;
        
        yn = Integer.parseInt(date[0]);
        mn = Integer.parseInt(date[1]);
        dn = Integer.parseInt(date[2]);
        
        if (yn >= 2019 && mn >= 1 && mn <= 12 && dn >= 1 && dn <= 31)
            break;
        }
        
        // IMPRIMIR MAPA DE UBICACION
        
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
        
        System.out.printf("Escribe la clave: ");
        String clave = this.tec.nextLine();
        
        System.out.printf("Escribe el sexo (M/f): ");
        String sexoStr = this.tec.nextLine().toUpperCase();
        char sexo = (sexoStr.charAt(0) == 'F' ? 'F' : 'M');
        
        Paciente paciente = new Paciente(clave, sexo, yn, mn, dn, yi, mi, di);
        
        edificio[ind] = paciente;
        
        
    }
    
    public void imprimirMapa(){
        // esi = esquina superior izquierda / esd = esquina superior derecha
        char esi = '\u2554';
        char esd = '\u2557';
        
        // esp = espacio, linea horizontal
        char esh = '\u2500';
        
        // us = union superior
        char us = '\u2566';
        
        // esv = espacio, linea vertical
        char esv = '\u2502';

        // ui = union inferior
        char ui = '\u2569';
        
        // eii = esquina inferior izquierda / eid = esquina inferior derecha
        char eii = '\u255A';
        char eid = '\u255D';
        
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
        for(int i = 0 ; i < 14 ; i++){
        System.out.printf("%-1s%1s%2s","","X",esv);
        }
        System.out.printf("%-1s%1s%2s%n","","X",esv);
        
        
        //linea inferior
        System.out.printf("%3s%1s","",eii);
        for(int i = 0 ; i < 14 ; i++){
        System.out.printf("%1s%1s%1s%1s",esh,esh,esh,ui);
        }
        System.out.printf("%1s%1s%1s%1s%n",esh,esh,esh,eid);
        System.out.println();
        
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
        for(int i = 0 ; i < 9 ; i++){
        System.out.printf("%-1s%1s%2s","","X",esv);
        }
        System.out.printf("%-1s%1s%2s%n","","X",esv);
        
        
        //linea inferior
        System.out.printf("%3s%1s","",eii);
        for(int i = 0 ; i < 9 ; i++){
        System.out.printf("%1s%1s%1s%1s",esh,esh,esh,ui);
        }
        System.out.printf("%1s%1s%1s%1s%n",esh,esh,esh,eid);    
    }
    
    
}
