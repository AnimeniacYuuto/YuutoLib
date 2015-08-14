package yuuto.yuutolib.block.traits

import net.minecraft.world.World
import net.minecraft.inventory.IInventory
import net.minecraft.block.Block
import net.minecraft.item.ItemStack
import net.minecraft.entity.item.EntityItem
import yuuto.yuutolib.block.ModBlockContainer
import net.minecraft.nbt.NBTTagCompound

/**
 * @author Jacob
 */
trait TBlockContainerInventory extends ModBlockContainer{
  
  /**
   * Drops all items from an inventory
   * @param inv
   * @param world
   * @param x
   * @param y
   * @param z
   * @param block
   * @param meta
   */
  def dropItemsFromInventory(inv:IInventory, world:World, x:Int, y:Int, z:Int, block:Block, meta:Int){
    for (i1 <- 0 until inv.getSizeInventory() if(inv.getStackInSlot(i1) != null))
        {
            val itemstack:ItemStack = inv.getStackInSlot(i1);

            val f:Float = this.getRandom().nextFloat() * 0.8F + 0.1F;
            val f1:Float = this.getRandom().nextFloat() * 0.8F + 0.1F;
            var entityitem:EntityItem = null;

            while (itemstack.stackSize > 0)
            {
                val f2:Float = this.getRandom().nextFloat() * 0.8F + 0.1F;
                var j1:Int = this.getRandom().nextInt(21) + 10;

                if (j1 > itemstack.stackSize)
                {
                    j1 = itemstack.stackSize;
                }

                itemstack.stackSize -= j1;
                entityitem = new EntityItem(world, (x + f), (y + f1), (z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                val f3:Float = 0.05F;
                entityitem.motionX = (this.getRandom().nextGaussian() * f3);
                entityitem.motionY = (this.getRandom().nextGaussian() * f3 + 0.2F);
                entityitem.motionZ = (this.getRandom().nextGaussian() * f3);

                if (itemstack.hasTagCompound())
                {
                    entityitem.getEntityItem().setTagCompound(itemstack.getTagCompound().copy().asInstanceOf[NBTTagCompound]);
                }
                world.spawnEntityInWorld(entityitem)
            }
        }

        world.func_147453_f(x, y, z, block);
  }
}