import java.util.*;

public class FileOrganizer {

    // Structure to hold file information
    static class FileInfo {
        String originalName;
        String baseName;
        String extension;
        String category;
        String newName;
    }

    // (b) Extract file components using substring() and lastIndexOf()
    static FileInfo extractFileInfo(String fileName) {
        FileInfo info = new FileInfo();
        info.originalName = fileName;

        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            info.baseName = fileName.substring(0, dotIndex);
            info.extension = fileName.substring(dotIndex + 1).toLowerCase();
        } else {
            info.baseName = fileName;
            info.extension = "";
        }
        return info;
    }

    // (c) Categorize files by extension
    static String categorize(String ext) {
        if (ext.equals("txt") || ext.equals("doc") || ext.equals("pdf"))
            return "Document";
        else if (ext.equals("jpg") || ext.equals("png") || ext.equals("gif"))
            return "Image";
        else if (ext.equals("mp3") || ext.equals("wav"))
            return "Audio";
        else if (ext.equals("mp4") || ext.equals("mkv"))
            return "Video";
        else if (ext.equals("java") || ext.equals("cpp") || ext.equals("py"))
            return "Code";
        else if (ext.equals("")) 
            return "Unknown";
        else
            return "Other";
    }

    // (d) Generate new filename using StringBuilder
    static String generateNewName(String category, String baseName, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append(category.substring(0, 3).toUpperCase()); // first 3 letters of category
        sb.append("_");
        sb.append(baseName.replaceAll("[^a-zA-Z0-9]", "")); // remove invalid chars
        sb.append("_");
        sb.append(index);
        sb.append(".txt"); // default extension for demo
        return sb.toString();
    }

    // (e) Simulate content analysis (basic keyword detection for text files)
    static String contentAnalysis(String fileName) {
        if (fileName.toLowerCase().contains("resume")) return "Resume";
        if (fileName.toLowerCase().contains("report")) return "Report";
        if (fileName.toLowerCase().contains("code")) return "Code";
        return "General";
    }

    // (f) Display report in tabular format
    static void displayReport(ArrayList<FileInfo> files) {
        System.out.printf("%-20s %-12s %-20s\n", "Original Name", "Category", "New Name");
        System.out.println("--------------------------------------------------------------");
        for (FileInfo f : files) {
            System.out.printf("%-20s %-12s %-20s\n", f.originalName, f.category, f.newName);
        }
    }

    // (g) Generate batch rename commands
    static void generateRenameCommands(ArrayList<FileInfo> files) {
        System.out.println("\nBatch Rename Commands:");
        for (FileInfo f : files) {
            System.out.println("rename " + f.originalName + " -> " + f.newName);
        }
    }

    // MAIN
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of files:");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        ArrayList<FileInfo> files = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter file name " + (i+1) + ": ");
            String name = sc.nextLine();

            FileInfo info = extractFileInfo(name);
            info.category = categorize(info.extension);
            info.newName = generateNewName(info.category, info.baseName, i+1);

            files.add(info);
        }

        // Display report
        displayReport(files);

        // Show batch rename commands
        generateRenameCommands(files);
    }
}
