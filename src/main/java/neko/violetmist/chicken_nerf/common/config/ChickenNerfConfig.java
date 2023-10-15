package neko.violetmist.chicken_nerf.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ChickenNerfConfig {
    public static final ForgeConfigSpec INSTANCE;
    public static final ForgeConfigSpec.IntValue LAID_EGGS_COUNT_MINIMUM;
    public static final ForgeConfigSpec.IntValue LAID_EGGS_COUNT_VARIATION;
    public static final ForgeConfigSpec.DoubleValue CHICKEN_HATCHING_PROBABILITY;


    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("Chicken Nerf");
        LAID_EGGS_COUNT_MINIMUM = builder.comment("Minimum count of eggs produced by chickens after mating")
                .defineInRange("laid_eggs_count_minimum", 1, 0, 64);
        LAID_EGGS_COUNT_VARIATION = builder.comment("The amount by which the maximum count of eggs laid exceeds the minimum number of eggs laid")
                .defineInRange("laid_eggs_count_variation", 2, 0, 64);
        CHICKEN_HATCHING_PROBABILITY = builder.comment("Probability of an egg successfully hatching a chick")
                .defineInRange("chicken_hatching_probability", .6, .0, 1.0);
        INSTANCE = builder.build();
    }
}
