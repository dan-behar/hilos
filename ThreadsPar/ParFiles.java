package ThreadsPar;

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
// import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.File; 
import java.io.FileWriter;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;



public class ParFiles extends Thread{

    // Atributos 
    int inicio; 
    int fin;
    int hilos; 

    // Contructor
    public ParFiles(int inicio, int fin){
        this.inicio = inicio; 
        this.fin = fin;
        this.hilos = 1;
    }

    // Runner
    public void run(){
        for (int i = inicio; i < (fin + 1); i++){
            try {
                descriptorData(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void descriptorData(int n) throws InterruptedException{
        
        // Variables de lectura
        String filename = "so_data/index_data_"+ n +".csv";
        String output_filename = "output/output_data_"+ n +".csv";
        String line = "";  
        String splitBy = ",";
        Integer conteo = 0;  

        // Descripción de descriptores
        DescriptiveStats stats_open = new DescriptiveStats();
        DescriptiveStats stats_high = new DescriptiveStats();
        DescriptiveStats stats_low = new DescriptiveStats();
        DescriptiveStats stats_close = new DescriptiveStats();

        // DescriptiveStats[] columnas = {stats_open, stats_high, stats_low, stats_close};

        // Se cargan los datos para hacer las estadísticas
        try   
        {  
            BufferedReader buffer = new BufferedReader(new FileReader(filename));  
            
            buffer.readLine(); // leemos la primera línea con los encabezados
 

            while ((line = buffer.readLine()) != null)  
            {  
                String[] fila = line.split(splitBy);

                // System.out.println(fila[0] + ", " + fila[1]  + ", " + fila[2]  + ", " + fila[3]  + ", " + fila[4]); 

                stats_open.addValue(Integer.parseInt(fila[1]));
                stats_high.addValue(Integer.parseInt(fila[2]));
                stats_low.addValue(Integer.parseInt(fila[3]));
                stats_close.addValue(Integer.parseInt(fila[4]));

                conteo++; 

                // Thread.sleep(1000);
            }

            buffer.close();
        } 
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }


         

        

        /* Crear un switch para todos los casos
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         */
        // creacion de un solo hilo



        switch(this.hilos){
            case 1:
                // Definicion de trabajos
                DescriptiveStats[] variablesA = {
                    stats_open, stats_open, stats_open, stats_open, 
                    stats_high, stats_high, stats_high, stats_high, 
                    stats_low,  stats_low,  stats_low,  stats_low, 
                    stats_close, stats_close, stats_close, stats_close
                };
        
                int[] estadisticoA = {
                    1, 2, 3, 4,
                    1, 2, 3, 4,
                    1, 2, 3, 4,
                    1, 2, 3, 4 
                };
                
                // Creacion de Thread
                ParFun tr1A = new ParFun(variablesA, estadisticoA); 
                
                // Ejecucion de Threads 
                tr1A.start();
                tr1A.join();
                break;

            case 2:

                // Definicion de trabajos 
                DescriptiveStats[] variablesB1 = {
                    stats_open, stats_open, stats_open, stats_open, 
                    stats_high, stats_high, stats_high, stats_high 
                };

                DescriptiveStats[] variablesB2 = { 
                    stats_low,  stats_low,  stats_low,  stats_low, 
                    stats_close, stats_close, stats_close, stats_close
                };
        
                int[] estadisticoB1 = {
                    1, 2, 3, 4,
                    1, 2, 3, 4 
                };
                
                // Creacion de Threads
                ParFun tr1B = new ParFun(variablesB1, estadisticoB1);
                ParFun tr2B = new ParFun(variablesB2, estadisticoB1);


                // Ejecucion de Threads
                tr1B.start();
                tr2B.start();

                tr1B.join();
                tr2B.join();
        
                break;
            
            case 4: 
                // Definicion de trabajos 
                DescriptiveStats[] variablesC1 = {
                    stats_open, stats_open, stats_open, stats_open 
                };

                DescriptiveStats[] variablesC2 = {
                    stats_high, stats_high, stats_high, stats_high 
                };

                DescriptiveStats[] variablesC3 = { 
                    stats_low,  stats_low,  stats_low,  stats_low 
                };

                DescriptiveStats[] variablesC4 = { 
                    stats_close, stats_close, stats_close, stats_close
                };
        
                int[] estadisticoC1 = {
                    1, 2, 3, 4 
                };
                
                // Creacion de Threads
                ParFun tr1C = new ParFun(variablesC1, estadisticoC1);
                ParFun tr2C = new ParFun(variablesC2, estadisticoC1);
                ParFun tr3C = new ParFun(variablesC3, estadisticoC1);
                ParFun tr4C = new ParFun(variablesC4, estadisticoC1);


                // Ejecucion de Threads
                tr1C.start();
                tr2C.start();
                tr3C.start();
                tr4C.start();

                tr1C.join();
                tr2C.join();
                tr3C.join();
                tr4C.join();
            
            case 8: 
                // Definicion de trabajos 
                DescriptiveStats[] variablesD1 = {
                    stats_open, stats_open 
                };

                DescriptiveStats[] variablesD2 = {
                    stats_high, stats_high 
                };

                DescriptiveStats[] variablesD3 = { 
                    stats_low,  stats_low 
                };

                DescriptiveStats[] variablesD4 = { 
                    stats_close, stats_close
                };

                int[] estadisticoD1 = {
                    1, 2
                };

                int[] estadisticoD2 = {
                    3, 4 
                };

                
                // Creacion de Threads
                ParFun tr1D = new ParFun(variablesD1, estadisticoD1);
                ParFun tr2D = new ParFun(variablesD2, estadisticoD1);
                ParFun tr3D = new ParFun(variablesD3, estadisticoD1);
                ParFun tr4D = new ParFun(variablesD4, estadisticoD1);

                ParFun tr5D = new ParFun(variablesD1, estadisticoD2);
                ParFun tr6D = new ParFun(variablesD2, estadisticoD2);
                ParFun tr7D = new ParFun(variablesD3, estadisticoD2);
                ParFun tr8D = new ParFun(variablesD4, estadisticoD2);


                // Ejecucion de Threads
                tr1D.start();
                tr2D.start();
                tr3D.start();
                tr4D.start();
                tr5D.start();
                tr6D.start();
                tr7D.start();
                tr8D.start();

                tr1D.join();
                tr2D.join();
                tr3D.join();
                tr4D.join();
                tr5D.join();
                tr6D.join();
                tr7D.join();
                tr8D.join();
            default: 
                System.out.println("La eleccion de hilos es invalida");
                break;
        }
        

        // Se genera el archivo
        try {
            File myObj = new File(output_filename);
            if (myObj.createNewFile()) {
              //System.out.println("Archivo Creado: " + myObj.getName());
            } else {
              //System.out.println("El archivo ya existe.");
            }

        } catch (IOException e) {
            //System.out.println("Ocurrio un error generando el arhivo.");
            e.printStackTrace();
        }


        // Se escribe el resultado en el archivo
        try {
            FileWriter myWriter = new FileWriter(output_filename);
            
            myWriter.write("stats,Open,High,Low,Close\n");
            myWriter.write("count," + conteo + ","+  conteo + ","+  conteo + ","+  conteo + "\n");
            myWriter.write("mean,"  + stats_open.getCalMean() + ","+  stats_high.getCalMean().toString() + ","+  stats_low.getCalMean().toString() + ","+  stats_close.getCalMean().toString() + "\n");
            myWriter.write("std,"   + stats_open.getCalStd() + ","+  stats_high.getCalStd().toString() + ","+  stats_low.getCalStd().toString() + ","+  stats_close.getCalStd().toString() + "\n");
            myWriter.write("min,"   + stats_open.getCalMin()+ ","+  stats_high.getCalMin().toString() + ","+  stats_low.getCalMin().toString() + ","+  stats_close.getCalMin().toString() + "\n");
            myWriter.write("max,"   + stats_open.getCalMax() + ","+  stats_high.getCalMax().toString() + ","+  stats_low.getCalMax().toString() + ","+  stats_close.getCalMax().toString() + "\n");

            myWriter.close();

            //System.out.println("El archivo se escribió correctamente.");
            
        } catch (IOException e) {
            //System.out.println("Ocurrio un error.");
            e.printStackTrace();
        }

        //System.out.println("\nSe completó archivo: " + filename);
    }
}
