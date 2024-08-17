package io.github.discusser.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.discusser.objects.PowerfulSaucesItems;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemColors.class)
public class ItemColorsMixin {
    @Inject(method = "createDefault", at = @At(value = "TAIL"))
    private static void createDefault(BlockColors blockColors, CallbackInfoReturnable<ItemColors> cir, @Local ItemColors itemColors) {
        PowerfulSaucesItems.SAUCE_BOTTLES.forEach(registrySupplier -> itemColors.register(
                (itemStack, i) -> i == 0
                        ? 0xcccccc
                        : registrySupplier.get().textColor.getValue(), registrySupplier.get()
        ));
    }
}
