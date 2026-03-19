package net.rubii.simpleplates.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.rubii.simpleplates.SimplePlates;

public class ModTags {
    public static class Items{
        public static final TagKey<Item> BLACKLIST = registerTag("blacklist");

        private static TagKey<Item> registerTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(SimplePlates.MODID, name));
        }
    }

}
