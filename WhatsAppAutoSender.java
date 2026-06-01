import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * A Java program to send a message 100 times using AWT Robot.
 * * IMPORTANT: You must have WhatsApp Web open and the chat thread 
 * selected and focused BEFORE the 10-second delay runs out.
 */
public class WhatsAppAutoSender {

    private static final String MESSAGE_TO_SEND = "Hi";
    private static final int NUMBER_OF_TIMES = 100;
    private static final int START_DELAY_MS = 10000; // 10 seconds to switch windows
    private static final int MESSAGE_DELAY_MS = 0;  // Small delay between sending each message

    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            
            // 1. Prepare the message for copy/paste
            setClipboard(MESSAGE_TO_SEND);

            // 2. Initial Delay to let the user switch to the chat window
            System.out.println("--- Starting WhatsApp Auto Sender ---");
            System.out.println("ACTION: Switch to your WhatsApp Web chat window now! (10 seconds)");
            robot.delay(START_DELAY_MS);
            
            // 3. Main loop to paste and send the message
            for (int i = 1; i <= NUMBER_OF_TIMES; i++) {
                System.out.println("Sending message " + i + " of " + NUMBER_OF_TIMES + "...");
                
                // Simulate Ctrl+V (Paste) or Cmd+V on Mac
                paste(robot);
                
                // Simulate Enter key to send the message
                pressEnter(robot);
                
                // Wait briefly before sending the next one
                robot.delay(MESSAGE_DELAY_MS);
            }

            System.out.println("Finished sending " + NUMBER_OF_TIMES + " messages.");

        } catch (AWTException e) {
            System.err.println("AWT Robot is unavailable. Check security permissions.");
            e.printStackTrace();
        }
    }

    /**
     * Puts the given string onto the system clipboard.
     */
    private static void setClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    /**
     * Simulates the system's paste command (Ctrl+V or Cmd+V).
     */
    private static void paste(Robot robot) {
        // Platform check to determine the correct modifier key
        int mask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
        int vKey = KeyEvent.VK_V;
        
        robot.keyPress(mask);
        robot.keyPress(vKey);
        robot.keyRelease(vKey);
        robot.keyRelease(mask);
    }

    /**
     * Simulates pressing and releasing the Enter key.
     */
    private static void pressEnter(Robot robot) {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}