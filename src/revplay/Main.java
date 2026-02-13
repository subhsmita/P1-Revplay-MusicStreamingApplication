package revplay;

import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static UserDAO userDAO = new UserDAO();
    static SongDAO songDAO = new SongDAO();
    static FavoriteDAO favoriteDAO = new FavoriteDAO();
    static PlaylistDAO playlistDAO = new PlaylistDAO();

    static User currentUser = null;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n==== REVPLAY ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Goodbye");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    // ================= REGISTER =================
    static void register() {
        sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        boolean success = userDAO.register(name, email, password);

        if (success)
            System.out.println("Registration successful");
        else
            System.out.println("Registration failed");
    }

    // ================= LOGIN =================
    static void login() {
        sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        currentUser = userDAO.login(email, password);

        if (currentUser != null) {
            System.out.println("Welcome " + currentUser.getName());
            userMenu();
        } else {
            System.out.println("Invalid login");
        }
    }

    // ================= USER MENU =================
    static void userMenu() {

        while (true) {
            System.out.println("\n==== USER MENU ====");
            System.out.println("1. View Songs");
            System.out.println("2. Create Playlist");
            System.out.println("3. View My Playlists");
            System.out.println("4. Add Song to Favorites");
            System.out.println("5. View Favorites");
            System.out.println("6. Play Song");
            System.out.println("7. Logout");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> viewSongs();
                case 2 -> createPlaylist();
                case 3 -> viewPlaylists();
                case 4 -> addFavorite();
                case 5 -> viewFavorites();
                case 6 -> playSong();
                case 7 -> {
                    currentUser = null;
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    // ================= VIEW SONGS =================
    static void viewSongs() {
        List<Song> songs = songDAO.getAllSongs();

        for (Song s : songs) {
            System.out.println(
                    s.getId() + " | " +
                            s.getTitle() + " | " +
                            s.getArtist() + " | " +
                            s.getGenre() + " | " +
                            s.getDuration()
            );
        }
    }

    // ================= CREATE PLAYLIST =================
    static void createPlaylist() {
        sc.nextLine();
        System.out.print("Enter playlist name: ");
        String name = sc.nextLine();

        boolean ok = playlistDAO.createPlaylist(currentUser.getId(), name);

        System.out.println(ok ? "Playlist created" : "Failed");
    }

    // ================= VIEW PLAYLIST =================
    static void viewPlaylists() {
        List<Playlist> list = playlistDAO.getUserPlaylists(currentUser.getId());

        for (Playlist p : list) {
            System.out.println(p.getId() + " | " + p.getName());
        }
    }

    // ================= FAVORITE =================
    static void addFavorite() {
        System.out.print("Enter song id: ");
        int id = sc.nextInt();

        boolean ok = favoriteDAO.addFavorite(currentUser.getId(), id);

        System.out.println(ok ? "Added to favorites" : "Failed");
    }

    static void viewFavorites() {
        List<Song> favs = favoriteDAO.getFavorites(currentUser.getId());

        for (Song s : favs) {
            System.out.println(s.getTitle() + " - " + s.getArtist());
        }
    }

    // ================= PLAYER =================
    static void playSong() {
        System.out.print("Enter song id: ");
        int id = sc.nextInt();

        Song song = songDAO.getSongById(id);

        if (song == null) {
            System.out.println("Song not found");
            return;
        }

        Player.playSong(song);
    }
}
