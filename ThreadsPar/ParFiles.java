package ThreadsPar;

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
// import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.File; 
import java.io.FileWriter;


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
            descriptorData(i);
        }
    }

    public void descriptorData(int n){
        
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

        // se generan las estadísticas
        double[] openStats = {conteo, stats_open.getMean(), stats_open.getStandardDeviation(), stats_open.getMin(), stats_open.getMax()};
        double[] highStats = {conteo, stats_high.getMean(), stats_high.getStandardDeviation(), stats_high.getMin(), stats_high.getMax()}; 
        double[] lowStats = {conteo, stats_low.getMean(), stats_low.getStandardDeviation(), stats_low.getMin(), stats_low.getMax()}; 
        double[] closeStats = {conteo, stats_close.getMean(), stats_close.getStandardDeviation(), stats_close.getMin(), stats_close.getMax()}; 

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
            myWriter.write("count," + openStats[0] + ","+  highStats[0] + ","+  lowStats[0] + ","+  closeStats[0] + "\n");
            myWriter.write("mean," + openStats[1] + ","+  highStats[1] + ","+  lowStats[1] + ","+  closeStats[1] + "\n");
            myWriter.write("std," + openStats[2] + ","+  highStats[2] + ","+  lowStats[2] + ","+  closeStats[2] + "\n");
            myWriter.write("min," + openStats[3] + ","+  highStats[3] + ","+  lowStats[3] + ","+  closeStats[3] + "\n");
            myWriter.write("max," + openStats[4] + ","+  highStats[4] + ","+  lowStats[4] + ","+  closeStats[4] + "\n");

            myWriter.close();

            //System.out.println("El archivo se escribió correctamente.");
            
        } catch (IOException e) {
            //System.out.println("Ocurrio un error.");
            e.printStackTrace();
        }

        //System.out.println("\nSe completó archivo: " + filename);
    }
}
