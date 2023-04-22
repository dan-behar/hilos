import ThreadsPar.ParFiles;

// Agregar caso de 8 hilos para archivos (LISTO)
// crear archivo con el tiempo ("Txt con el tiempo") 
// Volumenes de docker para el resultado 
// Modificar el descriptor
// Paralelizar funciones 

// numero de cores 
// cantidad de memoria

public class Main {
    public static void main(String args[]) throws InterruptedException 
    {   

        Integer hilos = 1;  
        Integer hilos_funciones = 1;

        try {
            hilos = Integer.parseInt(args[0]); 
            System.out.println("Hilos para Archivos: " + hilos);

        } catch(ArrayIndexOutOfBoundsException e){

            System.out.println("No se ingresaron parametros, hilos Archivos = 1 por defecto.");
        }
        catch(NumberFormatException e){

            System.out.println("No se ingresaron parametros, hilos Archivos = 1 por defecto.");
        }

        try {
            hilos_funciones = Integer.parseInt(args[1]); 
            System.out.println("Hilos para funciones: " + hilos_funciones);

        } catch(ArrayIndexOutOfBoundsException e){

            System.out.println("No se ingresaron parametros, hilos funciones  = 1 por defecto.");
        }
        catch(NumberFormatException e){

            System.out.println("No se ingresaron parametros, hilos funciones = 1 por defecto.");
        }


        long startTime = System.currentTimeMillis();



        switch(hilos) {
            case 1:
                ParFiles tr1A = new ParFiles(1, 999, hilos_funciones);
                tr1A.start();
                tr1A.join();
                break;

            case 2:

                ParFiles tr1B = new ParFiles(1, 499, hilos_funciones);
                ParFiles tr2B = new ParFiles(500, 999, hilos_funciones);

                // Inicilizacion de hilos
                tr1B.start();
                tr2B.start();

                // Monitoreos
                tr1B.join();
                tr2B.join();


                break;

            case 4:
                ParFiles tr1C = new ParFiles(1, 249, hilos_funciones);
                ParFiles tr2C = new ParFiles(250, 499, hilos_funciones);
                ParFiles tr3C = new ParFiles(500, 749, hilos_funciones);
                ParFiles tr4C = new ParFiles(750, 999, hilos_funciones);
                
                // Inicilizacion de hilos
                tr1C.start();
                tr2C.start();
                tr3C.start();
                tr4C.start();

                // Monitoreos
                tr1C.join();
                tr2C.join();
                tr3C.join();
                tr4C.join();
                
                break;

            case 8:
                ParFiles tr1E = new ParFiles(1, 124, hilos_funciones);
                ParFiles tr2E = new ParFiles(125, 249, hilos_funciones);
                ParFiles tr3E = new ParFiles(250, 374, hilos_funciones);
                ParFiles tr4E = new ParFiles(375, 499, hilos_funciones);
                ParFiles tr5E = new ParFiles(500, 624, hilos_funciones);
                ParFiles tr6E = new ParFiles(625, 749, hilos_funciones);
                ParFiles tr7E = new ParFiles(750, 874, hilos_funciones);
                ParFiles tr8E = new ParFiles(875, 999, hilos_funciones);

                // Inicilizacion de hilos 
                tr1E.start();
                tr2E.start();
                tr3E.start();
                tr4E.start();
                tr5E.start();
                tr6E.start();
                tr7E.start();
                tr8E.start();

                // Monitoreos 
                tr1E.join();
                tr2E.join();
                tr3E.join();
                tr4E.join();
                tr5E.join();
                tr6E.join();
                tr7E.join();
                tr8E.join();

                break;

            default:
                System.out.println("La opcion de " + hilos + " no existe. Hilos = 1 por defecto.");

                ParFiles tr1D = new ParFiles(1, 999, hilos_funciones);
                tr1D.start();

                tr1D.join(); 
                break;            
        }

        System.out.println("\n\nLa ejecucion tardo: " + (System.currentTimeMillis() - startTime) + " milisegundos.");

        // System.gc();
    }
}