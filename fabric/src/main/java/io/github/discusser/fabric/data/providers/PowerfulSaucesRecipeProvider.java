package io.github.discusser.fabric.data.providers;

import io.github.discusser.objects.PowerfulSaucesItems;
import io.github.discusser.objects.SauceBottle;
import io.github.discusser.util.PowerfulSaucesUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class PowerfulSaucesRecipeProvider extends FabricRecipeProvider {
    public PowerfulSaucesRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    public ShapedRecipeBuilder shapedUnlockedBy(Item output, int count, ItemLike... unlockedBy) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, output, count)
                .unlockedBy("powerful_sauces:has_item", InventoryChangeTrigger.TriggerInstance.hasItems(unlockedBy));
    }

    public ShapelessRecipeBuilder shapelessUnlockedBy(Item output, int count, ItemLike... unlockedBy) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, output, count)
                .unlockedBy("powerful_sauces:has_item", InventoryChangeTrigger.TriggerInstance.hasItems(unlockedBy));
    }

    public ShapelessRecipeBuilder shapelessUnlockedBy(Item output, int count, TagKey<Item> unlockedBy) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, output, count)
                .unlockedBy("powerful_sauces:has_item", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(unlockedBy).build()
                ));
    }

    public ShapelessRecipeBuilder shapelessSauceBottleRecipe(Item output, int count) {
        return shapelessUnlockedBy(output, count, PowerfulSaucesItems.SAUCE_BOTTLE.get())
                .requires(PowerfulSaucesItems.SAUCE_BOTTLE.get());
    }

    public static TagKey<Item> globalTag(String name) {
        return TagKey.create(Registries.ITEM, PowerfulSaucesUtil.globalLoc(name));
    }

    public void buildIngredientRecipes(Consumer<FinishedRecipe> consumer) {
        shapedUnlockedBy(PowerfulSaucesItems.SAUCE_BOTTLE.get(), 16, Items.GLASS_PANE)
                .pattern("GGG").define('G', Items.GLASS_PANE)
                .pattern("G G").define('B', ItemTags.BUTTONS)
                .pattern(" B ").save(consumer);
        shapelessUnlockedBy(PowerfulSaucesItems.SPICE_MIX.get(), 8, Tags.VEGETABLE)
                .requires(Tags.ONION)
                .requires(Tags.GARLIC)
                .requires(Tags.MUSTARD)
                .requires(Tags.CELERY).save(consumer);
        shapelessUnlockedBy(PowerfulSaucesItems.VINEGAR.get(), 2, Tags.JUICE)
                .requires(Tags.COOKING_POT)
                .requires(Tags.WATER_BOTTLE)
                .requires(Items.SUGAR)
                .requires(Tags.JUICE).save(consumer);
        shapelessUnlockedBy(PowerfulSaucesItems.BROWN_SUGAR.get(), 1, Items.SUGAR)
                .requires(Tags.MOLASSES)
                .requires(Items.SUGAR).save(consumer);
        shapelessUnlockedBy(PowerfulSaucesItems.TOMATO_PASTE.get(), 1, Tags.TOMATO)
                .requires(Tags.MORTAR_AND_PESTLE)
                .requires(Tags.TOMATO).save(consumer);
        shapelessUnlockedBy(PowerfulSaucesItems.PICKLE.get(), 1, Tags.CUCUMBER)
                .requires(Tags.COOKING_POT)
                .requires(Tags.CUCUMBER)
                .requires(Tags.BRINE)
                .requires(Tags.GARLIC).save(consumer);
        shapelessUnlockedBy(PowerfulSaucesItems.CHOPPED_PICKLE.get(), 4, Tags.PICKLE)
                .requires(Tags.KNIFE)
                .requires(Tags.PICKLE).save(consumer);
        shapelessUnlockedBy(PowerfulSaucesItems.BRINE.get(), 8, Tags.SPICE_MIX)
                .requires(Tags.WATER_BOTTLE)
                .requires(Tags.VINEGAR)
                .requires(Tags.SPICE_MIX).save(consumer);
    }

    public void buildSauceRecipes(Consumer<FinishedRecipe> consumer) {
        shapelessSauceBottleRecipe(PowerfulSaucesItems.KETCHUP.get(), 2)
                .requires(Tags.TOMATO_PASTE)
                .requires(Items.SUGAR)
                .requires(Tags.SPICE_MIX).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.MUSTARD.get(), 2)
                .requires(Tags.WATER_BOTTLE)
                .requires(Tags.SEED_MUSTARD)
                .requires(Tags.LEMON)
                .requires(Tags.SALT)
                .requires(Tags.SPICE_MIX).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.MAYONNAISE.get(), 2)
                .requires(Tags.OLIVE_OIL)
                .requires(Items.EGG)
                .requires(Tags.SPICE_MIX)
                .requires(Tags.VINEGAR).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.BARBECUE.get(), 2)
                .requires(PowerfulSaucesItems.KETCHUP.get())
                .requires(Tags.BROWN_SUGAR)
                .requires(Tags.VINEGAR)
                .requires(Tags.SPICE_MIX).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.WORCESTERSHIRE.get(), 2)
                .requires(Tags.VINEGAR)
                .requires(Tags.BROWN_SUGAR)
                .requires(Tags.SALT)
                .requires(Tags.ANCHOVY)
                .requires(Tags.SPICE_MIX).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.MUSHROOM.get(), 2)
                .requires(Ingredient.of(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM))
                .requires(Tags.CHEESE)
                .requires(Tags.MILK)
                .requires(Tags.OLIVE_OIL)
                .requires(Tags.SPICE_MIX).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.CHILI.get(), 3)
                .requires(Tags.TOMATO_PASTE)
                .requires(Tags.SPICE_MIX)
                .requires(Tags.VINEGAR)
                .requires(Tags.WATER_BOTTLE)
                .requires(Tags.BROWN_SUGAR)
                .requires(PowerfulSaucesItems.WORCESTERSHIRE.get()).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.TARTAR.get(), 3)
                .requires(PowerfulSaucesItems.MAYONNAISE.get())
                .requires(Tags.CHOPPED_PICKLE)
                .requires(Tags.LEMON)
                .requires(Items.SUGAR)
                .requires(Tags.PEPPER).save(consumer);
    }

    public void buildAugmentedSauceRecipes(Consumer<FinishedRecipe> consumer) {
        for (SauceBottle bottle : PowerfulSaucesItems.SAUCE_BOTTLES) {
            shapedUnlockedBy(bottle.getAugmented(), 1, bottle.get())
                    .pattern(" X ").define('X', Items.EXPERIENCE_BOTTLE)
                    .pattern("XSX").define('S', bottle.get())
                    .pattern(" X ").save(consumer);
        }
    }

    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        buildIngredientRecipes(consumer);
        buildSauceRecipes(consumer);
        buildAugmentedSauceRecipes(consumer);
    }

    public static class Tags {
        public static final TagKey<Item> ANCHOVY = globalTag("anchovies");
        public static final TagKey<Item> BRINE = globalTag("brines");
        public static final TagKey<Item> BROWN_SUGAR = globalTag("brown_sugars");
        public static final TagKey<Item> CELERY = globalTag("celery");
        public static final TagKey<Item> CHEESE = globalTag("cheeses");
        public static final TagKey<Item> CHOPPED_PICKLE = globalTag("chopped_pickles");
        public static final TagKey<Item> COOKING_POT = globalTag("cooking_pots");
        public static final TagKey<Item> CUCUMBER = globalTag("cucumbers");
        public static final TagKey<Item> GARLIC = globalTag("garlic");
        public static final TagKey<Item> JUICE = globalTag("juices");
        public static final TagKey<Item> KNIFE = globalTag("knives");
        public static final TagKey<Item> LEMON = globalTag("lemons");
        public static final TagKey<Item> MILK = globalTag("milks");
        public static final TagKey<Item> MOLASSES = globalTag("molasses");
        public static final TagKey<Item> MORTAR_AND_PESTLE = globalTag("mortar_and_pestles");
        public static final TagKey<Item> MUSTARD = globalTag("mustard");
        public static final TagKey<Item> OLIVE_OIL = globalTag("olive_oils");
        public static final TagKey<Item> ONION = globalTag("onions");
        public static final TagKey<Item> PICKLE = globalTag("pickles");
        public static final TagKey<Item> PEPPER = globalTag("peppers");
        public static final TagKey<Item> SALT = globalTag("salts");
        public static final TagKey<Item> SEED_MUSTARD = globalTag("seeds/mustard");
        public static final TagKey<Item> SPICE_MIX = globalTag("spice_mixes");
        public static final TagKey<Item> TOMATO = globalTag("tomatoes");
        public static final TagKey<Item> TOMATO_PASTE = globalTag("tomato_pastes");
        public static final TagKey<Item> VEGETABLE = globalTag("vegetables");
        public static final TagKey<Item> VINEGAR = globalTag("vinegars");
        public static final TagKey<Item> WATER_BOTTLE = globalTag("water_bottles");

    }
}
