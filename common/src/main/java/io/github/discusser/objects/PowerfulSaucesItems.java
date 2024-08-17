package io.github.discusser.objects;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.discusser.objects.items.SauceItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import java.util.Set;

import static io.github.discusser.PowerfulSauces.MOD_ID;

@SuppressWarnings("unused")
public class PowerfulSaucesItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final FoodProperties SAUCE_FOOD = new FoodProperties.Builder().nutrition(1).build();

    public static final RegistrySupplier<SauceItem> KETCHUP = registerSauce("ketchup", Set.of(
            new MobEffectInstance(MobEffects.REGENERATION, 10 * 20, 0)
    ), 0x850101);

    public static final RegistrySupplier<SauceItem> MUSTARD = registerSauce("mustard", Set.of(
            new MobEffectInstance(MobEffects.ABSORPTION,  20 * 20, 0)
    ), 0xffdb58);

    public static RegistrySupplier<SauceItem> registerSauce(String name, Set<MobEffectInstance> effects, int textColor) {
        return ITEMS.register(name, () -> new SauceItem(
                new Item.Properties().food(SAUCE_FOOD).arch$tab(PowerfulSaucesTabs.SAUCES_TAB.get()),
                effects,
                TextColor.fromRgb(textColor)
        ));
    }
}
