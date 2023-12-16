import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.Scanner;
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
            System.out.println("3\tView All Users and Add Friends");
            //4. View another user and their friends -> add friend
            System.out.println("4\tView User's Friends");
            //5. View all my friends
            System.out.println("5\tView My Friends");
            //6. View all my friends' friends
            System.out.println("6\tView My Friends and Their Friends");
            //7. Switch accounts
            System.out.println("7\tSwitch Accounts");
            //8. Delete a user's account -> Warning: this will permanently delete user + name. Are you sure? Y/N
            System.out.println("8\tDelete An Account");
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
                    System.out.println(manager.displayProfiles());
                    System.out.println("That's all the current users. Would you like to add a friend from the list?");
                    System.out.println("Enter y or Enter for yes, otherwise no.");
                    String addFriend = readInput.nextLine().strip().toLowerCase();
                    if (addFriend.isEmpty()) addFriend = "y";
                    addFriends(addFriend);
                    break;
                case "4":
                    viewProfileAndFriends();
                    break;
                case "5":
                    System.out.println(admin);
                    System.out.println(admin.printFriends());
                    break;
                case "6":
                    ArrayList<Profile> friends = admin.getFriends();
                    for (Profile friend : friends) {
                        System.out.println("Friend: " + friend);
                        System.out.println(friend.printFriends());
                    }
                    break;
                case "7":
                    String cont = "y";
                    while (cont.equals("y")) {
                        System.out.println("Which account would you like to switch to?");
                        while (!readInput.hasNextInt()) {
                            System.out.println("Please enter an ID (whole number)");
                            readInput.next();
                        }
                        int id = readInput.nextInt();
                        readInput.nextLine(); //remove /n at end of this line
                        Profile newAdmin = manager.getUser(id);
                        if (newAdmin == null) {
                            System.out.println("There is no profile with that id.");
                        } else if (newAdmin == admin) {
                            System.out.println("You are already logged in to that account.");
                        } else {
                            admin = newAdmin;
                            System.out.println("You are now logged in as " + admin);
                        }
                        System.out.println("Would you like to switch to a different account?");
                        cont = "";
                        while (! cont.equals("y") && ! cont.equals("n")) {
                            System.out.println("Enter y for yes, n for no.");
                            cont = readInput.nextLine().strip().toLowerCase();
                        }
                    }
                    break;
                case "8":
                    System.out.println("This will permanently delete an account. Are you sure? Enter y for yes.");
                    String delete = readInput.nextLine().strip().toLowerCase();
                    if (delete.equals("y")) {
                        System.out.println("Which account do you want to delete?");
                        while (!readInput.hasNextInt()) {
                            System.out.println("Please enter an ID (whole number)");
                            readInput.next();
                        }
                        int id = readInput.nextInt();
                        readInput.nextLine(); //remove /n at end of this line
                        Profile del = manager.getUser(id);
                        if (del == null) {
                            System.out.println("There is no profile with that id.");
                        } else if (del == admin) {
                            System.out.println("You can't delete the account you're logged into. Try switching accounts first.");
                        } else {
                            System.out.println("You are about to delete " + del + ". Are you sure? Enter y for yes.");
                        }
                        delete = readInput.nextLine().strip().toLowerCase();
                        if (delete.equals("y")) {
                            manager.removeProfile(del);
                            System.out.println(del + " has been successfully deleted.");
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

    public static void viewProfileAndFriends() {
        String input = "";
        while (input.equals("y") || input.isEmpty()) {
            System.out.println("Whose profile would you like to view?");
            while (! readInput.hasNextInt()) {
                System.out.println("Please enter an ID (whole number)");
                readInput.next();
            }
            int id = readInput.nextInt();
            readInput.nextLine(); //remove /n at end of this line

            Profile user = manager.getUser(id);
            if (user == null) {
                System.out.println("User #" + id + " does not exist.");
            } else {
                System.out.println(user);
                System.out.println(user.printFriends());
                System.out.println("That's all their friends.");
            }
            System.out.println("Would you like to view another profile?");
            input = "x";
            while (!input.isEmpty() && !input.equals("y") && !input.equals("n")) {
                System.out.println("Enter y or Enter for yes, n for no.");
                input = readInput.nextLine().strip();
            }
        }
    }
}
