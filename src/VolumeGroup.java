import java.util.ArrayList;

public class VolumeGroup extends Storage{
    private ArrayList<PhysicalVolume> physicalVolumes;
    private ArrayList<LogicalVolume> logicalVolumes;
    private int available;

    public VolumeGroup(String name, PhysicalVolume physicalVolume){
        super(name, 0);
        addPhysicalVolume(physicalVolume);
    }

    public ArrayList<PhysicalVolume> getPhysicalVolumes() {
        return physicalVolumes;
    }

    public void setPhysicalVolumes(ArrayList<PhysicalVolume> physicalVolumes) {
        this.physicalVolumes = physicalVolumes;
    }

    public ArrayList<LogicalVolume> getLogicalVolumes() {
        return logicalVolumes;
    }

    public void setLogicalVolumes(ArrayList<LogicalVolume> logicalVolumes) {
        this.logicalVolumes = logicalVolumes;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void addPhysicalVolume(PhysicalVolume pv){
        physicalVolumes.add(pv);
        super.setStorage(super.getStorage() + pv.getStorage());
        available += pv.getStorage();
    }

    public void addLogicalVolume(LogicalVolume lv){
        logicalVolumes.add(lv);
        available -= lv.getStorage();
        lv.setVolumeGroupName(super.getName());
    }
}
