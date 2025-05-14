import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FabricPayMod implements ModInitializer {

    @Override
    public void onInitialize() {
        // Mod initialization logic
        System.out.println("FabricPayMod is initializing!");
    }

    public static void handlePayment(String chatMessage) {
        // Match amounts from 100k to 2m
        Pattern pattern = Pattern.compile("(\\w+) Paid you ((100k|200k|300k|400k|500k|600k|700k|800k|900k|1m|1\\.1m|1\\.2m|1\\.3m|1\\.4m|1\\.5m|1\\.6m|1\\.7m|1\\.8m|1\\.9m|2m))");
        Matcher matcher = pattern.matcher(chatMessage);

        if (matcher.find()) {
            String username = matcher.group(1);
            String amountStr = matcher.group(2);
            String doubledAmount = doubleAmount(amountStr);

            Random random = new Random();
            if (random.nextInt(100) < 30) { // 30% chance
                String command = "/pay " + username + " " + doubledAmount;
                sendCommand(command);
                sendMessage(username, "Wow you just hit the jackpot");
            } else {
                sendMessage(username, "Oh no, you lost good luck next time");
            }
        }
    }

    // Doubles the amount string (basic string conversion)
    public static String doubleAmount(String amount) {
        if (amount.endsWith("k")) {
            int value = Integer.parseInt(amount.replace("k", ""));
            return (value * 2) + "k";
        } else if (amount.endsWith("m")) {
            double value = Double.parseDouble(amount.replace("m", ""));
            return (value * 2) + "m";
        }
        return amount; // fallback
    }

    // Simulates sending a command in Minecraft
    public static void sendCommand(String command) {
        System.out.println("Executing command: " + command);
    }

    // Simulates sending a message to the player
    public static void sendMessage(String username, String message) {
        System.out.println("/msg " + username + " " + message);
    }
}
