// The interpretation of the "test.db" file is as the following
// For simplicity, we suggest using a "db" file for a single shot.
// If there are multiple shots to process, generate *.db files in 
// batch.
 

.\test	// storing corresponding frames within shots, here shot "1009" only
100000	// next_shotid 
1 	// number of shots
	// one blank row is necessary here. This cannot be omitted.
1009 	// shot id (same as folder name under the path designated on the first line)
-UNKNOWN- // video file name (for mpeg2dec. This is not used in MOT)
234300	// start time (not used in MOT)
50	// duration (not used in MOT)
40	// start frame number
30	// number of frames
352	// width of the frame
240	// height of the frame
2	// (not used in MOT)
6002138 // (not used in MOT)

