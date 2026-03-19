package net.rubii.simpleplates.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rubii.simpleplates.SimplePlates;
import net.rubii.simpleplates.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SimplePlates.MODID);

    public static final RegistryObject<BlockEntityType<PlateBlockEntity>> PLATE_BE = BLOCK_ENTITIES.register("plate_be",
            () -> BlockEntityType.Builder.of(PlateBlockEntity::new, ModBlocks.PLATE.get()).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
