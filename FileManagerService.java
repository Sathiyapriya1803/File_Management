// SOLID -> Dependency Inversion Principle:
// Main.java depends on this ABSTRACTION, not on the concrete FileManagerServiceImpl.
// SOLID -> Single Responsibility Principle:
// this interface only defines file-management operations (create/delete/list/search)
public interface FileManagerService {
    void createFolder(String path, String folderName);
    void createFile(String path, String fileName, int sizeKB);
    void deleteItem(String path, String itemName);
    void listContents(String path);
    FileSystemItem search(String name);
    void indexAll();
}
