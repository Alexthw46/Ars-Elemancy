package lyrellion.ars_elemancy.registry;

import com.hollingsworth.arsnouveau.setup.registry.CreativeTabRegistry;
import com.mojang.serialization.MapCodec;
import lyrellion.ars_elemancy.recipe.ElemancyArmorRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static lyrellion.ars_elemancy.ArsElemancy.MODID;
import static lyrellion.ars_elemancy.ArsElemancy.prefix;
import static lyrellion.ars_elemancy.common.items.armor.AAMaterials.A_MATERIALS;
import static lyrellion.ars_elemancy.registry.ModItems.BLOCKS;
import static lyrellion.ars_elemancy.registry.ModItems.ITEMS;

public class ModRegistry {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<DataComponentType<?>> D_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, MODID);
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MODID);
    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS = DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, MODID);

    public static TagKey<DamageType> FIRE_DAMAGE = TagKey.create(Registries.DAMAGE_TYPE, prefix("fire_damage"));
    public static TagKey<DamageType> WATER_DAMAGE = TagKey.create(Registries.DAMAGE_TYPE, prefix("water_damage"));
    public static TagKey<DamageType> EARTH_DAMAGE = TagKey.create(Registries.DAMAGE_TYPE, prefix("earth_damage"));
    public static TagKey<DamageType> AIR_DAMAGE = TagKey.create(Registries.DAMAGE_TYPE, prefix("air_damage"));


    public static void registerRegistries(IEventBus bus) {
        A_MATERIALS.register(bus);
        BLOCKS.register(bus);
        ITEMS.register(bus);
        RECIPES.register(bus);
        SERIALIZERS.register(bus);
        TABS.register(bus);
        D_COMPONENTS.register(bus);
        CONDITION_CODECS.register(bus);
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ELEMANCY_TAB;
    public static final DeferredHolder<RecipeType<?>, RecipeType<ElemancyArmorRecipe>> ELEMANCY_ARMOR_UP;
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ElemancyArmorRecipe>> ELEMANCY_ARMOR_UP_SERIALIZER;

    static {
        ELEMANCY_ARMOR_UP = RECIPES.register("armor_upgrade", () -> RecipeType.simple(prefix("armor_upgrade")));
        ELEMANCY_ARMOR_UP_SERIALIZER = SERIALIZERS.register("armor_upgrade", ElemancyArmorRecipe.Serializer::new);

        ELEMANCY_TAB = TABS.register("general", () -> {
            return CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.ars_elemancy"))
                    .icon(() -> ModItems.TEMPEST_ESSENCE.get().getDefaultInstance())
                    .displayItems((params, output) -> {
                        for (var entry : ITEMS.getEntries()) {
                            output.accept(entry.get().getDefaultInstance());
                        }
                    }).withTabsBefore(CreativeTabRegistry.BLOCKS.getId())
                    .build();
        });
    }

    static <T> ResourceKey<T> key(ResourceKey<Registry<T>> registryResourceKey, String name) {
        return ResourceKey.create(registryResourceKey, prefix(name));
    }
}
