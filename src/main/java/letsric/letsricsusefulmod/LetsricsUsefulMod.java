package letsric.letsricsusefulmod;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

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
        TablistInToggleMode = Boolean.parseBoolean(optionsFileData2.get(0)[1]);
        int autoTextArraySize = Integer.parseInt(optionsFileData2.get(1)[1]);
        for (int i = 0 ; i < autoTextArraySize ; i++) {
            String i2 = optionsFileData2.get(i + 2)[1];
            if (AutoText.addAutoTextKeybind(i2) == 0) {
                autoTextArray.add(i2);
            }
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
