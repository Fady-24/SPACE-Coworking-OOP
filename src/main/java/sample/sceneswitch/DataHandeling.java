package sample.sceneswitch;

import java.io.*;
import java.util.ArrayList;

public class DataHandeling {
    public static void removeObject(String filePath, Object targetObject) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        ArrayList<Object> objects = new ArrayList<>();

        // Step 1: Read all objects from the file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            objects = (ArrayList<Object>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Step 2: Remove the target object
        boolean removed = objects.removeIf(obj -> obj.equals(targetObject));
        if (!removed) {
            System.out.println("Object not found.");
            return;
        }

        // Step 3: Write the updated objects back to the file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(objects);
            System.out.println("Object removed successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}
