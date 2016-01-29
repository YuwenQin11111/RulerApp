package com.pl.measure1;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;

public class MainActivity extends Activity
{
	private RulerView ruler;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		LinearLayout llRuler = (LinearLayout) findViewById(R.id.llRuler);
		if (llRuler != null){
			ruler = new RulerView(this);
			llRuler.addView(ruler);
		}
		
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		
		try {		
			DisplayMetrics dm = new DisplayMetrics();
			disp.getMetrics(dm);
			float xD = dm.xdpi;
			float yD = dm.ydpi;
			int xPixel = dm.widthPixels;
			int yPixel = dm.heightPixels;
			
			ruler.setxDpi(xD);
			ruler.setPixelNum(xPixel);
		} catch (Exception ex){
			Toast.makeText(this, "Exception: " + ex.getMessage(), Toast.LENGTH_LONG).show();
		}	
        
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  // ignore orientation/keyboard change
	  super.onConfigurationChanged(newConfig);
	}
}
