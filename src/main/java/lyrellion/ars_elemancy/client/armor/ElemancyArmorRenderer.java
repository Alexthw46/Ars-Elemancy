package lyrellion.ars_elemancy.client.armor;

import lyrellion.ars_elemancy.ArsElemancy;
import com.hollingsworth.arsnouveau.client.renderer.item.ArmorRenderer;
import com.hollingsworth.arsnouveau.common.armor.AnimatedMagicArmor;
import lyrellion.ars_elemancy.api.item.IElemancyArmor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ElemancyArmorRenderer extends ArmorRenderer {

    public ElemancyArmorRenderer(GeoModel<AnimatedMagicArmor> modelProvider) {
        super(modelProvider);
    }

    @Override
    public ResourceLocation getTextureLocation(AnimatedMagicArmor instance) {
        if (instance instanceof IElemancyArmor armor) {
            return ResourceLocation.fromNamespaceAndPath(ArsElemancy.MODID, "textures/armor/" + armor.getTier() + "_armor_" + armor.getSchool().getId() + ".png");
        }
        return super.getTextureLocation(instance);
    }

}
