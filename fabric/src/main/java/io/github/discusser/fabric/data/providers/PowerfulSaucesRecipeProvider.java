package io.github.discusser.fabric.data.providers;

import com.epherical.croptopia.register.Content;
import com.epherical.croptopia.register.TagCategory;
import io.github.discusser.objects.PowerfulSaucesItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.resources.ResourceLocation;
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

    public ShapelessRecipeBuilder shapelessSauceBottleRecipe(Item output) {
        return shapelessSauceBottleRecipe(output, 1);
    }

    public ShapelessRecipeBuilder shapelessSauceBottleRecipe(Item output, int count) {
        return shapelessUnlockedBy(output, count, PowerfulSaucesItems.SAUCE_BOTTLE.get())
                .requires(PowerfulSaucesItems.SAUCE_BOTTLE.get());
    }

    public TagKey<Item> globalTag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation("c", name));
    }

    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        shapedUnlockedBy(PowerfulSaucesItems.SAUCE_BOTTLE.get(), 16, Items.GLASS_PANE)
                .pattern("GGG").define('G', Items.GLASS_PANE)
                .pattern("G G").define('B', ItemTags.BUTTONS)
                .pattern(" B ").save(consumer);

        shapelessUnlockedBy(PowerfulSaucesItems.SPICE_MIX.get(), 8,
                Content.ONION.asItem(), Content.GARLIC.asItem(), Content.MUSTARD.asItem(), Content.CELERY.asItem())
                .requires(globalTag("onions"))
                .requires(globalTag("garlic"))
                .requires(globalTag("mustard"))
                .requires(globalTag("celery")).save(consumer);

        shapelessUnlockedBy(PowerfulSaucesItems.VINEGAR.get(), 2, Content.WINE.asItem())
                .requires(globalTag("cooking_pots"))
                .requires(globalTag("wines"))
                .requires(globalTag("juices")).save(consumer);

        shapelessUnlockedBy(PowerfulSaucesItems.BROWN_SUGAR.get(), 1, Items.SUGAR)
                .requires(globalTag("molasses"))
                .requires(Items.SUGAR).save(consumer);

        shapelessUnlockedBy(PowerfulSaucesItems.TOMATO_PASTE.get(), 1, Content.TOMATO.asItem())
                .requires(globalTag("mortar_and_pestles"))
                .requires(globalTag("tomatoes")).save(consumer);

        shapelessSauceBottleRecipe(PowerfulSaucesItems.KETCHUP.get())
                .requires(PowerfulSaucesItems.TOMATO_PASTE.get())
                .requires(Items.SUGAR)
                .requires(PowerfulSaucesItems.SPICE_MIX.get()).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.MUSTARD.get())
                .requires(globalTag("water_bottles"))
                .requires(globalTag("seeds/mustard"))
                .requires(globalTag("lemons"))
                .requires(globalTag("salt"))
                .requires(PowerfulSaucesItems.SPICE_MIX.get()).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.MAYONNAISE.get())
                .requires(globalTag("olive_oils"))
                .requires(Items.EGG)
                .requires(PowerfulSaucesItems.SPICE_MIX.get())
                .requires(PowerfulSaucesItems.VINEGAR.get()).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.BARBECUE.get())
                .requires(PowerfulSaucesItems.KETCHUP.get())
                .requires(PowerfulSaucesItems.BROWN_SUGAR.get())
                .requires(PowerfulSaucesItems.VINEGAR.get())
                .requires(PowerfulSaucesItems.SPICE_MIX.get())
                .requires(globalTag("molasses")).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.WORCESTERSHIRE.get())
                .requires(PowerfulSaucesItems.VINEGAR.get())
                .requires(PowerfulSaucesItems.BROWN_SUGAR.get())
                .requires(globalTag("salt"))
                .requires(globalTag("anchovies"))
                .requires(PowerfulSaucesItems.SPICE_MIX.get()).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.MUSHROOM.get())
                .requires(Ingredient.of(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM))
                .requires(globalTag("cheeses"))
                .requires(globalTag("milks"))
                .requires(globalTag("olive_oils"))
                .requires(globalTag("butter"))
                .requires(PowerfulSaucesItems.SPICE_MIX.get()).save(consumer);
        shapelessSauceBottleRecipe(PowerfulSaucesItems.CHILI.get(), 2)
                .requires(PowerfulSaucesItems.TOMATO_PASTE.get())
                .requires(PowerfulSaucesItems.SPICE_MIX.get())
                .requires(PowerfulSaucesItems.VINEGAR.get())
                .requires(globalTag("water_bottles"))
                .requires(PowerfulSaucesItems.BROWN_SUGAR.get())
                .requires(PowerfulSaucesItems.WORCESTERSHIRE.get()).save(consumer);
    }
}
