public class PhysicalVolume extends Storage {
    private Drive drive;
    private String VolumeGroupName;

    public PhysicalVolume(String name, Drive drive){
        super(name, drive.getStorage());
        this.drive = drive;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public String getVolumeGroupName() {
        return VolumeGroupName;
    }

    public void setVolumeGroupName(String volumeGroupName) {
        VolumeGroupName = volumeGroupName;
    }

    @Override
    public String toString(){
        String string = super.toString() + "[" + super.getStorage() + " GB]";
        if (VolumeGroupName != null) string += "[" + VolumeGroupName + "]";
        return string;
    }
}
