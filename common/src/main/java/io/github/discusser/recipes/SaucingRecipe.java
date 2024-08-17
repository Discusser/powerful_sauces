package io.github.discusser.recipes;

import com.google.gson.JsonObject;
import io.github.discusser.objects.PowerfulSaucesSerializers;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static io.github.discusser.PowerfulSauces.MOD_ID;

public record SaucingRecipe(ResourceLocation id, ItemStack sauce, ItemStack foodInput) implements CraftingRecipe {
    @Override
    public @NotNull CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        int sauceCount = 0;
        int foodCount = 0;

        for (ItemStack stack : container.getItems()) {
            if (stack.is(sauce.getItem())) sauceCount += 1;
            if (stack.is(foodInput.getItem())) foodCount += 1;
        }

        return sauceCount == 1 && foodCount == 1;
    }

    @Override
    public @NotNull ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        return this.getResultItem(registryAccess);
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i * j >= 2;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess registryAccess) {
        ItemStack copy = this.foodInput.copy();
        CompoundTag tag = copy.getOrCreateTag();
        tag.putString(MOD_ID + ":sauce", this.sauce.getItem().arch$registryName().toString());
        copy.setTag(tag);
        return copy;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return PowerfulSaucesSerializers.SAUCING_RECIPE.get();
    }

    public static class Serializer implements RecipeSerializer<SaucingRecipe> {

        @Override
        public @NotNull SaucingRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            ItemStack sauce = new ItemStack(GsonHelper.getAsItem(jsonObject, "sauce"));
            ItemStack foodInput = new ItemStack(GsonHelper.getAsItem(jsonObject, "foodInput"));

            return new SaucingRecipe(resourceLocation, sauce, foodInput);
        }

        @Override
        public @NotNull SaucingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            ItemStack sauce = friendlyByteBuf.readItem();
            ItemStack foodInput = friendlyByteBuf.readItem();
            return new SaucingRecipe(resourceLocation, sauce, foodInput);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, SaucingRecipe recipe) {
            friendlyByteBuf.writeItem(recipe.sauce());
            friendlyByteBuf.writeItem(recipe.foodInput());
        }
    }
}
