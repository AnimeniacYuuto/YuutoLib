package yuuto.yuutolib.inventory

import net.minecraft.inventory.ISidedInventory
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.entity.player.EntityPlayer

/**
 * @author Jacob
 */
object SidedInventoryWrapper{
  def getWrapper(inv:IInventory):ISidedInventory={
    if(inv.isInstanceOf[ISidedInventory]){
      return inv.asInstanceOf[ISidedInventory];
    }else{
      return new SidedInventoryWrapper(inv);
    }
  }
}
class SidedInventoryWrapper(inv:IInventory) extends ISidedInventory{
  val sides:Array[Int] = new Array[Int](inv.getSizeInventory);
  var i = 0
  for(i<-1 until inv.getSizeInventory()){
      sides(i) = i;
  }
  
  def getSizeInventory():Int=inv.getSizeInventory();

  def getStackInSlot(slot:Int):ItemStack=inv.getStackInSlot(slot);

  def decrStackSize(slot:Int, amount:Int):ItemStack={
    return inv.decrStackSize(slot, amount);
  }

  def getStackInSlotOnClosing(slot:Int):ItemStack=inv.getStackInSlotOnClosing(slot);

  def setInventorySlotContents(slot:Int, stack:ItemStack)=inv.setInventorySlotContents(slot, stack);

  def getInventoryName():String=inv.getInventoryName();
  def hasCustomInventoryName():Boolean=inv.hasCustomInventoryName();

  def getInventoryStackLimit():Int=inv.getInventoryStackLimit();

  def markDirty()=inv.markDirty();

  def isUseableByPlayer(player:EntityPlayer):Boolean=inv.isUseableByPlayer(player);

  def openInventory()=inv.openInventory();

  def closeInventory()=inv.closeInventory();

  def isItemValidForSlot(slot:Int, stack:ItemStack):Boolean=inv.isItemValidForSlot(slot, stack);

  def getAccessibleSlotsFromSide(side:Int):Array[Int]=sides

  def canInsertItem(slot:Int, stack:ItemStack, side:Int):Boolean=isItemValidForSlot(slot, stack);

  def canExtractItem(slot:Int, stack:ItemStack, side:Int):Boolean=true;
}