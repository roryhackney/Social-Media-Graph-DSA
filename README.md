# Project 4: Social Media Network
**Purpose:** To practice and demonstrate proficiency in creating, manipulating and utilizing graphs as well as previously studied Abstract Data Types (ADTs) / data structures, including linked lists, dictionaries / maps, heaps, (priority) queues, and stacks, using the Java language.

## Summary
For this project, I worked with [https://github.com/Jofleming](Jordan) to design and implement an application that maintains the data for a fictional social media network. Each user in the network has a profile containing their name, optional profile picture, current status, and a list of their friends.

The application provides a menu that allows a user to:
* Join the network (by creating a profile)
* Modify their profile
* Display a list of all the profiles in the network
* Add a friend (from the displayed list)
* See a list of their friends
* See a list of their friend's friends
* Leave the network (by removing their profile)
* Change the current user
* Add additional new user profiles (admin privilege, for demoing)
* Remove user profiles (admin privilege, for demoing)
* End the program, resetting the graph to empty

## Part 1: UML Class and Object Diagrams
We created a UML class diagram to define various interfaces, classes, methods, and fields, for reference while implementing the application.
We also created object diagrams in order to clarify the relationships between various data structures and the data within them, such as profiles.

## Part 2: Java Implementation
The undirected graph inherits the directed graph, which is actually the implementation of a LinkedDictionary. There are layers and layers of data structures here.

The undirected graph holds the new profiles but unless they are friends, there will be no edge between them. That means you could add 10 profiles and it would be a graph with no edges. You cannot traverse a graph with no edges. This is why there must be a list of existing profiles in order to display them or traverse them.

When a profile is deleted, it is removed from all relevant data structures, including the graph, main list of profiles, and friend lists.

### Packages, Interfaces and Classes

    * Profile
       * Holds information about each user
    * ProfileManager
        * Manages the profiles in the undirected graph / social network
    * Main
        * Where you run the experiment
* ADTPackage
    * DictionaryInterface
        * LinkedDictionary
    * ListInterface
        * ListWithIteratorInterface
            * LinkedListWithIterator
    * MaxHeapInterface
        * MaxHeap
    * PriorityQueueInterface
        * HeapPriorityQueue
        * PriorityQueue
    * QueueInterface
        * LinkedQueue
    * StackInterface
        * LinkedStack
* GraphPackage
    * GraphInterface
        * BasicGraphInterface
        * GraphAlgorithmsInterface
        * DirectedGraph
            * UndirectedGraph (extends DirectedGraph)
    * VertexInterface
        * Vertex

### Profile class is able to:
* Create a new profile with a picture (optional), name, status and a list of friendProfiles (constructor)
* Get the optional profile picture
* Set and get the name
* Set and get the status
* Add or remove a friend
* Print out all the details of the profile including the list of friends

### ProfileManager is able to:
* Create an undirected graph
* Add a profile to an undirected graph
* Connect two vertexes on the graph (create a friendship between profiles)
* Display all of the profiles
* Display each profile's information and friends (through a breadth first traversal)
* Remove a profile

### Layout
![image demonstrating the desired functionality](img/SocialMediaLayout.JPG)

### Documentation
* In addition to this README, Javadoc comments are on each function to explain what they do, except where the Javadoc is inherited from the implemented interface.

## Output
When Main is run, the menu simulates a user interacting with the social media system. The user has admin rights to create new profiles, delete anyone's profile, etc, in order to demo the functionality of the application. On run, the user must create a profile, and the current user is stored in a variable. This allows the current user to be switched. The user interacts by typing things into their keyboard based on the displayed prompt.

1. Modify My Profile
2. Add Another User
3. View All Users and Add Friends
4. View User's Friends
5. View My Friends
6. View My Friends and Their Friends
7. Switch Accounts
8. Delete An Account
9. Log Out

![image](https://github.com/user-attachments/assets/d8382177-1820-4911-adfd-4694d662bd5a)

