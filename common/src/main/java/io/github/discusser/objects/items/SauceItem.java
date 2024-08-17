package io.github.discusser.objects.items;

import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;

public class SauceItem extends Item {
    public final TextColor textColor;

    public SauceItem(Properties properties, TextColor textColor) {
        super(properties);
        this.textColor = textColor;
    }

    public Style getStyle() {
        return Style.EMPTY.withColor(this.textColor.getValue());
    }
}
