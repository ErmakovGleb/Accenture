public class Sequence {
    static int[] numbers;
    static int size;

    public Sequence(int size){
        numbers = new int[size];
        this.size=size;
    }

    public static void Generation(){
        numbers[0]=(int)(Math.random()*10);

        for(int i=1;i<size;i++){
            numbers[i]=(int)(Math.random()*10);
            for(int j=0;j<i;j++){
                if(numbers[j]==numbers[i]){
                    numbers[i]=(int)(Math.random()*10);
                    j=-1;
                }
            }
        }
    }

    public static String GetString(){
        String string="";
        for(int i=0;i<size;i++){
            string=string+String.valueOf(numbers[i]);
        }
        return  string;
    }

    public static int[] GetArray(){
        return numbers;
    }

}
