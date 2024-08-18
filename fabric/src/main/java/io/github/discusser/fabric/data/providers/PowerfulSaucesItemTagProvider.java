package io.github.discusser.fabric.data.providers;

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
