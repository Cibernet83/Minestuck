package com.mraof.minestuck.client.model;// Made with Blockbench 4.7.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelNakagatorIshumix extends ModelBase {
	private final ModelRenderer Torso;
	private final ModelRenderer body_r1;
	private final ModelRenderer Tail;
	private final ModelRenderer tail1;
	private final ModelRenderer tail1_r1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail2_r1;
	private final ModelRenderer Head;
	private final ModelRenderer mouthspike2_r1;
	private final ModelRenderer head_r1;
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

	public ModelNakagatorIshumix() {
		textureWidth = 64;
		textureHeight = 64;

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, 11.0F, 0.0F);
		

		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(0.0F, 13.0F, 0.0F);
		Torso.addChild(body_r1);
		setRotationAngle(body_r1, -3.1416F, 0.0F, 3.1416F);
		body_r1.cubeList.add(new ModelBox(body_r1, 0, 10, -3.0F, -13.0F, -1.5F, 6, 9, 3, 0.0F, false));

		Tail = new ModelRenderer(this);
		Tail.setRotationPoint(0.0F, 20.0F, 1.0F);
		

		tail1 = new ModelRenderer(this);
		tail1.setRotationPoint(0.0F, 4.0F, -1.0F);
		Tail.addChild(tail1);
		

		tail1_r1 = new ModelRenderer(this);
		tail1_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail1.addChild(tail1_r1);
		setRotationAngle(tail1_r1, -2.2666F, -0.0051F, 3.1286F);
		tail1_r1.cubeList.add(new ModelBox(tail1_r1, 13, 21, -1.0535F, -4.1525F, -1.6862F, 2, 1, 5, 0.0F, false));

		tail2 = new ModelRenderer(this);
		tail2.setRotationPoint(0.0F, 2.5F, 2.55F);
		Tail.addChild(tail2);
		

		tail2_r1 = new ModelRenderer(this);
		tail2_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail2.addChild(tail2_r1);
		setRotationAngle(tail2_r1, -2.8798F, 0.0F, 3.1416F);
		tail2_r1.cubeList.add(new ModelBox(tail2_r1, 17, 0, -0.5F, -0.5F, -3.85F, 1, 1, 4, 0.0F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 10.0F, -1.5F);
		

		mouthspike2_r1 = new ModelRenderer(this);
		mouthspike2_r1.setRotationPoint(0.0F, 1.0F, 1.5F);
		Head.addChild(mouthspike2_r1);
		setRotationAngle(mouthspike2_r1, 3.1416F, 0.0F, 3.1416F);
		mouthspike2_r1.cubeList.add(new ModelBox(mouthspike2_r1, 22, 20, 2.01F, -1.0F, 3.5077F, 0, 1, 1, 0.0F, true));
		mouthspike2_r1.cubeList.add(new ModelBox(mouthspike2_r1, 22, 20, -2.01F, -1.0F, 3.5077F, 0, 1, 1, 0.0F, false));
		mouthspike2_r1.cubeList.add(new ModelBox(mouthspike2_r1, 10, 22, -2.0F, -3.9514F, 8.5077F, 4, 4, 0, 0.0F, false));
		mouthspike2_r1.cubeList.add(new ModelBox(mouthspike2_r1, 18, 13, -3.0F, -4.9014F, -3.4923F, 6, 5, 2, 0.0F, false));
		mouthspike2_r1.cubeList.add(new ModelBox(mouthspike2_r1, 17, 5, -2.0F, -2.9514F, 3.5077F, 4, 2, 5, 0.0F, false));

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, 1.0F, 0.0F);
		Head.addChild(head_r1);
		setRotationAngle(head_r1, 3.1416F, 0.0F, 3.1416F);
		head_r1.cubeList.add(new ModelBox(head_r1, 0, 0, -3.0F, -4.9014F, -2.9923F, 6, 5, 5, 0.0F, false));

		Jaw = new ModelRenderer(this);
		Jaw.setRotationPoint(0.0F, 1.0F, 1.5F);
		Head.addChild(Jaw);
		setRotationAngle(Jaw, 0.0F, 0.0F, -0.0524F);
		

		jaw_r1 = new ModelRenderer(this);
		jaw_r1.setRotationPoint(0.0F, 0.0F, -1.5F);
		Jaw.addChild(jaw_r1);
		setRotationAngle(jaw_r1, 3.1416F, 0.0F, 3.1416F);
		jaw_r1.cubeList.add(new ModelBox(jaw_r1, 28, 13, -2.0F, -0.8514F, -0.2423F, 4, 1, 7, 0.0F, false));

		Right_Arm = new ModelRenderer(this);
		Right_Arm.setRotationPoint(-3.0F, 12.5F, 0.0F);
		

		armr_r1 = new ModelRenderer(this);
		armr_r1.setRotationPoint(-0.0313F, 0.0013F, -0.0156F);
		Right_Arm.addChild(armr_r1);
		setRotationAngle(armr_r1, -3.1416F, 0.0F, -3.0107F);
		armr_r1.cubeList.add(new ModelBox(armr_r1, 0, 22, -0.0436F, -0.0019F, -1.0F, 1, 6, 2, 0.0F, false));

		Left_Arm = new ModelRenderer(this);
		Left_Arm.setRotationPoint(3.0F, 12.5F, 0.0F);
		

		arml_r1 = new ModelRenderer(this);
		arml_r1.setRotationPoint(0.0366F, 0.0017F, 0.0182F);
		Left_Arm.addChild(arml_r1);
		setRotationAngle(arml_r1, 3.1416F, 0.0F, 3.0107F);
		arml_r1.cubeList.add(new ModelBox(arml_r1, 26, 25, -0.9564F, -0.0019F, -1.0F, 1, 6, 2, 0.0F, false));

		Right_Leg = new ModelRenderer(this);
		Right_Leg.setRotationPoint(-2.0F, 20.0F, 0.0F);
		

		legr_r1 = new ModelRenderer(this);
		legr_r1.setRotationPoint(2.0F, 4.0F, 0.0F);
		Right_Leg.addChild(legr_r1);
		setRotationAngle(legr_r1, -3.1416F, 0.0F, 3.1416F);
		legr_r1.cubeList.add(new ModelBox(legr_r1, 16, 27, 1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F, false));

		Left_Leg = new ModelRenderer(this);
		Left_Leg.setRotationPoint(2.0F, 20.0F, 0.0F);
		

		legl_r1 = new ModelRenderer(this);
		legl_r1.setRotationPoint(-2.0F, 4.0F, 0.0F);
		Left_Leg.addChild(legl_r1);
		setRotationAngle(legl_r1, -3.1416F, 0.0F, 3.1416F);
		legl_r1.cubeList.add(new ModelBox(legl_r1, 8, 27, -3.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F, false));
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