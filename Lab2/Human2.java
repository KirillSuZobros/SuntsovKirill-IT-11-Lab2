
public class Human2{
    private Name2 name;
    private int height;
    private Human2 father = null;
    private boolean male = true;

    public Human2(Name2 new_name, int new_height, Human2 father){
        this.name = new_name;
        this.height = new_height;
        this.father = father;

        String father_surname;

        if( (name.getSurname() == null || name.getSurname().isEmpty()) && father != null && father.getName().getSurname() != null) {

            father_surname = father.getName().getSurname();

            if(male) name = new Name2 (name.getName(), father_surname, name.getPatronymic() );
            
            else if(father_surname.endsWith("ов") || father_surname.endsWith("ин"))
            name = new Name2 (name.getName(), father_surname + "а", name.getPatronymic() );

            else name = new Name2 (name.getName(), father_surname, name.getPatronymic() );
        }

        String father_name;

        if( (name.getPatronymic() == null || name.getPatronymic().isEmpty()) && father != null && father.getName().getName() != null) {

            father_name = father.getName().getName();

            if(father_name.endsWith("а") || father_name.endsWith("я")) 
            father_name = father_name.substring(0, father_name.length()-1);

            if(male) name = new Name2 (name.getName(), name.getSurname(), father_name + "ович ");

            else  name = new Name2 (name.getName(), name.getSurname(), father_name + "овна ");
        }
    }    

    public Human2(String s1, int new_height , Human2 father){
        this(new Name2(s1), new_height, father);
    }

    public Human2(Name2 new_name, int new_height){
        this(new_name, new_height, null);
    }

    public Human2(String s1, int new_height){
        this(new Name2(s1), new_height, null);
    }

    public void setFather(Human2 new_father){
        this.father = new_father;
    }

    public void setSexMale(boolean sex_male){
        this.male = sex_male;
    }
    public Name2 getName(){return name;}

    public Human2 getFather(){return father;}

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
        
        
        return result.trim() + ", " + height;
    }
}