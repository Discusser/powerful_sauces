package io.github.discusser.forge;

import dev.architectury.platform.forge.EventBuses;
import io.github.discusser.objects.PowerfulSaucesItems;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import io.github.discusser.PowerfulSauces;

@Mod(PowerfulSauces.MOD_ID)
public final class PowerfulSaucesForge {
    public PowerfulSaucesForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(PowerfulSauces.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        PowerfulSauces.init();
    }
}
