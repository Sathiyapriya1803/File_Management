// Abstract class -> OOP Abstraction + base of the Tree structure
// SOLID -> Open/Closed Principle: new item types can EXTEND this without modifying it
public abstract class FileSystemItem {

    protected String name;
    protected FileSystemItem parent; // link to parent node (Tree structure)

    public FileSystemItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParent(FileSystemItem parent) {
        this.parent = parent;
    }

    public FileSystemItem getParent() {
        return parent;
    }

    // Polymorphism -> each subclass (File / Folder) prints itself differently
    public abstract void display(String indent);

    // Polymorphism -> file returns its own size, folder returns sum of children
    public abstract int getSize();

    // Builds full path by walking up the tree (recursion + String concatenation)
    public abstract String getPath();
}
