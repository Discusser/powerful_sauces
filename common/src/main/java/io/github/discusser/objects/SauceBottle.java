package io.github.discusser.objects;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.discusser.objects.items.AugmentedSauceItem;
import io.github.discusser.objects.items.SauceItem;

public record SauceBottle(RegistrySupplier<SauceItem> sauce, RegistrySupplier<AugmentedSauceItem> augmentedSauce) {
    public SauceItem get() {
        return this.sauce.get();
    }

    public AugmentedSauceItem getAugmented() {
        return this.augmentedSauce.get();
    }
}
