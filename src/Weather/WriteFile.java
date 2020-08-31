package Weather;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    private static  WriteFile writeFile ;
    private  static BufferedWriter bufferedWriter;
    private  static  File file ;
    private  static  FileWriter fileWriter;

    private  WriteFile(){

    }
    public  static  WriteFile getWriteFile(){

        if (writeFile != null) {
            return writeFile;
        }
        try{
            writeFile = new WriteFile();
            file = new File("simulation.txt");
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

        }catch (IOException e){}

        return writeFile;
    }

    public  void  writeToFile(String str){
        try{
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        }catch (IOException e){}
    }
    public  void close(){
        try{
            if(bufferedWriter !=null)
                bufferedWriter.close();

        }catch(IOException e){
            System.out.println("Error in closing the BufferedWriter" + e);
        }
    }
}
