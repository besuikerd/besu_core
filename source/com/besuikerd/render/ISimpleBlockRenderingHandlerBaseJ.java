package com.besuikerd.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public abstract class ISimpleBlockRenderingHandlerBaseJ implements ISimpleBlockRenderingHandler{
	
	protected Tessellator t = Tessellator.instance;
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		return false;
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
	}
	
	public boolean renderStandardBlock(IBlockAccess world, Block block, int x, int y, int z, RenderBlocks renderer){
		renderer.enableAO = false;
        
        int cMult = block.colorMultiplier(world, x, y, z);
        float f = (float)(cMult >> 16 & 255) / 255.0F;
        float f1 = (float)(cMult >> 8 & 255) / 255.0F;
        float f2 = (float)(cMult & 255) / 255.0F;

        
        
        boolean flag = false;
        float f3 = 0.5F;
        float f4 = 1.0F;
        float f5 = 0.8F;
        float f6 = 0.6F;
        float f7 = f4 * f;
        float f8 = f4 * f1;
        float f9 = f4 * f2;
        float f10 = f3;
        float f11 = f5;
        float f12 = f6;
        float f13 = f3;
        float f14 = f5;
        float f15 = f6;
        float f16 = f3;
        float f17 = f5;
        float f18 = f6;

        if (block != Blocks.grass)
        {
            f10 = f3 * f;
            f11 = f5 * f;
            f12 = f6 * f;
            f13 = f3 * f1;
            f14 = f5 * f1;
            f15 = f6 * f1;
            f16 = f3 * f2;
            f17 = f5 * f2;
            f18 = f6 * f2;
        }

        int l = block.getMixedBrightnessForBlock(world, x, y, z);

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y - 1, z, 0))
        {
            t.setBrightness(renderer.renderMinY > 0.0D ? l : block.getMixedBrightnessForBlock(world, x, y - 1, z));
            t.setColorOpaque_F(f10, f13, f16);
            renderFaceBottom(block, x, y, z, renderer.getBlockIcon(block, world, x, y, z, 0), renderer);
            flag = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y + 1, z, 1))
        {
            t.setBrightness(renderer.renderMaxY < 1.0D ? l : block.getMixedBrightnessForBlock(world, x, y + 1, z));
            t.setColorOpaque_F(f7, f8, f9);
            renderFaceTop(block, x, y, z, renderer.getBlockIcon(block, world, x, y, z, 1), renderer);
            flag = true;
        }

        IIcon iicon;

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y, z - 1, 2))
        {
            t.setBrightness(renderer.renderMinZ > 0.0D ? l : block.getMixedBrightnessForBlock(world, x, y, z - 1));
            t.setColorOpaque_F(f11, f14, f17);
            iicon = renderer.getBlockIcon(block, world, x, y, z, 2);
            renderFaceNorth(block, x, y, z, iicon, renderer);
            flag = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x, y, z + 1, 3))
        {
            t.setBrightness(renderer.renderMaxZ < 1.0D ? l : block.getMixedBrightnessForBlock(world, x, y, z + 1));
            t.setColorOpaque_F(f11, f14, f17);
            iicon = renderer.getBlockIcon(block, world, x, y, z, 3);
            renderFaceSouth(block, x, y, z, iicon, renderer);
            flag = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x - 1, y, z, 4))
        {
            t.setBrightness(renderer.renderMinX > 0.0D ? l : block.getMixedBrightnessForBlock(world, x - 1, y, z));
            t.setColorOpaque_F(f12, f15, f18);
            iicon = renderer.getBlockIcon(block, world, x, y, z, 4);
            renderFaceWest(block, x, y, z, iicon, renderer);
            flag = true;
        }

        if (renderer.renderAllFaces || block.shouldSideBeRendered(world, x + 1, y, z, 5))
        {
            t.setBrightness(renderer.renderMaxX < 1.0D ? l : block.getMixedBrightnessForBlock(world, x + 1, y, z));
            t.setColorOpaque_F(f12, f15, f18);
            iicon = renderer.getBlockIcon(block, world, x, y, z, 5);
            renderFaceEast(block, x, y, z, iicon, renderer);
            flag = true;
        }
        return flag;
	}
	
	public void renderFaceWest(Block block, int x, int y, int z, IIcon icon, RenderBlocks renderer){renderer.renderFaceXNeg(block, x, y, z, icon);}
	public void renderFaceSouth(Block block, int x, int y, int z, IIcon icon, RenderBlocks renderer){renderer.renderFaceZPos(block, x, y, z, icon);}
	public void renderFaceBottom(Block block, int x, int y, int z, IIcon icon, RenderBlocks renderer){renderer.renderFaceYNeg(block, x, y, z, icon);}
	public void renderFaceTop(Block block, int x, int y, int z, IIcon icon, RenderBlocks renderer){renderer.renderFaceYPos(block, x, y, z, icon);}
	
	public void renderFaceNorth(Block block, int x, int y, int z, IIcon icon, RenderBlocks renderer){
		if (renderer.hasOverrideBlockTexture())
        {
            icon = renderer.overrideBlockTexture;
        }

        double d3 = (double)icon.getInterpolatedU(renderer.renderMaxX * 16.0D);
        double d4 = (double)icon.getInterpolatedU(renderer.renderMinX * 16.0D);
        double d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxY * 16.0D);
        double d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinY * 16.0D);
        double d7;

        if (renderer.flipTexture)
        {
            d7 = d3;
            d3 = d4;
            d4 = d7;
        }

        if (renderer.renderMinX < 0.0D || renderer.renderMaxX > 1.0D)
        {
            d3 = (double)icon.getMinU();
            d4 = (double)icon.getMaxU();
        }

        if (renderer.renderMinY < 0.0D || renderer.renderMaxY > 1.0D)
        {
            d5 = (double)icon.getMinV();
            d6 = (double)icon.getMaxV();
        }

        d7 = d4;
        double d8 = d3;
        double d9 = d5;
        double d10 = d6;

        if (renderer.uvRotateEast == 2)
        {
            d3 = (double)icon.getInterpolatedU(renderer.renderMinY * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(renderer.renderMaxY * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxX * 16.0D);
            d9 = d5;
            d10 = d6;
            d7 = d3;
            d8 = d4;
            d5 = d6;
            d6 = d9;
        }
        else if (renderer.uvRotateEast == 1)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxY * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMaxX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMinX * 16.0D);
            d7 = d4;
            d8 = d3;
            d3 = d4;
            d4 = d8;
            d9 = d6;
            d10 = d5;
        }
        else if (renderer.uvRotateEast == 3)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxX * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMaxY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMinY * 16.0D);
            d7 = d4;
            d8 = d3;
            d9 = d5;
            d10 = d6;
        }

        double d11 = x + renderer.renderMinX;
        double d12 = x + renderer.renderMaxX;
        double d13 = y + renderer.renderMinY;
        double d14 = y + renderer.renderMaxY;
        double d15 = z + renderer.renderMinZ;

        if (renderer.renderFromInside)
        {
            d11 = x + renderer.renderMaxX;
            d12 = x + renderer.renderMinX;
        }

        if (renderer.enableAO)
        {
            t.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
            t.setBrightness(renderer.brightnessTopLeft);
            t.addVertexWithUV(d11, d14, d15, d7, d9);
            t.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
            t.setBrightness(renderer.brightnessBottomLeft);
            t.addVertexWithUV(d12, d14, d15, d3, d5);
            t.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
            t.setBrightness(renderer.brightnessBottomRight);
            t.addVertexWithUV(d12, d13, d15, d8, d10);
            t.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
            t.setBrightness(renderer.brightnessTopRight);
            t.addVertexWithUV(d11, d13, d15, d4, d6);
        }
        else
        {
        	if(renderer.uvRotateEast == 3){
        		t.addVertexWithUV(d11, d14, d15, d8, d10);
                t.addVertexWithUV(d12, d14, d15, d4, d6);
                t.addVertexWithUV(d12, d13, d15, d7, d9);
                t.addVertexWithUV(d11, d13, d15, d3, d5);
        	} else {
        		t.addVertexWithUV(d11, d14, d15, d7, d9);
                t.addVertexWithUV(d12, d14, d15, d3, d5);
                t.addVertexWithUV(d12, d13, d15, d8, d10);
                t.addVertexWithUV(d11, d13, d15, d4, d6);
        	}
            
        }
	}
	
	public void renderFaceEast(Block block, int x, int y, int z, IIcon icon, RenderBlocks renderer){
		
		if (renderer.hasOverrideBlockTexture())
        {
            icon = renderer.overrideBlockTexture;
        }

        double d3 = (double)icon.getInterpolatedU(renderer.renderMaxZ * 16.0D);
        double d4 = (double)icon.getInterpolatedU(renderer.renderMinZ * 16.0D);
        double d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxY * 16.0D);
        double d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinY * 16.0D);
        double d7;

        if (renderer.flipTexture)
        {
            d7 = d3;
            d3 = d4;
            d4 = d7;
        }

        if (renderer.renderMinZ < 0.0D || renderer.renderMaxZ > 1.0D)
        {
            d3 = (double)icon.getMinU();
            d4 = (double)icon.getMaxU();
        }

        if (renderer.renderMinY < 0.0D || renderer.renderMaxY > 1.0D)
        {
            d5 = (double)icon.getMinV();
            d6 = (double)icon.getMaxV();
        }

        d7 = d4;
        double d8 = d3;
        double d9 = d5;
        double d10 = d6;

        if (renderer.uvRotateSouth == 2)
        {
            d3 = (double)icon.getInterpolatedU(renderer.renderMinY * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinZ * 16.0D);
            d4 = (double)icon.getInterpolatedU(renderer.renderMaxY * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxZ * 16.0D);
            d9 = d5;
            d10 = d6;
            d7 = d3;
            d8 = d4;
            d5 = d6;
            d6 = d9;
        }
        else if (renderer.uvRotateSouth == 1)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxY * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMaxZ * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMinZ * 16.0D);
            d7 = d4;
            d8 = d3;
            d3 = d4;
            d4 = d8;
            d9 = d6;
            d10 = d5;
        }
        else if (renderer.uvRotateSouth == 3)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinZ * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxZ * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMaxY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMinY * 16.0D);
            d7 = d4;
            d8 = d3;
            d9 = d5;
            d10 = d6;
        }

        double d11 = x + renderer.renderMaxX;
        double d12 = y + renderer.renderMinY;
        double d13 = y + renderer.renderMaxY;
        double d14 = z + renderer.renderMinZ;
        double d15 = z + renderer.renderMaxZ;

        if (renderer.renderFromInside)
        {
            d14 = z + renderer.renderMaxZ;
            d15 = z + renderer.renderMinZ;
        }

        if (renderer.enableAO)
        {
            t.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
            t.setBrightness(renderer.brightnessTopLeft);
            t.addVertexWithUV(d11, d12, d15, d8, d10);
            t.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
            t.setBrightness(renderer.brightnessBottomLeft);
            t.addVertexWithUV(d11, d12, d14, d4, d6);
            t.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
            t.setBrightness(renderer.brightnessBottomRight);
            t.addVertexWithUV(d11, d13, d14, d7, d9);
            t.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
            t.setBrightness(renderer.brightnessTopRight);
            t.addVertexWithUV(d11, d13, d15, d3, d5);
        }
        else
        {
        	//fix east side rotation bug
        	if(renderer.uvRotateSouth == 3){
        		t.addVertexWithUV(d11, d12, d15, d7, d9);
                t.addVertexWithUV(d11, d12, d14, d3, d5);
                t.addVertexWithUV(d11, d13, d14, d8, d10);
                t.addVertexWithUV(d11, d13, d15, d4, d6);
        	} else{
        		t.addVertexWithUV(d11, d12, d15, d8, d10);
                t.addVertexWithUV(d11, d12, d14, d4, d6);
                t.addVertexWithUV(d11, d13, d14, d7, d9);
                t.addVertexWithUV(d11, d13, d15, d3, d5);
        	}
        	
            
        }
		
		
		
	}
}
