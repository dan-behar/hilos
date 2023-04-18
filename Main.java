import ThreadsPar.ParFiles;

public class Main {
    public static void main(String args[]) throws InterruptedException 
    {   

        Integer hilos = 1; 

        try {
            hilos = Integer.parseInt(args[0]); 
            System.out.println("Hilos: " + hilos);

        } catch(ArrayIndexOutOfBoundsException e){

            System.out.println("No se ingresaron parametros, hilos = 1 por defecto.");
        }
        catch(NumberFormatException e){

            System.out.println("No se ingresaron parametros, hilos = 1 por defecto.");
        }


        long startTime = System.currentTimeMillis();



        switch(hilos) {
            case 1:
                ParFiles tr1A = new ParFiles(1, 999);
                tr1A.start();
                tr1A.join();
                // while(tr1A.isAlive()){}
                break;

            case 2:

                ParFiles tr1B = new ParFiles(1, 499);
                ParFiles tr2B = new ParFiles(500, 999);

                tr1B.start();
                tr2B.start();

                tr1B.join();
                tr2B.join();

                // while(tr1B.isAlive()){}
                // while(tr2B.isAlive()){}
                break;

            case 4:
                ParFiles tr1C = new ParFiles(1, 249);
                ParFiles tr2C = new ParFiles(250, 499);
                ParFiles tr3C = new ParFiles(500, 749);
                ParFiles tr4C = new ParFiles(750, 999);

                tr1C.start();
                tr2C.start();
                tr3C.start();
                tr4C.start();


                tr1C.join();
                tr2C.join();
                tr3C.join();
                tr4C.join();
                
                // while(tr1C.isAlive()){}
                // while(tr2C.isAlive()){}
                // while(tr3C.isAlive()){}
                // while(tr4C.isAlive()){}
                break;

            default:
                System.out.println("La opcion de " + hilos + " no existe. Hilos = 1 por defecto.");

                ParFiles tr1D = new ParFiles(1, 999);
                tr1D.start();

                tr1D.join(); 
                // while(tr1D.isAlive()){}
                break;            
        }

        System.out.println("\n\nLa ejecucion tardo: " + (System.currentTimeMillis() - startTime) + " milisegundos.");

        System.gc();
    }
}