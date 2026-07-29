import java.util.List;
import java.util.Scanner;

// Entry point -> Programming Foundations (loops, conditionals, switch, console I/O)
public class Main {
    public static void main(String[] args) {

        Folder root = new Folder("root"); // root of the Tree

        // Dependency Inversion -> declare variable using the INTERFACE type
        FileManagerService service = new FileManagerServiceImpl(root);

        Scanner sc = new Scanner(System.in);

        // Array -> fixed set of menu options (Arrays topic)
        String[] menu = {
            "1. Create Folder",
            "2. Create File",
            "3. Delete Item",
            "4. List Contents",
            "5. Search by Name (HashMap)",
            "6. Search by Extension",
            "7. Exit"
        };

        // Seed some sample data so the tree isn't empty on first run
        service.createFolder("root", "Documents");
        service.createFolder("root", "Pictures");
        service.createFile("Documents", "resume.pdf", 120);
        service.createFile("Documents", "notes.txt", 15);
        service.createFile("Pictures", "photo.jpg", 500);

        boolean running = true;
        while (running) {
            System.out.println("\n===== File Management System =====");
            for (String item : menu) {
                System.out.println(item);
            }
            System.out.print("Choose option: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": {
                    System.out.print("Enter parent path (e.g. root or root/Documents): ");
                    String p1 = sc.nextLine().trim();
                    System.out.print("Enter folder name: ");
                    String fname = sc.nextLine().trim();
                    service.createFolder(p1, fname);
                    break;
                }
                case "2": {
                    System.out.print("Enter parent path: ");
                    String p2 = sc.nextLine().trim();
                    System.out.print("Enter file name (with extension): ");
                    String filename = sc.nextLine().trim();
                    System.out.print("Enter size in KB: ");
                    int size = Integer.parseInt(sc.nextLine().trim());
                    service.createFile(p2, filename, size);
                    break;
                }
                case "3": {
                    System.out.print("Enter parent path: ");
                    String p3 = sc.nextLine().trim();
                    System.out.print("Enter item name to delete: ");
                    String delName = sc.nextLine().trim();
                    service.deleteItem(p3, delName);
                    break;
                }
                case "4": {
                    System.out.print("Enter path to list (or 'root'): ");
                    String p4 = sc.nextLine().trim();
                    service.listContents(p4);
                    break;
                }
                case "5": {
                    System.out.print("Enter name to search: ");
                    String searchName = sc.nextLine().trim();
                    FileSystemItem result = service.search(searchName);
                    if (result != null) {
                        System.out.println("Found: " + result.getPath());
                    } else {
                        System.out.println("Not found.");
                    }
                    break;
                }
                case "6": {
                    System.out.print("Enter extension (e.g. txt): ");
                    String ext = sc.nextLine().trim();
                    // Downcast to access Searchable-specific method
                    List<FileSystemItem> matches =
                            ((FileManagerServiceImpl) service).searchByExtension(ext);
                    if (matches.isEmpty()) {
                        System.out.println("No files with extension ." + ext);
                    } else {
                        for (FileSystemItem f : matches) {
                            System.out.println(f.getPath());
                        }
                    }
                    break;
                }
                case "7": {
                    running = false;
                    System.out.println("Exiting... Bye!");
                    break;
                }
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        sc.close();
    }
}
