package com.mraof.minestuck.client.gui;

import com.mraof.minestuck.inventory.ContainerUraniumCooker;
import com.mraof.minestuck.tileentity.TileEntityUraniumCooker;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiUraniumCooker extends GuiMachine
{
	private static final ResourceLocation BACKGROUND = new ResourceLocation("minestuck:textures/gui/uranium_cooker.png");
	private static final ResourceLocation PROGRESS = new ResourceLocation("minestuck:textures/gui/progress/uranium_cooker.png");
	
	protected final TileEntityUraniumCooker te;
	protected final InventoryPlayer playerInventory;
	
	private int progressX;
	private int progressY;
	private int progressWidth;
	private int progressHeight;
	private int goX;
	private int goY;

	public GuiUraniumCooker (InventoryPlayer inventoryPlayer, TileEntityUraniumCooker tileEntity)
	{
		super(new ContainerUraniumCooker(inventoryPlayer, tileEntity), tileEntity);
		this.te = tileEntity;
		this.playerInventory = inventoryPlayer;
		
		//sets progress bar information
		progressX = 67;
		progressY = 24;
		progressWidth = 35;
		progressHeight = 39;
		goX = 69;
		goY = 69;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		//draws "Cookalyzer"
		fontRenderer.drawString(this.te.getDisplayName().getFormattedText(), 8, 6, 4210752);
		
		//draws "Inventory" or your regional equivalent
		fontRenderer.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8, ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		//draw background
		this.mc.getTextureManager().bindTexture(BACKGROUND);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		//draw progress bar
		this.mc.getTextureManager().bindTexture(PROGRESS);
		int width = progressWidth;
		int height = getScaledValue(te.getFuel(),te.getMaxFuel(),progressHeight);
		drawModalRectWithCustomSizedTexture(x+progressX, y+progressY+progressHeight-height, 0, progressHeight-height, width, height, progressWidth, progressHeight);
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		goButton = new GoButton(1, (width - xSize) / 2 + goX, (height - ySize) / 2 + goY, 30, 12, te.overrideStop ? "STOP" : "GO");
		addButton(goButton);
	}
}