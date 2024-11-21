package edu.ntnu.idi.idatt.Utils;

public class InterfaceTextSource {
    public static String welcome() {
        return """
    Hello!
    Welcome to your new favorite food-application!
    What do you want to do today?
    """;
    }

    public static String clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
      return "";
    }
}
