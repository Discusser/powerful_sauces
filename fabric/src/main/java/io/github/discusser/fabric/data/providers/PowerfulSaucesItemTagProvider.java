package io.github.discusser.fabric.data.providers;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.discusser.mixin.accessor.TagProviderAccessor;
import io.github.discusser.objects.PowerfulSaucesItems;
import io.github.discusser.objects.SauceBottle;
import io.github.discusser.util.PowerfulSaucesUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.TagManager;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class PowerfulSaucesItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public PowerfulSaucesItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
        ((TagProviderAccessor) this).setPathProvider(new PowerfulSaucesPathProvider(
                output,
                PackOutput.Target.DATA_PACK,
                TagManager.getTagDir(Registries.ITEM))
        );
    }

    public void globalTag(String name, Item... item) {
        this.getOrCreateTagBuilder(TagKey.create(Registries.ITEM, PowerfulSaucesUtil.globalLoc(name))).add(item);
    }

    // Handles most cases
    public String plural(String name) {
        if (name.endsWith("y")) {
            name = name.substring(0, name.length() - 1);
            return name + "ies";
        } else if (name.endsWith("us")) {
            name = name.substring(0, name.length() - 1);
            return name + "i";
        } else if (name.endsWith("knife") || name.endsWith("wife") || name.endsWith("life")) {
            // These case are maybe pointless, but we might as well check for them
            return name + "ve";
        } else if (name.endsWith("e")) {
            return name + "s";
        } else if (name.endsWith("s") || name.endsWith("x") || name.endsWith("sh") || name.endsWith("ch") || name.endsWith("tomato") || name.endsWith("potato")) {
            return name + "es";
        } else {
            return name + "s";
        }
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        globalTag("sauces", PowerfulSaucesItems.SAUCE_BOTTLES.stream()
                .map(SauceBottle::get)
                .toArray(Item[]::new));
        globalTag("augmented_sauces", PowerfulSaucesItems.SAUCE_BOTTLES.stream()
                .map(SauceBottle::getAugmented)
                .toArray(Item[]::new));
        for (SauceBottle bottle : PowerfulSaucesItems.SAUCE_BOTTLES) {
            globalTag("sauces/" + bottle.sauce().getId().getPath(), bottle.sauce().get());
            globalTag("augmented_sauces/" + bottle.augmentedSauce().getId().getPath(), bottle.augmentedSauce().get());
        }

        for (RegistrySupplier<Item> item : PowerfulSaucesItems.INGREDIENTS) {
            globalTag(plural(item.getId().getPath()), item.get());
        }
    }
}
