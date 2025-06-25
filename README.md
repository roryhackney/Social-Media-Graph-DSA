# Project 4: Social Media Network
**Purpose:** To practice and demonstrate proficiency in creating, manipulating and utilizing graphs as well as previous Abstract Data Types (ADTs) and data structures, including linked lists, dictionaries/maps, heaps, queues, and stacks, using Java.

## Summary
For this project, I worked with [https://github.com/Jofleming](Jordan) to design and implement an application that maintains the data for a social media network. Each person in the network has a profile containing their name, optional profile picture, current status, and a list of their friends.

The application provides a menu that allows a user to:
1. Join the network (by creating a profile)
2. Modify their profile
3. Display a list of all the profiles in the network
4. Add a friend (from the displayed list)
5. See a list of their friends
6. See a list of their friend's friends
7. Leave the network (by removing their profile)
8. Change the current user
9. Add additional new user profiles (admin privilege)
10. Remove user profiles (admin privilege)

### Part 1: UML Class and Object Diagrams
We created a UML class diagram to define various interfaces, classes, methods, and fields, for reference while implementing the application.
We also created object diagrams in order to clarify the relationships between various data structures and the data within them, such as profiles.

### Part 2: Java Implementation
Packages, Interfaces and Classes

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

### Profile is able to:
* Create a new profile with a picture (optional), name, status and a list of friendProfiles.
* Retrieve the profile picture (optional)
* Set and get the name
* Set and get the status
* Add a friend
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

### Output
When Main is run, the menu simulates a user interacting with the social media system. The user will have admin rights to create new profiles, delete anyone's profile, etc. On run, the user must create a profile, and the current user is stored in a variable. This allows the current user to be switched. The user interacts by typing things in.

* Modify profile
* View all profiles (and add a friend from the list)
* Add a friend
* View your friend list
* View your friend's friend list
* Delete a profile
* Add another profile
* Switch the current user
* Logout (end program)

### Extra Credit

I will award a variable amount of extra credit if the undirected graph is implemented with weights where an edge has a weight of 1 if they are a Close Friend and 0 if they are not a close friend. Create a method to display the close friend list. Please indicate in a comment that you added this functionality and test it by adding an option to see close friends list in the main menu.

The undirected graph inherits the directed graph, which is actually the implementation of a LinkedDictionary. There are layers and layers of data structures here.
The undirected graph holds the new profiles but unless they are friends, there will be no edge between them. That means you could add 10 profiles and it would be a graph with no edges. You cannot traverse a graph with no edges. This is why there must be a list of existing profiles in order to display them or traverse them.
When a profile is deleted, it is removed from all relevant data structures, including the graph, main list of profiles, and friend lists.

### Some other items of importance:
* Use javadoc comments on your functions to explain what they do. You do not have to do this in the class implementations where there is an interface, you can use @inheritDoc.
* Be sure your code is clean, legible and easy to read.
* Feel free to go above and beyond!  Add new methods and functionality, test in different ways in addition to what you are required to do, be creative! The top grades go to those who go above and beyond. Strive for excellence.
* You are not required to create unit tests but are welcome to do so if you wish.
* Including the runtime as a comment on algorithms with loops in them will be seen as going above and beyond as well.

## Format Requirements
Please ensure you follow the structure as listed above. Use IntelliJ to do your work. Use Javadoc comments where appropriate.
Ensure that your UML diagram submission is in PDF or an image file format.

## Assessment
Please see the Rubric attached for assessment.

## Deliverables
* Please add your UML documents to your Git repository. I will look for them there.
* Submit a link to your Git repository. Make sure to add me as a collaborator, so I can see your code.
* Each partner should submit a link. This is to ensure both partners don't forget to write a reflection of their partnership in Canvas.
