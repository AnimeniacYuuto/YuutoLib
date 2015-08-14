package yuuto.yuutolib.block

import java.util.List
import net.minecraft.inventory.Container
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack

/**
 * @author Jacob
 */
abstract class ContainerAlt(val inventory:IInventory, val playerInventory:InventoryPlayer) extends Container{
    var playerInvStart:Int = 0;
    var playerInvEnd:Int = 0;
    bindPlayerInventory(bindInventorySlots());
    bindOtherSlots();
    
    def bindInventorySlots():Array[Int];
    def bindPlayerInventory(pos:Array[Int]){
      playerInvStart = this.inventorySlots.size();
      var i = 0
      var j = 0;
      for (i<-0 until 3) {
          for (j<-0 until 9) {
              addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9,
                              pos(0) + j * 18, pos(1) + i * 18));
          }
      }
      i = 0;
      for (i<-0 until 9) {
          addSlotToContainer(new Slot(playerInventory, i, pos(0) + i * 18, pos(1)+58));
      }
      playerInvEnd = this.inventorySlots.size();
    }
    def bindOtherSlots()={}
    
    override def transferStackInSlot(player:EntityPlayer, slot:Int):ItemStack={
            var stack:ItemStack = null;
            val slotObject:Slot = inventorySlots.get(slot).asInstanceOf[Slot];

            //null checks and checks if the item can be stacked (maxStackSize > 1)
            if (slotObject != null && slotObject.getHasStack()) {
                    val stackInSlot:ItemStack = slotObject.getStack();
                    stack = stackInSlot.copy();

                    //merges the item into player inventory since its in the tileEntity
                    if (slot < playerInvStart) {
                            if (!this.mergeItemStack(stackInSlot, playerInvStart, playerInvEnd, true)) {
                                    return null;
                            }
                    }
                    //places it into the tileEntity is possible since its in the player inventory
                    else if (!this.mergeItemStack(stackInSlot, 0, playerInvStart, false)) {
                            return null;
                    }

                    if (stackInSlot.stackSize == 0) {
                            slotObject.putStack(null);
                    } else {
                            slotObject.onSlotChanged();
                    }

                    if (stackInSlot.stackSize == stack.stackSize) {
                            return null;
                    }
                    slotObject.onPickupFromSlot(player, stackInSlot);
            }
            return stack;
    }
}