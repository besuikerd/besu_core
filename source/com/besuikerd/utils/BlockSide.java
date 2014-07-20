package com.besuikerd.utils;

import java.util.Iterator;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.besuikerd.utils.tuple.Vector3;
import com.google.common.collect.ImmutableSet;

public enum BlockSide {
	BOTTOM,
	TOP,
	NORTH,
	SOUTH,
	WEST,
	EAST;
	
	public boolean isOnSameAxis(BlockSide other){
		return Axis.getAxisOf(this).sides.contains(other);
	}
	
	public static BlockSide lookup(int side){
		return side >= 0 && side < values().length ? values()[side] : null;
	}
	
	public Vector3 getRelativeCoordinates(int x, int y, int z, int distance){
		switch(this){
		case TOP: 		return new Vector3(x, y + distance, z);
		case BOTTOM: 	return new Vector3(x, y - distance, z);
		case NORTH:		return new Vector3(x, y, z - distance);
		case SOUTH:		return new Vector3(x, y, z + distance);
		case WEST:		return new Vector3(x - distance, y, z);
		case EAST:		return new Vector3(x + distance, y, z);
		default:		return new Vector3(x, y, z);
		}
	}
	
	public Vector3 getRelativeCoordinates(int x, int y, int z){
		return getRelativeCoordinates(x, y, z, 1);
	}
	
	/**
	 * returns Blockside opposite of this BlockSide
	 * @return
	 */
	public BlockSide opposite(){
		return values()[ordinal() + (ordinal() % 2 == 0 ? 1 : -1)];
	}
	
	/**
	 * turns an array of blocksides to a byte representation
	 * @param blockSides
	 * @return
	 */
	public static byte toByte(BlockSide... blockSides){
		byte result = 0;
		for(BlockSide b : blockSides){
			result |= 1 << b.ordinal();
		}
		return result;
	}
	
	public static BlockSide[] fromByte(byte sides){
		BlockSide[] result = new BlockSide[Integer.bitCount(sides & 0xff)];
		int current = 0;
		for(int i = 0 ; i < 6 ; i++){
			 if(((sides >> i) & 1) == 1){
				 result[current] = BlockSide.values()[i];
				 current++;
			 }
		}
		return result;
	}
	
	public static boolean isSideSelected(byte selectedSides, int side){
		return ((selectedSides >> side) & 1) == 1;
	}
	
	public static boolean isSideSelected(byte selectedSides, BlockSide side){
		return isSideSelected(selectedSides, side.ordinal());
	}
	
	public static <E> Iterable<E> blockSideIterator(final Class<E> cls, final World world, final int x, final int y, final int z){
		return new Iterable<E>(){
			@Override
			public Iterator<E> iterator() {
				return new Iterator<E>() {
					private int i = 0;
					private E next;
					
					{
						this.next = find();
					}
					
					@Override
					public boolean hasNext() {
						return next != null;
					}
					
					@Override
					public E next() {
						E theNext = next;
						this.next = find();
						return theNext;
					}
					
					private E find(){
						E found = null;
						while(found == null && i < values().length){
							BlockSide side = values()[i++];
							Vector3 rel = side.getRelativeCoordinates(x, y, z);
							TileEntity tile = world.getTileEntity(rel._1, rel._2, rel._3);
							if(tile != null && cls.isInstance(tile)){
								found = cls.cast(tile);
							}
						}
						return found;
					}
					
					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
	
	public ForgeDirection toForgeDirection(){
		return ForgeDirection.values()[ordinal()];
	}
	
	public enum Axis{
		X(NORTH, SOUTH),
		Y(TOP, BOTTOM),
		Z(WEST, EAST);
		
		private ImmutableSet<BlockSide> sides;
		private Axis(BlockSide fst, BlockSide snd){
			sides = ImmutableSet.of(fst, snd);
		}
		
		public static Axis getAxisOf(BlockSide side){
			for(Axis axis : values()){
				if(axis.sides.contains(side)){
					return axis;
				}
			}
			return null;
		}
		
		public ImmutableSet<BlockSide> getSides() {
			return sides;
		}
	}
}
