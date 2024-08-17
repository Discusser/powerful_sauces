package io.github.discusser.objects.items;

import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;

import java.util.Set;

public class SauceItem extends Item {
    public final Set<MobEffectInstance> effects;
    public final TextColor textColor;

    public SauceItem(Properties properties, Set<MobEffectInstance> effects, TextColor textColor) {
        super(properties);
        this.effects = effects;
        this.textColor = textColor;
    }

    public Style getStyle() {
        return Style.EMPTY.withColor(this.textColor.getValue());
    }
}
