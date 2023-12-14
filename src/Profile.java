public class Profile {
    public enum Status {ONLINE, OFFLINE}
    private static int numProfilesConstructed = 0;
    private int id; //use to uniquely identify profiles so accounts with the same name can be created?
    private String name;
    private String picture;
    private Status status;

    public Profile(String name, String pic) {
        numProfilesConstructed++;
        id = numProfilesConstructed;
        this.name = name;
        this.picture = pic;
        this.status = Status.ONLINE;
    }

    public String toString() {
        return id + " " + name + " " + picture + " " + status;
    }
}
