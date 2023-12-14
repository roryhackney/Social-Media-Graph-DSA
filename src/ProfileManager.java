import ADTPackage.QueueInterface;
import GraphPackage.UndirectedGraph;
import src.Profile;

import java.util.ArrayList;

/**
 * ProfileManager class to manage Profiles for social network
 * @author Rory Hackney
 * @author Jordan Fleming
 * @version 1.0
 * @since 12/13/2023
 */
public class ProfileManager {
    /**
     * UndirectedGraph to hold Profile objects
     */
    private UndirectedGraph<Profile> profileGraph;
    private ArrayList<Profile> allProfiles;

    /**
     * Sole Constructor for ProfileManager class.
     */
    public ProfileManager() {
        profileGraph = new UndirectedGraph<Profile>();
        allProfiles = new ArrayList<Profile>();
    }

    /**
     * Adds a given profile to the underlying graph.
     * @param profile       Profile to be added
     */
    public void addProfile(Profile profile) {
        profileGraph.addVertex(profile);
        allProfiles.add(profile);
    }

    /**
     * Adds a connection between two profiles
     * @param currentProfile    Starting profile to add edge to.
     * @param friendProfile     End profile that the starting profile is linked to
     */
    public void addConnection(Profile currentProfile, Profile friendProfile) {
        profileGraph.addEdge(currentProfile, friendProfile);
        currentProfile.addFriend(friendProfile);
    }

    /**
     * Gives a string representation of all profiles currently in profileGraph.
     * @param profile       The profile to begin traversal from.
     * @return              String of all profiles in graph.
     */
    public String displayProfiles(Profile profile) {
        StringBuilder profilesString = new StringBuilder();
        profilesString.append("All profiles: \n");
        for (Profile currentProfile : allProfiles) {
            profilesString.append(currentProfile.toString() + "\n");
        }
        return  profilesString.toString();
    }

    /**
     * Gives a string of all profiles and all their connected friends.
     * @return              String representation of all profiles with their associated edges.
     */
    public String displayProfilesAndFriends() {
        StringBuilder profilesString = new StringBuilder();
        profilesString.append("All profiles: \n");
        for (Profile currentProfile : allProfiles) {
            profilesString.append(currentProfile.toString() + "\n");
            profilesString.append("Friends of " + currentProfile.getName() + ":\n");
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
        while (!breadthFirst.isEmpty()) {
            Profile currentProfile = breadthFirst.dequeue();
            profilesString.append(currentProfile.toString() + "\n");
            profilesString.append("Friends of " + currentProfile.getName() + ":\n");
            profilesString.append(currentProfile.printFriends());
        }
        return  profilesString.toString();
    }


    /**
     * Removes a profile from the graph
     * @param profile       Profile to be removed.
     */
    public void removeProfile(Profile profile) {
        profileGraph.remove(profile);
    }
}
