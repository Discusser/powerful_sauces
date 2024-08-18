package io.github.discusser.objects;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.discusser.objects.items.AugmentedSauceItem;
import io.github.discusser.objects.items.SauceItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.discusser.PowerfulSauces.MOD_ID;

@SuppressWarnings("unused")
public class PowerfulSaucesItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final List<SauceBottle> SAUCE_BOTTLES = new ArrayList<>();
    public static final List<RegistrySupplier<Item>> INGREDIENTS = new ArrayList<>();

    public static final FoodProperties SAUCE_FOOD = new FoodProperties.Builder().nutrition(1).build();

    public static final SauceBottle KETCHUP = registerAugmentableSauceBottle("ketchup", Set.of(
            new MobEffectInstance(MobEffects.REGENERATION, 10 * 20, 0)
    ), 0xec0606);

    public static final SauceBottle MUSTARD = registerAugmentableSauceBottle("mustard", Set.of(
            new MobEffectInstance(MobEffects.ABSORPTION, 20 * 20, 0)
    ), 0xffdb58);

    public static final SauceBottle MAYONNAISE = registerAugmentableSauceBottle("mayonnaise", Set.of(
            new MobEffectInstance(MobEffects.HEALTH_BOOST, 20 * 20, 0)
    ), 0xfaf3e4);

    public static final SauceBottle BARBECUE = registerAugmentableSauceBottle("barbecue", Set.of(
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 20, 0)
    ), 0x4a130c);

    public static final SauceBottle WORCESTERSHIRE = registerAugmentableSauceBottle("worcestershire", Set.of(
            new MobEffectInstance(MobEffects.HUNGER, 10 * 20, 0)
    ), 0x572b26);

    public static final SauceBottle MUSHROOM = registerAugmentableSauceBottle("mushroom", Set.of(
            new MobEffectInstance(MobEffects.HEAL, 1, 0)
    ), 0xd1c9b4);

    public static final SauceBottle CHILI = registerAugmentableSauceBottle("chili", Set.of(
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 20, 0)
    ), 0xbc4e40);

    public static final RegistrySupplier<Item> SAUCE_BOTTLE = ITEMS.register("sauce_bottle",
            () -> new Item(new Item.Properties().arch$tab(PowerfulSaucesTabs.SAUCES_TAB)));

    public static final RegistrySupplier<Item> SPICE_MIX = registerIngredient("spice_mix");
    public static final RegistrySupplier<Item> VINEGAR = registerIngredient("vinegar");
    public static final RegistrySupplier<Item> BROWN_SUGAR = registerIngredient("brown_sugar");
    public static final RegistrySupplier<Item> TOMATO_PASTE = registerIngredient("tomato_paste");

    public static SauceBottle registerAugmentableSauceBottle(String name, Set<MobEffectInstance> effects, int textColor) {
        Set<MobEffectInstance> augmentedEffects = effects.stream().map(effect -> new MobEffectInstance(
                effect.getEffect(),
                (int) (effect.getDuration() * 1.5),
                effect.getAmplifier() + 1)
        ).collect(Collectors.toSet());

        RegistrySupplier<SauceItem> sauceBottle = registerSauce(name, effects, textColor);
        RegistrySupplier<AugmentedSauceItem> augmentedSauceBottle = ITEMS.register("augmented_" + name,
                () -> new AugmentedSauceItem(
                        new Item.Properties().food(SAUCE_FOOD).arch$tab(PowerfulSaucesTabs.SAUCES_TAB),
                        augmentedEffects,
                        TextColor.fromRgb(textColor)
                ));

        SauceBottle bottle = new SauceBottle(sauceBottle, augmentedSauceBottle);
        SAUCE_BOTTLES.add(bottle);

        return bottle;
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
