package org.example;
import java.io.IOException;
import java.io.InputStream;

public class CustomInputStream extends InputStream {

    private String text;
    private int position;

    public CustomInputStream(String text) {
        String[] lines = text.split("\\n");
        String lastLine = lines[lines.length - 1];
        this.text = lastLine;
        this.position = 0;

    }

    @Override
    public int read() throws IOException {
        if (position < text.length()) {
            char c = text.charAt(position++);
            return (int) c;
        } else {
            return -1;
        }
    }
    
}
