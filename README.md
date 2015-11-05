# creatureEvolve

##### READ ME  
Tess Daughton
Nathaniel Gonzales
James Holland
Tyler Shelton

##### GUI description  
Evolve creature can run in with a GUI or in headless mode.
When running in GUI you will have a visualization window showing the best creature jumping in the physics space.
From here you can select the drop down  near the title and select what mode you want the algorithm to run with:
* Genetic/Hill Climbing (The default)
  * This will hill climb for a minimum of two minutes, after that it will wait until the fitness per minute is less than the user specified amount
* HillCliming
* Genetic

On the right side of the window have a select of each creature in the Gene pool, selecting one will show which creature you selected
In the bottom right hand corner there is a button to load a creature from file. This creature must be a .geno type written by genofile.
In the bottom left hand corner you can change the threshold, which is measured in
On the bottom there are two slide bars, one changes zoom level, while the other changes speed that the program is running at.

##### Headless description  
In headless mode you can start with either hill or hill/genetic modes with below examples:

##### Example Execution  
GUI  
 java -jar creatureEvolve.jar

HEADLESS Hill-Climbing Only  
 java -jar creatureEvolve.jar hill

HEADLESS Both hill-climbing and genetic algorithms  
 java -jar creatureEvolve.jar both

##### Output  
Creature outputs are written every hour into a creatures folder.  
Reading them can only be done in the GUI.

##### Preset GenePools  
Creating a folder called GenePool and filling with existing *.geno files will cause future runs to use these as starting seeds.

##### Compilation  
We used a build tool called gradle. Install a recent version of gradle EG version 2.8.  
This tool will pull JMonkeyEngine dependencies and required libraries for your machine and also compile the project.  
gradle idea    This builds intellij files.
gradle run     This executes in hill/genetic mode
gradle jar     This compiles a jar file.
