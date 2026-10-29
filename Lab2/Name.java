
public class Name{
    private String name = null;
    private String surname = null;
    private String patronymic = null;

    public Name(String s1, String s2, String s3){
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