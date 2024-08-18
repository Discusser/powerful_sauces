package io.github.discusser.fabric.data.providers;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.discusser.objects.PowerfulSaucesItems;
import io.github.discusser.objects.SauceBottle;
import io.github.discusser.objects.items.SauceItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Optional;

import static io.github.discusser.PowerfulSauces.MOD_ID;

public class PowerfulSaucesModelProvider extends FabricModelProvider {
    public PowerfulSaucesModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateSauceBottle(String name, ItemModelGenerators generator) {
        Templates.SAUCE_BOTTLE.create(
                new ResourceLocation(MOD_ID, "item/" + name),
                new TextureMapping(),
                generator.output
        );
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        Templates.TEMPLATE_SAUCE_BOTTLE.create(
                new ResourceLocation(MOD_ID, "item/template_sauce_bottle"),
                TextureMapping.layered(
                        new ResourceLocation(MOD_ID, "item/sauce_bottle"),
                        new ResourceLocation(MOD_ID, "item/sauce_bottle_overlay")
                ),
                itemModelGenerator.output
        );

        for (SauceBottle bottle : PowerfulSaucesItems.SAUCE_BOTTLES) {
            generateSauceBottle(bottle.sauce().getId().getPath(), itemModelGenerator);
            generateSauceBottle(bottle.augmentedSauce().getId().getPath(), itemModelGenerator);
        }
        generateSauceBottle(PowerfulSaucesItems.SAUCE_BOTTLE.getId().getPath(), itemModelGenerator);

        for (RegistrySupplier<Item> supplier : PowerfulSaucesItems.INGREDIENTS) {
            itemModelGenerator.generateFlatItem(supplier.get(), ModelTemplates.FLAT_ITEM);
        }
    }

    public static class Templates {
        public static final ModelTemplate TEMPLATE_SAUCE_BOTTLE = new ModelTemplate(
                Optional.of(new ResourceLocation("minecraft", "item/generated")),
                Optional.empty(),
                TextureSlot.LAYER0, TextureSlot.LAYER1
        );

        public static final ModelTemplate SAUCE_BOTTLE = new ModelTemplate(
                Optional.of(new ResourceLocation(MOD_ID, "item/template_sauce_bottle")),
                Optional.empty()
        );
    }
}
