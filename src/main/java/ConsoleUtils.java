import java.io.Console;

public class ConsoleUtils {

  public static String bold(String msg) {
    return String.format("\033[1m%s\033[22m", msg);
  }

  public static String underline(String msg) {
    return String.format("\033[4m%s\033[24m", msg);
  }

  public static String redText(String msg) {
    return String.format("\033[1m\033[31m%s\033[0m", msg);
  }

  public static void pauseScreen() {
    Console cons = System.console();
    System.out.println(redText("Press Enter to continue"));
    cons.readLine();
  }

  public static void clearConsole() {
    System.out.println("\033[0m");
    for (int i = 0; i < 22; i++) {
      for (int j = 0; j < 80; j++) {
        System.out.print(" ");
      }
      System.out.println();
    }
    System.out.println("\033[H\033[2J");
  }

  public static void center(String msg) {
    int consoleWidth = 80;
    int msgWidth = msg.length();
    int start = (int)Math.floor((consoleWidth / 2) - (msgWidth / 2));
    for (int i = 0; i < start; i++) {
      System.out.print(" ");
    }
    System.out.print(msg);
    for (int i = start + msgWidth; i < consoleWidth; i++) {
      System.out.print(" ");
    }
  }

  public static void setTitle(String title) {
    int consoleWidth = 80;
    int titleWidth = title.length();
    System.out.print("\033[40m\033[1m\033[37m");
    int start = (int)Math.floor((consoleWidth / 2) - (titleWidth / 2));
    for (int i = 0; i < start; i++) {
      System.out.print(" ");
    }
    System.out.print(title);
    for (int i = start + titleWidth; i < consoleWidth; i++) {
      System.out.print(" ");
    }
    System.out.print("\033[0m");
  }



}
