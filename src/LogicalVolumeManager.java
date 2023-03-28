import java.util.ArrayList;

public class LogicalVolumeManager {
    private static ArrayList<Drive> driveList;
    private static ArrayList<PhysicalVolume> pvList;
    private static ArrayList<VolumeGroup> vgList;
    private static ArrayList<LogicalVolume> lvList;

    public LogicalVolumeManager(){
        driveList = new ArrayList<Drive>();
        pvList = new ArrayList<PhysicalVolume>();
        vgList = new ArrayList<VolumeGroup>();
        lvList = new ArrayList<LogicalVolume>();
    }

    public String installDrive(String name, int storage){
        driveList.add(new Drive(name, storage));
        return "Drive " + name + " installed";
    }

    public String createPhysicalVolume(String name, String driveName){
        Drive associatedDrive = new Drive("", 0);
        for (Drive drive : driveList){
            if (drive.getName().equals(driveName)) associatedDrive = drive;
        }
        pvList.add(new PhysicalVolume(name, associatedDrive));
        return name + " created";
    }

    public String createVolumeGroup(String name, String physicalVolumeName){
        PhysicalVolume firstPV = new PhysicalVolume("", new Drive("", 0));
        for (PhysicalVolume pv : pvList){
            if (pv.getName().equals(physicalVolumeName)) firstPV = pv;
        }
        vgList.add(new VolumeGroup(name, firstPV));
        return name + " created";
    }

    public String extendVolumeGroup(String volumeGroupName, String physicalVolumeName){
        PhysicalVolume physicalVolume = new PhysicalVolume("", new Drive("", 0));
        VolumeGroup volumeGroup = new VolumeGroup("", physicalVolume);
        for (VolumeGroup vg : vgList){
            if (vg.getName().equals(volumeGroupName)) volumeGroup = vg;
        }
        for (PhysicalVolume pv : pvList){
            if (pv.getName().equals(physicalVolumeName)) physicalVolume = pv;
        }
        volumeGroup.addPhysicalVolume(physicalVolume);
        return physicalVolumeName + " added to " + volumeGroupName;
    }


}
