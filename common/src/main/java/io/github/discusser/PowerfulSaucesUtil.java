package io.github.discusser;

import io.github.discusser.objects.items.SauceItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
        if (stack.getTag() == null)
            return null;

        return stack.getTag().getList(MOD_ID + ":sauces", Tag.TAG_STRING);
    }

    public static List<SauceItem> tryGetSauces(ItemStack stack) {
        ListTag listTag = tryGetSaucesTag(stack);
        if (listTag == null)
            return List.of();

        List<SauceItem> sauces = new ArrayList<>();
        for (int i = 0; i < listTag.size(); i++) {
            String location = listTag.getString(i);
            ResourceLocation resourceLocation = ResourceLocation.tryParse(location);
            if (resourceLocation == null)
                return List.of();

            Item item = BuiltInRegistries.ITEM.get(resourceLocation);
            if (!(item instanceof SauceItem sauce))
                return List.of();

            sauces.add(sauce);
        }

        return sauces;
    }

    public static MutableComponent getEffectDescription(MobEffectInstance effect) {
        MutableComponent effectName = ((MutableComponent) effect.getEffect().getDisplayName())
                .withStyle(Style.EMPTY.withColor(effect.getEffect().getColor()));

        return Component.translatable("text.powerful_sauces.effect_description",
                    effectName,
                    effect.getAmplifier() + 1,
                    effect.getDuration() / 20).withStyle(ChatFormatting.GRAY);
    }

    public static List<Component> getEffectDescriptions(SauceItem sauceItem) {
        List<Component> list = new ArrayList<>();

        for (MobEffectInstance effect : sauceItem.effects) {
            list.add(getEffectDescription(effect));
        }

        return list;
    }

    public static MobEffectInstance getHungerForSauces(int sauceCount) {
        return new MobEffectInstance(
                MobEffects.HUNGER,
                30 * 20,
                (int) Math.min(Math.pow(2, sauceCount - 3), 30));
    }

    public static boolean shouldGiveHunger(int sauceCount) {
        return sauceCount > 3;
    }
}
