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

  private boolean nextBoolean() {
    String input = scanner.next();
    if (input.toLowerCase().charAt(0) == 'y') return true;
    return false;
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
    Room room = new Room();
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
    Room room = new Room();

    // Set the Room ID to be the next available autoincrement ID
    room.setiID(room.countRows() + 1);

    // Ask user for every single field
    System.out.println("Please enter the Room Number");
    room.setiRoomNumber(scanner.nextInt());
    System.out.println("Please enter the Floor");
    room.setiFloor(scanner.nextInt());
    System.out.println("Please enter the Cost");
    room.setiCost(scanner.nextInt());
    System.out.println("Please enter the Occupancy");
    room.setiOccupancy(scanner.nextInt());
    System.out.println("Please enter the number of single bed(s) available");
    room.setSingleBed(scanner.nextInt());
    System.out.println("Please enter the number of double bed(s) available");
    room.setDoubleBed(scanner.nextInt());
    System.out.println("Please enter the number of triple bed(s) available");
    room.setTripleBed(scanner.nextInt());
    System.out.println("Please enter the number of queen bed(s) available");
    room.setQueenBed(scanner.nextInt());
    System.out.println("Please enter the number of king bed(s) available");
    room.setKingBed(scanner.nextInt());
    System.out.println("Please enter the number of twin bed(s) available");
    room.setTwinBed(scanner.nextInt());
    System.out.println("Please enter Y/N if the room is: Ensuite");
    room.setbEnsuite(this.nextBoolean());
    System.out.println("Please enter Y/N if the room is: Minibar");
    room.setbMinibar(this.nextBoolean());
    System.out.println("Please enter Y/N if the room is: Jacuzzi");
    room.setbJacuzzi(this.nextBoolean());
    System.out.println("Please enter Y/N if the room is: Seaview");
    room.setbSeaview(this.nextBoolean());
    System.out.println("Please enter Y/N if the room is: Honeymoon");
    room.setbHoneymoon(this.nextBoolean());
    System.out.println("Please enter Y/N if the room is: Family");
    room.setbFamily(this.nextBoolean());

    room.display();
    System.out.println("Is this correct? Please enter Y/N.");
    if (this.nextBoolean()) {
      room.write();
    } else {
      System.out.println("Cancelled.");
    }

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
