package io.github.discusser.mixin;

import io.github.discusser.PowerfulSaucesUtil;
import io.github.discusser.objects.items.SauceItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract boolean addEffect(MobEffectInstance mobEffectInstance);

    @Inject(method = "eat", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/world/entity/LivingEntity;addEatEffect(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void eat(Level level, ItemStack itemStack, CallbackInfoReturnable<ItemStack> cir) {
        if (level.isClientSide) return;

        List<SauceItem> sauces = PowerfulSaucesUtil.tryGetSauces(itemStack);
        sauces.forEach(sauce -> sauce.effects.forEach(effect -> this.addEffect(new MobEffectInstance(effect))));

        if (PowerfulSaucesUtil.shouldGiveHunger(sauces.size())) {
            this.addEffect(PowerfulSaucesUtil.getHungerForSauces(sauces.size()));
        }
    }
}
