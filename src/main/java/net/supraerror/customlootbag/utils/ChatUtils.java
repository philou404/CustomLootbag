package net.supraerror.customlootbag.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

/**
 * The type Chat utils.
 */
public final class ChatUtils {
    /**
     * Sends a message to all players on the server.
     *
     * @param level   The level (world) to get players from.
     * @param message The message to send.
     */
    public static void sendMessageToAllPlayers(Level level, String message) {
        Component chatMessage = Component.literal(message);

        // Iterate over all players in the level and send them the message
        level.players().forEach(player -> {
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.sendSystemMessage(chatMessage);
            }
        });
    }


}
