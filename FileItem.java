// Represents a single FILE -> a LEAF node in the folder Tree
// OOP -> Inheritance (extends FileSystemItem)
public class FileItem extends FileSystemItem {

    private int sizeInKB;
    private String extension;

    public FileItem(String name, int sizeInKB) {
        super(name);
        this.sizeInKB = sizeInKB;
        this.extension = extractExtension(name); // Strings handling
    }

    // Strings -> extracting extension using lastIndexOf() and substring()
    private String extractExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "unknown";
        }
        return fileName.substring(dotIndex + 1);
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public int getSize() {
        return sizeInKB;
    }

    @Override
    public String getPath() {
        // Recursive path building (Tree traversal towards root)
        return (parent != null ? parent.getPath() + "/" : "") + name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "|-- " + name + "  (" + sizeInKB + " KB)");
    }
}
