package io.github.discusser.objects.items;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;

import java.util.Set;

public class AugmentedSauceItem extends SauceItem {
    public AugmentedSauceItem(Properties properties, Set<MobEffectInstance> effects, TextColor textColor) {
        super(properties, effects, textColor);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return true;
    }
}
