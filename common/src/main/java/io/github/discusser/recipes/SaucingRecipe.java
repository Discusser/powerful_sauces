package io.github.discusser.recipes;

import com.google.gson.JsonObject;
import io.github.discusser.PowerfulSaucesUtil;
import io.github.discusser.objects.PowerfulSaucesSerializers;
import io.github.discusser.objects.items.SauceItem;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.github.discusser.PowerfulSauces.LOGGER;
import static io.github.discusser.PowerfulSauces.MOD_ID;

public record SaucingRecipe(ResourceLocation id, ItemStack sauce) implements CraftingRecipe {
    @Override
    public @NotNull CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    public boolean isValidFood(ItemStack stack) {
        return stack.getItem().getFoodProperties() != null &&
                    !(stack.getItem() instanceof SauceItem) &&
                    !(PowerfulSaucesUtil.isSauced(stack));
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        int sauceCount = 0;
        int foodCount = 0;

        for (ItemStack stack : container.getItems()) {
            if (stack.is(sauce.getItem()))
                sauceCount += 1;
            if (isValidFood(stack))
                foodCount += 1;
        }

        return sauceCount == 1 && foodCount == 1;
    }

    @Override
    public @NotNull ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        Optional<ItemStack> optional = container.getItems().stream().filter(this::isValidFood).findFirst();
        if (optional.isPresent()) {
            ResourceLocation sauceName = this.sauce.getItem().arch$registryName();
            if (sauceName == null) {
                LOGGER.error("Could not get registry name for sauce '" + this.sauce + "'");
                return ItemStack.EMPTY;
            }

            ItemStack foodInput = optional.get();
            ItemStack copy = foodInput.copyWithCount(1);
            CompoundTag tag = copy.getOrCreateTag();
            tag.putString(MOD_ID + ":sauce", sauceName.toString());
            copy.setTag(tag);
            return copy;
        } else {
            LOGGER.error("Expected food item to be present when assembling SaucingRecipe");
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i * j >= 2;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
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

            return new SaucingRecipe(resourceLocation, sauce);
        }

        @Override
        public @NotNull SaucingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            ItemStack sauce = friendlyByteBuf.readItem();
            return new SaucingRecipe(resourceLocation, sauce);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, SaucingRecipe recipe) {
            friendlyByteBuf.writeItem(recipe.sauce());
        }
    }
}
