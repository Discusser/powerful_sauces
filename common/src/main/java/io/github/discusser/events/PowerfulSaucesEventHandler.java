package io.github.discusser.events;

import dev.architectury.event.events.client.ClientTooltipEvent;
import io.github.discusser.PowerfulSaucesUtil;
import io.github.discusser.objects.items.SauceItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;

public class PowerfulSaucesEventHandler {
    public static void register() {
        ClientTooltipEvent.ITEM.register(PowerfulSaucesEventHandler::clientTooltipItem);
    }

    private static void clientTooltipItem(ItemStack stack, List<Component> lines, TooltipFlag flag) {
        List<SauceItem> sauces = PowerfulSaucesUtil.tryGetSauces(stack);
        if (sauces.isEmpty()) return;

        MutableComponent component = Component.translatable("text.powerful_sauces.sauced_with").withStyle(ChatFormatting.GRAY);
        for (int i = 0; i < sauces.size(); i++) {
            SauceItem sauce = sauces.get(i);

            MutableComponent sauceName = (MutableComponent) sauce.getName(new ItemStack(sauce));
            component.append(sauceName.withStyle(sauce.getStyle()));

            if (i < sauces.size() - 1) {
                component.append(", ");
            }
        }
        lines.add(component);

        for (SauceItem sauce : sauces) {
            for (Component effectDescription : PowerfulSaucesUtil.getEffectDescriptions(sauce)) {
                if (lines.stream().noneMatch(component1 -> component1.getString().equals(effectDescription.getString()))) {
                    lines.add(effectDescription);
                }
            }
        }

        if (PowerfulSaucesUtil.shouldGiveHunger(sauces.size())) {
            lines.add(Component.translatable("text.powerful_sauces.sauce_overload_description")
                            .withStyle(ChatFormatting.GRAY)
                            .append(" ")
                            .append(PowerfulSaucesUtil.getEffectDescription(
                                    PowerfulSaucesUtil.getHungerForSauces(sauces.size())
                            )));
        }
    }
}
