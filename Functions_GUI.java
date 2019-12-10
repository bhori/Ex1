package Ex1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.json.*;



public class Functions_GUI implements functions  {
	private ArrayList<function> functions;
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
	public Functions_GUI() {
	   functions= new ArrayList<function>();
	}

	@Override 
	public boolean add(function arg0) {
		return functions.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return functions.addAll(arg0);
	}

	@Override
	public void clear() {
		functions.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {
		return functions.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return functions.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return functions.isEmpty() ;
	}

	@Override
	public Iterator<function> iterator() {
		return functions.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return functions.remove(arg0) ;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return functions.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return  functions.retainAll(arg0);
	}

	@Override
	public int size() {
		return  functions.size();
	}

	@Override
	public Object[] toArray() {
		return  functions.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return functions.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException {
		try 
	        {
	          BufferedReader br = new BufferedReader(new FileReader(file));
	          while(br.ready()) {
	        	String function = "";
	        	
	            while ((function = br.readLine()) != null) 
	            {
	            	ComplexFunction cf = new ComplexFunction(new Monom("x^2"));
	                functions.add(cf.initFromString(function));
	            }
	          }

	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            System.out.println("could not read file");
	        }
		
	}

	@Override
	public void saveToFile(String file) throws IOException {

		try 
		{
			File f=new File(file);
			PrintWriter pw = new PrintWriter(file);
			pw.write(this.toString());
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		int n = resolution;
		StdDraw.setCanvasSize(width, height);
		int size = this.size();
		double[] x = new double[n+1];
		double[][] yy = new double[size][n+1];
		double x_step = (rx.get_max()-rx.get_min())/n;
		double x0 = rx.get_min();
		for (int i=0; i<=n; i++) {
			x[i] = x0;
			for(int a=0;a<size;a++) {
				yy[a][i] = functions.get(a).f(x[i]);
			}
			x0+=x_step;
		}
		
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		for(int i=(int)rx.get_min();i<=rx.get_max();i++) {
			StdDraw.setPenRadius(0.001);
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
			StdDraw.setPenRadius(0.004);
			StdDraw.text(i, -1, ""+i);
		}
		for(int i=(int)ry.get_min();i<=ry.get_max();i++) {
			StdDraw.setPenRadius(0.001);
			StdDraw.line(rx.get_min(),i , rx.get_max(),i );
			StdDraw.setPenRadius(0.004);
			StdDraw.text(0.2, i, ""+i);
		}
		StdDraw.setPenRadius(0.004);
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		// plot the approximation to the function
		for(int a=0;a<size;a++) {
			int c = a%Colors.length;
			StdDraw.setPenColor(Colors[c]);
			StdDraw.setPenRadius(0.008);
		    System.out.println(a+") "+Colors[a]+"  f(x)= "+functions.get(a));
			for (int i = 0; i < n; i++) {
				StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
			}
		}
		
	}

	@Override
	public void drawFunctions(String json_file) {
		InputStream fis;
		try {
			fis = new FileInputStream(json_file);
			//create JsonReader object
			JsonReader jsonReader = Json.createReader(fis);	
			//get JsonObject from JsonReader
			JsonObject jsonObject = jsonReader.readObject();
			//we can close IO resource and JsonReader now
			jsonReader.close();
			fis.close();
			//JsonObject jrx = jsonObject.getJsonObject("Range_X");
			JsonArray jsonArrayx = jsonObject.getJsonArray("Range_X");
			double[] x = new double[jsonArrayx.size()];
			int index = 0;
			for(JsonValue value : jsonArrayx){
				x[index++] = Double.parseDouble(value.toString());
			}
			//jrx.getJsonNumber("min").doubleValue();
			Range rx = new Range(x[0],x[1]);
			//JsonObject jry = jsonObject.getJsonObject("Range_Y");
			JsonArray jsonArrayy = jsonObject.getJsonArray("Range_Y");
			double[] y = new double[jsonArrayy.size()];
			int indexy = 0;
			for(JsonValue value : jsonArrayy){
				y[indexy++] = Double.parseDouble(value.toString());
			}
			//jry.getJsonNumber("min").doubleValue();
			Range ry = new Range(y[0],y[1]);
			drawFunctions(jsonObject.getInt("Width"), jsonObject.getInt("Height"), rx, ry,jsonObject.getInt("Resolution"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<functions.size(); i++) {
		    sb.append(functions.get(i)+ "\n");
		}
		return  sb.toString();
	}

	public Object get(int i) {
        return functions.get(i);
	}

}

//
///**
// * This class demonstrate a simple set of sin funxtions and draw them in a GUI window.
// * A trivial example of inner class is also presented.
// */
//class Sinus_Main {
//	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
//			Color.red, Color.GREEN, Color.PINK};
//	public static void main(String[] args) {
//		ArrayList<function> ff = new ArrayList<function>();
//		ff.addAll(init());
//		drawFunctions(ff);
//	}
//
//	public static ArrayList<function> init() {
//		ArrayList<function> ans = new ArrayList<function>();
//		Sinus s0 = new Sinus();
//		Monom m1 = new Monom("4x");
//		Monom m2 = new Monom("0.3x^3");
//		Sinus s1 = new Sinus(m1);
//		Sinus s2 = new Sinus(m2);
//		ans.add(s0); ans.add(s1); ans.add(s2);
//		return ans;
//	}
//	public static void drawFunctions(ArrayList<function> ff) {drawFunctions(ff, GUI_Params.DEFAULT_GUI_PARAMS);}
//	public static void drawFunctions(ArrayList<function> ff, GUI_Params gp) {
//		drawFunctions(ff, gp.get_width(),gp.get_height(),gp.get_rx(), gp.get_ry(),gp.get_resolution());
//	}
//	public static void drawFunctions(ArrayList<function> ff, int width, int height, Range rx, Range ry, int res) {
//		int n = res;
//		StdDraw.setCanvasSize(width, height);
//		int size = ff.size();
//		double[] x = new double[n+1];
//		double[][] yy = new double[size][n+1];
//		double x_step = (rx.get_max()-rx.get_min())/n;
//		double x0 = rx.get_min();
//		for (int i=0; i<=n; i++) {
//			x[i] = x0;
//			for(int a=0;a<size;a++) {
//				yy[a][i] = ff.get(a).f(x[i]);
//			}
//			x0+=x_step;
//		}
//		StdDraw.setXscale(rx.get_min(), rx.get_max());
//		StdDraw.setYscale(ry.get_min(), ry.get_max());
//		
//		
//		// plot the approximation to the function
//		for(int a=0;a<size;a++) {
//			int c = a%Colors.length;
//			StdDraw.setPenColor(Colors[c]);
//		
//			System.out.println(a+") "+Colors[a]+"  f(x)= "+ff.get(a));
//			for (int i = 0; i < n; i++) {
//				StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
//			}
//		}	
//	}
//
//	/**
//	 * Simple example for inner class.
//	 */
//	static class GUI_Params {
//		public static final int W=800, H=600, RES=100;
//		public static final Range RX=new Range(0,10);
//		public static final Range RY=new Range(-1.2,1.5);
//		public static GUI_Params  DEFAULT_GUI_PARAMS = new GUI_Params(W,H,RX,RY,RES);
//		private int _width;
//		private int _height;
//		private Range _rx;
//		private Range _ry;
//		private int _resolution;
//		public GUI_Params(int w, int h, Range rx, Range ry, int res) {
//			this._width = w;
//			this._height = h;
//			this._rx = rx;
//			this._ry = ry;
//			this._resolution = res;
//		}
//		public int get_width() {return this._width;}
//		public int get_height() {return this._height;}
//		public int get_resolution() {return this._resolution;}
//		public Range get_rx() {return this._rx;}
//		public Range get_ry() {return this._ry;}
//	}
//}
