package com.unrealdinnerbone.jamd.forge;

import com.unrealdinnerbone.jamd.JamdExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class JamdExpectPlatformImpl {
    /**
     * This is our actual method to {@link JamdExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }

}
