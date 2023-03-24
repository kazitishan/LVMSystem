public class PhysicalVolume extends Storage {
    Drive drive;

    public PhysicalVolume(String name, Drive drive){
        super(name, drive.getStorage());
        this.drive = drive;
    }
}
