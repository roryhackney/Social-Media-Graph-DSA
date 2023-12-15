import ADTPackage.QueueInterface;
import GraphPackage.UndirectedGraph;
import java.util.Iterator;
import ADTPackage.UnsortedLinkedDictionary;
/**
 * ProfileManager class to manage Profiles for social network
 * @author Rory Hackney
 */
public class ProfileManager {
    /** UndirectedGraph to hold Profile objects*/
    private UndirectedGraph<Profile> profileGraph;
    /** Dictionary that holds all profiles searchable by ID */
    private UnsortedLinkedDictionary<Integer, Profile> allProfiles;

    /**
     * Sole Constructor for ProfileManager class.
     */
    public ProfileManager() {
        profileGraph = new UndirectedGraph<>();
        allProfiles = new UnsortedLinkedDictionary<>();
    }

    /**
     * Adds a new Profile to the graph/list of users
     * @param profile the Profile to be added, will not be added if null
     */
    public void addProfile(Profile profile) {
        if (profile != null) {
            profileGraph.addVertex(profile);
            allProfiles.add(profile.getId(), profile);
        }
    }

    /**
     * Returns a String of all the Profiles in the graph/list.
     * @return a String representation of all the Profiles
     */
    public String displayProfiles() {
        StringBuilder profilesString = new StringBuilder();
        profilesString.append("Current users: ").append(profileGraph.getNumberOfVertices()).append("\n");
        Iterator<Profile> iter = allProfiles.getValueIterator();
        while (iter.hasNext()) {
            profilesString.append(iter.next()).append("\n");
        }
        return profilesString.toString();
    }

    /**
     * Gives a string of all profiles and all their connected friends.
     * @return              String representation of all profiles with their associated edges.
     */
    public String displayProfilesAndFriends() {
        StringBuilder profilesString = new StringBuilder();
        profilesString.append("All profiles: \n");
        Iterator<Profile> iter = allProfiles.getValueIterator();
        while (iter.hasNext()) {
            Profile currentProfile = iter.next();
            profilesString.append(currentProfile).append("\n");
            profilesString.append("Friends of ").append(currentProfile.getName()).append(":\n");
            profilesString.append(currentProfile.printFriends());
        }
        return  profilesString.toString();
    }

    /**
     * Gives a string of all profiles and all their connected friends using a Breadth First Traversal
     * @param profile       Profile to begin traversal from
     * @return              String representation of all profiles with their associated edges.
     */
    public String displayProfilesBreadthFirst(Profile profile) {
        StringBuilder profilesString = new StringBuilder();
        profilesString.append("All profiles: \n");
        QueueInterface<Profile> breadthFirst = profileGraph.getBreadthFirstTraversal(profile);
        return profilesString.toString();
    }

    /**
     * Removes a profile from the graph
     * @param profile       Profile to be removed.
     */
    public void removeProfile(Profile profile) {
        //TODO: remove edges between this profile's vertex and its friends
        //TODO: remove this profile from its friends profiles list of friends
        //TODO: remove the profile from the auxiliary dict
        //TODO: remove the profile's vertex from the graph
//        profileGraph.remove(profile);
    }

    /**
     * Returns the profile with the given id, or null if user doesn't exist
     * @param id the id of the profile, should be a number
     * @return the profile with the given id, or null if profile doesn't exist
     */
    public Profile getUser(int id) {
        return allProfiles.getValue(id);
    }

    public boolean addFriendship(Profile friend1, Profile friend2) {
        boolean result = false;
        if (friend1 != null && friend2 != null) {
            result = profileGraph.addEdge(friend1, friend2);
            if (result) {
                friend1.addFriend(friend2);
                friend2.addFriend(friend1);
            }
        }
        return result;
    }

    public boolean removeFriendship(Profile friend1, Profile friend2) {
        boolean result = false;
        if (friend1 != null && friend2 != null) {
            result = profileGraph.removeEdge(friend1, friend2);
        }
        if (result) {
            friend1.removeFriend(friend2);
            friend2.removeFriend(friend1);
        }
        return result;
    }
}
