package ThreadsPar;

public class ParFun extends Thread{
    
    // Atributes
    DescriptiveStats[] descriptor;
    int [] statistic; 
    
    public ParFun(DescriptiveStats[] descriptor, int[] statistic){
        this.descriptor = descriptor;
        this.statistic = statistic;
    }

    public void run(){

        // Se hace una iteracion sobre las columnas y sobre los estadisticos
        for (int i = 0; i < this.descriptor.length; i++){

            switch(this.statistic[i]){
                case 1:
                    this.descriptor[i].getMean(); 
                    break;
                case 2:
                    this.descriptor[i].getStandardDeviation(); 
                    break;
                case 3:
                    this.descriptor[i].getMin(); 
                    break;
                case 4:
                    this.descriptor[i].getMax(); 
                    break;
                default: 
                    System.out.println("Run Opcion invalida");
            }
        }
    }
}
