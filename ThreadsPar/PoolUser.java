package ThreadsPar;

public class PoolUser implements Runnable{
    // Atributos
    DescriptiveStats descriptor;
    int funcion;

    // Constructor
    public PoolUser(DescriptiveStats descrip, int fun){
        this.descriptor = descrip; 
        this.funcion = fun;
    }

    public void run(){
        switch(this.funcion){
            case 1: 
                this.descriptor.getMean(); 
                break; 
            case 2:
                this.descriptor.getStandardDeviation(); 
                break;
            case 3: 
                this.descriptor.getMin(); 
                break;
            case 4: 
                this.descriptor.getMax(); 
                break;
            default: 
                System.out.println("La funcion indicada no existe.");
        }
    }
}
