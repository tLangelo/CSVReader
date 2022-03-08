import java.util.Scanner;

class App{
    private final Scanner sc = new Scanner(System.in);
    private csvReader cr;

    public void run(){
        String filePath = "resources";
        String input;
        System.out.println("Please input the path/name of the file you want to convert: (ex. resources/imdb_data.csv)");
        cr = new csvReader(sc.nextLine());
        do{
            System.out.println("""
                What do you wanna do?
                1.\tCreate DDL & DML
                2.\tChange save path(current: $filePath)
                3.\tChange the split variable(current: "£splitVar")
                4.\tQuit
                """.replace("£splitVar", cr.getSplitVar()).replace("$filePath", filePath));
            input = sc.nextLine();
            switch (input) {
                case "1":
                    cr.createDDL(filePath);
                    cr.createDML(filePath);
                    return;
                case "2":
                    filePath = changeFilePath(filePath);
                    break;
                case "3":
                    System.out.println("Please input split variable");
                    cr.setSplitVar(sc.nextLine());
                    break;
                default:
                    System.out.println("Not a valid input");
            }
        }while(!input.equals("4"));
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
