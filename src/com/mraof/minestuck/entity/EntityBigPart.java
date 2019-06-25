package com.mraof.minestuck.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * Created by mraof on 2017 January 26 at 9:31 PM.
 */
public class EntityBigPart extends EntityLivingBase implements IEntityAdditionalSpawnData
{
	PartGroup group;
	private int partId;

	public EntityBigPart(World world)
	{
		super(ModEntityTypes.BIG_PART, world);
	}

	EntityBigPart(World worldIn, PartGroup group, Vec3d size)
	{
		this(worldIn);
		this.group = group;
		this.setSize((float) size.x, (float) size.y);
	}

	void setPartId(int id)
	{
		this.partId = id;
	}
	
	/*@Override TODO Consider which nbt functions that should be overwritten, alternatively look at making the entity type not serializable
	public void readEntityFromNBT(NBTTagCompound compound)
	{
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
	}*/
	
	@Override
	public void baseTick()
	{
		if(this.group == null || this.group.parent == null || this.group.parent.removed)
		{
			this.remove();
		}
		super.baseTick();
		//world.getHeight(this.getPosition());
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float amount)
	{
		return this.group != null && this.group.attackFrom(damageSource, amount);
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList()
	{
		return new ArrayList<ItemStack>();
	}
	
	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, @Nullable ItemStack stack)
	{

	}

	@Override
	public EnumHandSide getPrimaryHand()
	{
		return EnumHandSide.RIGHT;
	}
	
	@Override
	public void writeSpawnData(PacketBuffer buffer)
	{
		buffer.writeInt(this.group.parent.getEntityId());
		buffer.writeFloat(this.width);
		buffer.writeFloat(this.height);
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData)
	{
		Entity entity = world.getEntityByID(additionalData.readInt());
		if(entity instanceof IBigEntity)
		{
			ArrayList<EntityBigPart> parts = ((IBigEntity) entity).getGroup().parts;
			int index = parts.size() - 1;
			while(index > 0 && parts.get(index).partId > this.partId)
			{
				index--;
			}
			parts.add(index, this);
		}
		this.setSize(this.width, this.height);
	}

	@Override
	public boolean isEntityEqual(Entity entityIn)
	{
		return entityIn == this || this.group != null && (entityIn == this.group.parent || entityIn instanceof EntityBigPart && ((EntityBigPart) entityIn).group == this.group);
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public boolean isEntityInsideOpaqueBlock()
	{
		return false;
	}

	@Override
	protected void collideWithEntity(Entity entityIn)
	{
	}

	@Override
	public void move(MoverType type, double x, double y, double z)
	{
		this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
		this.resetPositionToBB();
	}
}