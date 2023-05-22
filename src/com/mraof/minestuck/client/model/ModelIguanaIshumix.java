package com.mraof.minestuck.client.model;// Made with Blockbench 4.7.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelIguanaIshumix extends ModelBase {
	private final ModelRenderer Torso;
	private final ModelRenderer backspikes_r1;
	private final ModelRenderer Tail;
	private final ModelRenderer tail1;
	private final ModelRenderer tail1_r1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail2_r1;
	private final ModelRenderer Head;
	private final ModelRenderer headspikes_r1;
	private final ModelRenderer Jaw;
	private final ModelRenderer jaw_r1;
	private final ModelRenderer Right_Arm;
	private final ModelRenderer armr_r1;
	private final ModelRenderer Left_Arm;
	private final ModelRenderer arml_r1;
	private final ModelRenderer Right_Leg;
	private final ModelRenderer legr_r1;
	private final ModelRenderer Left_Leg;
	private final ModelRenderer legl_r1;

	public ModelIguanaIshumix() {
		textureWidth = 64;
		textureHeight = 64;

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, 11.0F, 0.0F);
		

		backspikes_r1 = new ModelRenderer(this);
		backspikes_r1.setRotationPoint(0.0F, 13.0F, 0.0F);
		Torso.addChild(backspikes_r1);
		setRotationAngle(backspikes_r1, -3.1416F, 0.0F, 3.1416F);
		backspikes_r1.cubeList.add(new ModelBox(backspikes_r1, 0, 18, 0.0F, -13.0F, -5.5F, 0, 6, 7, 0.0F, false));
		backspikes_r1.cubeList.add(new ModelBox(backspikes_r1, 0, 13, -3.0F, -13.0F, -1.5F, 6, 9, 3, 0.0F, false));

		Tail = new ModelRenderer(this);
		Tail.setRotationPoint(0.0F, 19.3F, 1.5F);
		

		tail1 = new ModelRenderer(this);
		tail1.setRotationPoint(0.0F, 4.7F, -1.5F);
		Tail.addChild(tail1);
		

		tail1_r1 = new ModelRenderer(this);
		tail1_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail1.addChild(tail1_r1);
		setRotationAngle(tail1_r1, -2.2666F, -0.0051F, 3.1286F);
		tail1_r1.cubeList.add(new ModelBox(tail1_r1, 1, 32, -1.0535F, -4.1525F, -1.6862F, 2, 1, 5, 0.0F, false));

		tail2 = new ModelRenderer(this);
		tail2.setRotationPoint(0.0F, 3.35F, 2.1F);
		Tail.addChild(tail2);
		

		tail2_r1 = new ModelRenderer(this);
		tail2_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail2.addChild(tail2_r1);
		setRotationAngle(tail2_r1, -2.9234F, 0.0F, 3.1416F);
		tail2_r1.cubeList.add(new ModelBox(tail2_r1, 0, 40, -0.5F, -0.5F, -3.95F, 1, 1, 4, 0.0F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 11.0F, 0.0F);
		

		headspikes_r1 = new ModelRenderer(this);
		headspikes_r1.setRotationPoint(0.0F, 13.0F, 0.0F);
		Head.addChild(headspikes_r1);
		setRotationAngle(headspikes_r1, -3.0543F, 0.0F, 3.1416F);
		headspikes_r1.cubeList.add(new ModelBox(headspikes_r1, 18, 13, -3.0F, -17.852F, -3.3593F, 6, 5, 3, 0.0F, false));
		headspikes_r1.cubeList.add(new ModelBox(headspikes_r1, 32, 0, -3.0F, -17.852F, -0.3593F, 6, 5, 2, 0.0F, false));
		headspikes_r1.cubeList.add(new ModelBox(headspikes_r1, 0, 0, -3.0F, -17.852F, 1.6407F, 6, 4, 5, 0.0F, false));

		Jaw = new ModelRenderer(this);
		Jaw.setRotationPoint(0.0F, -1.0F, -0.5F);
		Head.addChild(Jaw);
		

		jaw_r1 = new ModelRenderer(this);
		jaw_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Jaw.addChild(jaw_r1);
		setRotationAngle(jaw_r1, -3.0543F, 0.0F, 3.1416F);
		jaw_r1.cubeList.add(new ModelBox(jaw_r1, 10, 39, -3.0F, 0.0512F, -0.0776F, 6, 1, 5, 0.0F, false));

		Right_Arm = new ModelRenderer(this);
		Right_Arm.setRotationPoint(-3.0F, 12.5F, 0.0F);
		

		armr_r1 = new ModelRenderer(this);
		armr_r1.setRotationPoint(0.0F, -0.75F, 0.0F);
		Right_Arm.addChild(armr_r1);
		setRotationAngle(armr_r1, -3.1416F, 0.0F, -3.0107F);
		armr_r1.cubeList.add(new ModelBox(armr_r1, 18, 22, -0.1F, 0.75F, -1.0F, 1, 6, 2, 0.0F, false));

		Left_Arm = new ModelRenderer(this);
		Left_Arm.setRotationPoint(3.0F, 12.5F, 0.0F);
		

		arml_r1 = new ModelRenderer(this);
		arml_r1.setRotationPoint(0.0F, -0.75F, 0.0F);
		Left_Arm.addChild(arml_r1);
		setRotationAngle(arml_r1, 3.1416F, 0.0F, 3.0107F);
		arml_r1.cubeList.add(new ModelBox(arml_r1, 26, 0, -0.9F, 0.75F, -1.0F, 1, 6, 2, 0.0F, false));

		Right_Leg = new ModelRenderer(this);
		Right_Leg.setRotationPoint(-2.0F, 20.0F, 0.0F);
		

		legr_r1 = new ModelRenderer(this);
		legr_r1.setRotationPoint(2.0F, 4.0F, 0.0F);
		Right_Leg.addChild(legr_r1);
		setRotationAngle(legr_r1, -3.1416F, 0.0F, 3.1416F);
		legr_r1.cubeList.add(new ModelBox(legr_r1, 24, 28, 1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F, false));

		Left_Leg = new ModelRenderer(this);
		Left_Leg.setRotationPoint(2.0F, 20.0F, 0.0F);
		

		legl_r1 = new ModelRenderer(this);
		legl_r1.setRotationPoint(-2.0F, 4.0F, 0.0F);
		Left_Leg.addChild(legl_r1);
		setRotationAngle(legl_r1, -3.1416F, 0.0F, 3.1416F);
		legl_r1.cubeList.add(new ModelBox(legl_r1, 26, 22, -3.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F, false));

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Torso.render(f5);
		Tail.render(f5);
		Head.render(f5);
		Right_Arm.render(f5);
		Left_Arm.render(f5);
		Right_Leg.render(f5);
		Left_Leg.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}


	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		Head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
		Head.rotateAngleX = headPitch / (180F / (float)Math.PI);

		Left_Leg.rotateAngleX = Right_Arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		Right_Leg.rotateAngleX = Left_Arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		Right_Arm.rotateAngleZ = MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		Left_Arm.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		Right_Arm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		Left_Arm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

		Left_Arm.rotateAngleY *= 0.6f;
		Right_Arm.rotateAngleY *= 0.6f;
	}
}