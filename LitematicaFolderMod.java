package com.example.litmfolder;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LitematicaFolderMod implements ModInitializer {
    public static final String MOD_ID = "litematica-folder-button";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Litematica Folder Button Mod initialized!");
    }
}
