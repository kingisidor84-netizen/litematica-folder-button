package com.example.litmfolder.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Environment(EnvType.CLIENT)
public class LitematicaScreenHandler {
    private static final String SCHEMATIC_FOLDER_NAME = "schematics";

    public static void setupScreenListener() {
        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen != null && isLitematicaScreen(screen)) {
                addFolderButton(screen, scaledWidth, scaledHeight);
            }
        });
    }

    private static boolean isLitematicaScreen(Screen screen) {
        String screenName = screen.getClass().getName();
        return screenName.contains("litematica") && screenName.contains("MainScreen");
    }

    private static void addFolderButton(Screen screen, int width, int height) {
        ButtonWidget folderButton = ButtonWidget.builder(
                Text.literal("📁 Schematics"),
                button -> openSchematicFolder()
        )
                .position(width - 110, 10)
                .size(100, 20)
                .build();

        screen.addDrawableChild(folderButton);
    }

    private static void openSchematicFolder() {
        try {
            Path schematicPath = getSchematicPath();
            Files.createDirectories(schematicPath);
            
            File folder = schematicPath.toFile();
            
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(folder);
            } else {
                String os = System.getProperty("os.name").toLowerCase();
                if (os.contains("win")) {
                    Runtime.getRuntime().exec("explorer.exe /select," + folder.getAbsolutePath());
                } else if (os.contains("mac")) {
                    Runtime.getRuntime().exec(new String[]{"open", folder.getAbsolutePath()});
                } else if (os.contains("nux")) {
                    Runtime.getRuntime().exec(new String[]{"xdg-open", folder.getAbsolutePath()});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path getSchematicPath() {
        String gameDir = System.getProperty("user.dir");
        return Paths.get(gameDir, SCHEMATIC_FOLDER_NAME);
    }
}
