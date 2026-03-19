package net.rubii.simpleplates.block.custom;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rubii.simpleplates.SimplePlates;
import net.rubii.simpleplates.block.entity.PlateBlockEntity;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;

public class PlateBlock extends BaseEntityBlock {
    public static VoxelShape SHAPE = Block.box(3, 0, 3, 13, 2, 13);

    public PlateBlock(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof PlateBlockEntity) {
                ((PlateBlockEntity) blockEntity).drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof PlateBlockEntity) {
                ItemStack handStack = pPlayer.getItemInHand(pHand);
                ItemStack returnedStack = ((PlateBlockEntity) blockEntity).interact(handStack, pPlayer);

                if (returnedStack == null && !handStack.isEmpty()) {
                    handStack.setCount(handStack.getCount() - 1);
                    pPlayer.playNotifySound(SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 2.0F);
                }else{
                    ItemStack stack1 = handStack.copy();
                    stack1.setCount(1);
                    ItemStack stack2 = returnedStack.copy();
                    stack2.setCount(1);

                    if (stack1.equals(stack2, false) && handStack.getCount() < handStack.getMaxStackSize()) {
                        handStack.setCount(handStack.getCount() + returnedStack.getCount());
                        ((PlateBlockEntity) blockEntity).shrink(1);
                        pPlayer.playNotifySound(SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }else if (handStack.isEmpty()){
                        pPlayer.setItemInHand(pHand, returnedStack);
                        ((PlateBlockEntity) blockEntity).shrink(1);
                        pPlayer.playNotifySound(SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PlateBlockEntity(blockPos, blockState);
    }
}
