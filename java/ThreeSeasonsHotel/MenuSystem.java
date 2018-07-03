import java.util.Scanner;

public class MenuSystem {
  private Scanner scanner = new Scanner(System.in);

  MenuSystem() {
    this.mainMenu();
    this.scanner.close();
    System.err.println("Goodbye!");
  }

  private int displayOptions(String[] options) {
    int selection = 0;
    boolean invalid = true;

    while (invalid) {
      // Iterate over the options and list each one
      for (int i = 0; i < options.length; i++) {
        int iLength = Integer.toString(i).length();
        System.out.print(i);
        // Make the length of the stuff before the text 8 characters long.
        // If a number is two digits long, there will be only 6 dots to match.
        for (int j = 0; j < (8 - iLength); j++) {
          System.out.print(".");
        }
        System.out.println(options[i]);
      }

      // Read the user's option
      selection = scanner.nextInt();
      if (selection >= 0 && selection <= options.length) {
        invalid = false;
      } else {
        System.err.println("That was an invalid option. Try again");
      }
    }
    return selection;
  }

  public void mainMenu() {
    String options[] = { "Quit", "Fetch", "Insert", "Delete" };
    
    while (true) {
      System.out.println("Welcome to the TSH Terminal");
      // Grab a selection33ewd2
      int selection = displayOptions(options);
      switch (selection) {
      case 0:
        return;
      case 1:
        System.err.println("GET");
        getMenu();
        break;
      case 2:
        System.err.println("POST");
        break;
      case 3:
        System.err.println("DELETE");
        break;
      }
    }
  }

  public void getMenu() {
    String options[] = { "Back to main menu", "Room", "Customer", "Booking" };

    while (true) {
      System.out.println("Fetch what?");
      int selection = displayOptions(options);
      switch (selection) {
      case 0:
        return;
      case 1:
        System.err.println("Room");
        break;
      case 2:
        System.err.println("Customer");
        break;
      case 3:
        System.err.println("Booking");
        break;
      }
    }
  }

  public static void main(String[] args) {
    new MenuSystem();
  }

}
