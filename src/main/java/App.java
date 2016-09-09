import java.text.DecimalFormat;
import java.io.Console;
import java.util.Arrays;

public class App {
  private static Event event;

  public static void main(String[] args) {
    Console console = System.console();
    event = new Event();
    boolean running = true;
    while (running) {
      String testing = Arrays.toString(Meal.getAllMealNames());
      event.setNumberOfAttendees(getAttendees());
      event.setMealId(getMealChoice());

      ConsoleUtils.clearConsole();
      setScreen();
      DecimalFormat formatter = new DecimalFormat("#,###.00");
      System.out.println(String.format("The total cost for your event is: " + ConsoleUtils.bold("$%s"), formatter.format(event.getTotalEventCost())));
      System.out.println("Would you like to plan another event?");
      System.out.println();
      System.out.print(ConsoleUtils.bold("[Y/n] "));
      String choice = console.readLine();
      if (choice.equals("N") || choice.equals("n")) {
        running = false;
      } else {
        event = new Event();
      }
    }
  }

  private static Integer getAttendees() {
    String msg = String.format("We'll need to collect a bit of information about your upcoming event.\n" +
    "How many people will be attending your upcoming event?\n");
    String prompt = "People: ";
    String errorMsg = "Please enter a number.";
    Integer[] validChoices = {};
    return getChoice(msg, prompt, errorMsg, validChoices);
  }

  private static Integer getMealChoice() {
    String msg = String.format("What type of meal will we serve?\n" +
      "The options are:\n\n");
    String header = "ID Name           Cost/Person\n";
    msg += String.format(ConsoleUtils.center(ConsoleUtils.underline(header), header.length()));
    for (int i = 0; i < Meal.getAllMealNames().length; i++){
      String spacer = "";
      int spacesNeeded = 24 - (Meal.getAllMealNames()[i].length() + Meal.getAllMealCosts()[i].toString().length());
      for (int j = 0; j < spacesNeeded; j++) {
        spacer += " ";
      }
      msg += (ConsoleUtils.center(String.format("%d: %s%s$%.2f\n", i, Meal.getAllMealNames()[i], spacer, Meal.getAllMealCosts()[i])));
    }
    String errorMsg = "Sorry, I don't recognize that ID.";
    String prompt = "ID: ";
    Integer[] validChoices = { 0, 1, 2, 3, 4, 5 };
    Integer choice = getChoice(msg, prompt, errorMsg, validChoices);
    return choice;
  }

  private static Integer getChoice(String msg, String prompt, String errorMsg, Integer[] validChoices) {
    Console console = System.console();
    String choice = "";
    Integer output = null;
    int timesRun = 0;
    boolean choiceIsValid = false;
    while (!choiceIsValid) {
      setScreen();
      System.out.println(msg);
      System.out.println();
      if (!choiceIsValid && timesRun > 0) {
        System.out.println(errorMsg);
      }
      System.out.print(ConsoleUtils.bold(String.format("%s: ", prompt)));
      choice = console.readLine();
      timesRun++;
      try {
        output = Integer.parseInt(choice);
      } catch (NumberFormatException e) {
        choiceIsValid = false;
      } finally {
        if (output != null) {
          if (validChoices.length > 0) {
            choiceIsValid = (Arrays.asList(validChoices).contains(output));
          } else {
            choiceIsValid = true;
          }
        }
      }
    }
    System.out.println("Thanks!");
    System.out.println();
    ConsoleUtils.pauseScreen();
    return output;
  }

  private static void setScreen() {
    ConsoleUtils.clearConsole();
    Integer people = event.getNumberOfAttendees();
    Double cost = event.getTotalEventCost();
    DecimalFormat formatter = new DecimalFormat("#,###.00");
    ConsoleUtils.setFooter(String.format("People: %d", people), String.format("Current Cost: $%s", formatter.format(cost)));
    ConsoleUtils.setTitle("Welcome to Epicodus Event Planners!");
  }
}
