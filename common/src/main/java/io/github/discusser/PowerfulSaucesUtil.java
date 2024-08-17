package io.github.discusser;

import io.github.discusser.objects.items.SauceItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static com.mojang.text2speech.Narrator.LOGGER;
import static io.github.discusser.PowerfulSauces.MOD_ID;

public class PowerfulSaucesUtil {
    public static SauceItem tryGetSauce(ItemStack stack) {
        if (stack.getTag() != null) {
            String location = stack.getTag().getString(MOD_ID + ":sauce");
            if (location.isEmpty())
                return null;

            ResourceLocation resourceLocation = ResourceLocation.tryParse(location);
            if (resourceLocation == null) {
                LOGGER.debug("Tried to unsuccessfully parse resource location from '" + location + "' found in tag of item '" + stack + "'");
                return null;
            }

            Item item = BuiltInRegistries.ITEM.get(resourceLocation);
            if (!(item instanceof SauceItem sauce)) {
                LOGGER.debug("An item that is not an instance of SauceItem was found in the '" + MOD_ID + ":sauce' tag");
                return null;
            }

            return sauce;
        }

        return null;
    }
}
