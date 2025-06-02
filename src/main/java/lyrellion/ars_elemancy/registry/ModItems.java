package lyrellion.ars_elemancy.registry;

import com.hollingsworth.arsnouveau.api.spell.SpellSchools;
import lyrellion.ars_elemancy.ArsNouveauRegistry;
import lyrellion.ars_elemancy.common.items.*;
import lyrellion.ars_elemancy.common.items.armor.ArmorSet;
import lyrellion.ars_elemancy.common.items.bangles.ElemancyBangle;
import lyrellion.ars_elemancy.common.items.foci.ElemancyFocus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static lyrellion.ars_elemancy.ArsElemancy.MODID;

@SuppressWarnings("SameParameterValue")
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(MODID);

    // Curios
    public static final DeferredHolder<Item, ElemancyFocus> TEMPEST_FOCUS = ITEMS.register("tempest_focus", () -> new ElemancyFocus(focusProps(), ArsNouveauRegistry.TEMPEST));
    public static final DeferredHolder<Item, ElemancyFocus> SILT_FOCUS = ITEMS.register("silt_focus", () -> new ElemancyFocus(focusProps(), ArsNouveauRegistry.SILT));
    public static final DeferredHolder<Item, ElemancyFocus> MIRE_FOCUS = ITEMS.register("mire_focus", () -> new ElemancyFocus(focusProps(), ArsNouveauRegistry.MIRE));
    public static final DeferredHolder<Item, ElemancyFocus> LAVA_FOCUS = ITEMS.register("lava_focus", () -> new ElemancyFocus(focusProps(), ArsNouveauRegistry.LAVA));
    public static final DeferredHolder<Item, ElemancyFocus> CINDER_FOCUS = ITEMS.register("cinder_focus", () -> new ElemancyFocus(focusProps(), ArsNouveauRegistry.CINDER));
    public static final DeferredHolder<Item, ElemancyFocus> VAPOR_FOCUS = ITEMS.register("vapor_focus", () -> new ElemancyFocus(focusProps(), ArsNouveauRegistry.VAPOR));
    public static final DeferredHolder<Item, ElemancyFocus> ELEMANCER_FOCUS = ITEMS.register("elemancer_focus", () -> new ElemancyFocus(focusProps(), SpellSchools.ELEMENTAL));

    // Armor Sets
    public static final ArmorSet TEMPEST_ARMOR = new ArmorSet("tempest", ArsNouveauRegistry.TEMPEST);
    public static final ArmorSet MIRE_ARMOR = new ArmorSet("mire", ArsNouveauRegistry.MIRE);
    public static final ArmorSet SILT_ARMOR = new ArmorSet("silt", ArsNouveauRegistry.SILT);
    public static final ArmorSet LAVA_ARMOR = new ArmorSet("lava", ArsNouveauRegistry.LAVA);
    public static final ArmorSet VAPOR_ARMOR = new ArmorSet("vapor", ArsNouveauRegistry.VAPOR);
    public static final ArmorSet CINDER_ARMOR = new ArmorSet("cinder", ArsNouveauRegistry.CINDER);
    public static final ArmorSet ELEMANCER_ARMOR = new ArmorSet("elemancer", ArsNouveauRegistry.ELEMENTAL);

    // Essences
    public static final DeferredHolder<Item, TempestEssence> TEMPEST_ESSENCE = ITEMS.register("tempest_essence", () -> new TempestEssence(itemProps()));
    public static final DeferredHolder<Item, SiltEssence> SILT_ESSENCE = ITEMS.register("silt_essence", () -> new SiltEssence(itemProps()));
    public static final DeferredHolder<Item, MireEssence> MIRE_ESSENCE = ITEMS.register("mire_essence", () -> new MireEssence(itemProps()));
    public static final DeferredHolder<Item, LavaEssence> LAVA_ESSENCE = ITEMS.register("lava_essence", () -> new LavaEssence(itemProps()));
    public static final DeferredHolder<Item, CinderEssence> CINDER_ESSENCE = ITEMS.register("cinder_essence", () -> new CinderEssence(itemProps()));
    public static final DeferredHolder<Item, VaporEssence> VAPOR_ESSENCE = ITEMS.register("vapor_essence", () -> new VaporEssence(itemProps()));
    public static final DeferredHolder<Item, ElemancerEssence> ELEMANCER_ESSENCE = ITEMS.register("elemancer_essence", () -> new ElemancerEssence(itemProps()));

    // Bangles
    public static final DeferredHolder<Item, ElemancyBangle> TEMPEST_BANGLE = ITEMS.register("tempest_bangle", () -> new ElemancyBangle(bangleProps(), ArsNouveauRegistry.TEMPEST));
    public static final DeferredHolder<Item, ElemancyBangle> MIRE_BANGLE = ITEMS.register("mire_bangle", () -> new ElemancyBangle(bangleProps(), ArsNouveauRegistry.MIRE));
    public static final DeferredHolder<Item, ElemancyBangle> VAPOR_BANGLE = ITEMS.register("vapor_bangle", () -> new ElemancyBangle(bangleProps(), ArsNouveauRegistry.VAPOR));
    public static final DeferredHolder<Item, ElemancyBangle> SILT_BANGLE = ITEMS.register("silt_bangle", () -> new ElemancyBangle(bangleProps(), ArsNouveauRegistry.SILT));
    public static final DeferredHolder<Item, ElemancyBangle> LAVA_BANGLE = ITEMS.register("lava_bangle", () -> new ElemancyBangle(bangleProps(), ArsNouveauRegistry.LAVA));
    public static final DeferredHolder<Item, ElemancyBangle> CINDER_BANGLE = ITEMS.register("cinder_bangle", () -> new ElemancyBangle(bangleProps(), ArsNouveauRegistry.CINDER));
    public static final DeferredHolder<Item, ElemancyBangle> ELEMANCER_BANGLE = ITEMS.register("elemancer_bangle", () -> new ElemancyBangle(bangleProps(), SpellSchools.ELEMENTAL));

    // Caster tomes

    // Trees

    static Item.Properties itemProps() {
        return new Item.Properties();
    }

    static Item.Properties focusProps() {
        return itemProps().stacksTo(1).fireResistant().rarity(Rarity.EPIC);
    }

    static Item.Properties bangleProps() {
        return itemProps().stacksTo(1).fireResistant().rarity(Rarity.EPIC);
    }

    static Item.Properties uncommonProps() {
        return itemProps().stacksTo(1).rarity(Rarity.UNCOMMON);
    }

    public static Item.Properties armorProps() {
        return itemProps().rarity(Rarity.EPIC);
    }

    static DeferredHolder<Block, ? extends Block> addBlock(String name, Supplier<Block> blockSupp) {
        DeferredHolder<Block, ? extends Block> block = BLOCKS.register(name, blockSupp);
        ITEMS.register(name, () -> new BlockItem(block.get(), itemProps()));
        return block;
    }


    static BlockBehaviour.Properties blockProps(Block copyFrom, MapColor color) {
        return BlockBehaviour.Properties.ofFullCopy(copyFrom).mapColor(color);
    }

    private static Boolean allowsSpawnOnLeaves(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    private static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }

}
