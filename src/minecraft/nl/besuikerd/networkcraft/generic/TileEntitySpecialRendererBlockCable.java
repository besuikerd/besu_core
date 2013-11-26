package nl.besuikerd.networkcraft.generic;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import nl.besuikerd.networkcraft.core.NCLogger;

import org.lwjgl.opengl.GL11;

public class TileEntitySpecialRendererBlockCable extends TileEntitySpecialRendererConnecting{

	private static final SimpleModelBase rendererBase = new SimpleModelBase();
	private static final SimpleModelBase rendererConnection = new SimpleModelBase();
	
	static{
		ModelRenderer renderer = rendererBase.attach();
		renderer.addBox(0f, 0f, 0f, 10, 10, 10);
		renderer.setRotationPoint(0f, -6f, 0f);
		renderer.setTextureSize(64, 64);
		renderer.mirror = true;
		renderer.rotateAngleX = 0f;
		renderer.rotateAngleY = 0f;
		renderer.rotateAngleZ = 0f;
		
		renderer = rendererConnection.attach();
		renderer.addBox(0f, 0f, 0f, 2, 6, 2);
		renderer.setRotationPoint(7f, -12f, 7f);
		renderer.setTextureSize(64, 64);
		renderer.mirror = true;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y,
			double z, float f) {
		//The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
//This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
//This is the texture of your block. It's pathed to be the same place as your other blocks here.
        //Outdated bindTextureByName("/mods/roads/textures/blocks/TrafficLightPoleRed.png");
//Use in 1.6.2  this
//the ':' is very important
//binding the textures
        bindTexture(new ResourceLocation("networkcraft:textures/blocks/cable.png"));

//This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
//A reference to your Model file. Again, very important.
        rendererBase.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
//Tell it to stop rendering for both the PushMatrix's
        GL11.glPopMatrix();
        GL11.glPopMatrix();

	}
	
	@Override
	public void renderBase(TileEntity entity, double x, double y, double z,
			float f) {
		bindTexture(new ResourceLocation("networkcraft", "textures/blocks/cable.png"));
		GL11.glPushMatrix();
		rendererBase.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625f);
		GL11.glPopMatrix();
	}

	@Override
	public void renderConnection(TileEntityConnecting entity, int side,
			double x, double y, double z, float f) {
		
		GL11.glPushMatrix();
		translateForSide(side);
		rendererConnection.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625f);
		GL11.glPopMatrix();
	}
	
	/*
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) {
		GL11.glPushMatrix();
        //This will move our renderer so that it will be on proper place in the world
        GL11.glTranslated(d0, d1, d2);
        bindTexture(new ResourceLocation("generic", "textures/blocks/cable.png"));
        
        GL11.glPushMatrix();
       	CableRenderModel.instance.render(null, 0.0F, 0.0F, 0, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
	}	
	*/	
	

}