package com.besuikerd.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SimpleModelBase extends ModelBase{
	
	private ModelRenderer[] renderers;
	
	public final float minX, minY, minZ;
	public final float maxX, maxY, maxZ;
	public final float dimX, dimY, dimZ;
	
	public SimpleModelBase(int textureWidth, int textureHeight, ShapeBuilder... builders) {
		super();
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
		
		
		float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE, minZ = Float.MAX_VALUE;
		float maxX = 0, maxY = 0, maxZ = 0;
		
		renderers = new ModelRenderer[builders.length];
		for(int i = 0 ; i < builders.length ; i++){
			ShapeBuilder b = builders[i];
			ModelRenderer m = new ModelRenderer(this, b.texX, b.texY);
			renderers[i] = m;
			m.addBox(b.offX, b.offY, b.offZ, b.dimX, b.dimY, b.dimZ);
			m.setRotationPoint(b.rotPointY, b.rotPointY, b.rotPointZ);
			m.setTextureSize(textureWidth, textureHeight);
			m.mirror = b.mirror;
			
			//update base bounding box
			if(b.offX < minX) minX = b.offX;
			if(b.offY < minY) minY = b.offY;
			if(b.offZ < minZ) minZ = b.offZ;
			if(b.offX + b.dimX > maxX) maxX = b.offX + b.dimX;
			if(b.offY + b.dimY > maxY) maxY = b.offY + b.dimY;
			if(b.offZ + b.dimZ > maxZ) maxZ = b.offZ + b.dimZ;
			
			
			setRotation(m, b.rotX, b.rotY, b.rotZ);
		}
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
		this.dimX = maxX - minX;
		this.dimY = maxY - minY;
		this.dimZ = maxZ - minZ;
		
		//BLogger.debug("bounding box: (%f, %f, %f) to (%f, %f, %f), size: (%f, %f, %f)", minX, minY, minZ, maxX, maxY, maxZ, dimX, dimY, dimZ);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		for(ModelRenderer m : renderers){
			m.render(f5);
		}
	}
	  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
	  model.rotateAngleX = x;
	  model.rotateAngleY = y;
	  model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
	  super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }
	
	public static class ShapeBuilder{
		private float offX, offY, offZ;
		private int dimX, dimY, dimZ;
		private float rotX, rotY, rotZ;
		private float rotPointX, rotPointY, rotPointZ;
		private int texX, texY;
		private boolean mirror;
		
		public ShapeBuilder offset(float x, float y, float z){
			this.offX = x;
			this.offY = y;
			this.offZ = z;
			return this;
		}
		
		public ShapeBuilder offX(float x){
			this.offX = x;
			return this;
		}
		
		public ShapeBuilder offY(float y){
			this.offY = y;
			return this;
		}
		
		public ShapeBuilder offZ(float z){
			this.offZ = z;
			return this;
		}
		
		public ShapeBuilder dimension(int x, int y, int z){
			this.dimX = x;
			this.dimY = y;
			this.dimZ = z;
			return this;
		}
		
		public ShapeBuilder dimX(int x){
			this.dimX = x;
			return this;
		}
		
		public ShapeBuilder dimY(int y){
			this.dimY = y;
			return this;
		}
		
		public ShapeBuilder dimZ(int z){
			this.dimZ = z;
			return this;
		}
		
		public ShapeBuilder rotation(float x, float y, float z){
			this.rotX = x;
			this.rotY = y;
			this.rotZ = z;
			return this;
		}
		
		public ShapeBuilder rotX(float x){
			this.rotX = x;
			return this;
		}
		
		public ShapeBuilder rotY(float y){
			this.rotY = y;
			return this;
		}
		
		public ShapeBuilder rotZ(float z){
			this.rotZ = z;
			return this;
		}
		
		public ShapeBuilder rotationPoint(float x, float y, float z){
			this.rotPointX = x;
			this.rotPointY = y;
			this.rotPointZ = z;
			return this;
		}
		
		public ShapeBuilder rotPointX(float x){
			this.rotPointX = x;
			return this;
		}
		
		public ShapeBuilder rotPointY(float y){
			this.rotPointY = y;
			return this;
		}
		
		public ShapeBuilder rotPointZ(float z){
			this.rotPointZ = z;
			return this;
		}
		
		public ShapeBuilder textureOffset(int x, int y){
			this.texX = x;
			this.texY = y;
			return this;
		}
		
		public ShapeBuilder texX(int x){
			this.texX = x;
			return this;
		}
		
		public ShapeBuilder texY(int y){
			this.texY = y;
			return this;
		}
		
		public ShapeBuilder mirror(boolean mirror){
			this.mirror = true;
			return this;
		}
	}
}
