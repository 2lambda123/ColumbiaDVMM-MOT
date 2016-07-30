// In this example, we demonstrate how to extract region information from
// *.oif files. Each region is defined by a bounding box and a mask. Eeach 
// frame might contain multiple regions.
// 
// We try to extract the region information from the oif file of the first video
// frame, "000", from the shot "0019"
//
// Digital Video and Multimedia Group, Columbia University
// http://www.ee.columbia.edu/dvmm/
// Date: 07/31/2003

package sample;
			
import java.lang.* ;
import java.util.* ;
import java.io.* ;
import java.awt.* ;

import feature.RegionInfo ;
import image.*;

public class SampleRegionInfo {
	public static void main(String[] args) throws IOException {
		String fnum = "000"; // frame number (three digits), in this example, we process the first frame only
		
		if( args.length != 2 ){
			System.err.println( "Usage: java sample.SampleRegionInfo {directory name} {shot id}" );
			System.exit( 1 );
		}
		
		//region info file (OIF file)
		int sid = Integer.valueOf( args[ 1 ] ).intValue(); //shot id
						 
		String dirName = args[ 0 ] + File.separator + sid; //directory name for this shot
		String regInfoName = dirName + File.separator + "odet" + File.separator + sid + "_" + fnum + "_obj.oif";
		
		//read region information from the file designated by regInfoName
		RegionInfo[] cur_rlist=RegionInfo.read( regInfoName ); 
		
		//extract region information 
		// cur_rlist.lenth: the number of regions in the OIF
		for( int k = 0; k < cur_rlist.length; k++ )
			extractRegInfo( cur_rlist[ k ] );
		
		String regInfoName2 = "test.oif";
		
		//write region information to the file designated by regInfoName2
		RegionInfo.write( regInfoName2, cur_rlist, 10, 10, 10, new float[ 10 ] );
		
		System.exit( 0 );
	}
	
	private static void extractRegInfo( RegionInfo cr )
	{
		int id = cr.id; // region id
		int x0 = cr.ul.x, y0 = cr.ul.y; // Coordinate of the upper-left corner of boundary box
		int x1 = cr.lr.x, y1 = cr.lr.y; // Coordinate of the lower-right corner of boundary box
		int numPels = cr.pixelnum; // The number of the pixels within the region
		boolean[][] mask = cr.mask; // mask information of the region
		float[] meanColor = cr.feat1; // mean Luv color
		float[] mp = cr.feat2; // motion parameters;
		/*
		Motion is compensated by the following equation:
      x = mp[0] + mp[1] * x0 + mp[2] * y0 + mp[6] * x0 * x0 + mp[7] * x0 * y0 + x0,
      y = mp[3] + mp[4] * x0 + mp[5] * y0 + mp[6] * x0 * y0 + mp[7] * y0 * y0 + y0
    where (x0,y0) is a point in the current region and (x,y) is its corresponding point in the next frame. 
		*/
		int[] regIDs = cr.getConnectList(); //adjoining region IDs
		int perimeter = cr.perimeter(); // perimeter of this region

		// display region information 
		System.out.println( "Region ID: " + id );
		System.out.println( "Number of pixels: " + numPels );
		System.out.println( "Perimeter: " + perimeter );
		System.out.println( "Upper-left corner of boundary box: (" + x0 + "," + y0 + ")" );
		System.out.println( "Lower-right corner of boundary box: ("+ x1 + "," + y1 + ")" );
		System.out.print( "Adjoint region IDs: " );
		for( int i = 0; i < regIDs.length; i++ ){
			if( 0 != i )
				System.out.print( "," );
			System.out.print( regIDs[ i ] );
		}
		System.out.println( "" );
		System.out.println( "Mean color (L,u,v): (" + meanColor[ 0 ] + "," + meanColor[ 1 ] + "," + meanColor[ 2 ] + ")" );
		
		System.out.print( "Motion parameters: (" );
		for( int i = 0; i < 8; i++ ){
			if( 0 != i )
				System.out.print( "," );
			System.out.print( mp[ i ] );
		}
		System.out.println( ")" );
		System.out.print( "Color of this region " );
		cr.printRandomColor();
			
		System.out.println( "" );
	}
}
