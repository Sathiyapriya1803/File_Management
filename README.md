# File Management System (Java Console Project)

A console-based File Management System that simulates a real folder/file
structure in memory, built to demonstrate:

| Topic                          | Where it's used                                                        |
|---------------------------------|--------------------------------------------------------------------------|
| Programming Foundations         | `Main.java` – loops, conditionals, switch-case, Scanner input           |
| Arrays / ArrayList               | `menu[]` array in `Main.java`, `List<FileSystemItem> children` in `Folder.java` |
| Strings                          | Extension extraction, path building/splitting (`FileItem`, `FileManagerServiceImpl`) |
| Tree (Folder Structure)          | `Folder` (internal node) + `FileItem` (leaf node) form a recursive tree |
| HashMap                          | `Map<String, FileSystemItem> index` in `FileManagerServiceImpl.java` for O(1) name search |
| Searching                        | HashMap lookup by name (O(1)) + linear search by extension / inside folders |
| OOP                              | Abstraction (`FileSystemItem`), Inheritance (`FileItem`, `Folder`), Polymorphism (`display()`, `getSize()`) |
| SOLID                            | See below                                                                |

## SOLID mapping
- **S**ingle Responsibility – `Folder`/`FileItem` only model data; `FileManagerServiceImpl` only handles operations.
- **O**pen/Closed – `FileSystemItem` can be extended (e.g. add `ShortcutItem`) without changing existing code.
- **L**iskov Substitution – `FileItem`/`Folder` can be used anywhere a `FileSystemItem` is expected.
- **I**nterface Segregation – `Searchable` is a small, focused interface, separate from `FileManagerService`.
- **D**ependency Inversion – `Main.java` depends on the `FileManagerService` interface, not the concrete class.

## Project Structure
```
FileManagementSystem/
└── src/
    ├── Main.java                  (entry point / menu)
    ├── FileSystemItem.java        (abstract base class)
    ├── FileItem.java              (file = leaf node)
    ├── Folder.java                (folder = tree node, uses ArrayList)
    ├── Searchable.java            (interface)
    ├── FileManagerService.java    (interface)
    └── FileManagerServiceImpl.java(HashMap + tree logic)
```

## How to run in Visual Studio Code

1. Install **VS Code** and the **"Extension Pack for Java"** (by Microsoft) from the Extensions marketplace.
2. Make sure a **JDK (17+)** is installed on your PC. Check with:
   ```
   java -version
   javac -version
   ```
   If missing, install from https://adoptium.net (Temurin JDK).
3. Open the `FileManagementSystem` folder in VS Code (`File > Open Folder`).
4. Open `src/Main.java`.
5. Click the **Run** button (▶) above the `main` method,
   OR press `F5`,
   OR right-click `Main.java` → **Run Java**.
6. The program runs in the VS Code terminal — use the menu numbers to
   create folders/files, list contents, delete, and search.

### Run from terminal instead (no extension needed)
```bash
cd FileManagementSystem/src
javac *.java
java Main
```

## Sample usage
```
1. Create Folder
2. Create File
3. Delete Item
4. List Contents
5. Search by Name (HashMap)
6. Search by Extension
7. Exit
Choose option: 4
Enter path to list (or 'root'): root
[+] root/
    [+] Documents/
        |-- resume.pdf  (120 KB)
        |-- notes.txt  (15 KB)
    [+] Pictures/
        |-- photo.jpg  (500 KB)
Total size: 635 KB
```
