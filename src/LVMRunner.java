import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class LVMRunner{

    private static LogicalVolumeManager LVM = new LogicalVolumeManager();
    public static void main(String[] args) throws Exception {

        File data = new File("src/lvmdata.txt");
        getDataFromFile(data);

        Scanner s = new Scanner(System.in);
        String input = "";
        System.out.println("Welcome to the LVM system.");
        while (!input.equals("exit")){
            System.out.print("cmd#: ");
            input = s.nextLine();
            LVM.cmd(input);
        }

        try {
            PrintWriter writer = new PrintWriter(data);
            writer.println(LVM.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // saves drive data but nothing else (i tried serialization but it did not work for some reason)
    public static void getDataFromFile(File file) throws FileNotFoundException {
        if (file.length() > 0){
            Scanner scan = new Scanner(file);
            String[] data = scan.nextLine().split("//");
            if (!data[0].equals(" ")){
                String[] drives = data[0].split(",");
                for (String driveData : drives){
                    LVM.addDrive(driveData);
                }
            }
        }
    }
}

