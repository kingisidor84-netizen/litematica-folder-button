package com.example.litmfolder.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class LitematicaFolderModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LitematicaScreenHandler.setupScreenListener();
    }
}
