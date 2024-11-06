package lyrellion.ars_elemancy.registry;

import com.hollingsworth.arsnouveau.api.spell.SpellSchools;
import lyrellion.ars_elemancy.ArsNouveauRegistry;
import lyrellion.ars_elemancy.common.items.FusedEssence;
import lyrellion.ars_elemancy.common.items.armor.ArmorSet;
import lyrellion.ars_elemancy.common.items.bangles.BaseBangle;
import lyrellion.ars_elemancy.common.items.foci.ElementalFocus;
import lyrellion.ars_elemancy.common.items.foci.GreaterElementalFocus;
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

    public static final DeferredHolder<Item, GreaterElementalFocus> TEMPEST_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> SILT_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> MIRE_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> LAVA_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> CINDER_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> VAPOR_FOCUS;
    public static final DeferredHolder<Item, GreaterElementalFocus> ELEMANCER_FOCUS;

    public static final ArmorSet TEMPEST_ARMOR = new ArmorSet("tempest", ArsNouveauRegistry.TEMPEST);
    public static final ArmorSet MIRE_ARMOR = new ArmorSet("mire", ArsNouveauRegistry.MIRE);
    public static final ArmorSet SILT_ARMOR = new ArmorSet("silt", ArsNouveauRegistry.SILT);
    public static final ArmorSet LAVA_ARMOR = new ArmorSet("lava", ArsNouveauRegistry.LAVA);
    public static final ArmorSet VAPOR_ARMOR = new ArmorSet("vapor", ArsNouveauRegistry.VAPOR);
    public static final ArmorSet CINDER_ARMOR = new ArmorSet("cinder", ArsNouveauRegistry.CINDER);
    public static final ArmorSet ELEMANCER_ARMOR = new ArmorSet("elemancer", SpellSchools.ELEMENTAL);


    public static final DeferredHolder<Item, BaseBangle> ENCHANTER_BANGLE;

    public static final DeferredHolder<Item, FusedEssence> TEMPEST_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> SILT_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> MIRE_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> LAVA_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> CINDER_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> VAPOR_ESSENCE;
    public static final DeferredHolder<Item, FusedEssence> ELEMANCER_ESSENCE;


    static {
        TEMPEST_ESSENCE = ITEMS.register("tempest_essence", () -> new FusedEssence(itemProps()));
        SILT_ESSENCE = ITEMS.register("silt_essence", () -> new FusedEssence(itemProps()));
        MIRE_ESSENCE = ITEMS.register("mire_essence", () -> new FusedEssence(itemProps()));
        VAPOR_ESSENCE = ITEMS.register("vapor_essence", () -> new FusedEssence(itemProps()));
        CINDER_ESSENCE = ITEMS.register("cinder_essence", () -> new FusedEssence(itemProps()));
        LAVA_ESSENCE = ITEMS.register("lava_essence", () -> new FusedEssence(itemProps()));
        ELEMANCER_ESSENCE = ITEMS.register("elemancer_essence", () -> new FusedEssence(itemProps()));


        //curio
        TEMPEST_FOCUS = ITEMS.register("tempest_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.TEMPEST));
        MIRE_FOCUS = ITEMS.register("mire_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.MIRE));
        VAPOR_FOCUS = ITEMS.register("vapor_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.VAPOR));
        SILT_FOCUS = ITEMS.register("silt_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.SILT));
        LAVA_FOCUS = ITEMS.register("lava_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.LAVA));
        CINDER_FOCUS = ITEMS.register("cinder_focus", () -> new GreaterElementalFocus(FocusProp(), ArsNouveauRegistry.CINDER));
        ELEMANCER_FOCUS = ITEMS.register("elemancer_focus", () -> new GreaterElementalFocus(FocusProp(), SpellSchools.ELEMENTAL));

        //bangles
        ENCHANTER_BANGLE = ITEMS.register("base_bangle", () -> new BaseBangle(itemProps().stacksTo(1)));

        //caster tomes


        //Trees
    }

    static Item.Properties itemProps() {
        return new Item.Properties();
    }

    static Item.Properties FocusProp() {
        return itemProps().stacksTo(1).fireResistant().rarity(Rarity.EPIC);
    }

    static Item.Properties UncommonProp() {
        return itemProps().stacksTo(1).rarity(Rarity.UNCOMMON);
    }

    public static Item.Properties ArmorProp() {
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
