package com.unrealdinnerbone.jamd;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import me.shedaniel.architectury.platform.Platform;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

import java.nio.file.Path;

public class JamdExpectPlatform {
    /**
     * We can use {@link Platform#getConfigFolder()} but this is just an example of {@link ExpectPlatform}.
     * <p>
     * This must be a public static method. The platform-implemented solution must be placed under a
     * platform sub-package, with its class suffixed with {@code Impl}.
     * <p>
     * Example:
     * Expect: net.examplemod.ExampleExpectPlatform#getConfigDirectory()
     * Actual Fabric: net.examplemod.fabric.ExampleExpectPlatformImpl#getConfigDirectory()
     * Actual Forge: net.examplemod.forge.ExampleExpectPlatformImpl#getConfigDirectory()
     */
    @ExpectPlatform
    public static Path getConfigDirectory() {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }


//    @ExpectPlatform
//    public static void teleportPlayer(Player player, ServerLevel serverLevel, int x, int y, int z) {
//        throw new AssertionError();
//    }
}
