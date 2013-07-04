package com.materna.ImageViewSwipe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageViewSwipeActivity extends Activity implements
		OnGestureListener, OnDoubleTapListener {

	/** Called when the activity is first created. */
	GestureDetector mDetector;
	// private GestureDetector mDetector;
	String path = "/mnt/sdcard/Documents/PresResource/";
	// String path = Environment.getExternalStorageDirectory().toString() +
	// "/Documents/PresResource/";
	// String path = "/data/materna/";
	static Bilder[] minabilder;
	private boolean parent = true;
	int nubild;
	private Intent gridIntent;
	

	// final Lock lock = new ReentrantLock();
	// final Condition notFull = lock.newCondition();

	// BitmapFactory factory = new BitmapFactory();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Up down right left
		
		minabilder = new Bilder[50];
		
		try {

		
			Bundle b = getIntent().getExtras();
			String library = "presentations.txt";
			if (b != null && b.getString("key") != null) {
				path = path + b.getString("key");
				parent = false;
			}

			BufferedReader reader = new BufferedReader(new FileReader(path + library));

			int i = 0;
			String bild = null;
			String line = reader.readLine();
			while (line != null) {

				String[] lineA = line.split(",");
				if (lineA.length < 6)
					break;
				int andra = Integer.parseInt(lineA[1]);
				bild = path + lineA[0] + ".PNG";
				minabilder[i] = new Bilder(bild, andra, lineA[2],
						Integer.parseInt(lineA[3]), Integer.parseInt(lineA[4]),
						Integer.parseInt(lineA[5]), Integer.parseInt(lineA[6]
								.trim()));
				i += 1;
				line = reader.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (parent) {

			gridIntent = new Intent(ImageViewSwipeActivity.this,
					GridViewActivity.class);

			startActivityForResult(gridIntent, 1);

		} else {

			setContentView(R.layout.main);
			final ImageView imageView = (ImageView) findViewById(R.id.imageView1);
			new BitmapFactory();
			imageView.setImageBitmap(BitmapFactory.decodeFile(minabilder[0]
					.getimage()));

			mDetector = new GestureDetector(this, this);

			// mDetector = new GestureDetector(imageView.getContext(),
			// new GestureListener());

			// new GestureDetector(getApplicationContext(), new
			// GestureListener());
			// mDetector = new GestureDetector(imageView.getContext(), this);
			// Set the gesture detector as the double tap
			// listener.
			mDetector.setOnDoubleTapListener(this);

		}

	}

	// private class GestureListener extends
	// GestureDetector.SimpleOnGestureListener {

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;

	@Override
	public boolean onTouchEvent(MotionEvent me) {

		return mDetector.onTouchEvent(me);

	}

	public boolean onDown(MotionEvent arg0) {

		return true;
	}

	@Override
	public void onActivityResult(int reqCode, int resCode, Intent data) {
		super.onActivityResult(reqCode, resCode, data);
		switch (reqCode) {
		case (1): {
			if (resCode == Activity.RESULT_OK) {
				int tmpbild = data.getIntExtra("bild", 0);

				if (parent) {

					Intent intent = new Intent(ImageViewSwipeActivity.this,
							ImageViewSwipeActivity.class);
					Bundle b = new Bundle();
					b.putString("key", minabilder[tmpbild].geturl()); // Your
																		// id
					intent.putExtras(b); // Put your id to your next Intent
					startActivityForResult(intent, 2);

					// finish();

				} else {
					int imageCount = 0;
					int index = 0;

					/**
					 * If first is internet increase index until we
					 */
					while (minabilder[index].gettyp() != 0) {
						index++;
					}
					while (imageCount < tmpbild) {

						index++;
						if (minabilder[index].gettyp() != 0) {

							continue;
						}

						imageCount++;
					}
					nubild = index;

					if (index == 23) {
						System.out.println(minabilder[index].gettyp());
						System.out.println(minabilder[index].getimage());

					}

					final ImageView imageView = (ImageView) findViewById(R.id.imageView1);
					imageView.setImageBitmap(BitmapFactory
							.decodeFile(minabilder[nubild].getimage()));
					Context context = getApplicationContext();
					CharSequence text = "" + nubild;
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}

			}

			break;
		}
		case (2): {
			if (parent) {
				System.out.println(path);
				String library = "presentations.txt";
				
			try {
				Bundle b = getIntent().getExtras();
				if (b != null && b.getString("key") != null) {
					path = path + b.getString("key");
					parent = false;
				}

				BufferedReader reader = new BufferedReader(new FileReader(path + library));

				int i = 0;
				String bild = null;
				minabilder = new Bilder[50];
				String line = reader.readLine();
				while (line != null) {

					String[] lineA = line.split(",");
					if (lineA.length < 6)
						break;
					int andra = Integer.parseInt(lineA[1]);
					bild = path + lineA[0] + ".PNG";
					minabilder[i] = new Bilder(bild, andra, lineA[2],
							Integer.parseInt(lineA[3]), Integer.parseInt(lineA[4]),
							Integer.parseInt(lineA[5]), Integer.parseInt(lineA[6]
									.trim()));
					i += 1;
					line = reader.readLine();
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
				gridIntent = new Intent(ImageViewSwipeActivity.this,
						GridViewActivity.class);

				startActivityForResult(gridIntent, 1);

			//	startActivityForResult(gridIntent, 1);

			}

		}
		}
	}

	public boolean onDoubleTap(MotionEvent arg0) {
		// Context context = getApplicationContext();
		// CharSequence text = "Hello toast!";
		// int duration = Toast.LENGTH_SHORT;
		//
		// Toast toast = Toast.makeText(context, text, duration);
		// toast.show();
		// Toast.makeText(getApplicationContext(), "Doubletap",
		// Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(ImageViewSwipeActivity.this,
				GridViewActivity.class);

		startActivityForResult(intent, 1);

		return true;

	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		// Context context = getApplicationContext();
		// CharSequence text = "Hello toast!";
		// int duration = Toast.LENGTH_SHORT;
		//
		// Toast toast = Toast.makeText(context, text, duration);
		// toast.show();

		int nextid;
		final ImageView imageView = (ImageView) findViewById(R.id.imageView1);

		if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY
				&& e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
			// Diagonal up left
			nextid = 0;
			switch (minabilder[nextid].gettyp()) {
			case 0:
				if (nubild != nextid) {
					imageView.setImageBitmap(BitmapFactory
							.decodeFile(minabilder[nextid].getimage()));
				}
				break;
			case 1:
				Uri uriUrl = Uri.parse(minabilder[nextid].geturl());
				Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launchBrowser);
				// Toast.makeText(getApplicationContext(),
				// minabilder[nextid].geturl(), Toast.LENGTH_SHORT).show();
				break;

			}
			nubild = nextid;
			return false;
		}

		if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			nextid = minabilder[nubild].getright();

			// Toast.makeText(getApplicationContext(),
			// "right"+Integer.toString(nextid), Toast.LENGTH_SHORT).show();
			switch (minabilder[nextid].gettyp()) {
			case 0:
				if (nubild != nextid) {
					new BitmapFactory();
					imageView.setImageBitmap(BitmapFactory
							.decodeFile(minabilder[nextid].getimage()));
				}
				break;
			case 1:
				Uri uriUrl = Uri.parse(minabilder[nextid].geturl());
				Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launchBrowser);
				// Toast.makeText(getApplicationContext(),
				// minabilder[nextid].geturl(), Toast.LENGTH_SHORT).show();
				break;

			}
			nubild = nextid;
			return false; // Right to left
		} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			nextid = minabilder[nubild].getleft();
			// Toast.makeText(getApplicationContext(),
			// "left"+Integer.toString(nextid), Toast.LENGTH_SHORT).show();
			switch (minabilder[nextid].gettyp()) {
			case 0:
				if (nubild != nextid) {
					imageView.setImageBitmap(BitmapFactory
							.decodeFile(minabilder[nextid].getimage()));
				}
				break;
			case 1:
				Uri uriUrl = Uri.parse(minabilder[nextid].geturl());
				Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launchBrowser);
				// Toast.makeText(getApplicationContext(),
				// minabilder[nextid].geturl(), Toast.LENGTH_SHORT).show();
				break;

			}
			nubild = nextid;
			return false; // Left to right
		}

		if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
			nextid = minabilder[nubild].getdown();
			// Toast.makeText(getApplicationContext(),
			// "down"+Integer.toString(nextid), Toast.LENGTH_SHORT).show();
			switch (minabilder[nextid].gettyp()) {
			case 0:
				if (nubild != nextid) {
					imageView.setImageBitmap(BitmapFactory
							.decodeFile(minabilder[nextid].getimage()));
				}
				break;
			case 1:
				Uri uriUrl = Uri.parse(minabilder[nextid].geturl());
				Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launchBrowser);
				// Toast.makeText(getApplicationContext(),
				// minabilder[nextid].geturl(), Toast.LENGTH_SHORT).show();
				break;

			}
			nubild = nextid;
			return false; // Bottom to top
		} else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
			nextid = minabilder[nubild].getup();
			// Toast.makeText(getApplicationContext(),
			// "up"+Integer.toString(nextid), Toast.LENGTH_SHORT).show();
			switch (minabilder[nextid].gettyp()) {
			case 0:
				if (nubild != nextid) {
					imageView.setImageBitmap(BitmapFactory
							.decodeFile(minabilder[nextid].getimage()));
				}
				break;
			case 1:
				Uri uriUrl = Uri.parse(minabilder[nextid].geturl());
				Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launchBrowser);
				// Toast.makeText(getApplicationContext(),
				// minabilder[nextid].geturl(), Toast.LENGTH_SHORT).show();
				break;

			}
			nubild = nextid;
			return false; // Top to bottom
		}
		return false;
	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	// }

	// }

}
