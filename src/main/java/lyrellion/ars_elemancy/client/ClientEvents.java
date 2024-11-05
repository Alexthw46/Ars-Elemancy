package lyrellion.ars_elemancy.client;

import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.client.particle.SparkParticle;
import lyrellion.ars_elemancy.client.particle.VenomParticle;

import lyrellion.ars_elemancy.common.CasterHolderContainer;
import lyrellion.ars_elemancy.common.CurioHolderContainer;
import lyrellion.ars_elemancy.common.entity.spells.EntityLerpedProjectile;
import lyrellion.ars_elemancy.common.items.CurioHolder;
import lyrellion.ars_elemancy.network.OpenCurioBagPacket;
import lyrellion.ars_elemancy.registry.*;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.item.inv.SlotReference;
import com.hollingsworth.arsnouveau.client.renderer.entity.RenderSpell;
import com.hollingsworth.arsnouveau.client.renderer.entity.RenderSummonSkeleton;
import com.hollingsworth.arsnouveau.client.renderer.entity.WealdWalkerModel;
import com.hollingsworth.arsnouveau.common.entity.EntityProjectileSpell;
import com.hollingsworth.arsnouveau.common.network.Networking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import static lyrellion.ars_elemancy.ArsElemancy.prefix;

@EventBusSubscriber(modid = ArsElemancy.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    static final ResourceLocation SkeletalHorseTexture = ResourceLocation.withDefaultNamespace("textures/entity/horse/horse_skeleton.png");
    static final ResourceLocation VhexTexture = prefix("textures/entity/vhex.png");

    public static final KeyMapping CURIO_BAG_KEYBINDING = new KeyMapping("key.ars_elemental.open_pouch", GLFW.GLFW_KEY_J, "key.category.ars_nouveau.general");

    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {

    }


    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.SPARK.get(), SparkParticle::factory);
        event.registerSpriteSet(ModParticles.VENOM.get(), VenomParticle::factory);
    }

    @SubscribeEvent
    public static void bindRenderers(final EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(ModEntities.FLASHING_WEALD_WALKER.get(), v -> new GeoEntityRenderer<>(v, new WealdWalkerModel<>("flashing_weald")));

        event.registerEntityRenderer(ModEntities.LINGER_MAGNET.get(), ClientEvents::projectileRender);
        event.registerEntityRenderer(ModEntities.FLASH_LIGHTNING.get(), LightningBoltRenderer::new);
        event.registerEntityRenderer(ModEntities.DRIPSTONE_SPIKE.get(), SpikeRenderer::new);
        event.registerEntityRenderer(ModEntities.ICE_SPIKE.get(), renderManager -> new SpikeRenderer(renderManager, prefix("textures/entity/ice_spike.png")));

        event.registerEntityRenderer(ModEntities.LERP_PROJECTILE.get(), (m) -> new EntityRenderer<>(m) {
            @Override
            public @NotNull ResourceLocation getTextureLocation(@NotNull EntityLerpedProjectile pEntity) {
                return ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "textures/entity/spell_proj.png");
            }
        });

    }

    @SubscribeEvent
    public static void initItemColors(final RegisterColorHandlersEvent.Item event) {
        event.register((stack, color) -> color > 0 ? -1 :
                        stack.get(DataComponents.BASE_COLOR).getTextureDiffuseColor() + 0xFF000000,
                ModItems.CASTER_BAG.get());
    }

    //keybinding
    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(CURIO_BAG_KEYBINDING);
    }

    //Curio bag stuff
    @SubscribeEvent
    public static void bindContainerRenderers(RegisterMenuScreensEvent event) {
        event.register(ModRegistry.CURIO_HOLDER.get(), (CurioHolderContainer screenContainer, Inventory inv, Component titleIn) -> new CurioHolderScreen<>(screenContainer, inv, titleIn, prefix("textures/gui/curio_bag.png"), 175, 163));
        event.register(ModRegistry.CASTER_HOLDER.get(), (CasterHolderContainer screenContainer, Inventory inv, Component titleIn) -> new CurioHolderScreen<>(screenContainer, inv, titleIn, prefix("textures/gui/curio_bag_2.png"), 175, 217));
    }

    private static @NotNull EntityRenderer<EntityProjectileSpell> projectileRender(EntityRendererProvider.Context renderManager) {
        return new RenderSpell(renderManager, ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "textures/entity/spell_proj.png"));
    }

    public void openBackpackGui(ClientTickEvent.Post event) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            Minecraft minecraft = Minecraft.getInstance();
            Player playerEntity = minecraft.player;
            if (!(minecraft.screen instanceof CurioHolderScreen) && (playerEntity != null)) {
                if (CURIO_BAG_KEYBINDING.isDown()) {
                    SlotReference backpack = CurioHolder.isEquipped(playerEntity);

                    if (!backpack.isEmpty()) {
                        Networking.sendToServer(new OpenCurioBagPacket());
                    }
                }
            }
        }
    }

}
