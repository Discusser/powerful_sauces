package io.github.discusser.data;

import io.github.discusser.objects.PowerfulSaucesItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static net.minecraft.data.models.model.TextureMapping.pattern;

public class PowerfulSaucesData {
    public static void gatherData(DataGenerator.PackGenerator generator) {
        generator.addProvider(Recipes::new);
    }

    public static class Recipes extends RecipeProvider {

        public Recipes(PackOutput packOutput) {
            super(packOutput);
        }

        @Override
        protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
            ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, PowerfulSaucesItems.SAUCE_BOTTLE.get(), 16)
                    .unlockedBy("powerful_sauces:has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS_PANE))
                    .pattern("GGG").define('G', Items.GLASS_PANE)
                    .pattern("G G").define('B', ItemTags.BUTTONS)
                    .pattern(" B ").save(consumer);
        }
    }
}
