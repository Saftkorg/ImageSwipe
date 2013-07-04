package com.materna.ImageViewSwipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Vector<ImageView> mySDCardImages;
	private Integer[] mThumbIds;
	//private BitmapFactory bf;
    
    public ImageAdapter(Context c) {
        mContext = c;
        //bf =  new BitmapFactory();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize= 4;
		Bilder[] minabilder = ImageViewSwipeActivity.minabilder;
		List<Integer> drawablesId = new ArrayList<Integer>();
		mySDCardImages = new Vector<ImageView>();
		int picIndex = 1234;
		for(Bilder i : minabilder){
			if(i != null && 0==i.gettyp()){// 0 image, 1 url
			
					   ImageView myImageView = new ImageView(mContext);
					   
					   myImageView.setImageBitmap(BitmapFactory.decodeFile(i.getimage(), options)) ;
					   //setImageDrawable(resize(i.getimage()));
					   myImageView.setId(picIndex);
					   drawablesId.add(picIndex);
					   picIndex++;
					   
					   mySDCardImages.add(myImageView);
					}
					
		}
		Log.w("ImageAdapter", "finished mThumbsID");
		mThumbIds = (Integer[])drawablesId.toArray(new Integer[drawablesId.size()]);
		
    }

   
//	private Drawable resize(String getimage) {
//		// TODO Auto-generated method stub
//    	BitmapDrawable d = (BitmapDrawable) BitmapDrawable.createFromPath(getimage);
//    	Bitmap e = d.getBitmap();
//    	Bitmap bitmapOrig = Bitmap.createScaledBitmap(e, 151, 85, false);
//    	return new BitmapDrawable(bitmapOrig);
//    	
//	}

	public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageDrawable(mySDCardImages.get(position).getDrawable());
        return imageView;
    }

}
