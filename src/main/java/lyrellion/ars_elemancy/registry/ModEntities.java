package lyrellion.ars_elemancy.registry;

import lyrellion.ars_elemancy.common.entity.DripstoneSpikeEntity;
import lyrellion.ars_elemancy.common.entity.IceSpikeEntity;
import lyrellion.ars_elemancy.common.entity.spells.EntityLerpedProjectile;
import lyrellion.ars_elemancy.common.entity.spells.EntityMagnetSpell;
import lyrellion.ars_elemancy.common.entity.spells.FlashLightning;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.common.entity.WealdWalker;
import com.hollingsworth.arsnouveau.common.spell.effect.EffectDelay;
import com.hollingsworth.arsnouveau.common.spell.effect.EffectLaunch;
import com.hollingsworth.arsnouveau.common.spell.effect.EffectWindshear;
import com.hollingsworth.arsnouveau.common.spell.method.MethodProjectile;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static lyrellion.ars_elemancy.ArsElemancy.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MODID);

    public static final DeferredHolder<EntityType<?>,EntityType<WealdWalker>> FLASHING_WEALD_WALKER;

    public static final DeferredHolder<EntityType<?>,EntityType<EntityMagnetSpell>> LINGER_MAGNET;
    public static final DeferredHolder<EntityType<?>,EntityType<EntityLerpedProjectile>> LERP_PROJECTILE;
    public static final DeferredHolder<EntityType<?>,EntityType<FlashLightning>> FLASH_LIGHTNING;
    public static final DeferredHolder<EntityType<?>,EntityType<DripstoneSpikeEntity>> DRIPSTONE_SPIKE;
    public static final DeferredHolder<EntityType<?>,EntityType<IceSpikeEntity>> ICE_SPIKE;

    static {


        FLASHING_WEALD_WALKER = registerEntity("flashing_weald_walker", 1.4F, 3F,
                (EntityType<WealdWalker> type, Level world) -> {
                    WealdWalker walker = new WealdWalker(type, world);
                    walker.spell = new Spell(MethodProjectile.INSTANCE, EffectLaunch.INSTANCE, EffectLaunch.INSTANCE, EffectDelay.INSTANCE, EffectWindshear.INSTANCE);
                    walker.color = new ParticleColor(200, 150, 15);
                    return walker;
                }, MobCategory.CREATURE);


        LINGER_MAGNET = addEntity("linger_magnet", 0.5F, 0.5F, true, true, EntityMagnetSpell::new, MobCategory.MISC);
        LERP_PROJECTILE = addEntity("lerp", 0.5F, 0.5F, true, true, EntityLerpedProjectile::new, MobCategory.MISC);
        FLASH_LIGHTNING = addEntity("flash_lightning", 0.5F, 0.5F, true, true, FlashLightning::new, MobCategory.MISC);
        DRIPSTONE_SPIKE = addEntity("dripstone_spike", 1.0F, 1.0F, true, true, DripstoneSpikeEntity::new, MobCategory.MISC);
        ICE_SPIKE = addEntity("ice_spike", 1.0F, 1.0F, true, true, IceSpikeEntity::new, MobCategory.MISC);

    }

    static <T extends Entity> DeferredHolder<EntityType<?>,EntityType<T>> registerEntity(String name, float width, float height, EntityType.EntityFactory<T> factory, MobCategory kind) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(factory, kind).setTrackingRange(16).sized(width, height).build(MODID + ":" + name));
    }

    static <T extends Entity> DeferredHolder<EntityType<?>,EntityType<T>> addEntity(String name, float width, float height, boolean fire, boolean noSave, EntityType.EntityFactory<T> factory, MobCategory kind) {
        return ENTITIES.register(name, () -> {
            EntityType.Builder<T> builder = EntityType.Builder.of(factory, kind)
                    .setTrackingRange(32)
                    .sized(width, height);
            if (noSave) {
                builder.noSave();
            }
            if (fire) {
                builder.fireImmune();
            }
            return builder.build(MODID + ":" + name);
        });
    }


    @SubscribeEvent
    public static void registerSP(RegisterSpawnPlacementsEvent event) {

        event.register(FLASHING_WEALD_WALKER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, com.hollingsworth.arsnouveau.setup.registry.ModEntities::genericGroundSpawn, RegisterSpawnPlacementsEvent.Operation.AND);


    }

}
