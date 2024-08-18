package io.github.discusser.objects;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.discusser.objects.items.SauceItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static io.github.discusser.PowerfulSauces.MOD_ID;

@SuppressWarnings("unused")
public class PowerfulSaucesItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final List<RegistrySupplier<SauceItem>> SAUCE_BOTTLES = new ArrayList<>();
    public static final List<RegistrySupplier<Item>> INGREDIENTS = new ArrayList<>();

    public static final FoodProperties SAUCE_FOOD = new FoodProperties.Builder().nutrition(1).build();

    public static final RegistrySupplier<SauceItem> KETCHUP = registerSauceBottle("ketchup", Set.of(
            new MobEffectInstance(MobEffects.REGENERATION, 10 * 20, 0)
    ), 0xec0606);

    public static final RegistrySupplier<SauceItem> MUSTARD = registerSauceBottle("mustard", Set.of(
            new MobEffectInstance(MobEffects.ABSORPTION, 20 * 20, 0)
    ), 0xffdb58);

    public static final RegistrySupplier<SauceItem> MAYONNAISE = registerSauceBottle("mayonnaise", Set.of(
            new MobEffectInstance(MobEffects.HEALTH_BOOST, 20 * 20, 0)
    ), 0xfaf3e4);

    public static final RegistrySupplier<SauceItem> BARBECUE = registerSauceBottle("barbecue", Set.of(
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 20, 0)
    ), 0x4a130c);

    public static final RegistrySupplier<SauceItem> WORCESTERSHIRE = registerSauceBottle("worcestershire", Set.of(
            new MobEffectInstance(MobEffects.HUNGER, 10 * 20, 0)
    ), 0x572b26);

    public static final RegistrySupplier<SauceItem> MUSHROOM = registerSauceBottle("mushroom", Set.of(
            new MobEffectInstance(MobEffects.HEAL, 10 * 20, 0)
    ), 0xd1c9b4);

    public static final RegistrySupplier<SauceItem> CHILI = registerSauceBottle("chili", Set.of(
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 20, 0)
    ), 0xbc4e40);

    public static final RegistrySupplier<Item> SAUCE_BOTTLE = ITEMS.register("sauce_bottle",
            () -> new Item(new Item.Properties().arch$tab(PowerfulSaucesTabs.SAUCES_TAB)));

    public static final RegistrySupplier<Item> SPICE_MIX = registerIngredient("spice_mix");
    public static final RegistrySupplier<Item> VINEGAR = registerIngredient("vinegar");
    public static final RegistrySupplier<Item> BROWN_SUGAR = registerIngredient("brown_sugar");
    public static final RegistrySupplier<Item> TOMATO_PASTE = registerIngredient("tomato_paste");

    public static RegistrySupplier<SauceItem> registerSauceBottle(String name, Set<MobEffectInstance> effects, int textColor) {
        RegistrySupplier<SauceItem> item = registerSauce(name, effects, textColor);
        SAUCE_BOTTLES.add(item);
        return item;
    }

    public static RegistrySupplier<SauceItem> registerSauce(String name, Set<MobEffectInstance> effects, int textColor) {
        return ITEMS.register(name, () -> new SauceItem(
                new Item.Properties().food(SAUCE_FOOD).arch$tab(PowerfulSaucesTabs.SAUCES_TAB),
                effects,
                TextColor.fromRgb(textColor)
        ));
    }

    public static RegistrySupplier<Item> registerIngredient(String name) {
        RegistrySupplier<Item> item = ITEMS.register(name,
                () -> new Item(new Item.Properties().arch$tab(PowerfulSaucesTabs.SAUCES_TAB)));
        INGREDIENTS.add(item);
        return item;
    }
}
