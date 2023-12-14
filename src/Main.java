import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner readInput = new Scanner(System.in);
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
            while (! pic.isBlank() && ! pic.endsWith(".jpg") && ! pic.endsWith(".png")) {
                System.out.println("Please press Enter to skip or provide a filename ending with .jpg or .png");
                pic = readInput.nextLine().strip();
            }

            System.out.println("Name: " + name);
            if (! pic.isEmpty()) System.out.println("Picture: " + pic);
            System.out.println("Is this correct? Enter y or Enter for yes or n for no. You can edit your profile in the future.");
            complete = readInput.nextLine().strip().toLowerCase();
            while (! complete.isBlank() && ! complete.equals("y") && ! complete.equals("n")) {
                System.out.print("Please enter y for yes or n for no. Is your name " + name);
                if (! pic.isBlank()) System.out.print(" and picture " + pic);
                System.out.println(" correct?");
                complete = readInput.nextLine().strip().toLowerCase();
            }
        }
        Profile admin = new Profile(name, pic);
        System.out.println("Account created: " + admin);
        System.out.println(new Profile("A", "B"));
        System.out.println(new Profile("A", "B"));
        System.out.println(new Profile("A", "B"));
        System.out.println(new Profile("A", "B"));
        System.out.println(admin);

//        ProfileManager manager = new ProfileManager();
//        manager.setAdmin(admin);
//        manager.addUser(admin);
//
//        System.out.println("Welcome, " + admin.getName() + ". What would you like to do?");

        //1. Modify my profile -> name, pic, status, ...
        //2. Add another user
        //3. View all users -> add friend
        //3. View another user and their friends -> add friend
        //2. View all my friends -> remove friend?
        //3. View all my friends' friends -> add friend / remove friend?
        //4. Switch accounts
        //5. Delete a user's account -> Warning: this will permanently delete user + name. Are you sure? Y/N
        //5. Log out -> Warning: this will end your session and delete all data. Are you sure you want to exit? Y/N
    }
}
