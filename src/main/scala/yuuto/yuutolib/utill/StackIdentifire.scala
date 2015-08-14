package yuuto.yuutolib.utill

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

class StackIdentifire(stack:ItemStack) extends ItemIdentifire{
  
  def getStack():ItemStack={
    return stack;
  }
  
  override def matches(stack1:ItemStack):Boolean={
    if(stack == null || stack1 == null)
      return false;
    if(stack.getItem() != stack1.getItem())
      return false;
    if(stack.getItemDamage() != OreDictionary.WILDCARD_VALUE && stack.getItemDamage() != stack1.getItemDamage())
      return false;
    if(stack.hasTagCompound() && !ItemStack.areItemStackTagsEqual(stack, stack1))
      return false;
    return true;
  }

  override def matches(id:ItemIdentifire):Boolean={
    if(!id.isInstanceOf[StackIdentifire]){
      if(id.isInstanceOf[OreIdentifire])
        return id.matches(getStack());
      return false;
    }
    val sid:StackIdentifire = id.asInstanceOf[StackIdentifire];
    return matches(sid.getStack()) && sid.matches(stack);
  }
  

}