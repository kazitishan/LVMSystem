import java.util.ArrayList;

public class VolumeGroup extends Storage{
    private ArrayList<PhysicalVolume> physicalVolumes;
    private ArrayList<LogicalVolume> logicalVolumes;
    private int available;

    public VolumeGroup(String name, PhysicalVolume physicalVolume){
        super(name, 0);
        physicalVolumes = new ArrayList<PhysicalVolume>();
        logicalVolumes = new ArrayList<LogicalVolume>();
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
    }

    @Override
    public String toString() {
        String allPVs = "";
        for (PhysicalVolume pv : physicalVolumes){
            allPVs += pv.getName() + ",";
        }
        allPVs = allPVs.substring(0, allPVs.length() - 1);
        return super.toString() + "total:[" + super.getStorage() + " GB] available:[" + available + " GB] [" + allPVs + "]";
    }
}
