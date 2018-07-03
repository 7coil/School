import java.util.Scanner;

public class MenuSystem {
  private Scanner scanner = new Scanner(System.in);

  MenuSystem() {
    this.mainMenu();
    this.scanner.close();
  }

  private int displayOptions(String[] options) {
    int selection = 0;
    boolean invalid = true;

    // Add a "Zeroth" Option
    System.out.println("0.\tQUIT");
    
    // Iterate over the options and list each one
    for (int i = 0; i < options.length; i++) {
      System.out.println((i + 1) + ".\t" + options[i]);
    }

    while (invalid) {
      // Read the user's option
      selection = scanner.nextInt();
      if (selection == 0) {
        System.out.println("Goodbye");
        System.exit(0);
      } else if (selection >= 0 && selection <= options.length) {
        invalid = false;
      } else {
        System.err.println("That was an invalid option. Try again");
      }
    }
    System.err.println(selection);
    return (selection - 1);
  }

  public void mainMenu() {
    System.out.println("Welcome to the TSH Terminal");
    String options[] = { "BACK", "GET", "POST", "DELETE" };
    int selection = displayOptions(options);
    switch (selection) {
    case 0:
      System.err.println("Can't go back!");
      break;
    case 1:
      System.err.println("GET");
      break;
    case 2:
      System.err.println("POST");
      break;
    case 3:
      System.err.println("DELETE");
      break;
    }

  }

  public static void main(String[] args) {
    new MenuSystem();
  }

}
