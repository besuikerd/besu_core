package com.besuikerd.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.besuikerd.core.BlockSide;
import com.besuikerd.core.block.IConnectingSides;

public abstract class TileEntitySpecialRendererConnecting extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		
		
		renderBase(entity, x, y, z, f);
		
		if(entity instanceof IConnectingSides){
			IConnectingSides sides = ((IConnectingSides) entity);
			GL11.glPushMatrix();
			GL11.glTranslated(0.5d, 0.5d, 0.5d);
			for(BlockSide side : sides.getConnectingSides()){
				renderConnection(entity, side, x, y, z, f);
			}
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
	}

	
	public void translateForSide(SimpleModelBase b, BlockSide side){
		switch(side){
		case TOP:
			break;
		case BOTTOM:
			GL11.glRotated(180, 1d, 0, 0);
			break;
		case NORTH:
			GL11.glRotated(-90, 1d, 0, 0);
			break;
		case EAST:
			GL11.glRotated(-90, 0, 0, 1d);
			break;
		case SOUTH:
			GL11.glRotated(90, 1d, 0, 0);
			break;
		case WEST:
			GL11.glRotated(90, 0, 0, 1d);
			break;
		}
	}
	
	public abstract void renderBase(TileEntity entity, double x, double y, double z, float f);
	
	public abstract void renderConnection(TileEntity entity, BlockSide side, double x, double y, double z, float f);
}
