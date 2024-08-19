package io.github.discusser.forge;

import dev.architectury.platform.forge.EventBuses;
import io.github.discusser.PowerfulSauces;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PowerfulSauces.MOD_ID)
public final class PowerfulSaucesForge {
    public PowerfulSaucesForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(PowerfulSauces.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        PowerfulSauces.init();
    }
}
