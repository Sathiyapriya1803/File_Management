import java.util.List;

// SOLID -> Interface Segregation Principle:
// a small, focused interface only about searching (not mixed with create/delete logic)
public interface Searchable {
    FileSystemItem searchByName(String name);
    List<FileSystemItem> searchByExtension(String extension);
}
