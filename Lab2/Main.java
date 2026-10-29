import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    public static int getInt(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВведите число:");
        while(!scanner.hasNextInt()){
           System.out.println("\nВведено не число");
           scanner.next();
           System.out.println("\nВведите число:");
        }

        return scanner.nextInt();
    }

    public static int getInt(String str){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + str);
        while(!scanner.hasNextInt()){
           System.out.println("\nНеверный ввод");
           scanner.next();
           System.out.println("\n" + str);
        }

        return scanner.nextInt();
    }


    public static boolean isNamePart(String str){
        return str == null || str.isEmpty() || str.matches("^[а-яА-ЯёЁ]+$");
    }

    public static String getNamePart(String str){
        Scanner scanner = new Scanner(System.in, "CP866");
        System.out.println("\nВведите " + str);
        String result;
        boolean isGood;  
        do{
            result = scanner.nextLine();
            isGood = isNamePart(result);
            if(!isGood) System.out.println("\nНеверный ввод " + result);

        } while(!isGood);

        if(result!=null && !result.isEmpty()){
            result = result.substring(0,1).toUpperCase() + result.substring(1).toLowerCase();
        }

        return result;
    }

    public static String getString(String str){
        Scanner scanner = new Scanner(System.in, "CP866");
        System.out.println(str);
        return scanner.nextLine();
    }

    public static void Task1(){
        System.out.println(GREEN + "\n1) - Задание 1 - 3" + RESET);
        String t1_name, t1_surname, t1_patr;
        do{
            System.out.println("Введите ФИО человека - хотя бы 1 параметр должен быть заполнен");
            t1_name = getNamePart("Имя");
            t1_surname = getNamePart("Фамилию");
            t1_patr = getNamePart("Отчество");

        } while(t1_name.isEmpty() && t1_surname.isEmpty() && t1_patr.isEmpty());
        Name name = new Name(t1_name, t1_surname, t1_patr); 
        System.out.println(GREEN + "\nПолучившееся ФИО: " + RESET + name.toString());
    }

    public static void Task2(){
        System.out.println(GREEN + "\n2) - Задание 2 - 2" + RESET);
        String t1_name, t1_surname, t1_patr;
        int height;
        do{
            System.out.println("Введите ФИО человека - хотя бы 1 параметр должен быть заполнен");
            t1_name = getNamePart("Имя");
            t1_surname = getNamePart("Фамилию");
            t1_patr = getNamePart("Отчество");
            height = getInt("Введите рост человека (не меньше 1)");

        } while(t1_name.isEmpty() && t1_surname.isEmpty() && t1_patr.isEmpty() || height < 1);
        Human human = new Human(t1_name, t1_surname, t1_patr, height); 
        System.out.println(GREEN + "\nПолучившийся человек: " + RESET + human.toString());
    }

    static public void Task3PrintArray(List<HumanWithFather> arr){
        int i = 1;
        if(arr.isEmpty()) System.out.println(GREEN + "Пока нет людей" + RESET);
        else
        for (HumanWithFather arr1 : arr) {
            System.out.println(GREEN + i + ") " + RESET + arr1.toString());
            i++;
        }
    }

    public static void Task3(){        
        boolean running = true;        
        List<HumanWithFather> main_arr = new ArrayList<HumanWithFather>();
        while (running) { 
            System.out.println(GREEN + "\n3) - Задание 2 - 3\n" + RESET);
            Task3PrintArray(main_arr);
            System.out.println("\n1) - Добавить человека");
            System.out.println("2) - Добавить отца для человека");
            System.out.println("3) - Указать пол человеку");
            System.out.println("4) - Обновить список людей");
            int user_choice;
            do { 
                user_choice = getInt("Введите номер действия (от 1 до 4) или 0 для выхода");
            } while (user_choice > 4 || user_choice < 0);
            switch (user_choice) {
                case 1:
                    String t1_name, t1_surname, t1_patr;
                    int height;
                    do{
                        System.out.println("Введите ФИО человека - хотя бы 1 параметр должен быть заполнен");
                        t1_name = getNamePart("Имя");
                        t1_surname = getNamePart("Фамилию");
                        t1_patr = getNamePart("Отчество");
                        height = getInt("Введите рост человека (не меньше 1)");

                    } while(t1_name.isEmpty() && t1_surname.isEmpty() && t1_patr.isEmpty() || height < 1);
                    main_arr.add(new HumanWithFather(t1_name, t1_surname, t1_patr, height));
                    break;
                case 2:
                    if(!main_arr.isEmpty()){
                        int max = main_arr.size();                        
                        int son;
                        int father;
                        do{
                            do { 
                                son = getInt("Введите номер человека (сына) - от 1 до " + max);
                            } while (son > max || son < 1);
                            
                            do { 
                                father = getInt("Введите номер человека (отца) - от 1 до " + max);
                            } while (father > max || father < 1);

                            if (father == son) System.out.println(RED+"Отец и сын не могут быть одним и тем же человеком"+RESET);
                        } while(father == son);
                        HumanWithFather son_ptr = main_arr.get(son - 1);
                        HumanWithFather father_ptr = main_arr.get(father - 1);

                        boolean good = true;

                        HumanWithFather current = father_ptr.getFather();
                        while(current != null){
                            if(current.equals(son_ptr)) good = false;
                            current = current.getFather();
                        }

                        if(!good) System.out.println(RED+"Сын не может быть предком отца"+RESET);

                        if(son_ptr != null && father_ptr != null && good) son_ptr.setFather(father_ptr);
                    }
                    break;
                case 3:
                    if(!main_arr.isEmpty()){
                        int max = main_arr.size();                        
                        int choice;
                        do { 
                            choice = getInt("Введите номер человека - от 1 до " + max);
                        } while (choice > max || choice < 1);
                        HumanWithFather choice_ptr = main_arr.get(choice - 1);
                        int sex;
                        do { 
                            sex = getInt("Введите пол человека - 1 это мижчина и 2 это женщина ");
                        } while (sex > 2 || sex < 1);
                        if(choice_ptr != null) {
                            if(sex == 1) choice_ptr.setSexMale(true);
                            else choice_ptr.setSexMale(false);
                        }
                    }
                    break;
                case 4:

                    break;
                default:
                    running = false;
            }
        }
    }

    public static void Task4PrintSet(Set<City> main_arr){
        for(City city : main_arr) {
            System.out.println(city.toString());
        }
    }

    public static void Task4(){        
        boolean running = true;        
        Set<City> main_set = new HashSet<>();
        while (running) { 
            System.out.println(GREEN + "\n4) - Задание 3 - 3\n" + RESET);
            Task4PrintSet(main_set);
            System.out.println("\n1) - Добавить город");
            System.out.println("2) - Добавить дорогу");
            System.out.println("3) - Загрузить карту из файла");
            System.out.println("4) - Очистить города");
            System.out.println("5) - Обновить список городов");
            int user_choice;
            do { 
                user_choice = getInt("Введите номер действия (от 1 до 4) или 0 для выхода");
            } while (user_choice > 5 || user_choice < 0);
            switch (user_choice) {
                case 1:
                    String from_name = getString("Введите название нового города");

                    City fromCity = null;

                    for(City city_other : main_set){
                        if(city_other.getName().equals(from_name)) fromCity = city_other;
                    }

                    if(fromCity == null){
                        fromCity = new City(from_name);
                        main_set.add(fromCity);
                    }
                    else{
                        System.out.println(RED + "\nГород " + from_name + " уже есть в списке городов\n" + RESET);
                    }
                    break;
                case 2:
                    from_name = getString("Введите название города отправления");

                    fromCity = null;

                    for(City city_other : main_set){
                        if(city_other.getName().equals(from_name)) fromCity = city_other;
                    }

                    if(fromCity != null){
                        String to_name = getString("Введите название нового города прибытия");

                        City toCity = null;

                        for(City city_other : main_set){
                            if(city_other.getName().equals(to_name)) toCity = city_other;
                        }

                        if(toCity != null){
                            int road;
                            int dist;
                             do { 
                                dist = getInt("Введите длину дороги (больше 0)");
                            } while (dist < 1);
                            do { 
                                road = getInt("Введите 1 для односторонней дороги и 2 для двусторонней ");
                            } while (road > 2 || road < 1);
                            if(road == 1) fromCity.addOneWayRoad(toCity, dist);
                            else fromCity.addTwoWayRoad(toCity, dist);
                        }
                        else{
                            System.out.println(RED + "\nГорода " + to_name + " нет в списке городов\n" + RESET);
                        }
                    }
                    else{
                        System.out.println(RED + "\nГорода " + from_name + " нет в списке городов\n" + RESET);
                    }
                    
                    break;
                case 3:
                    Set<City> back_up = main_set;
                    main_set = MapLoader.loadMapFromFile(getString("Введите название файла с картой городов"));
                    if(main_set.isEmpty()) main_set = back_up;
                    break;
                case 4:
                    main_set = new HashSet<>();
                    break;
                case 5:

                    break;
                default:
                    running = false;
            }
        }
    }

    public static void Task5(){
        System.out.println(GREEN + "\n5) - Задание 4 - 5" + RESET);
        String t1_name, t1_surname, t1_patr;
        do{
            System.out.println("Введите ФИО человека - хотя бы 1 параметр должен быть заполнен");
            t1_name = getNamePart("Имя");
            t1_surname = getNamePart("Фамилию");
            t1_patr = getNamePart("Отчество");

        } while(t1_name.isEmpty() && t1_surname.isEmpty() && t1_patr.isEmpty());
        Name2 name = null; 
        if(t1_patr.isEmpty()){
            if(t1_surname.isEmpty()) name = new Name2(t1_name);
            else name = new Name2(t1_name,t1_surname);
        }
        else name = new Name2(t1_name, t1_surname, t1_patr);         
        System.out.println(GREEN + "\nПолучившееся ФИО: " + RESET + name.toString());
    }

    public static void Task6(){
        System.out.println(GREEN + "\n6) - Задание 4 - 6" + RESET);
        
        Human2 human1 = new Human2("Лев", 170);
        Human2 human2 = new Human2(new Name2("Сергей","Пушкин"), 168, human1);
        Human2 human3 = new Human2("Александр", 167, human2);

        System.out.println(GREEN + "\nПолучившееся ФИО 1: " + RESET + human1 );
        System.out.println(GREEN + "\nПолучившееся ФИО 2: " + RESET + human2 );
        System.out.println(GREEN + "\nПолучившееся ФИО 3: " + RESET + human3 );        
    }

    public static void Task7(){
        
        boolean running = true;        
        Cat cat = null;
        while (running) { 
            System.out.println(GREEN + "\n7) - Задание 5 - 2" + RESET);
            if(cat == null) System.out.println("\nКота пока нет");
            else System.out.println("\n" + cat);
            System.out.println("\n1) - Добавить(пересоздать) кота");
            System.out.println("2) - Мяукнуть");
            System.out.println("3) - Мяукнуть несколько раз");
            int user_choice;
            do { 
                user_choice = getInt("Введите номер действия (от 1 до 3) или 0 для выхода");
            } while (user_choice > 3 || user_choice < 0);
            switch (user_choice) {
                case 1:
                    cat = new Cat(getNamePart(("имя кота")));
                    break;
                case 2:
                    if (cat != null) cat.Meow();
                    else System.out.println( RED + "\nМяукнуть пока некому" + RESET);
                    break;
                case 3:
                     if (cat != null) {
                        int num;
                        do { 
                            num = getInt("Введите количество мяу для кота(больше 0)");
                        } while (num < 1);
                        cat.Meow(num);
                     }
                    else System.out.println( RED + "\nМяукнуть пока некому" + RESET);
                    break;
                default:
                    running = false;
            }
        }
    }

    public static void main(String[] args)  {
        boolean running = true;
        while (running) { 
            System.out.print(GREEN + "\nГлавное меню" + RESET);
            System.out.print("\n1) - Задание 1 - 3");
            System.out.print("\n2) - Задание 2 - 2");
            System.out.print("\n3) - Задание 2 - 3");
            System.out.print("\n4) - Задание 3 - 3");
            System.out.print("\n5) - Задание 4 - 5");
            System.out.print("\n6) - Задание 4 - 6");
            System.out.print("\n7) - Задание 5 - 2");

            int user_choice;
            do { 
                user_choice = getInt("Введите номер задания (от 1 до 7) или 0 для выхода");
            } while (user_choice > 7 || user_choice < 0);

            switch (user_choice) {
                case 1:
                    Task1();
                    break;
                case 2:
                    Task2();
                    break;
                case 3:
                    Task3();
                    break;
                case 4:
                    Task4();
                    break;
                case 5:
                    Task5();
                    break;
                case 6:
                    Task6();
                    break;
                case 7:
                    Task7();
                    break;
                default:
                    running = false;
            }
        }
           
        

    }
}