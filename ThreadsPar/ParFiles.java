package ThreadsPar;

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
// import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.File; 
import java.io.FileWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ParFiles extends Thread{

    // Atributos 
    int inicio; 
    int fin;

    // Contructor
    public ParFiles(int inicio, int fin){
        this.inicio = inicio; 
        this.fin = fin; 
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
        String output_filename = "output_data/output_data_"+ n +".csv";
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



        
        DescriptiveStats[] variables = {
            stats_open, stats_open, stats_open, stats_open, 
            stats_high, stats_high, stats_high, stats_high, 
            stats_low,  stats_low,  stats_low,  stats_low, 
            stats_close, stats_close, stats_close, stats_close
        };

        int[] estadistico = {
            1, 2, 3, 4,
            1, 2, 3, 4,
            1, 2, 3, 4,
            1, 2, 3, 4 
        };

        ParFun tr1 = new ParFun(variables, estadistico); 

        tr1.start();
        tr1.join();
        

        

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
