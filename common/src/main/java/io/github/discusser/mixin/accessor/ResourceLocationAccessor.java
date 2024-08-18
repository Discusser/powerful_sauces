package io.github.discusser.mixin.accessor;

import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ResourceLocation.class)
public interface ResourceLocationAccessor {
    @Accessor("namespace") @Mutable
    void setNamespace(String namespace);
}
