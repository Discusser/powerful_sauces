package io.github.discusser.fabric;

import io.github.discusser.objects.PowerfulSaucesItems;
import net.fabricmc.api.ModInitializer;

import io.github.discusser.PowerfulSauces;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public final class PowerfulSaucesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        PowerfulSauces.init();

        PowerfulSaucesItems.SAUCE_BOTTLES.forEach(bottle -> {
            ColorProviderRegistry.ITEM.register((itemStack, i) -> i == 0 ? 0xcccccc : bottle.get().textColor.getValue(), bottle.get());
            ColorProviderRegistry.ITEM.register((itemStack, i) -> i == 0 ? 0xcccccc : bottle.getAugmented().textColor.getValue(), bottle.getAugmented());
        });
    }
}
