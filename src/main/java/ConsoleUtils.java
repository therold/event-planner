import java.io.Console;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
    System.out.print("\033[H\033[2J");
  }

  public static void center(String msg) {
    int consoleWidth = getConsoleWidth();
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

  public static void center(String msg, Integer length) {
    int consoleWidth = getConsoleWidth();
    int msgWidth = length;
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
    int consoleWidth = getConsoleWidth();
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

  public static void setFooter(String footer) {
    int consoleHeight = getConsoleHeight();
    int consoleWidth = getConsoleWidth();
    int footerWidth = footer.length();
    for (int i = 0; i < consoleHeight; i++) {
      System.out.println();
    }
    System.out.print("\033[40m\033[1m\033[37m");
    int start = (int)Math.floor((consoleWidth / 2) - (footerWidth / 2));
    for (int i = 0; i < start; i++) {
      System.out.print(" ");
    }
    System.out.print(footer);
    for (int i = start + footerWidth; i < consoleWidth; i++) {
      System.out.print(" ");
    }
    System.out.print("\033[0m");
    System.out.print("\033[H");
  }

  public static void setFooter(String firstFooter, String secondFooter) {
    int consoleHeight = getConsoleHeight();
    int consoleWidth = getConsoleWidth();
    int firstFooterWidth = firstFooter.length();
    int secondFooterWidth = secondFooter.length();
    for (int i = 0; i < consoleHeight; i++) {
      System.out.println();
    }
    System.out.print("\033[40m\033[1m\033[37m");
    int start = 3;
    for (int i = 0; i < start; i++) {
      System.out.print(" ");
    }
    System.out.print(firstFooter);
    int secondStart = consoleWidth - (3 + secondFooterWidth);
    if (start + firstFooterWidth < secondStart) {
      for (int i = start + firstFooterWidth; i < secondStart; i++) {
        System.out.print(" ");
      }
      System.out.print(secondFooter);
      for (int i = secondStart + secondFooterWidth; i < consoleWidth; i++) {
        System.out.print(" ");
      }
    } else {
      for (int i = start + firstFooterWidth; i < consoleWidth; i++) {
        System.out.print(" ");
      }
    }
    System.out.print("\033[0m");
    System.out.print("\033[H");
  }

  public static Integer getConsoleWidth() {
    Integer width = 0;
    try {
      Process p = Runtime.getRuntime().exec(new String[] {
      "bash", "-c", "tput cols 2> /dev/tty" });
      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
      width = Integer.parseInt(br.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return width;
  }

  public static Integer getConsoleHeight() {
    Integer height = 0;
    try {
      Process p = Runtime.getRuntime().exec(new String[] {
      "bash", "-c", "tput lines 2> /dev/tty" });
      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
      height = Integer.parseInt(br.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return height;
  }

}
