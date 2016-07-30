MOT - Moving Object Segmentation and Tracking System
___
####Terms of Use

Copyright (c) 2003 by DVMM Laboratory

Department of Electrical Engineering</br>
Columbia University</br>
Rm 1312 S.W. Mudd, 500 West 120th Street</br>
New York, NY 10027</br>
USA


Di Zhong and Shih-Fu Chang, Columbia University
{dzhong,sfchang}@ee.columbia.edu

####1. SYSTEM OVERVIEW


MOT is a moving object segmentation and tracking system for salient objects
over long period in general video sources. The system utilizes innovative
methods of combining color and edge information in improving the object motion
estimation results.  It then uses the long-term spatio-temporal constraints to
achieve reliable object tracking over long sequences. 

The system contains two stages.  In the first stage, we apply an iterative
motion layer detection process based on the estimation and merging of affine
motion models. Each iteration generates one motion layer. The difference from
existing methods is that motion models are estimated from spatially segmented
color regions instead of just pixels or blocks.  In the second stage, temporal
constraints are applied to detect moving objects in spatial and temporal space.
Layers in individual frames are linked together based on characteristics of
their underlying regions. One or more layers will be declared as motion objects
according to specific spatio-temporal consistency rules.

The input are decoded frames in either PPM, GIF or JPEG format. The system does
not include an interface to decode MPEG video into image files. One can use the
AMOS system to decode MPEG streams and build database of video shots. 

In this binary distribution, we provide the classes for local segmentation and 
global detection. We also provide sample Java codes to extract region information
described in OIF files. Before using the system, the video clip should have
been segmented into individual shots and store individual frames in PPM format.

####2.SYSTEM REQUIREMENT/ INSTALLATION


The system is developed in Java language on Sun Solaris 2.5, Windows NT 4.0,
Windows 2000, or Windows XP.  It requires following software packages.

a) JDK 1.1.6+ or 1.2 (with JIT compiler)


The final installation will include following directory structure:
00README.txt         ---   this file
test.db              ---   an example database file (generate by AMOS)
runmot.bat           ---   run object tracking for the example database file
image                ---   general image I/O and processing functions
feature              ---   segmentation and feature computation
db                   ---   management of video shots and objects
VisualNumerics       ---   numeric functions from the third party
odet                 ---   main program for object segmentation and tracking
test                 ---   this is the root directory of "test.db" and repository
                	   of frames of a sample shot "1009"
sample		     ---   sample code for extracting region information
makesample.bat	     ---   how to compile the region information sample code
runsample.bat	     ---   execute region information extraction
README_for_db_file   ---   interpretation of "db" file



####3. USING THE SYSTEM


As previously discussed, it include two steps to track moving objects, which 
are both provided with binary Java codes. The last step is to extract the
region information, kept in *.OIF files, and is demonstrated in a sample code.

#####3.1 Local Segmentation


Usage: Java odet.ObjectDetection {v.db} {th} {min_size}
where: v.db      ---  the database file for video shots
       th        ---  the color merging threshold
       min_size  ---  minimum size of tracked regions (in number of pixels)

This program generates intermedia segmentation results in OIF files, including:
- salient regions
- foreground or background classification at individual frames

The source video sequences, in PPM formats, are located in ".\test\1009\frames\" and
OIF files are further generated in ".\test\1009\odet\".

#####3.2 Global Detection

Usage: java odet.GlobalDetection {v.db} {min_mv} {min_lifespan}
where: v.db          ---  the database file for video shots
       min_mv        ---  the minimum number of frames with motion
       min_lifespan  ---  the minimum number of total frames

This program generates final moving object. Outputs are stored to PPM files 
under the directory ".\test\1009\odet\" for each image sequence.

#####3.3 db File


The interpretation of the "db" file is exemplified in "README_for_db_file.txt".

#####3.4 Example


[path to Java run time program] java odet.ObjectDetection test.db 20 100
[path to Java run time program] java odet.GlobalDetection test.db 8 15

Please reference "runmot.bat" for these scripts and parameters. Before 
executing the program, pease make sure Java environment and corresponding
PATH are well configured.

####4. Region information extraction


Each image will be segmented into regions defined by boundary boxes
and masks and described in *.OIF file. To know how to extract region
information, please reference "RegionInfoDoc.tex" in directory "sample".


####5. Misc.


Before executing the binary distribution and compiling the sample code, please
MAKE SURE that Java runtime and development enviroment are well configured.