import java.util.ArrayList;
import java.util.List;

// Represents a FOLDER -> an INTERNAL NODE of the Tree
// Can contain many children -> other Folders or FileItems (recursive Tree structure)
public class Folder extends FileSystemItem {

    // ArrayList -> dynamic list of children (Arrays/ArrayList topic)
    private List<FileSystemItem> children;

    public Folder(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    public void addItem(FileSystemItem item) {
        item.setParent(this);
        children.add(item);
    }

    // Linear Search through ArrayList by name (Searching topic)
    public boolean removeItem(String name) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equalsIgnoreCase(name)) {
                children.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<FileSystemItem> getChildren() {
        return children;
    }

    @Override
    public int getSize() {
        // Recursive Tree traversal -> sum sizes of all descendants
        int total = 0;
        for (FileSystemItem item : children) {
            total += item.getSize();
        }
        return total;
    }

    @Override
    public String getPath() {
        return (parent != null ? parent.getPath() + "/" : "") + name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "[+] " + name + "/");
        for (FileSystemItem item : children) {
            item.display(indent + "    "); // Recursive DFS print of the Tree
        }
    }
}
