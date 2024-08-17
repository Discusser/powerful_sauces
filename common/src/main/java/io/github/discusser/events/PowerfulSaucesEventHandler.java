package io.github.discusser.events;

import dev.architectury.event.events.client.ClientTooltipEvent;
import io.github.discusser.PowerfulSaucesUtil;
import io.github.discusser.objects.items.SauceItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class PowerfulSaucesEventHandler {
    public static void register() {
        ClientTooltipEvent.ITEM.register(PowerfulSaucesEventHandler::clientTooltipItem);
    }

    private static void clientTooltipItem(ItemStack stack, List<Component> lines, TooltipFlag flag) {
        SauceItem sauce = PowerfulSaucesUtil.tryGetSauce(stack);

        if (sauce != null) {
            MutableComponent sauceName = (MutableComponent) sauce.getName(new ItemStack(sauce));
            lines.add(Component.translatable("text.powerful_sauces.sauced_with", sauceName.withStyle(sauce.getStyle())).withStyle(ChatFormatting.GRAY));
        }
    }
}
