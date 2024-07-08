import java.awt.AWTException;
import java.io.IOException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
public class Piano {
    private static Map<Character, Integer> keyMap = new HashMap<>();
    private static Robot robot;
    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        keyMap.put('1', KeyEvent.VK_1);
        keyMap.put('2', KeyEvent.VK_2);
        keyMap.put('3', KeyEvent.VK_3);
        keyMap.put('4', KeyEvent.VK_4);
        keyMap.put('5', KeyEvent.VK_5);
        keyMap.put('6', KeyEvent.VK_6);
        keyMap.put('7', KeyEvent.VK_7);
        keyMap.put('8', KeyEvent.VK_8);
        keyMap.put('9', KeyEvent.VK_9);
        keyMap.put('0', KeyEvent.VK_0);
        keyMap.put('q', KeyEvent.VK_Q);
        keyMap.put('w', KeyEvent.VK_W);
        keyMap.put('e', KeyEvent.VK_E);
        keyMap.put('r', KeyEvent.VK_R);
        keyMap.put('t', KeyEvent.VK_T);
        keyMap.put('y', KeyEvent.VK_Y);
        keyMap.put('u', KeyEvent.VK_U);
        keyMap.put('i', KeyEvent.VK_I);
        keyMap.put('o', KeyEvent.VK_O);
        keyMap.put('p', KeyEvent.VK_P);
        keyMap.put('a', KeyEvent.VK_A);
        keyMap.put('s', KeyEvent.VK_S);
        keyMap.put('d', KeyEvent.VK_D);
        keyMap.put('f', KeyEvent.VK_F);
        keyMap.put('g', KeyEvent.VK_G);
        keyMap.put('h', KeyEvent.VK_H);
        keyMap.put('j', KeyEvent.VK_J);
        keyMap.put('k', KeyEvent.VK_K);
        keyMap.put('l', KeyEvent.VK_L);
        keyMap.put('z', KeyEvent.VK_Z);
        keyMap.put('x', KeyEvent.VK_X);
        keyMap.put('c', KeyEvent.VK_C);
        keyMap.put('v', KeyEvent.VK_V);
        keyMap.put('b', KeyEvent.VK_B);
        keyMap.put('n', KeyEvent.VK_N);
        keyMap.put('m', KeyEvent.VK_M);
    }
    public static void main(String[] args) {
        try {
            String sheet = new String(Files.readAllBytes(Paths.get("sheet.txt")));
            int bpm = Integer.parseInt(new String(Files.readAllBytes(Paths.get("bpm.txt"))).trim());
            int timeA = (60000 / (bpm * 2));
            Thread.sleep(1500);
            boolean noTimeout = false;
            for (char a : sheet.toCharArray()) {
                if (Character.isWhitespace(a)) {
                    continue;
                }

                if (a == '[') {
                    noTimeout = true;
                    Thread.sleep((long) (timeA /2));
                    continue;
                } else if (a == ']') {
                    noTimeout = false;
                    continue;
                }

                if (!noTimeout) {
                    if (a != ' ') {
                        Thread.sleep((long) (timeA /2));
                    }
                }
                
                keyInject(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void keyInject(char key) {
        boolean upper = false;
        char lowerKey = Character.toLowerCase(key);
        if (lowerKey != key) {
            upper = true;
        }
        if (key == '^') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_6);
            robot.keyRelease(KeyEvent.VK_6);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (key == '(') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_9);
            robot.keyRelease(KeyEvent.VK_9);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (key == '*') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_8);
            robot.keyRelease(KeyEvent.VK_8);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (key == '%') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_5);
            robot.keyRelease(KeyEvent.VK_5);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (key == '$') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_4);
            robot.keyRelease(KeyEvent.VK_4);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (key == '@') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_2);
            robot.keyRelease(KeyEvent.VK_2);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else if (key == '!') {
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_1);
            robot.keyRelease(KeyEvent.VK_SHIFT);
        } else {
            if (upper) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
            keyPress(lowerKey);
            if (upper) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
    }
    private static void keyPress(char key) {
        if (keyMap.containsKey(key)) {
            robot.keyPress(keyMap.get(key));
            robot.keyRelease(keyMap.get(key));
        }
    }
}
