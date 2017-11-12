package helpers;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardHelper {

    private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    private StringSelection stringSelection;

    public String getTextFromClipboard() throws IOException, UnsupportedFlavorException {
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public void insertTextInClipboard(String text) {
        stringSelection = new StringSelection(text);
        clipboard.setContents(stringSelection, null);
    }
}
