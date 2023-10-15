package neko.violetmist.chicken_nerf;

import neko.violetmist.chicken_nerf.common.config.ChickenNerfConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod("chicken_nerf")
public class ChickenNerf {
    public static final String ID = "chicken_nerf";

    public ChickenNerf() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ChickenNerfConfig.INSTANCE);
    }
}
