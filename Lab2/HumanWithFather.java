
public class HumanWithFather{
    private Name name;
    private int height;
    private HumanWithFather father = null;
    private boolean male = true;

    public HumanWithFather(Name new_name, int new_height){
        this.name = new_name;
        this.height = new_height;
    }

    public HumanWithFather(String s1, String s2, String s3, int new_height){
        this.name = new Name(s1, s2, s3);
        this.height = new_height;
    }

    public void setFather(HumanWithFather new_father){
        this.father = new_father;
    }

    public void setSexMale(boolean sex_male){
        this.male = sex_male;
    }
    public Name getName(){return name;}

    public HumanWithFather getFather(){return father;}

    @Override
    public String toString(){

        if (name == null) return "Имя неизвестно, рост: " + height;

        String result = "";
        String father_surname;

        if(name.getSurname() != null && !name.getSurname().isEmpty()) result = name.getSurname() + " ";

        else if(father != null && father.getName().getSurname() != null) {

            father_surname = father.getName().getSurname();

            if(male) result = father_surname + " ";
            
            else if(father_surname.endsWith("ов") || father_surname.endsWith("ин"))
            result = father_surname + "а ";

            else result = father_surname + " ";
        }
        
        if(name.getName() != null && !name.getName().isEmpty()) result = result + name.getName() + " ";
        
        String father_name;
        if(name.getPatronymic() != null && !name.getPatronymic().isEmpty()) result = result + name.getPatronymic() + " ";

        else if(father != null && father.getName().getName() != null) {

            father_name = father.getName().getName();

            if(father_name.endsWith("а") || father_name.endsWith("я")) 
            father_name = father_name.substring(0, father_name.length()-1);

            if(male) result = result + father_name + "ович ";

            else result = result + father_name + "овна ";
        }
        
        
        return result.trim() + ", рост: " + height;
    }
}