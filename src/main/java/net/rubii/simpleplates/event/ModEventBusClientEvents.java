package net.rubii.simpleplates.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rubii.simpleplates.SimplePlates;
import net.rubii.simpleplates.block.entity.ModBlockEntities;
import net.rubii.simpleplates.block.entity.renderer.PlateBlockEntityRenderer;

@Mod.EventBusSubscriber(modid = SimplePlates.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.PLATE_BE.get(), PlateBlockEntityRenderer::new);
    }
}
