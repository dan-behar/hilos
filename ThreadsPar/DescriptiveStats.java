package ThreadsPar;

import java.util.ArrayList;
import java.lang.Math;


public class DescriptiveStats {
    // Atributes 

    ArrayList<Double> lista;
    int size;

    public DescriptiveStats(){
        this.lista = new ArrayList<Double>(); 
    }

    public void addValue(double value){
        lista.add(value); 
    }

    public Double getMean(){
        double result = 0;
        int n = 0; 

        for (Double i : this.lista){
            result += i; 
            n++;
        }

        result = result / n; 
        return result; 
    }

    public Double getStandardDeviation(){
        double result = 0;
        double mean = this.getMean(); 
        int n = 0; 

        for (Double i : this.lista){
            result += (i-mean)*(i-mean); 
            n++;
        }

        result = result / (n - 1); 
        result = Math.sqrt(result); 

        return result; 
    }

    public Double getMin(){
        Double result = this.lista.get(0);

        for (Double i : this.lista){
            if (i < result){
                result = i; 
            }
        }

        return result; 
    }

    public Double getMax(){
        Double result = 0.0;

        for (Double i : this.lista){
            if (i > result){
                result = i; 
            }
        }

        return result;
    }
}
