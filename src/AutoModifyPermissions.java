//package org.boreas320.auto.permissiom.modify;

import com.sun.nio.file.SensitivityWatchEventModifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

public class AutoModifyPermissions {

    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(args[0]);
        path.register(watchService, new WatchEvent.Kind[]{ENTRY_CREATE}, SensitivityWatchEventModifier.HIGH);
        System.out.println("ready to watch!");
        while (true) {

            WatchKey key = watchService.take();
            List<WatchEvent<?>> watchEvents = key.pollEvents();


            watchEvents.forEach(watchEvent -> {
                WatchEvent.Kind<?> kind = watchEvent.kind();
                if (kind == OVERFLOW) {
                    return;
                }
                Path context = (Path) watchEvent.context();
                if (!context.toFile().getName().endsWith(".js")) {
                    return;
                }
                Path resolve = path.resolve(context);
                File created = resolve.toFile();
                if (!created.canExecute()) {
                    boolean b = created.setExecutable(true, false);
                    System.out.printf("set %s exists %b executable %b \n", created, created.exists(), b);
                }
            });
            key.reset();

        }

    }
}
