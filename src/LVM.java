import java.util.UUID;

public class LVM {
    private String name;
    private String uuid;

    public LVM(String name){
        UUID u = UUID.randomUUID();
        uuid = u.toString();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


}
