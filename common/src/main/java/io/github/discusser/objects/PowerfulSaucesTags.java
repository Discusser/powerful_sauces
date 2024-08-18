package io.github.discusser.objects;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PowerfulSaucesTags {
    public static final TagKey<Item> SAUCES = TagKey.create(Registries.ITEM, new ResourceLocation("c", "sauces"));
}
