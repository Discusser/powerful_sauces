package io.github.discusser.forge.events;

import io.github.discusser.objects.PowerfulSaucesItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static io.github.discusser.PowerfulSauces.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PowerfulSaucesForgeEventHandler {
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        PowerfulSaucesItems.SAUCE_BOTTLES.forEach(bottle -> {
            event.register((itemStack, i) -> i == 0 ? 0xcccccc : bottle.get().textColor.getValue(), bottle.get());
            event.register((itemStack, i) -> i == 0 ? 0xcccccc : bottle.getAugmented().textColor.getValue(), bottle.getAugmented());
        });
    }
}
