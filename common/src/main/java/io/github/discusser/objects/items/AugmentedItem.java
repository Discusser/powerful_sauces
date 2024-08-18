package io.github.discusser.objects.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AugmentedItem extends Item {
    public AugmentedItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return true;
    }
}
