package com.example.questao1.Manager;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileManager {
    public static void saveFile(Context context, String fileName, String fileContent) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContent.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
