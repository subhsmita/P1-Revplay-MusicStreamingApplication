package revplay;

import java.util.Scanner;

public class Player {

    public static void playSong(Song song) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Now Playing: " + song.getTitle() + " - " + song.getArtist());

        while (true) {
            System.out.println("\n1. Pause");
            System.out.println("2. Resume");
            System.out.println("3. Skip");
            System.out.println("4. Repeat");
            System.out.println("5. Stop");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Song Paused");
                    break;

                case 2:
                    System.out.println("Song Resumed");
                    break;

                case 3:
                    System.out.println("Song Skipped");
                    return;

                case 4:
                    System.out.println("Replaying song...");
                    System.out.println("Now Playing: " + song.getTitle() + " - " + song.getArtist());
                    break;

                case 5:
                    System.out.println("Stopped playing");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
