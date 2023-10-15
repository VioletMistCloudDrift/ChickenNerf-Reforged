package neko.violetmist.chicken_nerf.common.mixin;

import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Chicken.class)
public abstract class MixinChicken {
    @Redirect(method = "aiStep", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/world/level/Level;isClientSide:Z"))
    private boolean redirect$aiStep(Level instance) {
        return true;
    }
}
