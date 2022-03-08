import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class csvReader {
    private final File file;
    private Scanner sc;
    private final ArrayList<String> overskrifter = new ArrayList<>();


    public csvReader(String filePath) {
        file = new File(filePath);

        try{
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillTitles() {
        String currLine = sc.nextLine();
        String[] split = currLine.split(";");
        overskrifter.addAll(Arrays.asList(split));
    }

    private void fillList(){
        if (overskrifter.size() == 0)
            fillTitles();
    }

    private String getFilename(){
        if(this.file.getName().contains("."))
            return this.file.getName().split("\\.")[0];
        else
            return this.file.getName();
    }

    private String getTitlesAsSQL(){
        fillList();
        String sql = "CREATE TABLE " + getFilename() + "(\n";
        for (String word : overskrifter) {
            if (!overskrifter.get(overskrifter.size() - 1).matches(word))
                sql = sql + word + " varchar(255),\n";
            else
                sql = sql + word + " varchar(255)\n";
        }
        return sql + ");";

    }

    public void createDDL(String path){
        String fileName = path + "/" + "DDL" + ".SQL";
        File newFile = new File(fileName);
        try {
            FileWriter fr = new FileWriter(newFile);
            System.out.println("Writing SQL(DDL) file");
            fr.write(getTitlesAsSQL());
            fr.close();
        } catch (IOException e) {
            System.out.println("Failed to create FileWriter");
            e.printStackTrace();
        }
    }

    public void createDML(String path){
        String fileName = path + "/" + "DML" + ".SQL";
        File newFile = new File(fileName);

        try {
            FileWriter fr = new FileWriter(newFile);
            System.out.println("Writing SQL(DML) file");
            fr.write("INSERT INTO " + getFilename());
            while(sc.hasNext()){
                String currLine = sc.nextLine();
                String[] split = currLine.split(";");
                String data = "";
                for (String value : split) {
                    if(split[0].matches(value))
                        data = "'" + value + "'";
                    else
                        data = data + ", " + "'" + value + "'";
                }
                fr.write("\nVALUES (" + data + ");");
            }
            fr.close();
        } catch (IOException e) {
            System.out.println("Failed to create FileWriter");
            e.printStackTrace();
        }
    }


}
