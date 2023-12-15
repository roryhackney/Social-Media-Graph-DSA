import java.util.Scanner;
import java.util.List;
public class Main {
    private static ProfileManager manager;
    private static Scanner readInput;
    private static Profile admin;

    public static void main(String[] args) {
        readInput = new Scanner(System.in);

        admin = createAccount();
        System.out.println("Account created: " + admin);

//        System.out.println(new Profile("A", "B"));
//        System.out.println(new Profile("A", "B"));
//        System.out.println(new Profile("A", "B"));
//        System.out.println(new Profile("A", "B"));
//        System.out.println(admin);

        manager = new ProfileManager();
        manager.addProfile(admin);

        String selection = "";
        while (! selection.equals("9")) {
            System.out.println("Welcome, " + admin.getName() + ". What would you like to do?");
            System.out.println("1\tModify My Profile");
            System.out.println("2\tAdd Another User");
            //3. View all users -> add friend
            System.out.println("3\tView All Users");
            //4. View another user and their friends -> add friend
            System.out.println("4\tView Another User's Friends");
            //5. View all my friends
            //6. View all my friends' friends
            //7. Switch accounts
            //8. Delete a user's account -> Warning: this will permanently delete user + name. Are you sure? Y/N
            System.out.println("9\tLog Out");
            selection = readInput.nextLine().strip();
            switch (selection) {
                case "1":
                    alterAccount(admin);
                    break;
                case "2":
                    Profile newUser = createAccount();
                    manager.addProfile(newUser);
                    break;
                case "3":
                    manager.displayProfiles();
                    System.out.println("That's all the current users. Would you like to add a friend from the list?");
                    System.out.println("Enter y or Enter for yes, otherwise no.");
                    String addFriend = readInput.nextLine().strip().toLowerCase();
                    if (addFriend.isEmpty()) addFriend = "y";
                    addFriends(addFriend);
                    break;
                case "4":
                    String input = "y";
                    while (input.equals("y")) {
                        System.out.println("Whose profile would you like to view?");
                        while (!readInput.hasNextInt()) {
                            System.out.println("Please enter an ID (whole number)");
                            readInput.next();
                        }
                        int id = readInput.nextInt();
                        readInput.nextLine(); //remove /n at end of this line

                        Profile user = manager.getUser(id);
                        System.out.println(user);
                        System.out.println("Friends:");
                        List<Profile> friends = user.getFriends();
                        for (Profile friend : friends) {
                            System.out.println(friend);
                        }
                        System.out.println("That's all their friends.");
                        System.out.println("Would you like to view another profile?");
                        System.out.println("Enter y or Enter for yes, n for no.");
                        input = readInput.nextLine().strip();
                        while (!input.isEmpty() && !input.equals("y") && !input.equals("n")) {
                            System.out.println("Enter y or Enter for yes, n for no.");
                            input = readInput.nextLine().strip();
                        }
                    }
                    break;

                case "9":
                    System.out.println("This will end your session and delete all users. Are you sure you want to exit?");
                    String exit = "";
                    while (! exit.equals("y") && ! exit.equals("n")) {
                        System.out.println("Enter y for yes, n for no.");
                        exit = readInput.nextLine().strip().toLowerCase();
                    }
                    if (exit.equals("n")) selection = "";
                    else {
                        System.out.println("D E L E T I N G . . .");
                        System.out.println("Have a nice day!");
                    }
                    break;
                default:
                    System.out.println("Please enter a number from 1-9.");
            }
        }
    }

    public static void alterAccount(Profile profile) {
        System.out.println("Thank you for keeping your profile up to date.");

        String done = "n";
        while (done.equals("n")) {
            System.out.println("Enter your new name or press Enter to skip.");
            String name = readInput.nextLine().strip();

            System.out.println("Enter your new picture or press Enter to use the default for all users.");
            String pic = readInput.nextLine().strip();
            if (pic.isEmpty()) {
                pic = "default.jpg";
            }

            String online = "";
            while (!online.equals("y") && !online.equals("n")) {
                System.out.println("Would you like your account to be seen as ONLINE (y) or OFFLINE (n)?");
                online = readInput.nextLine().strip().toLowerCase();
            }

            if (!name.isEmpty()) {
                System.out.println("Name: " + name);
            } else {
                System.out.println(profile.getName());
            }
            System.out.println("Picture: " + pic);
            if (online.equals("n")) {
                System.out.println("Status: OFFLINE");
            } else {
                System.out.println("Status: ONLINE");
            }

            System.out.println("Is this correct? Enter y or Enter for yes, n for No.");
            done = readInput.nextLine().strip();
            while (!done.equals("n") && !done.equals("y") && !done.isEmpty()) {
                System.out.println("Enter y or Enter for yes, n for No.");
                done = readInput.nextLine().strip();
            }
        }
    }

    public static Profile createAccount() {
        System.out.println("Welcome to Social Media, the Social Media Site for Social People!");
        System.out.println("You must  J O I N   U S  to continue.");

        String complete = "n";
        String name = "";
        String pic = "";
        while (complete.equals("n")) {
            System.out.println("What is your name?");
            name = readInput.nextLine().strip();
            while (name.isBlank()) {
                System.out.println("Please enter your name.");
                name = readInput.nextLine().strip();
            }

            System.out.println("To upload a picture, enter the path, eg 'human.jpg' or 'human.png'");
            System.out.println("Or press Enter to use the default profile picture assigned to all users.");
            pic = readInput.nextLine().strip();
            while (! pic.isEmpty() && ! pic.endsWith(".jpg") && ! pic.endsWith(".png")) {
                System.out.println("Please press Enter to skip or provide a filename ending with .jpg or .png");
                pic = readInput.nextLine().strip();
            }
            if (pic.isEmpty()) {pic = "default.jpg";}

            System.out.println("Name: " + name);
            System.out.println("Picture: " + pic);
            System.out.println("Is this correct? Enter y or Enter for yes or n for no. You can edit your profile in the future.");
            complete = readInput.nextLine().strip().toLowerCase();
            while (! complete.isBlank() && ! complete.equals("y") && ! complete.equals("n")) {
                System.out.println("Please enter y for yes or n for no.");
                System.out.println("Is your name " + name + " and picture " + pic + " correct?");
                complete = readInput.nextLine().strip().toLowerCase();
            }
        }
        return new Profile(name, pic);
    }

    public static void addFriends(String addFriend) {
        while (addFriend.equals("y")) {
            System.out.println("Enter the ID of the friend you want to add.");
            while (! readInput.hasNextInt()) {
                System.out.println("Please enter an ID (whole number)");
                readInput.next();
            }
            int id = readInput.nextInt();
            readInput.nextLine(); //remove /n at end of this line

            Profile friend = manager.getUser(id);

            if (friend == null) System.out.println("User #" + id + " does not exist.");
            else if (friend.getId() == admin.getId()) {
                System.out.println("That's your ID. Make friends with someone else.");
            } else if (! manager.addFriendship(admin, friend)) {
                System.out.println("You are already friends with user #" + id + ": " + friend.getName());
            } else {
                System.out.println("User # " + id + ": " + friend.getName() + " has been added as your friend.");
            }

            System.out.println("Would you like to add anyone else? Enter y or Enter for yes, otherwise no.");
            addFriend = readInput.nextLine().strip().toLowerCase();
            if (addFriend.isEmpty()) addFriend = "y";
        }
    }
}
