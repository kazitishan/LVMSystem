import java.io.Serializable;
import java.util.ArrayList;

public class LogicalVolumeManager implements Serializable {
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

    // installs a new drive given a name and amount of storage
    public String installDrive(String name, int storage){
        driveList.add(new Drive(name, storage));
        return "Drive " + name + " installed";
    }

    // creates a new physical volume given an associated drive
    public String createPhysicalVolume(String name, String driveName){
        Drive associatedDrive = new Drive("", 0);
        for (Drive drive : driveList){
            if (drive.getName().equals(driveName)) associatedDrive = drive;
        }
        pvList.add(new PhysicalVolume(name, associatedDrive));
        return name + " created";
    }

    // creates a new volume group given a name and physical volume
    public String createVolumeGroup(String name, String physicalVolumeName){
        PhysicalVolume firstPV = new PhysicalVolume("", new Drive("", 0));
        for (PhysicalVolume pv : pvList){
            if (pv.getName().equals(physicalVolumeName)) firstPV = pv;
        }
        firstPV.setVolumeGroupName(name);
        vgList.add(new VolumeGroup(name, firstPV));
        return name + " created";
    }

    // adds a physical volume to a volume group
    public String extendVolumeGroup(String volumeGroupName, String physicalVolumeName){
        PhysicalVolume physicalVolume = new PhysicalVolume("", new Drive("", 0));
        VolumeGroup volumeGroup = new VolumeGroup("", physicalVolume);
        int index = 0;
        for (VolumeGroup vg : vgList){
            if (vg.getName().equals(volumeGroupName)) volumeGroup = vg;
            index = vgList.indexOf(vg);
        }
        for (PhysicalVolume pv : pvList){
            if (pv.getName().equals(physicalVolumeName)) physicalVolume = pv;
        }
        physicalVolume.setVolumeGroupName(volumeGroupName);
        volumeGroup.addPhysicalVolume(physicalVolume);
        vgList.set(index, volumeGroup);
        return physicalVolumeName + " added to " + volumeGroupName;
    }

    // creates a new logical volume given a name and storage
    public String createLogicalVolume(String name, int storage, String volumeGroupName){
        for (LogicalVolume lv : lvList){
            if (lv.getName().equals(name)) return name + " creation failed";
        }
        VolumeGroup volumeGroup = new VolumeGroup("", new PhysicalVolume("", new Drive("", 0)));
        int index = 0;
        for (VolumeGroup vg : vgList){
            if (vg.getName().equals(volumeGroupName)) volumeGroup = vg;
            index = vgList.indexOf(vg);
        }
        if (storage > volumeGroup.getStorage()) return name + " creation failed";
        LogicalVolume logicalVolume = new LogicalVolume(name, storage);
        logicalVolume.setVolumeGroupName(volumeGroupName);
        volumeGroup.addLogicalVolume(logicalVolume);
        vgList.set(index, volumeGroup);
        lvList.add(logicalVolume);
        return name + " created";
    }

    public void listDrives(){
        for (Drive drive : driveList){
            System.out.println(drive.toString());
        }
    }

    public void listPVs(){
        for (PhysicalVolume pv : pvList){
            System.out.println(pv.toString());
        }
    }

    public void listVGs(){
        for (VolumeGroup vg : vgList){
            System.out.println(vg.toString());
        }
    }

    public void listLVs(){
        for (LogicalVolume lv : lvList){
            System.out.println(lv.toString());
        }
    }

    public void cmd(String input){
        String method = input;
        String first = "";
        String second = "";
        if (input.contains(" ")){
            method = input.substring(0, input.indexOf(" "));
            input = input.substring(input.indexOf(" ") + 1);
            first = input.substring(0, input.indexOf(" "));
            input = input.substring(input.indexOf(" ") + 1);
            if (input.contains(" ")){
                second = input.substring(0, input.indexOf(" "));
                input = input.substring(input.indexOf(" ") + 1);
            }
            else second = input.substring(0, input.length());
        }

        if (method.equals("install-drive")){
            int storage = Integer.parseInt(second.substring(0, second.length() - 1));
            System.out.println(installDrive(first, storage));
        }
        else if (method.equals("pvcreate")){
            System.out.println(createPhysicalVolume(first, second));
        }
        else if (method.equals("vgcreate")){
            System.out.println(createVolumeGroup(first, second));
        }
        else if (method.equals("vgextend")){
            System.out.println(extendVolumeGroup(first, second));
        }
        else if (method.equals("lvcreate")){
            int storage = Integer.parseInt(second.substring(0, second.length() - 1));
            System.out.println(createLogicalVolume(first, storage, input));
        }
        else if (method.equals("list-drives")){
            listDrives();
        }
        else if (method.equals("pvlist")){
            listPVs();
        }
        else if (method.equals("vglist")){
            listVGs();
        }
        else if (method.equals("lvlist")){
            listLVs();
        }
    }



}
