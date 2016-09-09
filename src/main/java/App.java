import java.text.DecimalFormat;
import java.io.Console;
import java.util.Arrays;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
    Console console = System.console();
    setScreen();
    System.out.println("We'll need to collect a bit of information about your upcoming event.");

    System.out.println("How many people will be attending your upcoming event?");
    System.out.println();
    System.out.print(ConsoleUtils.bold("People: "));
    Integer people = Integer.parseInt(console.readLine());
    System.out.println("Thanks!");
    System.out.println();
    ConsoleUtils.pauseScreen();
    return (people);
  }

  // private static Integer getMealChoice() {
  //   Console console = System.console();
  //   String choice = "";
  //   boolean choiceIsValid = false;
  //   int timesRun = 0;
  //   while (!choiceIsValid) {
  //     setScreen();
  //     System.out.println("What type of meal will we serve?");
  //     System.out.println("The options are:");
  //     System.out.println();
  //     String header = "ID Name           Cost/Person";
  //     System.out.println(ConsoleUtils.center(ConsoleUtils.underline(header), header.length()));
  //     for (int i = 0; i < Meal.getAllMealNames().length; i++){
  //       String spacer = "";
  //       int spacesNeeded = 24 - (Meal.getAllMealNames()[i].length() + Meal.getAllMealCosts()[i].toString().length());
  //       for (int j = 0; j < spacesNeeded; j++) {
  //         spacer += " ";
  //       }
  //       System.out.println(ConsoleUtils.center(String.format("%d: %s%s$%.2f", i, Meal.getAllMealNames()[i], spacer, Meal.getAllMealCosts()[i])));
  //     }
  //     System.out.println();
  //     System.out.println();
  //     if (!choiceIsValid && timesRun > 0) {
  //       System.out.println("Sorry, I don't recognize that ID.");
  //     }
  //     System.out.print(ConsoleUtils.bold("ID: "));
  //     choice = console.readLine();
  //     timesRun++;
  //     choiceIsValid = (choice.equals("0") || choice.equals("1"));
  //   }
  //   Integer meal = Integer.parseInt(choice);
  //   System.out.println("Thanks!");
  //   System.out.println();
  //   ConsoleUtils.pauseScreen();
  //   return meal;
  // }

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
    Integer[] valids = { 0, 1, 2, 3, 4, 5 };
    Integer choice = getChoice(msg, prompt, errorMsg, valids);
    return choice;
  }

  private static Integer getChoice(String msg, String prompt, String errorMsg, Integer[] valids) {
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
          choiceIsValid = (Arrays.asList(valids).contains(output));
        }
      }
    }
    System.out.println();
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
