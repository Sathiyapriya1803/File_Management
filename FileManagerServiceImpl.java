import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Concrete implementation -> OOP (implements 2 interfaces = multiple contracts)
// SOLID -> Single Responsibility: this class only manages the tree + index operations
public class FileManagerServiceImpl implements FileManagerService, Searchable {

    private Folder root;

    // HashMap -> O(1) average lookup of any file/folder by its name (HashMap topic)
    private Map<String, FileSystemItem> index;

    public FileManagerServiceImpl(Folder root) {
        this.root = root;
        this.index = new HashMap<>();
        indexAll();
    }

    // Tree traversal (DFS/recursion) to (re)build the HashMap index
    @Override
    public void indexAll() {
        index.clear();
        buildIndex(root);
    }

    private void buildIndex(FileSystemItem item) {
        index.put(item.getName().toLowerCase(), item);
        if (item instanceof Folder) {
            for (FileSystemItem child : ((Folder) item).getChildren()) {
                buildIndex(child); // recursion down the Tree
            }
        }
    }

    // Resolves a path like "root/Documents/Sub" by walking the Tree level by level
    private Folder findFolder(String path) {
        if (path == null || path.trim().isEmpty() || path.equalsIgnoreCase("root")) {
            return root;
        }
        String[] parts = path.split("/"); // Strings -> split by separator
        FileSystemItem current = root;
        for (String part : parts) {
            if (part.equalsIgnoreCase("root") || part.isEmpty()) continue;
            if (!(current instanceof Folder)) return null;
            Folder folder = (Folder) current;
            FileSystemItem next = null;
            // Linear search inside children ArrayList
            for (FileSystemItem child : folder.getChildren()) {
                if (child.getName().equalsIgnoreCase(part)) {
                    next = child;
                    break;
                }
            }
            if (next == null) return null;
            current = next;
        }
        return (current instanceof Folder) ? (Folder) current : null;
    }

    @Override
    public void createFolder(String path, String folderName) {
        Folder parent = findFolder(path);
        if (parent == null) {
            System.out.println("Path not found: " + path);
            return;
        }
        Folder newFolder = new Folder(folderName);
        parent.addItem(newFolder);
        index.put(folderName.toLowerCase(), newFolder);
        System.out.println("Folder created: " + newFolder.getPath());
    }

    @Override
    public void createFile(String path, String fileName, int sizeKB) {
        Folder parent = findFolder(path);
        if (parent == null) {
            System.out.println("Path not found: " + path);
            return;
        }
        FileItem newFile = new FileItem(fileName, sizeKB);
        parent.addItem(newFile);
        index.put(fileName.toLowerCase(), newFile);
        System.out.println("File created: " + newFile.getPath());
    }

    @Override
    public void deleteItem(String path, String itemName) {
        Folder parent = findFolder(path);
        if (parent == null) {
            System.out.println("Path not found: " + path);
            return;
        }
        if (parent.removeItem(itemName)) {
            index.remove(itemName.toLowerCase());
            System.out.println("Deleted: " + itemName);
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }

    @Override
    public void listContents(String path) {
        Folder folder = findFolder(path);
        if (folder == null) {
            System.out.println("Path not found: " + path);
            return;
        }
        folder.display("");
        System.out.println("Total size: " + folder.getSize() + " KB");
    }

    // HashMap based search -> average O(1) (Searching topic)
    @Override
    public FileSystemItem search(String name) {
        return index.get(name.toLowerCase());
    }

    @Override
    public FileSystemItem searchByName(String name) {
        return search(name);
    }

    // Linear search across index values, matching by extension (Strings + Searching)
    @Override
    public List<FileSystemItem> searchByExtension(String extension) {
        List<FileSystemItem> results = new ArrayList<>();
        for (FileSystemItem item : index.values()) {
            if (item instanceof FileItem) {
                FileItem file = (FileItem) item;
                if (file.getExtension().equalsIgnoreCase(extension)) {
                    results.add(file);
                }
            }
        }
        return results;
    }
}
