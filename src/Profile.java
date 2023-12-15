import java.util.ArrayList;

/**
 * Profile class to hold user info and picture.
 * @author Rory Hackney
 * @author Jordan Fleming
 * @version 1.0
 * @since 12/10/2023
 */
public class Profile {
    /**
     * Status enum to mark online or offline
     */
    public enum Status {ONLINE, OFFLINE}

    /**
     * Static int for number of profiles created so far to use for id.
     */
    private static int numProfilesConstructed = 0;
    /**
     * Holder variable for the id number of the profile.
     */
    private int id; //use to uniquely identify profiles so accounts with the same name can be created?
    /**
     * Name of the person who owns the profile.
     */
    private String name;
    /**
     * Picture to add to profile.
     */
    private String picture;
    /**
     * The current status of the user/profile.
     */
    private Status status;
    /**
     * ArrayList to hold all friends.
     */
    private ArrayList<Profile> friendProfiles;

    /**
     * Full constructor for Profile class.
     * @param name      String name to attach to profile
     * @param pic       String url of pic to add to profile.
     */
    public Profile(String name, String pic) {
        numProfilesConstructed++;
        id = numProfilesConstructed;
        this.name = name;
        this.picture = pic;
        this.status = Status.ONLINE;
        this.friendProfiles = new ArrayList<Profile>();
    }

    /**
     * Constructor for Profile class.
     * @param name      String name to attach to profile.
     */
    public Profile(String name) {
        numProfilesConstructed++;
        id = numProfilesConstructed;
        this.name = name;
        this.picture = "";
        this.status = Status.ONLINE;
        this.friendProfiles = new ArrayList<Profile>();
    }

    /**
     * Sets the name variable of the profile to a given name
     * @param name      String name to use to update profile.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name associated with the profile.
     * @return      String name currently attached to the profile.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the status of the profile to online or offline
     * @param status    The desired STATUS for the profile.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Returns the current status of the profile.
     * @return      Enum Status of the profile.
     */
    public Profile.Status getStatus() {
        return status;
    }

    /**
     * Adds a friend to the profiles list of friends.
     * @param friend      Profile of the friend to be added
     * @return            the Profile of the friend added.
     */
    public Profile addFriend(Profile friend) {
        friendProfiles.add(friend);
        return friend;
    }

    /**
     * Removes a friend from this Profile's list of friends
     * @param friend the friend to remove
     * @return true if the friend was removed, else false (may not be in list of friends)
     */
    public boolean removeFriend(Profile friend) {
        return friendProfiles.remove(friend);
    }

    /**
     * Returns a list of all the friends added to this profile.
     * @return      A string list of all the friends using their toString method
     */
    public String printFriends() {
        StringBuilder friendString = new StringBuilder();
        friendString.append("Friends List: \n");
        for (Profile friend : friendProfiles) {
            friendString.append(friend.toString()).append("\n");
        }
        return friendString.toString();
    }

    /**
     * Returns the picture url
     * @return      String associated with the picture of the profile
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Returns the id associated with the profile.
     * @return      int ID of the profile
     */
    public int getId() {
        return id;
    }

    /**
     * Returns a string with all the relevant profile data.
     * @return      String representation of the profile.
     */
    public String toString() {
        return "User ID: " + id + ", Name: " + name + ", Picture: " + picture + ", Status: " + status;
    }

    /**
     * Returns a copy of the list of friends for this profile
     * @return a copy of the list of friends for this profile
     */
    public ArrayList<Profile> getFriends() {
        //copy so can't alter list of friends for this profile
        return new ArrayList<Profile>(friendProfiles);
    }
}
