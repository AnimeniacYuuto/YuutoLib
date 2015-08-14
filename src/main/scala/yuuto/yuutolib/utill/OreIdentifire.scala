package yuuto.yuutolib.utill

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

class OreIdentifire(ore:String) extends ItemIdentifire{
  
  def getOreName():String={
    return ore;
  }
  def getOreID():Int={
    return OreDictionary.getOreID(ore);
  }
  
  override def matches(stack:ItemStack):Boolean={
    val ids:Array[Int] = OreDictionary.getOreIDs(stack);
    val oreId:Int = getOreID();
    if(oreId < 0 || ids == null || ids.length < 1)
      return false;
    for(id <- ids){
      if(id == oreId)
        return true;
    }
    return false;
  }

  override def matches(id:ItemIdentifire):Boolean={
    if(!id.isInstanceOf[OreIdentifire]){
      if(id.isInstanceOf[StackIdentifire])
        return this.matches(id.asInstanceOf[StackIdentifire].getStack());
      return false;
    }
    return ore.trim().matches(id.asInstanceOf[OreIdentifire].getOreName());
  }

}