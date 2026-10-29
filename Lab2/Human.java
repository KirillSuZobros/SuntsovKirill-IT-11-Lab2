
public class Human{
    private Name name;
    private int height;

    public Human(Name new_name, int new_height){
        this.name = new_name;
        this.height = new_height;
    }

    public Human(String s1, String s2, String s3, int new_height){
        this.name = new Name(s1, s2, s3);
        this.height = new_height;
    }

    @Override
    public String toString(){
        if (name == null) return "Имя неизвестно, рост: " + height;
        String result = "";
        if(name.getSurname() != null && !name.getSurname().isEmpty()) result = name.getSurname() + " ";
        if(name.getName() != null && !name.getName().isEmpty()) result = result + name.getName() + " ";
        if(name.getPatronymic() != null && !name.getPatronymic().isEmpty()) result = result + name.getPatronymic()+" ";
        return result.trim() + ", рост: " + height;
    }
}