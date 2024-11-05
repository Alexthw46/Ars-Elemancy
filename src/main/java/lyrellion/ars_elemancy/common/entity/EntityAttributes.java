package lyrellion.ars_elemancy.common.entity;


import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.registry.ModEntities;
import com.hollingsworth.arsnouveau.common.entity.WealdWalker;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = ArsElemancy.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EntityAttributes {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {

        event.put(ModEntities.FLASHING_WEALD_WALKER.get(), WealdWalker.attributes().build());

    }
}
