import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

public class FileOperations {
    private Formatter ft;
    private FileWriter fw;
    private String filename;
    private Scanner sc;
    private Date date;
    private String username;
    public FileOperations(String filename,String username){
        this.filename = filename;
        this.sc = null;
        this.ft = null;
        this.date = new Date();
        this.username = username;
    }

    public boolean writeToFile(Player p,Player computer){
        boolean fine = true;
        int count=0;
        String buffer = "";
        try{
            sc = new Scanner(Paths.get(filename));
            while(sc.hasNextLine()){
                sc.nextLine();
                count++;
            }
            if(count >= 10){
                boolean first = true;
                sc.close();
                sc = new Scanner(Paths.get(filename));
                while(sc.hasNextLine()){
                    if(first){
                        sc.nextLine();
                        first = false;
                        continue;
                    }
                    buffer += sc.nextLine();
                    buffer += "\n";
                }
                fw = new FileWriter(filename,false);
                ft = new Formatter(fw);
                ft.format("%s",buffer);
                ft.close();
                fw.close();
            }
            fw = new FileWriter(filename,true);
            ft = new Formatter(fw);
            ft.format("%s: %d - %s: %d, %s\n",username,p.getScore(),"PC",computer.getScore(),timeStamp());
        }catch(NoSuchFileException e){
            fine = false;
            try {
                Files.createFile(Paths.get(filename));
                System.out.println("File not found but created.");
                fw = new FileWriter(filename,true);
                ft = new Formatter(fw);
                ft.format("%s: %d - %s: %d, %s\n",username,p.getScore(),"PC",computer.getScore(),timeStamp());
                ft.close();
                fw.close();
            } catch (IOException e1) {
                return fine;
            }
            return fine;
        }catch(Exception e){
            fine = false;
            return fine;
        }finally{
            if(sc != null)
                sc.close();
            if(fw != null){
                try{
                    fw.close();
                }catch(IOException e){
                    fine = false;
                    return fine;
                }
            }
            if(ft != null)
                ft.close();
        }
        return fine;
    }
    public String timeStamp(){
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }
}
