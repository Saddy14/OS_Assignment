package NonPrePriority;
import java.util.Scanner;


public class Menu {

    Scanner scanner = new Scanner(System.in);
    Menu() {

        // draw();
    }


    public String setpNumber() {
        System.out.print("\nEnter Process: ");
        String x =  scanner.next();
        return x;
    }


    public int setBrustTime() {
        System.out.print("Enter Brust Time: ");
        int x =  scanner.nextInt();
        return x;
    }


    public int setArrivalTime() {

        System.out.print("Enter Arrival Time: ");
        int x =  scanner.nextInt();
        return x;
    }


    public int setPriority() {
        System.out.print("Enter Priority: ");
        int x =  scanner.nextInt();
        return x;
    }

    public int howManyP() {
        System.out.print("Enter How Many Processes: ");
        int x =  scanner.nextInt();
        return x;

    }

    // public void setTurnAroundTime(int turnAroundTime) {
    //     System.out.print("Enter Brust Time: ");
    //     int x =  scanner.nextInt();
    //     return x;
    // }


    // public void setWaitingTime(int waitingTime) {
    //      System.out.print("Enter Brust Time: ");
    //     int x =  scanner.nextInt();
    //     return x;
    // }


    private void draw1() {

        System.out.println(" ___       __   _______   ___       ________  ________  _____ ______   _______           _________  ________                                                                           \r\n" + //
                "|\\  \\     |\\  \\|\\  ___ \\ |\\  \\     |\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\         |\\___   ___\\\\   __  \\                                                                          \r\n" + //
                "\\ \\  \\    \\ \\  \\ \\   __/|\\ \\  \\    \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|        \\|___ \\  \\_\\ \\  \\|\\  \\                                                                         \r\n" + //
                " \\ \\  \\  __\\ \\  \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\ \\  \\_|/__           \\ \\  \\ \\ \\  \\\\\\  \\                                                                        \r\n" + //
                "  \\ \\  \\|\\__\\_\\  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\           \\ \\  \\ \\ \\  \\\\\\  \\                                                                       \r\n" + //
                "   \\ \\____________\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\           \\ \\__\\ \\ \\_______\\                                                                      \r\n" + //
                "    \\|____________|\\|_______|\\|_______|\\|_______|\\|_______|\\|__|     \\|__|\\|_______|            \\|__|  \\|_______|                                                                      \r\n" + //
                "                                                                                                                                                                                       \r\n" + //
                "                                                                                                                                                                                       \r\n" + //
                "                                                                                                                                                                                       \r\n" + //
                " ________   ________  ________           ________  ________  _______   _______   _____ ______   ________  _________  ___  ___      ___ _______           ________        ___  ________ \r\n" + //
                "|\\   ___  \\|\\   __  \\|\\   ___  \\        |\\   __  \\|\\   __  \\|\\  ___ \\ |\\  ___ \\ |\\   _ \\  _   \\|\\   __  \\|\\___   ___\\\\  \\|\\  \\    /  /|\\  ___ \\         |\\   ____\\      |\\  \\|\\  _____\\\r\n" + //
                "\\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\       \\ \\  \\|\\  \\ \\  \\|\\  \\ \\   __/|\\ \\   __/|\\ \\  \\\\\\__\\ \\  \\ \\  \\|\\  \\|___ \\  \\_\\ \\  \\ \\  \\  /  / | \\   __/|        \\ \\  \\___|_     \\ \\  \\ \\  \\__/ \r\n" + //
                " \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\       \\ \\   ____\\ \\   _  _\\ \\  \\_|/_\\ \\  \\_|/_\\ \\  \\\\|__| \\  \\ \\   ____\\   \\ \\  \\ \\ \\  \\ \\  \\/  / / \\ \\  \\_|/__       \\ \\_____  \\  __ \\ \\  \\ \\   __\\\r\n" + //
                "  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\       \\ \\  \\___|\\ \\  \\\\  \\\\ \\  \\_|\\ \\ \\  \\_|\\ \\ \\  \\    \\ \\  \\ \\  \\___|    \\ \\  \\ \\ \\  \\ \\    / /   \\ \\  \\_|\\ \\       \\|____|\\  \\|\\  \\\\_\\  \\ \\  \\_|\r\n" + //
                "   \\ \\__\\\\ \\__\\ \\_______\\ \\__\\\\ \\__\\       \\ \\__\\    \\ \\__\\\\ _\\\\ \\_______\\ \\_______\\ \\__\\    \\ \\__\\ \\__\\        \\ \\__\\ \\ \\__\\ \\__/ /     \\ \\_______\\        ____\\_\\  \\ \\________\\ \\__\\ \r\n" + //
                "    \\|__| \\|__|\\|_______|\\|__| \\|__|        \\|__|     \\|__|\\|__|\\|_______|\\|_______|\\|__|     \\|__|\\|__|         \\|__|  \\|__|\\|__|/       \\|_______|       |\\_________\\|________|\\|__| \r\n" + //
                "                                                                                                                                                           \\|_________|                \r\n" + //
                "                                                                                                                                                                                       \r\n" + //
                "                                                                                                                                                                                       ");
    }

    private void draw () {
        System.out.println("  ██     ██ ███████ ██       ██████  ██████  ███    ███ ███████     ████████  ██████                                                           \r\n" + //
                "██     ██ ██      ██      ██      ██    ██ ████  ████ ██             ██    ██    ██                                                          \r\n" + //
                "██  █  ██ █████   ██      ██      ██    ██ ██ ████ ██ █████          ██    ██    ██                                                          \r\n" + //
                "██ ███ ██ ██      ██      ██      ██    ██ ██  ██  ██ ██             ██    ██    ██                                                          \r\n" + //
                " ███ ███  ███████ ███████  ██████  ██████  ██      ██ ███████        ██     ██████                                                           \r\n" + //
                "                                                                                                                                             \r\n" + //
                "                                                                                                                                             \r\n" + //
                "███    ██  ██████  ███    ██     ██████  ██████  ███████ ███████ ███    ███ ██████  ████████ ██ ██    ██ ███████     ███████      ██ ███████ \r\n" + //
                "████   ██ ██    ██ ████   ██     ██   ██ ██   ██ ██      ██      ████  ████ ██   ██    ██    ██ ██    ██ ██          ██           ██ ██      \r\n" + //
                "██ ██  ██ ██    ██ ██ ██  ██     ██████  ██████  █████   █████   ██ ████ ██ ██████     ██    ██ ██    ██ █████       ███████      ██ █████   \r\n" + //
                "██  ██ ██ ██    ██ ██  ██ ██     ██      ██   ██ ██      ██      ██  ██  ██ ██         ██    ██  ██  ██  ██               ██ ██   ██ ██      \r\n" + //
                "██   ████  ██████  ██   ████     ██      ██   ██ ███████ ███████ ██      ██ ██         ██    ██   ████   ███████     ███████  █████  ██      \r\n" + //
                "                                                                                                                                             \r\n" + //
                "                                                                                                                                               ");
    }

}
