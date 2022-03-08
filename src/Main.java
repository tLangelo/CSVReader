import java.util.Scanner;

class App{
    private final Scanner sc = new Scanner(System.in);
    private final csvReader cr = new csvReader("resources/imdb_data.csv");

    public void run(){
        String filePath = "resources";
        String input;
        do{
            System.out.println("""
                What do you wanna do?
                1.\tCreate DDL & DML
                2.\tChange file path(default: "resources")
                3.\tQuit
                """);
            input = sc.nextLine();
            switch (input) {
                case "1":
                    cr.createDDL(filePath);
                    cr.createDML(filePath);
                    return;
                case "2":
                    filePath = changeFilePath(filePath);
                    break;
                default:
                    System.out.println("Not a valid input");
            }
        }while(!input.equals("3"));
    }

    private String changeFilePath(String filepath){
        System.out.println("(Current file path: \"" + filepath + "\").\nInsert new file path: ");
        return sc.nextLine();
    }

}


public class Main {
    public static void main(String[] args) {
        new App().run();
    }


}
