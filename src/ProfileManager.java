import ADTPackage.QueueInterface;
import GraphPackage.Vertex;
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
        for (Profile friend : profile.getFriends()) {
            removeFriendship(friend, profile);
        }
        allProfiles.remove(profile.getId());
        profileGraph.remove(profile);

    }

    /**
     * Returns the profile with the given id, or null if user doesn't exist
     * @param id the id of the profile, should be a number
     * @return the profile with the given id, or null if profile doesn't exist
     */
    public Profile getUser(int id) {
        return allProfiles.getValue(id);
    }

    /** Connects two friends' profiles together in the graph with an edge, and adds the new friend to their profile 
    * @param friend1 the first profile to be connected
    * @param friend2 the second profile to be connected
    * @return true if the friendship was successfully added, else false
    */
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

    /** Disconnects two friends' profiles, removing their friendship, and removing the friend from their profile
    * @param friend1 the first profile to be disconnected
    * @param2 friend2 the second profile to be disconnected
    */
    public void removeFriendship(Profile friend1, Profile friend2) {
        if (friend1 != null && friend2 != null) {
            profileGraph.removeEdge(profileGraph.getVertex(friend1), profileGraph.getVertex(friend2));
            profileGraph.removeEdge(profileGraph.getVertex(friend2), profileGraph.getVertex(friend1));
            friend1.removeFriend(friend2);
            friend2.removeFriend(friend1);
        }
    }
}
