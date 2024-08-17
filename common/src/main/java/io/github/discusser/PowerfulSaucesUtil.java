package io.github.discusser;

import io.github.discusser.objects.items.SauceItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.mojang.text2speech.Narrator.LOGGER;
import static io.github.discusser.PowerfulSauces.MOD_ID;

public class PowerfulSaucesUtil {
    public static boolean isSauced(ItemStack stack) {
        return tryGetSauceTag(stack) != null;
    }

    public static String tryGetSauceTag(ItemStack stack) {
        if (stack.getTag() == null) return null;

        String location = stack.getTag().getString(MOD_ID + ":sauce");
        if (location.isEmpty()) return null;

        return location;
    }

    public static ResourceLocation tryGetSauceLocation(ItemStack stack) {
        String location = tryGetSauceTag(stack);
        if (location == null) return null;

        return ResourceLocation.tryParse(location);
    }

    public static SauceItem tryGetSauce(ItemStack stack) {
        ResourceLocation resourceLocation = tryGetSauceLocation(stack);
        if (resourceLocation == null) return null;

        Item item = BuiltInRegistries.ITEM.get(resourceLocation);
        if (!(item instanceof SauceItem sauce)) return null;

        return sauce;
    }
}
