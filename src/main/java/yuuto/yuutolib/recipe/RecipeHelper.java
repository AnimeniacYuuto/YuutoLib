package yuuto.yuutolib.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHelper {
	
	public static void addShapedRecipe(boolean ore, ItemStack output, Object[] recipe){
		if(ore)
			GameRegistry.addRecipe(new ShapedOreRecipe(output, recipe));
		else
			GameRegistry.addShapedRecipe(output, recipe);
	}
	public static void addShapelessRecipe(boolean ore, ItemStack output, Object[] recipe){
		if(ore)
			GameRegistry.addRecipe(new ShapelessOreRecipe(output, recipe));
		else
			GameRegistry.addShapelessRecipe(output, recipe);
	}

}
