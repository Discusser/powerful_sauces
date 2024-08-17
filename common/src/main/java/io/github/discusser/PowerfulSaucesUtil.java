package io.github.discusser;

import io.github.discusser.objects.items.SauceItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static io.github.discusser.PowerfulSauces.MOD_ID;

public class PowerfulSaucesUtil {
    public static boolean isSauced(ItemStack stack) {
        ListTag tag = tryGetSaucesTag(stack);
        return tag != null && !tag.isEmpty();
    }

    public static ListTag tryGetSaucesTag(ItemStack stack) {
        if (stack.getTag() == null) return null;

        return stack.getTag().getList(MOD_ID + ":sauces", Tag.TAG_STRING);
    }

    public static List<SauceItem> tryGetSauces(ItemStack stack) {
        ListTag listTag = tryGetSaucesTag(stack);
        if (listTag == null) return List.of();

        List<SauceItem> sauces = new ArrayList<>();
        for (int i = 0; i < listTag.size(); i++) {
            String location = listTag.getString(i);
            ResourceLocation resourceLocation = ResourceLocation.tryParse(location);
            if (resourceLocation == null) return List.of();

            Item item = BuiltInRegistries.ITEM.get(resourceLocation);
            if (!(item instanceof SauceItem sauce)) return List.of();

            sauces.add(sauce);
        }

        return sauces;
    }
}
