package yuuto.yuutolib.inventory

import net.minecraft.inventory.IInventory;

trait IInventoryParent {

  def onInventoryChanged(inventorySimple:IInventory);

}