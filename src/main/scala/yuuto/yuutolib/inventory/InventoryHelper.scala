package yuuto.yuutolib.inventory

import net.minecraft.inventory.ISidedInventory
import net.minecraft.item.ItemStack
import net.minecraftforge.common.util.ForgeDirection
import net.minecraft.inventory.IInventory

/**
 * @author Jacob
 */
object InventoryHelper {
  
  def pullStacks(origin:IInventory, target:IInventory, max:Int, pullDirection:ForgeDirection, simulate:Boolean):Boolean={
    return pullStacks(origin, target, max, pullDirection, pullDirection.getOpposite(), simulate);
  }
  def pullStacks(origin:IInventory, target:IInventory, max:Int, pullDirection:ForgeDirection, pushDirection:ForgeDirection, simulate:Boolean):Boolean={
    val inv:ISidedInventory = SidedInventoryWrapper.getWrapper(origin);
    if(inv.getSizeInventory() < 1)
      return false;
    val tar:ISidedInventory = SidedInventoryWrapper.getWrapper(target);
    val slots:Array[Int] = inv.getAccessibleSlotsFromSide(pullDirection.getOpposite.ordinal());
    System.out.println("Pulling from "+pullDirection.getOpposite);
    var break:Boolean = false;
    for(i <- 0 until slots.length if(!break)){
      val slot:Int = slots(i);
      if(inv.getStackInSlot(slot) == null || inv.getStackInSlot(slot).stackSize < 1){}
      else if(!inv.canExtractItem(slot, inv.getStackInSlot(slot), pullDirection.getOpposite.ordinal())){}
      else if(mergeStack(inv, slot,tar, max, pushDirection.getOpposite, simulate)){
        return true;
      }
    }
    return false;
  }
  
  def mergeStack(src:ISidedInventory, srcSlot:Int, target:ISidedInventory, max:Int, insertDirection:ForgeDirection, simulate:Boolean):Boolean={
    if(src.getStackInSlot(srcSlot) == null){
      return false;
    }
    val stack:ItemStack = if(simulate){src.getStackInSlot(srcSlot).copy()}else{src.getStackInSlot(srcSlot)};
    var maxTransfer = Math.min(max, stack.stackSize);
    if(maxTransfer < 1)
      return false;
    val slots:Array[Int] = target.getAccessibleSlotsFromSide(insertDirection.ordinal());
    for(i<- 0 until slots.length){
      val tStack:ItemStack = target.getStackInSlot(slots(i));
      if(!target.canInsertItem(slots(i), stack, insertDirection.ordinal())){}
      else if(tStack != null && 
          (tStack.stackSize == tStack.getMaxStackSize() || 
          tStack.stackSize == target.getInventoryStackLimit())){}
      else if(tStack == null){
        if(!simulate){
          target.setInventorySlotContents(slots(i), src.decrStackSize(srcSlot, maxTransfer));
        }
        return true;
      }
      else if(!tStack.isItemEqual(stack)){}
      else if(!ItemStack.areItemStackTagsEqual(stack, tStack)){}
      else{
        var maxT:Int = stack.getMaxStackSize();
        if(target.getInventoryStackLimit() < maxT){
          maxT = target.getInventoryStackLimit();
        }
        maxT -= tStack.stackSize;
        if(maxTransfer < maxT){
          maxT = maxTransfer;
        }
        if(maxT == 0){}
        else {
          if(simulate){
            maxTransfer-=max;
          }else{
            val d:Int=src.decrStackSize(srcSlot, maxT).stackSize;
            tStack.stackSize += d;
            maxTransfer-=d;
          }
        }
        if(simulate && maxTransfer < 1){
          return true;
        }else if(!simulate &&(src.getStackInSlot(srcSlot) == null || src.getStackInSlot(srcSlot).stackSize<1 || maxTransfer < 1)){
          return true;
        }
      }
    }
    return false;
  }
  def mergeStack(stack:ItemStack, target:ISidedInventory, insertDirection:ForgeDirection, simulate:Boolean):Boolean={
    if(stack.stackSize < 1){
      return false;
    }
    val slots:Array[Int] = target.getAccessibleSlotsFromSide(insertDirection.ordinal());
    for(i<- 0 until slots.length){
      val tStack:ItemStack = target.getStackInSlot(slots(i));
      if(!target.canInsertItem(slots(i), stack, insertDirection.ordinal())){}
      else if(tStack != null && 
          (tStack.stackSize == tStack.getMaxStackSize() || 
          tStack.stackSize == target.getInventoryStackLimit())){}
      else if(tStack == null){
        if(!simulate){
          target.setInventorySlotContents(slots(i), stack.copy());
        }
        stack.stackSize = 0;
        return true;
      }
      else if(tStack.getItem() != stack.getItem()){}
      else if(tStack.getItemDamage() != stack.getItemDamage()){}
      else if(!ItemStack.areItemStackTagsEqual(stack, tStack)){}
      else{
        var max:Int = stack.getMaxStackSize();
        if(target.getInventoryStackLimit() < max){
          max = target.getInventoryStackLimit();
        }
        max -= tStack.stackSize;
        if(stack.stackSize < max){
          max = stack.stackSize;
        }
        if(max <= 0){}
        else {
          if(!simulate){
            tStack.stackSize += max;
          }
          stack.stackSize -= max;
          if(stack.stackSize < 1){
            return true;
          }
        }
      }
    }
    return false;
  }
  def mergeStack(stack:ItemStack, inv:IInventory, simulate:Boolean):Boolean={
    return mergeStack(stack, InventoryWrapper.getWrapper(inv), simulate);
  }
  def mergeStack(stack:ItemStack, inv:IInventoryExtended, simulate:Boolean):Boolean={
    System.out.println("Merging stack");
    if(stack.stackSize < 1)
      return false;
    var max:Int= Math.min(stack.getMaxStackSize(), inv.getInventoryStackLimit());
    for(i<-0 until inv.getSizeInventory() if(inv.canInsertItem(i, stack))){
      var stack2=inv.getStackInSlot(i);
      if(stack2 == null){
        val m2=Math.min(stack.stackSize, max);
        if(m2 == stack.stackSize){
          stack2=stack.copy();
          stack.stackSize=0;
        }else{
          stack2=stack.splitStack(m2);
        }
        if(!simulate){
          inv.setInventorySlotContents(i, stack2);
        }
        if(stack.stackSize < 1)
          return true;
      }
      else if(!stack.isItemEqual(stack2)){}
      else if(!ItemStack.areItemStackTagsEqual(stack, stack2)){}
      else if(stack2.stackSize < max){
        val m2=Math.min(stack.stackSize, max-stack2.stackSize);
        if(!simulate){
          stack2.stackSize+=m2;
          inv.setInventorySlotContents(i, stack2);
        }
        stack.stackSize-=m2;
        if(stack.stackSize < 1)
          return true;
      }
    }
    return false;
  }
}