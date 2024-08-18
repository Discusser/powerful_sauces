package io.github.discusser.fabric.data.providers;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.discusser.objects.PowerfulSaucesItems;
import io.github.discusser.objects.SauceBottle;
import io.github.discusser.objects.items.SauceItem;
import io.github.discusser.util.PowerfulSaucesUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.github.discusser.PowerfulSauces.MOD_ID;

public class PowerfulSaucesItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public PowerfulSaucesItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    public void globalTag(String name, Item... item) {
        TagKey<Item> fabricTag = TagKey.create(Registries.ITEM, PowerfulSaucesUtil.globalLocFabric(name));
        TagKey<Item> forgeTag = TagKey.create(Registries.ITEM, PowerfulSaucesUtil.globalLocForge(name));
        this.getOrCreateTagBuilder(fabricTag).add(item);
        this.getOrCreateTagBuilder(forgeTag).add(item);

         this.getOrCreateTagBuilder(TagKey.create(
                Registries.ITEM,
                new ResourceLocation(MOD_ID, name)
        )).addOptionalTag(fabricTag).addOptionalTag(forgeTag);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        globalTag("sauces", PowerfulSaucesItems.SAUCE_BOTTLES.stream()
                .flatMap(sauceBottle -> Stream.of(sauceBottle.get(), sauceBottle.getAugmented()))
                .toArray(Item[]::new));
        for (SauceBottle bottle : PowerfulSaucesItems.SAUCE_BOTTLES) {
            globalTag("sauces/" + bottle.sauce().getId().getPath(), bottle.sauce().get());
            globalTag("sauces/" + bottle.augmentedSauce().getId().getPath(), bottle.augmentedSauce().get());
        }
    }
}
