package io.github.discusser.recipes;

import com.google.gson.JsonObject;
import io.github.discusser.PowerfulSaucesUtil;
import io.github.discusser.objects.PowerfulSaucesSerializers;
import io.github.discusser.objects.items.SauceItem;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
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

import java.util.*;

import static io.github.discusser.PowerfulSauces.LOGGER;
import static io.github.discusser.PowerfulSauces.MOD_ID;

public record SaucingRecipe(ResourceLocation id) implements CraftingRecipe {
    @Override
    public @NotNull CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    public boolean isValidFood(ItemStack sauceInContainer, ItemStack stack) {
        return stack.getItem().getFoodProperties() != null &&
                !(stack.getItem() instanceof SauceItem) &&
                PowerfulSaucesUtil.tryGetSauces(stack).stream().noneMatch(sauceInContainer::is);
    }

    public List<ItemStack> getSauceItems(CraftingContainer container) {
        return container.getItems().stream()
                .filter(stack -> stack.getItem() instanceof SauceItem)
                .toList();
    }

    public List<ItemStack> getFoodItems(CraftingContainer container, ItemStack sauceItem) {
        return container.getItems().stream()
                .filter(stack -> isValidFood(sauceItem, stack))
                .toList();
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        List<ItemStack> sauces = this.getSauceItems(container);
        if (sauces.size() != 1)
            return false;

        List<ItemStack> foods = this.getFoodItems(container, sauces.get(0));
        return foods.size() == 1;
    }

    @Override
    public @NotNull ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack sauce = this.getSauceItems(container).get(0);
        ItemStack food = this.getFoodItems(container, sauce).get(0);

        ResourceLocation sauceName = sauce.getItem().arch$registryName();
        if (sauceName == null) {
            LOGGER.error("Could not get registry name for sauce '" + sauce + "'");
            return ItemStack.EMPTY;
        }

        ItemStack copy = food.copyWithCount(1);

        CompoundTag tag = copy.getOrCreateTag();
        ListTag listTag = tag.getList(MOD_ID + ":sauces", CompoundTag.TAG_STRING);
        listTag.add(listTag.size(), StringTag.valueOf(sauceName.toString()));
        listTag.sort(Comparator.comparing(Tag::getAsString));
        tag.put(MOD_ID + ":sauces", listTag);

        copy.setTag(tag);
        return copy;
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
            return new SaucingRecipe(resourceLocation);
        }

        @Override
        public @NotNull SaucingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            return new SaucingRecipe(resourceLocation);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, SaucingRecipe recipe) {}
    }
}
