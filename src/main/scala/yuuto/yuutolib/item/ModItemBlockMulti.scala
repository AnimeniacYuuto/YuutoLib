package yuuto.yuutolib.item

import net.minecraft.item.ItemBlock
import net.minecraft.block.Block
import net.minecraft.util.IIcon
import net.minecraft.item.ItemStack

/**
 * @author Jacob
 */
class ModItemBlockMulti(block:Block) extends ItemBlock(block){
  this.hasSubtypes = true;
  
  override def getIconFromDamage(metadata:Int):IIcon={
    return this.field_150939_a.getIcon(2, metadata);
  }
  
  override def getUnlocalizedName(stack:ItemStack):String={
    return getUnlocalizedName()+"."+stack.getItemDamage;
  }
  
  override def getMetadata (damageValue:Int):Int={
    return damageValue;
  }
  
}