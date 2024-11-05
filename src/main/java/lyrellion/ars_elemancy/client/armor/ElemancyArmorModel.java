package lyrellion.ars_elemancy.client.armor;

import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.api.item.IElemancyArmor;
import com.hollingsworth.arsnouveau.ArsNouveau;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.model.GeoModel;

public class ElemancyArmorModel<T extends IElemancyArmor & GeoItem> extends GeoModel<T> {

    public ResourceLocation modelLocation;
    public ResourceLocation textLoc;
    public ResourceLocation animationLoc;

    public ElemancyArmorModel(String name) {
        this.modelLocation = ResourceLocation.fromNamespaceAndPath(ArsElemancy.MODID, "geo/" + name + ".geo.json");
        this.textLoc = ResourceLocation.fromNamespaceAndPath(ArsElemancy.MODID, "textures/armor/" + name + ".png");
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return modelLocation;
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return textLoc;
    }

    public GeoModel<T> withEmptyAnim() {
        this.animationLoc = ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "animations/empty.json");
        return this;
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return this.animationLoc;
    }

}
