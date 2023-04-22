package ThreadsPar;

import java.util.ArrayList;
import java.lang.Math;


public class DescriptiveStats {
    
    // Atributes 
    ArrayList<Double> lista;
    Double mean;
    Double std;
    Double min;
    Double max;
    int n; 

    // Contructor
    public DescriptiveStats(){
        this.lista = new ArrayList<Double>();
        this.n = 0;  
    }

    // Añade el valor a la lista
    public void addValue(double value){
        this.lista.add(value); 
        this.n++;
    }

    // Calcula la media
    public Double getMean(){
        double result = 0;
        // int n = 0; 

        for (Double i : this.lista){
            result += i; 
            // n++;
        }

        result = result / n;
        this.mean = result;  
        return result; 
    }

    // Calcula la desviacion estandar
    public Double getStandardDeviation(){
        double result = 0;
        double meanL = this.getMean(); 
        // int n = 0; 

        for (Double i : this.lista){
            result += (i-meanL)*(i-meanL); 
            // n++;
        }

        result = result / (n - 1); 
        result = Math.sqrt(result); 

        this.std = result; 
        return result; 
    }

    // Calcula el mínimo
    public Double getMin(){
        Double result = this.lista.get(0);

        for (Double i : this.lista){
            if (i < result){
                result = i; 
            }
        }

        this.min = result; 
        return result; 
    }


    // Calcula el Máximo
    public Double getMax(){
        Double result = 0.0;

        for (Double i : this.lista){
            if (i > result){
                result = i; 
            }
        }

        this.max = result; 
        return result;
    }


    // Retorna la media calculada 
    public Double getCalMean(){
        return this.mean;
    }

    // Retorna la desvicacion estadar calculada 
    public Double getCalStd(){
        return this.std;
    }

    // Retorna el minimo calculado
    public Double getCalMin(){
        return this.min;
    }

    // Retorna el máximo calculado 
    public Double getCalMax(){
        return this.max;
    }
}
