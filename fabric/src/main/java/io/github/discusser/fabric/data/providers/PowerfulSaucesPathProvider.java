package io.github.discusser.fabric.data.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class PowerfulSaucesPathProvider extends PackOutput.PathProvider {
    private final Path root;
    private final String kindCopy;

    public PowerfulSaucesPathProvider(FabricDataOutput dataGenerator, PackOutput.Target target, String string) {
        super(dataGenerator, target, string);
        this.root = dataGenerator.getOutputFolder().resolve("dependents/platform/");
        this.kindCopy = string;
    }

    @Override
    public @NotNull Path file(ResourceLocation resourceLocation, String string) {
        Path type = this.root.resolve(this.kindCopy);
        String path = resourceLocation.getPath();
        return type.resolve(path + "." + string);
    }

    @Override
    public @NotNull Path json(ResourceLocation resourceLocation) {
        return this.root.resolve(this.kindCopy).resolve(resourceLocation.getPath() + ".json");
    }
}
