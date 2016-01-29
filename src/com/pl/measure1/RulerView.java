package com.pl.measure1;

import android.view.*;
import android.annotation.SuppressLint;
import android.content.*;
import android.graphics.*;

public class RulerView extends View
{
	private Paint rulerPaint = new Paint();
	private Paint textPaint;
	private float xDpi;
	private int numPixel;
	
	// constant value
	private static final int Y_OFFSET = 100;
	private static final int TEXT_SIZE_DEFAULT = 30;
	private static final int LINE_HEIGHT_DEFAULT = 10;
	private static final int UNIT_PER_INCH = 16;
	private static final int Y_DISTANCE = 20;
	
	public float getxDpi() {
		return xDpi;
	}
	
	public void setxDpi(float value) {
		xDpi = value;
		this.invalidate();
	}
	
	public int getPixelNum() {
		return numPixel;
	}
	
	public void setPixelNum(int value) {
		numPixel = value;
	}
	
	public RulerView(Context context){
		super(context);
		
		rulerPaint.setColor(Color.WHITE);
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas){
		// Draw a line
		canvas.drawLine(0, Y_OFFSET, numPixel, Y_OFFSET, rulerPaint);
		
		int numUnits = (int) (((float)numPixel / xDpi) * UNIT_PER_INCH);
		
		for (int n = 0; n <= numUnits; n++) {
			float x = 10 + (float)n/16 * xDpi;
			float lineH = LINE_HEIGHT_DEFAULT;
			int textSize = TEXT_SIZE_DEFAULT;
			if (n % 2 == 0) {
				lineH += LINE_HEIGHT_DEFAULT;
				textSize += TEXT_SIZE_DEFAULT;
			}
			if (n % 4 == 0) {
				lineH += LINE_HEIGHT_DEFAULT;
				textSize += TEXT_SIZE_DEFAULT;
			}
			if (n % 8 == 0) {
				lineH += LINE_HEIGHT_DEFAULT;
				textSize += TEXT_SIZE_DEFAULT;
			}
			if (n % 16 == 0) {
				lineH += LINE_HEIGHT_DEFAULT;
				textSize += TEXT_SIZE_DEFAULT;
			}

			canvas.drawLine(x, Y_OFFSET, x, Y_OFFSET + lineH, rulerPaint);
			

			textPaint = new Paint(textSize);
			textPaint.setColor(Color.WHITE);
			textPaint.setUnderlineText(false);
			textPaint.setStrikeThruText(false);
			
			if (n % 16 == 0) {
				canvas.drawText((n / 16) + "", x, Y_OFFSET + Y_DISTANCE + lineH, textPaint); 
			}else if (n % 8 == 0) {
				canvas.drawText(((n / 8) % 2) + "/" + 2, x - TEXT_SIZE_DEFAULT / 2, Y_OFFSET + Y_DISTANCE + lineH, textPaint); 
			} else if (n % 4 == 0) {
	        	canvas.drawText(((n / 4) % 4) + "/" + 4, x - TEXT_SIZE_DEFAULT / 2, Y_OFFSET + Y_DISTANCE + lineH, textPaint); 
			} else if (n % 2 == 0) {
	        	canvas.drawText(((n / 2) % 8) + "/" + 8, x - TEXT_SIZE_DEFAULT / 2, Y_OFFSET + Y_DISTANCE + lineH, textPaint); 
			} else {
	        	canvas.drawText((n % 16) + "/" + 16, x - TEXT_SIZE_DEFAULT / 2, Y_OFFSET - Y_DISTANCE, textPaint); 
			}
		}
	}
}
