
public class Cat{
    private String name;

    Cat(String new_name){
        this.name = new_name;
    }
   
    public void Meow(){
        System.out.println(this.name + ": мяу!");
    }

    public void Meow(int n){
        if(n>0){
            String line = this.name + ": ";
            for (int i = 0; i < n - 1; i++){
                line = line + "мяу-";
            }
            line = line + "мяу!";
            System.out.println(line);

        }
        
    }

    @Override
    public String toString(){
        return "кот: " + this.name;
    }
}