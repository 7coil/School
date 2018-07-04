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

    // Loop until a valid option is selected
    while (invalid) {
      // Iterate over the options and list each one
      for (int i = 0; i < options.length; i++) {
        int iLength = Integer.toString(i).length();
        System.out.print(i + " ");
        // Make the length of the stuff before the text 8 characters long.
        // If a number is two digits long, there will be only 6 dots to match.
        for (int j = 0; j < (8 - iLength); j++) {
          System.out.print(".");
        }
        System.out.println(" " + options[i]);
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

    // Loop until the "quit" or "exit" command is ran
    while (true) {
      System.out.println("Welcome to the TSH Terminal");
      // Grab a selection33ewd2
      int selection = displayOptions(options);
      switch (selection) {
      case 0:
        return;
      case 1:
        this.getMenu(1);
        break;
      case 2:
        this.getMenu(2);
        break;
      case 3:
        this.getMenu(3);
        break;
      }
    }
  }

  public void getMenu(int type) {
    String options[] = { "Back to main menu", "Room", "Customer", "Booking" };

    // Loop until the "quit" or "exit" command is ran
    while (true) {
      System.out.println("What else would you like to do?");
      int selection = displayOptions(options);
      switch (selection) {
      case 0:
        return;
      case 1:
        // Based on the previous option, run the next subroutine
        switch (type) {
        case 1:
          this.fetchRoom();
          break;
        case 2:
          this.addRoom();
          break;
        case 3:
          this.deleteRoom();
          break;
        }
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

  public void printRooms() {
    var room = new Room();
    int roomLength = room.countRows();
    
    String print = "ID\troomNumber\tfloor\tcost\toccupancy\tsingleBed\tdoubleBed\ttripleBed\tqueenBed\tkingBed\ttwinBed\tensuite\tminibar\tjacuzzi\tseaView\tfamily\thoneyMoon\n";
    
    for (int i = 1; i <= roomLength; i++) {
      room.collectRecordById(i);
      print += room.getiID() + "\t";
      print += room.getiRoomNumber() + "\t\t";
      print += room.getiFloor() + "\t";
      print += room.getiCost() + "\t";
      print += room.getiOccupancy() + "\t\t";
      print += room.getSingleBed() + "\t\t";
      print += room.getDoubleBed() + "\t\t";
      print += room.getTripleBed() + "\t\t";
      print += room.getQueenBed() + "\t\t";
      print += room.getKingBed() + "\t";
      print += room.getTwinBed() + "\t";
      print += room.isbEnsuite() + "\t";
      print += room.isbMinibar() + "\t";
      print += room.isbJacuzzi() + "\t";
      print += room.isbSeaview() + "\t";
      print += room.isbHoneymoon() + "\t";
      print += room.isbFamily() + "\n";
    }
    
    System.out.println(print);
  }

  public void fetchRoom() {
    printRooms();
    System.err.println("fetchRoom");
  }

  public void addRoom() {
    System.err.println("addRoom");
  }

  public void deleteRoom() {
    printRooms();
    System.err.println("deleteRoom");
  }

  public static void main(String[] args) {
    new MenuSystem();
  }

}
