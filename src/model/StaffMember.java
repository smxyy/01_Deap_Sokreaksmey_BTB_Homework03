package model;

public abstract class StaffMember {
    protected int id;
    protected String name;
    protected String address;
    private static int instanceCount = 0;
    public StaffMember(){
        id = 0;
        name = "";
        address = "";
        instanceCount++;
    }
    public StaffMember(int id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
        instanceCount++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    @Override
    public String toString() {
        return "StaffMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public abstract double pay();
}
