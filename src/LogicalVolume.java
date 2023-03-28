public class LogicalVolume extends Storage{
    private String volumeGroupName;

    public LogicalVolume(String name, int storage){
        super(name, storage);
    }

    public String getVolumeGroupName() {
        return volumeGroupName;
    }

    public void setVolumeGroupName(String volumeGroupName) {
        this.volumeGroupName = volumeGroupName;
    }
}
