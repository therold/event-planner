import java.io.Console;

public class App {
  public static void main(String[] args) {
    Console console = System.console();
    Event event = new Event();
    Meal meals = new Meal();

    System.out.println("Welcome to Epicodus Event Planners!");
    System.out.println("We'll need to collect a bit of information about your upcoming event.");

    System.out.println("How many people will be attending your upcoming event?");
    System.out.print("People: ");
    Integer people = Integer.parseInt(console.readLine());
    event.setNumberOfAttendees(people);
    System.out.println("Thanks!");
    System.out.println();

    System.out.println("What type of meal will we serve?");
    System.out.println("The options are:");
    for (String meal : meals.getAllMealNames()) {
      System.out.println(meal);
    }
    String meal = console.readLine();
    event.setMealByName(meal);
    System.out.println("Thanks!");
    System.out.println();

    System.out.println(String.format("The total cost for your event is %f", event.getTotalEventCost()));
  }
}
