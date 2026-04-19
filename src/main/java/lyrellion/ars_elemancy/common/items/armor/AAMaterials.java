package lyrellion.ars_elemancy.common.items.armor;

import alexthw.ars_elemental.ArsElemental;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import static lyrellion.ars_elemancy.ArsElemancy.MODID;

public class AAMaterials {

    public static final DeferredRegister<ArmorMaterial> A_MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, MODID);

    public static final EnumMap<ArmorItem.Type, Integer> ARMOR_SLOT_PROTECTION_L = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.CHESTPLATE, 6);
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.BODY, 4);
    });

    public static final EnumMap<ArmorItem.Type, Integer> ARMOR_SLOT_PROTECTION_M = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BOOTS, 3);
        map.put(ArmorItem.Type.LEGGINGS, 6);
        map.put(ArmorItem.Type.CHESTPLATE, 8);
        map.put(ArmorItem.Type.HELMET, 3);
        map.put(ArmorItem.Type.BODY, 4);
    });

    public static final EnumMap<ArmorItem.Type, Integer> ARMOR_SLOT_PROTECTION_H = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BOOTS, 4);
        map.put(ArmorItem.Type.LEGGINGS, 7);
        map.put(ArmorItem.Type.CHESTPLATE, 10);
        map.put(ArmorItem.Type.HELMET, 4);
        map.put(ArmorItem.Type.BODY, 4);
    });

    public final static Holder<ArmorMaterial> l_fire = A_MATERIALS.register("light_fire", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_L
            , 50, new Holder.Direct<>(SoundEvents.GENERIC_BURN), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("light_fire"))), 1.0f, 0));
    public final static Holder<ArmorMaterial> l_water = A_MATERIALS.register("light_water", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_L
            , 50, new Holder.Direct<>(SoundEvents.BREWING_STAND_BREW), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("light_water"))), 1.0f, 0));
    public final static Holder<ArmorMaterial> l_earth = A_MATERIALS.register("light_earth", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_L, 50, new Holder.Direct<>(SoundEvents.GLASS_PLACE), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("light_earth"))), 1.0f, 0));
    public final static Holder<ArmorMaterial> l_air = A_MATERIALS.register("light_air", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_L, 50, SoundEvents.ARMOR_EQUIP_ELYTRA, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("light_air"))), 1.0f, 0));

    public final static Holder<ArmorMaterial> fire = A_MATERIALS.register("medium_fire", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_M
            , 40, new Holder.Direct<>(SoundEvents.GENERIC_BURN), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("medium_fire"))), 2.0f, 0.025F));
    public final static Holder<ArmorMaterial> water = A_MATERIALS.register("medium_water", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_M
            , 40, new Holder.Direct<>(SoundEvents.BREWING_STAND_BREW), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("medium_water"))), 2.0f, 0.025F));
    public final static Holder<ArmorMaterial> earth = A_MATERIALS.register("medium_earth", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_M, 40, new Holder.Direct<>(SoundEvents.GLASS_PLACE), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("medium_earth"))), 2.0f, 0.05F));
    public final static Holder<ArmorMaterial> air = A_MATERIALS.register("medium_air", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_M, 40, SoundEvents.ARMOR_EQUIP_ELYTRA, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("medium_air"))), 2.0f, 0.025F));

    public final static Holder<ArmorMaterial> h_fire = A_MATERIALS.register("heavy_fire", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_H
            , 30, new Holder.Direct<>(SoundEvents.GENERIC_BURN), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("heavy_fire"))), 4.0f, 0.05F));
    public final static Holder<ArmorMaterial> h_water = A_MATERIALS.register("heavy_water", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_H
            , 30, new Holder.Direct<>(SoundEvents.BREWING_STAND_BREW), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("heavy_water"))), 4.0f, 0.05F));
    public final static Holder<ArmorMaterial> h_earth = A_MATERIALS.register("heavy_earth", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_H, 30, new Holder.Direct<>(SoundEvents.GLASS_PLACE), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("heavy_earth"))), 4.0f, 0.1F));
    public final static Holder<ArmorMaterial> h_air = A_MATERIALS.register("heavy_air", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_H, 30, SoundEvents.ARMOR_EQUIP_ELYTRA, () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("heavy_air"))), 4.0f, 0.05F));

    public final static Holder<ArmorMaterial> l_tempest = A_MATERIALS.register("light_tempest", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_L, 50, new Holder.Direct<>(SoundEvents.SLIME_JUMP), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("light_tempest"))), 1.0f, 0));
    public final static Holder<ArmorMaterial> l_silt =  A_MATERIALS.register("light_silt", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_L, 50, new Holder.Direct<>(SoundEvents.SLIME_JUMP), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("light_silt"))), 1.0f, 0));
    public final static Holder<ArmorMaterial> l_mire = A_MATERIALS.register("light_mire", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_L, 50,  new Holder.Direct<>(SoundEvents.SLIME_JUMP), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("light_mire"))), 1.0f, 0));
    public final static Holder<ArmorMaterial> l_vapor = A_MATERIALS.register("light_vapor", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_L
            , 50, new Holder.Direct<>(SoundEvents.BREWING_STAND_BREW), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("light_vapor"))), 1.0f, 0));
    public final static Holder<ArmorMaterial> l_lava = A_MATERIALS.register("light_lava", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_L
            , 50, new Holder.Direct<>(SoundEvents.LAVA_AMBIENT), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("light_lava"))), 1.0f, 0));
    public final static Holder<ArmorMaterial> l_cinder = A_MATERIALS.register("light_cinder", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_L
            , 50, new Holder.Direct<>(SoundEvents.GENERIC_BURN), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("light_cinder"))), 1.0f, 0));
    public final static Holder<ArmorMaterial> l_elemental = A_MATERIALS.register("light_elemental", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_L
            , 50, new Holder.Direct<>(SoundEvents.AMETHYST_BLOCK_RESONATE), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("light_elemancer"))), 1.0f, 0));

    public final static Holder<ArmorMaterial> tempest = A_MATERIALS.register("medium_tempest", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_M
            , 40, new Holder.Direct<>(SoundEvents.LIGHTNING_BOLT_THUNDER), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("medium_tempest"))), 2.0f, 0.025F));
    public final static Holder<ArmorMaterial> silt =  A_MATERIALS.register("medium_silt", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_M
            , 40, new Holder.Direct<>(SoundEvents.BREEZE_SHOOT), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("medium_silt"))), 2.0f, 0.05F));
    public final static Holder<ArmorMaterial> mire = A_MATERIALS.register("medium_mire", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_M
            , 40, new Holder.Direct<>(SoundEvents.SLIME_JUMP), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("medium_mire"))), 2.0f, 0.05F));
    public final static Holder<ArmorMaterial> vapor = A_MATERIALS.register("medium_vapor", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_M
            , 40, new Holder.Direct<>(SoundEvents.BREWING_STAND_BREW), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("medium_vapor"))), 2.0f, 0.025F));
    public final static Holder<ArmorMaterial> lava = A_MATERIALS.register("medium_lava", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_M
            , 40, new Holder.Direct<>(SoundEvents.LAVA_AMBIENT), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("medium_lava"))), 2.0f, 0.05F));
    public final static Holder<ArmorMaterial> cinder = A_MATERIALS.register("medium_cinder", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_M
            , 40, new Holder.Direct<>(SoundEvents.GENERIC_BURN), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("medium_cinder"))), 2.0f, 0.025F));
    public final static Holder<ArmorMaterial> elemental = A_MATERIALS.register("medium_elemental", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_M
            , 40, new Holder.Direct<>(SoundEvents.AMETHYST_BLOCK_RESONATE), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("medium_elemancer"))), 2.0f, 0.05F));

    public final static Holder<ArmorMaterial> h_tempest = A_MATERIALS.register("heavy_tempest", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_H
            , 30, new Holder.Direct<>(SoundEvents.LIGHTNING_BOLT_THUNDER), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("heavy_tempest"))), 4.0f, 0.05F));
    public final static Holder<ArmorMaterial> h_silt =  A_MATERIALS.register("heavy_silt", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_H
            , 30, new Holder.Direct<>(SoundEvents.BREEZE_SHOOT), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("heavy_silt"))), 4.0f, 0.1F));
    public final static Holder<ArmorMaterial> h_mire = A_MATERIALS.register("heavy_mire", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_H
            , 30, new Holder.Direct<>(SoundEvents.SLIME_JUMP), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("heavy_mire"))), 4.0f, 0.1F));
    public final static Holder<ArmorMaterial> h_vapor = A_MATERIALS.register("heavy_vapor", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_H
            , 30, new Holder.Direct<>(SoundEvents.BREWING_STAND_BREW), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("heavy_vapor"))), 4.0f, 0.05F));
    public final static Holder<ArmorMaterial> h_lava = A_MATERIALS.register("heavy_lava", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_H
            , 30, new Holder.Direct<>(SoundEvents.LAVA_AMBIENT), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("heavy_lava"))), 4.0f, 0.1F));
    public final static Holder<ArmorMaterial> h_cinder = A_MATERIALS.register("heavy_cinder", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_H
            , 30, new Holder.Direct<>(SoundEvents.GENERIC_BURN), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("heavy_cinder"))), 4.0f, 0.05F));
    public final static Holder<ArmorMaterial> h_elemental = A_MATERIALS.register("heavy_elemental", () -> new ArmorMaterial(ARMOR_SLOT_PROTECTION_H
            , 30, new Holder.Direct<>(SoundEvents.AMETHYST_BLOCK_RESONATE), () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(ArsElemental.prefix("heavy_elemancer"))), 4.0f, 0.05F));


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