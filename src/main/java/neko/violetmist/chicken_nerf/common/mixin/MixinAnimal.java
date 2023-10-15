package neko.violetmist.chicken_nerf.common.mixin;

import neko.violetmist.chicken_nerf.common.config.ChickenNerfConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Animal.class)
public abstract class MixinAnimal extends AgeableMob {
    protected MixinAnimal(EntityType<? extends AgeableMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Redirect(
            method = "spawnChildFromBreeding",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V"
            )
    )
    private void redirect$breed(ServerLevel instance, Entity entity) {
        if (!(entity instanceof Chicken)) {
            instance.addFreshEntityWithPassengers(entity);
            return;
        }
        playSound(SoundEvents.CHICKEN_EGG, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        int count = Mth.clamp(ChickenNerfConfig.LAID_EGGS_COUNT_MINIMUM.get() + random.nextInt(ChickenNerfConfig.LAID_EGGS_COUNT_VARIATION.get() + 1),
                0, 64);
        if (count == 0) return;
        spawnAtLocation(new ItemStack(Items.EGG, count));
    }
}
