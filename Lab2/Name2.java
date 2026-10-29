
public class Name2{
    private String name = null;
    private String surname = null;
    private String patronymic = null;

    public Name2(String s1){
        this.name = s1;
    }

    public Name2(String s1, String s2){
        this.name = s1;
        this.surname = s2;
    }

    public Name2(String s1, String s2, String s3){
        this.name = s1;
        this.surname = s2;
        this.patronymic = s3;
    }
    public String getName(){return name;}
    public String getSurname(){return surname;}
    public String getPatronymic(){return patronymic;}

    @Override
    public String toString(){
        String result = "";
        if(surname != null && !surname.isEmpty()) result = surname + " ";
        if(name != null && !name.isEmpty()) result = result + name + " ";
        if(patronymic != null && !patronymic.isEmpty()) result = result + patronymic+" ";
        return result.trim();
    }
}