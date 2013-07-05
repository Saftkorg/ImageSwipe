package com.materna.ImageViewSwipe;

class Bilder {
	private String imageid;
	private String thumb;
	private int typ;
	private String url;
	private int nextup;
	private int nextdown;
	private int nextright;
	private int nextleft;
	
	public Bilder (String thumb,String imageid, int bildtyp, String urlarg,int up, int down, int right, int left){
		this.thumb = thumb;
		this.imageid=imageid;
		typ=bildtyp;
		url=urlarg;
		nextup=up;
		nextdown=down;
		nextright=right;
		nextleft=left;
	}
	
	public String getThumb(){
		return thumb;
	}
	/**
	 * 
	 * @return The imageid is the path to this image
	 */
	public String getimage(){
		return imageid;
	}
	
	public int gettyp(){
		return typ;
	}
	
	public String geturl(){
		return url;
	}
	
	public int getup(){
		return nextup;
	}
	public int getdown(){
		return nextdown;
	}
	public int getright(){
		return nextright;
	}
	public int getleft(){
		return nextleft;
	}

}
