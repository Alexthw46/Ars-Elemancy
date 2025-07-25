package lyrellion.ars_elemancy.common.items.armor;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import static lyrellion.ars_elemancy.ArsElemancy.MODID;

public class AAMaterials {

    public static final DeferredRegister<ArmorMaterial> A_MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, MODID);

    public static final EnumMap<ArmorItem.Type, Integer> ARMOR_SLOT_PROTECTION = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BOOTS, 3);
        map.put(ArmorItem.Type.LEGGINGS, 6);
        map.put(ArmorItem.Type.CHESTPLATE, 8);
        map.put(ArmorItem.Type.HELMET, 3);
        map.put(ArmorItem.Type.BODY, 4);
    });

    public final static Holder<ArmorMaterial> FIRE = A_MATERIALS.register("medium_fire", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION
            , 40, new Holder.Direct<>(SoundEvents.GENERIC_BURN), () -> Ingredient.EMPTY, List.of(), 2.0f, 0));
    public final static Holder<ArmorMaterial> WATER = A_MATERIALS.register("medium_water", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION
            , 40, new Holder.Direct<>(SoundEvents.BREWING_STAND_BREW), () -> Ingredient.EMPTY, List.of(), 2.0f, 0));
    public final static Holder<ArmorMaterial> EARTH = A_MATERIALS.register("medium_earth", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION, 40, new Holder.Direct<>(SoundEvents.ANVIL_PLACE), () -> Ingredient.EMPTY, List.of(), 2.0f, 0.02F));
    public final static Holder<ArmorMaterial> AIR = A_MATERIALS.register("medium_air", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION, 40, SoundEvents.ARMOR_EQUIP_ELYTRA, () -> Ingredient.EMPTY, List.of(), 2.0f, 0));

    public final static Holder<ArmorMaterial> TEMPEST = registerMerged("tempest", SoundEvents.ARMOR_EQUIP_ELYTRA, () -> Ingredient.EMPTY, List.of(), AIR, WATER);
    public final static Holder<ArmorMaterial> SILT =  registerMerged("silt", new Holder.Direct<>(SoundEvents.ANVIL_PLACE), () -> Ingredient.EMPTY, List.of(), AIR, EARTH);
    public final static Holder<ArmorMaterial> MIRE = registerMerged("mire", new Holder.Direct<>(SoundEvents.ANVIL_PLACE), () -> Ingredient.EMPTY, List.of(), EARTH, WATER);
    public final static Holder<ArmorMaterial> VAPOR = registerMerged("vapor", new Holder.Direct<>(SoundEvents.GENERIC_BURN), () -> Ingredient.EMPTY, List.of(), FIRE, WATER);
    public final static Holder<ArmorMaterial> LAVA = registerMerged("lava", new Holder.Direct<>(SoundEvents.GENERIC_BURN), () -> Ingredient.EMPTY, List.of(), FIRE, EARTH);
    public final static Holder<ArmorMaterial> CINDER = registerMerged("cinder", new Holder.Direct<>(SoundEvents.GENERIC_BURN), () -> Ingredient.EMPTY, List.of(), AIR, FIRE);
    public final static Holder<ArmorMaterial> ELEMANCER = registerMerged("elemancer", SoundEvents.ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, List.of(), FIRE, WATER, EARTH, AIR);

    @SafeVarargs
    @SuppressWarnings("SameParameterValue")
    private static Holder<ArmorMaterial> registerMerged(String name, Holder<SoundEvent> equipSound, Supplier<Ingredient> repairIngredient, List<ArmorMaterial.Layer> layers, Holder<ArmorMaterial> ...materials) {
        return switch (materials.length) {
            case 0 -> throw new RuntimeException("registerMerged was called with 0 materials to merge");
            case 1 -> materials[0];
            default -> A_MATERIALS.register(name, () -> {

                var first = materials[0].value();
                var defense = new HashMap<>(first.defense());
                var enchantmentValue = first.enchantmentValue();
                var toughness = first.toughness();
                var knockbackResistance = first.knockbackResistance();

                for (var i = 1; i < materials.length; i++) {
                    var next = materials[i].value();

                    for (var entry : next.defense().entrySet()) {
                        defense.compute(entry.getKey(), (k, v) -> Math.max(v, entry.getValue()));
                    }

                    enchantmentValue = Math.max(enchantmentValue, next.enchantmentValue());
                    toughness = Math.max(toughness, next.toughness());
                    knockbackResistance = Math.max(knockbackResistance, next.knockbackResistance());
                }

                final var finalEnchantmentValue = enchantmentValue;
                final var finalToughness = toughness;
                final var finalKnockbackResistance = knockbackResistance;

                return new ArmorMaterial(defense, finalEnchantmentValue, equipSound, repairIngredient, layers, finalToughness, finalKnockbackResistance);
            });
        };

    }
}