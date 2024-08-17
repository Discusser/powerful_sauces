package io.github.discusser.objects.items;

import io.github.discusser.PowerfulSaucesUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
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

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        list.addAll(PowerfulSaucesUtil.getEffectDescriptions(this));

        super.appendHoverText(itemStack, level, list, tooltipFlag);
    }
}
