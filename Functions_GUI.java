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


/*This class represents a collection of mathematical functions
*  which can be presented on a GUI window and can be saved (and load) to file.*/
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
	
	public Object get(int i) {
        return functions.get(i);
	}
	/**
	 * Init a new collection of functions from a file
	 * @param file - the file name
	 * @throws IOException if the file does not exists of unreadable (wrong format)
	 */
    @Override
	public void initFromFile(String file) throws IOException {
		try 
	        {
	          BufferedReader br = new BufferedReader(new FileReader(file));
	        	String function = "";
	        	function cf= new ComplexFunction(Monom.ZERO);
	            while ((function = br.readLine()) != null) 
	            {
	                functions.add(cf.initFromString(function));
	            }
              br.close();   
	        } 
	        catch (IOException e) 
	        {
	        	throw new RuntimeException("ERR:could not read "+ file);
	        }
		
	}
    /**
     * The method received name and write all functions in the collection  in file with the same name.
     * if the file not exist the method create the file. 
     * @param file - the file name
     * @throws IOException if the file is not writable
     */
	@Override
	public void saveToFile(String file) throws IOException {

		try 
		{
			PrintWriter pw = new PrintWriter(file);
			pw.write(this.toString());
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			throw new RuntimeException("ERR:not found "+ file);
		}
		
	}
	/**
	 * Draws all the functions in the collection in a GUI window using the
	 * given parameters for the GUI window and the range & resolution
	 * @param width - the width of the window - in pixels
	 * @param height - the height of the window - in pixels
	 * @param rx - the range of the horizontal axis
	 * @param ry - the range of the vertical axis
	 * @param resolution - the number of samples with in rx: the X_step = rx/resulution
	 */
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		if(width<=0 || height<=0 || rx==null || ry==null || resolution<=0 )
			throw new RuntimeException("ERR:One or more of the values is unacceptable ");
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
			StdDraw.text(i, -0.5, ""+i);
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
	/**
	 * Draws all the functions in the collection in a GUI window using the given JSON file
	 * @param json_file - the file with all the parameters for the GUI window. 
	 * Note: if the file id not readable or in wrong format use default values. 
	 */
	@Override
	public void drawFunctions(String json_file) {
		InputStream fis;
		try {
			fis = new FileInputStream(json_file);
			JsonReader jsonReader = Json.createReader(fis);	
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();
			fis.close();
			JsonArray jsonArrayx = jsonObject.getJsonArray("Range_X");
			double[] x = new double[jsonArrayx.size()];
			int index = 0;
			for(JsonValue value : jsonArrayx){
				x[index++] = Double.parseDouble(value.toString());
			}
			Range rx = new Range(x[0],x[1]);
			JsonArray jsonArrayy = jsonObject.getJsonArray("Range_Y");
			double[] y = new double[jsonArrayy.size()];
			int indexy = 0;
			for(JsonValue value : jsonArrayy){
				y[indexy++] = Double.parseDouble(value.toString());
			}
			Range ry = new Range(y[0],y[1]);
			drawFunctions(jsonObject.getInt("Width"), jsonObject.getInt("Height"), rx, ry,jsonObject.getInt("Resolution"));
		}
		catch (Exception e) 
		{
			int w=1000, h=600, res=203;
			Range rx = new Range(-10,10);
			Range ry = new Range(-5,15);
			drawFunctions(w,h,rx,ry,res);
		}		
		
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<functions.size(); i++) {
		    sb.append(functions.get(i)+ "\n");
		}
		return  sb.toString();
	}

	
}
