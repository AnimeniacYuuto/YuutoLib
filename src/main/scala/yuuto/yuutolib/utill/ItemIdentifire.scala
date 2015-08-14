package yuuto.yuutolib.utill

import net.minecraft.item.ItemStack

/**
 * @author Jacob
 */
object ItemIdentifire{
  def getIdentifire(stack:ItemStack):ItemIdentifire={
    return new StackIdentifire(stack);
  }
  def getIdentifire(ore:String):ItemIdentifire={
    return new OreIdentifire(ore);
  }
}
abstract class ItemIdentifire {
  
  def matches(stack:ItemStack):Boolean;
  def matches(id:ItemIdentifire):Boolean;
}