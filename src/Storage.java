import java.util.UUID;

public class Storage {
    private String name;
    private int storage;
    private String uuid;

    public Storage(String name, int storage){
        UUID u = UUID.randomUUID();
        uuid = u.toString();
        this.name = name;
        this.storage = storage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String toString(){
        return name + ": [" + uuid + "] [Total: " + storage + " GB]";
    }
}
