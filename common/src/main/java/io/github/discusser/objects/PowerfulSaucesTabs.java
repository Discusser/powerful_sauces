package io.github.discusser.objects;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static io.github.discusser.PowerfulSauces.MOD_ID;

public class PowerfulSaucesTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> SAUCES_TAB = TABS.register(
            "sauces", // Tab ID
            () -> CreativeTabRegistry.create(
                    Component.translatable("group.powerful_sauces.sauces"), // Tab Name
                    () -> new ItemStack(PowerfulSaucesItems.KETCHUP.get()) // Icon
            )
    );
}
