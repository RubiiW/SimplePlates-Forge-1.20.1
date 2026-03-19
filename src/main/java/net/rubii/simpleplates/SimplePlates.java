package net.rubii.simpleplates;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.rubii.simpleplates.block.ModBlocks;
import net.rubii.simpleplates.block.entity.ModBlockEntities;
import net.rubii.simpleplates.item.ModItems;
import org.slf4j.Logger;

@Mod(SimplePlates.MODID)
public class SimplePlates
{
    public static final String MODID = "simpleplates";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimplePlates(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ModBlocks.PLATE);
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModBlocks.PLATE);
        }
    }
}
