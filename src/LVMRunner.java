public class LVMRunner {
    public static void main(String[] args) {
        PhysicalVolume pv = new PhysicalVolume("Hard Drive", new Drive("drive", 100));
        System.out.println(pv.toString());
    }
}
