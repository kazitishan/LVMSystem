import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class LVMRunner implements Serializable{
    private static LogicalVolumeManager LVM = new LogicalVolumeManager();
    public static void main(String[] args) throws IOException {

        try {
            FileInputStream fileIn = new FileInputStream("lvm_ser.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            LVM = (LogicalVolumeManager) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Data loaded.");
        } catch (IOException i) {
            System.out.println("Error: " + i.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("LogicalVolumeManager class not found.");
            System.out.println("Error: " + c.getMessage());
        }

        Scanner s = new Scanner(System.in);
        String input = "";
        System.out.println("Welcome to the LVM system.");
        while (!input.equals("exit")){
            System.out.print("cmd#: ");
            input = s.nextLine();
            LVM.cmd(input);
        }
        saveToFile();
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lvm_ser.txt"))) {
            out.writeObject(LVM);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }
}

