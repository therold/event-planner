import java.text.DecimalFormat;
import java.io.Console;
import java.util.Arrays;
import java.util.Random;

public class App {
  private static Event event;
  private static DecimalFormat moneyFormat;

  public static void main(String[] args) {
    Console console = System.console();
    event = new Event();
    moneyFormat  = new DecimalFormat("$#,##0.00");
    boolean running = true;
    while (running) {
      String eventType = getEventRandom();
      boolean isEventRandom = false;
      if (eventType.equals("R") || eventType.equals("r")) {
        isEventRandom = true;
      }
      if (isEventRandom) {
        Random random = new Random();
        event.setNumberOfAttendees(random.nextInt(Integer.SIZE - 1));
        event.addMealById(random.nextInt(Meal.getAllMealNames().length - 1));
      } else {
        event.setNumberOfAttendees(getAttendees());
        boolean mealChoiceRunning = true;
        while (mealChoiceRunning) {
          event.addMealById(getMealChoice());
          String keepAddingMeals = getHaveEnoughMeals();
          if (keepAddingMeals.equals("N") || keepAddingMeals.equals("n")) {
            mealChoiceRunning = false;
          }
        }
      }
      String keepRunning = getRunAgain();
      if (keepRunning.equals("N") || keepRunning.equals("n")) {
        running = false;
      } else {
        event = new Event();
      }
    }
  }

  private static String getEventRandom() {
    String msg = "Welcome to " + ConsoleUtils.bold("Epicodus Event Planners!\n");
    msg += "You can choose from the following commands:\n[P]lan a new event\n[r]andom event.\n\n";
    msg += "What would you like to do?";
    String prompt = "[P/r]";
    String errorMsg = "Please enter S or R.";
    String[] validChoices = { "S", "s", "R", "r", "" };
    return getChoice(msg, prompt, errorMsg, validChoices);
  }

  private static String getRunAgain() {
    String totalCost = moneyFormat.format(event.getTotalEventCost());
    String msg = String.format("The total cost for your event is: " + ConsoleUtils.bold("%s\n"), totalCost);
    msg += "See below for more information.\n\n";
    msg += makeReceiptTable();
    msg += "Would you like to plan another event?\n";
    String prompt = "[Y/n] ";
    String errorMsg = "Please enter Y or N.";
    String[] validChoices = { "Y", "y", "N", "n", "" };
    return getChoice(msg, prompt, errorMsg, validChoices);
  }

  private static String makeReceiptTable() {
    String msg = "";
    int tableWidth = 35;
    msg += ConsoleUtils.makeTableHeader("Item", "Cost", tableWidth);
    String baseCostPerPerson = moneyFormat.format(5.00);
    msg += ConsoleUtils.makeTableLine("Entry fee:", baseCostPerPerson, tableWidth);
    for (int i = 0; i < event.getMealNames().size() - 1 ; i++) {
      String mealName = event.getMealNames().get(i);
      String mealCost = moneyFormat.format(event.getMealCosts().get(i));
      msg += ConsoleUtils.makeTableLine(mealName + ":", mealCost, tableWidth);
    }
    String mealName = event.getMealNames().get(event.getMealNames().size() - 1);
    String mealCost = moneyFormat.format(event.getMealCosts().get(event.getMealNames().size() - 1));
    msg += ConsoleUtils.makeTableSubtotalLine(mealName + ":", mealCost, tableWidth);
    Double mealSubtotal = 0.00;
    for (int i = 0; i < event.getMealCosts().size(); i++) {
      mealSubtotal += event.getMealCosts().get(i);
    }
    String subtotalPerPerson = moneyFormat.format(5.00 + mealSubtotal);
    msg += ConsoleUtils.makeTableLine("Subtotal (per attendee):", subtotalPerPerson, tableWidth);
    msg += ConsoleUtils.makeTableSubtotalLine("People attending: ", "x" + event.getNumberOfAttendees(), tableWidth);
    String totalCost = moneyFormat.format(event.getTotalEventCost());
    msg += ConsoleUtils.makeTableLine("Grand total:", totalCost, tableWidth);
    msg += "\n";
    return msg;
  }

  private static String getHaveEnoughMeals() {
    String msg = String.format("Would you like to add another meal?\n");
    String prompt = "[Y/n]";
    String errorMsg = "Please enter Y or N.";
    String[] validChoices = { "Y", "y", "N", "n", "" };
    String choice = getChoice(msg, prompt, errorMsg, validChoices);
    return choice;
  }

  private static Integer getAttendees() {
    String msg = String.format("We'll need to collect a bit of information about your upcoming event.\n" +
    "How many people will be attending your upcoming event?\n");
    String prompt = "People";
    String errorMsg = "Please enter the number of people.";
    Integer[] validChoices = {};
    Integer choice = getChoice(msg, prompt, errorMsg, validChoices);
    return choice;
  }

  private static Integer getMealChoice() {
    String msg = String.format("What type of meal will we serve?\n" +
      "The options are:\n\n");
    msg += makeAllMealsTable();
    String errorMsg = "Sorry, I don't recognize that ID.";
    String prompt = "ID";
    Integer[] validChoices = { 0, 1, 2, 3, 4, 5 };
    Integer choice = getChoice(msg, prompt, errorMsg, validChoices);
    return choice;
  }

  private static String makeAllMealsTable() {
    String msg = "";
    Integer tableWidth = 35;
    msg += ConsoleUtils.makeTableHeader("ID Name", "Cost/Person", tableWidth);
    for (int i = 0; i < Meal.getAllMealNames().length; i++){
      String mealName = String.format("%d: %s", i, Meal.getAllMealNames()[i]);
      String mealCost = moneyFormat.format(Meal.getAllMealCosts()[i]);
      msg += ConsoleUtils.makeTableLine(mealName, mealCost, tableWidth);
    }
    return msg;
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
            choiceIsValid = (output > 0);
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
