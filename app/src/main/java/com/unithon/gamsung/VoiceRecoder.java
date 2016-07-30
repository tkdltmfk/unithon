package com.unithon.gamsung;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class VoiceRecoder {
    String path;
    String fileName;
    FileOutputStream voiceFile;

    public VoiceRecoder(String path) {
        this.path = path;
    }

    public void open(String fileFinal) {
        File directory = new File(path);
        if (directory.exists() == false) {
            directory.mkdirs();
        }

        fileName = directory + "/" + fileFinal + ".pcm";
        try {
            voiceFile = new FileOutputStream(new File(fileName));
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void close() {
        if (voiceFile == null)
            return;

        try {
            voiceFile.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void write(short[] data) {
        if (voiceFile == null)
            return;

        ByteBuffer buffer = ByteBuffer.allocate(data.length * 2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < data.length; i++) {
            buffer.putShort(data[i]);
        }
        buffer.flip();

        try {
            voiceFile.write(buffer.array());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}