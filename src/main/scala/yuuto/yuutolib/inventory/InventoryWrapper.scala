/**
 * @author Yuuto
 */
package yuuto.yuutolib.inventory

import net.minecraft.inventory.IInventory
import net.minecraftforge.common.util.ForgeDirection
import net.minecraft.inventory.ISidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.entity.player.EntityPlayer

object InventoryWrapper {
  def getWrapper(inv:IInventory):IInventoryExtended={
    return getWrapper(inv, ForgeDirection.UNKNOWN);
  }
  def getWrapper(inv:IInventory, dir:ForgeDirection):IInventoryExtended={
    if(inv.isInstanceOf[IInventoryExtended])
      return inv.asInstanceOf[IInventoryExtended];
    if(inv.isInstanceOf[ISidedInventory]){
       val slots:Array[Int]=inv.asInstanceOf[ISidedInventory].getAccessibleSlotsFromSide(dir.ordinal());
       if(slots==null || slots.length < 1)
         return null;
       return new InventoryWrapperSided(inv.asInstanceOf[ISidedInventory], slots, dir);
    }
    return new InventoryWrapperBasic(inv); 
  }
}
class InventoryWrapperBasic(val inv:IInventory) extends IInventoryExtended{
  
  
  override def getSizeInventory():Int=inv.getSizeInventory();

  /**
   * Returns the stack in slot i
   */
  override def getStackInSlot(slot:Int):ItemStack=inv.getStackInSlot(slot);

  /**
   * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
   * new stack.
   */
  override def decrStackSize(slot:Int, amt:Int):ItemStack=inv.decrStackSize(slot, amt);

  /**
   * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
   * like when you close a workbench GUI.
   */
  override def getStackInSlotOnClosing(slot:Int):ItemStack=inv.getStackInSlotOnClosing(slot);

  /**
   * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
   */
  override def setInventorySlotContents(slot:Int, stack:ItemStack)=inv.setInventorySlotContents(slot, stack);

  /**
   * Returns the name of the inventory
   */
  override def getInventoryName():String=inv.getInventoryName();

  /**
   * Returns if the inventory is named
   */
  override def hasCustomInventoryName():Boolean=inv.hasCustomInventoryName();

  /**
   * Returns the maximum stack size for a inventory slot.
   */
  override def getInventoryStackLimit():Int=inv.getInventoryStackLimit();

  /**
   * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
   * hasn't changed and skip it.
   */
  override def markDirty()=inv.markDirty();

  /**
   * Do not make give this method the name canInteractWith because it clashes with Container
   */
  override def isUseableByPlayer(player:EntityPlayer):Boolean=inv.isUseableByPlayer(player);

  override def openInventory()=inv.openInventory();

  override def closeInventory()=inv.closeInventory();

  /**
   * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
   */
  override def isItemValidForSlot(slot:Int, stack:ItemStack):Boolean=inv.isItemValidForSlot(slot, stack);
  
  override def canExtractItem(slot:Int, stack:ItemStack):Boolean=true
  override def canInsertItem(slot:Int, stack:ItemStack):Boolean=isItemValidForSlot(slot, stack);
}
class InventoryWrapperSided(val inv:ISidedInventory, val slots:Array[Int], val dir:ForgeDirection) extends IInventoryExtended{
  
  
  override def getSizeInventory():Int=slots.length;

  /**
   * Returns the stack in slot i
   */
  override def getStackInSlot(slot:Int):ItemStack=inv.getStackInSlot(slots(slot));

  /**
   * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
   * new stack.
   */
  override def decrStackSize(slot:Int, amt:Int):ItemStack=inv.decrStackSize(slots(slot), amt);

  /**
   * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
   * like when you close a workbench GUI.
   */
  override def getStackInSlotOnClosing(slot:Int):ItemStack=inv.getStackInSlotOnClosing(slots(slot));

  /**
   * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
   */
  override def setInventorySlotContents(slot:Int, stack:ItemStack)=inv.setInventorySlotContents(slots(slot), stack);

  /**
   * Returns the name of the inventory
   */
  override def getInventoryName():String=inv.getInventoryName();

  /**
   * Returns if the inventory is named
   */
  override def hasCustomInventoryName():Boolean=inv.hasCustomInventoryName();

  /**
   * Returns the maximum stack size for a inventory slot.
   */
  override def getInventoryStackLimit():Int=inv.getInventoryStackLimit();

  /**
   * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
   * hasn't changed and skip it.
   */
  override def markDirty()=inv.markDirty();

  /**
   * Do not make give this method the name canInteractWith because it clashes with Container
   */
  override def isUseableByPlayer(player:EntityPlayer):Boolean=inv.isUseableByPlayer(player);

  override def openInventory()=inv.openInventory();

  override def closeInventory()=inv.closeInventory();

  /**
   * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
   */
  override def isItemValidForSlot(slot:Int, stack:ItemStack):Boolean=inv.isItemValidForSlot(slots(slot), stack);
  
  override def canExtractItem(slot:Int, stack:ItemStack):Boolean=inv.canExtractItem(slots(slot), stack, dir.ordinal())
  override def canInsertItem(slot:Int, stack:ItemStack):Boolean=inv.canInsertItem(slots(slot), stack, dir.ordinal());
}