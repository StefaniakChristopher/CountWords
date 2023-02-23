import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.print("\nType a filename: ");
        Scanner filenameAsker = new Scanner(System.in);
        String filename = filenameAsker.nextLine();

        ArrayList<String> fileContents = new ArrayList<>();
        readFile(filename, fileContents);
        filenameAsker.close();
        ArrayList<SimpleEntry<String, Integer>> wordsAndCount =  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>();
        processFile(wordsAndCount, fileContents);
        writeToFile(wordsAndCount);
        
    }

    public static void readFile(String filename, ArrayList<String> fileContents) {
        try {
            File fileToRead = new File(filename);
            Scanner fileReader = new Scanner(fileToRead);
            while (fileReader.hasNextLine()) {
                String word = fileReader.nextLine();
                fileContents.add(word);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        }
        
    }

    public static void processFile(ArrayList<SimpleEntry<String, Integer>> wordsAndCount, ArrayList<String> fileContents) {
        ArrayList<String> fileContentsWithoutDuplicates = new ArrayList<>();
        for (String word: fileContents) {
            if(!fileContentsWithoutDuplicates.contains(word)) {
                fileContentsWithoutDuplicates.add(word);
            }
        }
        for (String nonDupedWord: fileContentsWithoutDuplicates) {
            int wordInstances = 0;
            for (String word: fileContents) {
                if (nonDupedWord.equals(word)) {
                    wordInstances += 1;
                } 
            }
            wordsAndCount.add(new AbstractMap.SimpleEntry<>(nonDupedWord, wordInstances));
        }
    }

    public static void writeToFile(ArrayList<SimpleEntry<String, Integer>> wordsAndCount) {
        try {
            File file = new File("countedWords.txt");
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < wordsAndCount.size(); i++) {
                AbstractMap.SimpleEntry<String, Integer> map = wordsAndCount.get(i);
                fileWriter.write(map.getKey() + " " + map.getValue() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occured");
        }
        

    }

}
