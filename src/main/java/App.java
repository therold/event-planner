import java.text.DecimalFormat;
import java.io.Console;
import java.util.Arrays;

public class App {
  private static Event event;
  private static DecimalFormat moneyFormat;

  public static void main(String[] args) {
    Console console = System.console();
    event = new Event();
    moneyFormat  = new DecimalFormat("$#,##0.00");
    boolean running = true;
    while (running) {
      String testing = Arrays.toString(Meal.getAllMealNames());
      event.setNumberOfAttendees(getAttendees());
      event.setMealId(getMealChoice());
      String keepRunning = getRunAgain();
      if (keepRunning.equals("N") || keepRunning.equals("n")) {
        running = false;
      } else {
        event = new Event();
      }
    }
  }

  private static String getRunAgain() {
    String totalCost = moneyFormat.format(event.getTotalEventCost());
    String baseCostPerPerson = moneyFormat.format(5.00);
    String mealCostPerPerson = moneyFormat.format(event.getMealCostPerPerson());
    String subtotalPerPerson = moneyFormat.format(5.00 + event.getMealCostPerPerson());
    String msg = String.format("The total cost for your event is: " + ConsoleUtils.bold("%s\n"), totalCost);
    msg += "See below for more information.\n\n";

    int tableWidth = 35;
    msg += ConsoleUtils.makeTableHeader("Item", "Cost", tableWidth);
    msg += ConsoleUtils.makeTableLine("Base cost per person:", baseCostPerPerson, tableWidth);
    msg += ConsoleUtils.makeTableSubtotalLine("Meal cost per person:", mealCostPerPerson, tableWidth);
    msg += ConsoleUtils.makeTableLine("Subtotal per person:", subtotalPerPerson, tableWidth);
    msg += ConsoleUtils.makeTableSubtotalLine("People attending: ", "x" + event.getNumberOfAttendees(), tableWidth);
    msg += ConsoleUtils.makeTableLine("Grand total:", totalCost, tableWidth);
    msg += "\n";

    msg += "Would you like to plan another event?\n";
    String prompt = "[Y/n] ";
    String errorMsg = "Please enter Y or N.";
    String[] validChoices = { "Y", "y", "N", "n" };
    return getChoice(msg, prompt, errorMsg, validChoices);
  }


  private static Integer getAttendees() {
    String msg = String.format("We'll need to collect a bit of information about your upcoming event.\n" +
    "How many people will be attending your upcoming event?\n");
    String prompt = "People";
    String errorMsg = "Please enter a number.";
    Integer[] validChoices = {};
    Integer choice = getChoice(msg, prompt, errorMsg, validChoices);
    System.out.println("Thanks!");
    System.out.println();
    ConsoleUtils.pauseScreen();
    return choice;
  }

  private static Integer getMealChoice() {
    String msg = String.format("What type of meal will we serve?\n" +
      "The options are:\n\n");

    Integer tableWidth = 35;
    msg += ConsoleUtils.makeTableHeader("ID Name", "Cost/Person", tableWidth);
    for (int i = 0; i < Meal.getAllMealNames().length; i++){
      String mealName = String.format("%d: %s", i, Meal.getAllMealNames()[i]);
      String mealCost = moneyFormat.format(Meal.getAllMealCosts()[i]);
      msg += ConsoleUtils.makeTableLine(mealName, mealCost, tableWidth);
    }

    String errorMsg = "Sorry, I don't recognize that ID.";
    String prompt = "ID";
    Integer[] validChoices = { 0, 1, 2, 3, 4, 5 };
    Integer choice = getChoice(msg, prompt, errorMsg, validChoices);
    System.out.println("Thanks!");
    System.out.println();
    ConsoleUtils.pauseScreen();
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
    return output;
  }

  private static String getChoice(String msg, String prompt, String errorMsg, String[] validChoices) {
    Console console = System.console();
    int timesRun = 0;
    String output = null;
    boolean choiceIsValid = false;
    while (!choiceIsValid) {
      setScreen();
      System.out.println(msg);
      System.out.println();
      if (!choiceIsValid && timesRun > 0) {
        System.out.println(errorMsg);
      }
      System.out.print(ConsoleUtils.bold(String.format("%s: ", prompt)));
      output = console.readLine();
      choiceIsValid = (Arrays.asList(validChoices).contains(output));
      timesRun++;
    }
    return output;
  }

  private static void setScreen() {
    ConsoleUtils.clearConsole();
    Integer people = event.getNumberOfAttendees();
    Double cost = event.getTotalEventCost();
    ConsoleUtils.setFooter(String.format("People: %d", people), String.format("Current Cost: %s", moneyFormat.format(cost)));
    ConsoleUtils.setTitle("Welcome to Epicodus Event Planners!");
  }
}
