import java.io.Serializable;

public class Drive extends Storage implements Serializable {
    public Drive(String name, int storage){
        super(name, storage);
    }

    @Override
    public String toString(){
        return super.toString() + "[" + super.getStorage() + " GB]";
    }
}