package lyrellion.ars_elemancy;

import lyrellion.ars_elemancy.client.SpellFocusRenderer;
import lyrellion.ars_elemancy.registry.ModItems;
import lyrellion.ars_elemancy.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.UUID;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ArsElemancy.MODID)
public class ArsElemancy {

    public static final String MODID = "ars_elemancy";

    public static final UUID Dev = UUID.fromString("0e918660-22bf-4bed-8426-ece3b4bbd01d");
    public static boolean terrablenderLoaded = false;

    public ArsElemancy(IEventBus modEventBus, ModContainer modContainer) {
        terrablenderLoaded = ModList.get().isLoaded("terrablender");

        modContainer.registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);
        modContainer.registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC);
        ModRegistry.registerRegistries(modEventBus);
        ArsNouveauRegistry.init();
        modEventBus.addListener(this::setup);
        //modEventBus.addListener(this::sendImc);
        modEventBus.addListener(this::loadComplete);
        if (FMLEnvironment.dist.isClient()) {
            modEventBus.addListener(this::doClientStuff);
        }
        ArsNouveauAPI.ENABLE_DEBUG_NUMBERS = !FMLEnvironment.production;

    }

    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ArsNouveauRegistry.postInit();
        });
    }

    @OnlyIn(Dist.CLIENT)
    private void doClientStuff(final FMLClientSetupEvent event) {
        if (!ConfigHandler.Client.EnableSFRendering.get()) return;
        CuriosRendererRegistry.register(ModItems.TEMPEST_FOCUS.get(), SpellFocusRenderer::new);
        CuriosRendererRegistry.register(ModItems.SILT_FOCUS.get(), SpellFocusRenderer::new);
        CuriosRendererRegistry.register(ModItems.LAVA_FOCUS.get(), SpellFocusRenderer::new);
        CuriosRendererRegistry.register(ModItems.VAPOR_FOCUS.get(), SpellFocusRenderer::new);
        CuriosRendererRegistry.register(ModItems.CINDER_FOCUS.get(), SpellFocusRenderer::new);
        CuriosRendererRegistry.register(ModItems.MIRE_FOCUS.get(), SpellFocusRenderer::new);
        CuriosRendererRegistry.register(ModItems.ELEMANCER_FOCUS.get(), SpellFocusRenderer::new);
    }

    public void loadComplete(FMLLoadCompleteEvent event) {

    }

}
