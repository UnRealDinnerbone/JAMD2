package com.unrealdinnerbone.jamd.fabric;

import com.unrealdinnerbone.jamd.JamdExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loom.configuration.FabricApiExtension;
import net.minecraft.server.commands.TeleportCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.nio.file.Path;

public class JamdExpectPlatformImpl {
    /**
     * This is our actual method to {@link JamdExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }

//    public static void teleportPlayer(ServerPlayer player, ServerLevel serverLevel, int x, int y, int z) {
//        throw player.tel
//    }
}
