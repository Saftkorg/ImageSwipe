package com.materna.ImageViewSwipe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GridViewActivity extends Activity  {
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		
		
		
		setContentView(R.layout.grid);
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
		
		gridview.setAdapter(new ImageAdapter(this));
		
		gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, android.view.View v, int position, long id) {
	            //Toast.makeText(GridViewActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	            
	            Intent resultIntent = new Intent();
	            
	            resultIntent.putExtra("bild", position);
	            setResult(Activity.RESULT_OK, resultIntent);
	            finish();
	            
	        }
	    });
	}
	
}
