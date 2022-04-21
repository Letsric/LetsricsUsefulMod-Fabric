package letsric.letsricsusefulmod;

import net.fabricmc.api.ModInitializer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class LetsricsUsefulMod implements ModInitializer {
    public static boolean TablistInToggleMode = false;
    public static ArrayList<String> autoTextArray = new ArrayList<>();

    @Override
    public void onInitialize() {
        LoadUFMOptionsFile();
    }

    public static void LoadUFMOptionsFile() {
        File optionsFile = new File("UsefulModOptions.txt");
        ArrayList<String> optionsFileData = new ArrayList<>();
        ArrayList<String[]> optionsFileData2 = new ArrayList<>();
        if (!optionsFile.exists()) {
            return;
        }
        try {
            Scanner reader = new Scanner(optionsFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                optionsFileData.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0 ; i < optionsFileData.size() ; i++) {
            optionsFileData2.add(optionsFileData.get(i).split(": "));
        }
        int nextline = 0;
        TablistInToggleMode = Boolean.parseBoolean(optionsFileData2.get(nextline)[1]);
        nextline++;
        int autoTextArraySize = Integer.parseInt(optionsFileData2.get(nextline)[1]);
        nextline++;
        for (int i = 0 ; i < autoTextArraySize ; i++) {
            String i2 = optionsFileData2.get(nextline)[1];
            if (AutoText.addAutoTextKeybind(i2) == 0) {
                autoTextArray.add(i2);
            }
            nextline++;
        }
        int chatsoundfiltersize = Integer.parseInt(optionsFileData2.get(nextline)[1]);
        nextline++;
        for (int i = 0 ; i < chatsoundfiltersize ; i++) {
            ChatSound.addFilter(optionsFileData2.get(nextline)[1]);
            nextline++;
        }

    }
    public static void WriteUFMOptionsFile() {
        File optionsFile = new File("UsefulModOptions.txt");
        try (final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(optionsFile), StandardCharsets.UTF_8))) {
            printWriter.println("TablistInToggleMode: " + TablistInToggleMode);
            printWriter.println("autotextsize: " + autoTextArray.size());
            for (int i = 0 ; i < autoTextArray.size() ; i++) {
                printWriter.println("autotext" + i + ": " + autoTextArray.get(i));
            }
            int chatsoundfiltersize = ChatSound.Filters.size();
            printWriter.println("chatsoundfiltersize: " + chatsoundfiltersize);
            for (int i = 0 ; i < chatsoundfiltersize ; i++) printWriter.println("chatsoundfilter" + i + ": " + ChatSound.Filters.get(i));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
