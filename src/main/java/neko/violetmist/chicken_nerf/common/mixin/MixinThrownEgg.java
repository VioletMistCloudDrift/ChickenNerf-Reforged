package neko.violetmist.chicken_nerf.common.mixin;

import neko.violetmist.chicken_nerf.common.config.ChickenNerfConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.level.Level;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ThrownEgg.class)
public abstract class MixinThrownEgg extends ThrowableItemProjectile {
    public MixinThrownEgg(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Redirect(method = "onHit", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/world/level/Level;isClientSide:Z"))
    private boolean redirect$onHit(Level instance) {
        if (instance.isClientSide) return true;
        if (random.nextDouble() > ChickenNerfConfig.CHICKEN_HATCHING_PROBABILITY.get()) return true;
        Chicken chick = EntityType.CHICKEN.create(instance);
        if (chick == null) return true;
        chick.setAge(-24000);
        chick.moveTo(getX(), getY(), getZ(), getYRot(), .0F);
        instance.addFreshEntity(chick);
        instance.broadcastEntityEvent(this, (byte) 3);
        discard();
        return true;
    }
}
