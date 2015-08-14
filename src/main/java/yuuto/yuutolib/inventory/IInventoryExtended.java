package yuuto.yuutolib.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IInventoryExtended extends IInventory{
	
	boolean canExtractItem(int slot, ItemStack stack);
	boolean canInsertItem(int slot, ItemStack stack);

}
