package io.github.discusser.objects;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.discusser.recipes.SaucingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;

import static io.github.discusser.PowerfulSauces.MOD_ID;

public class PowerfulSaucesSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(MOD_ID, Registries.RECIPE_SERIALIZER);

    public static final RegistrySupplier<RecipeSerializer<?>> SAUCING_RECIPE = SERIALIZERS.register("saucing", SaucingRecipe.Serializer::new);
}
