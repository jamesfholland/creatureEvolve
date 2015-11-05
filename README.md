# creatureEvolve

#### Task list

###### For now if any thing is missing just make a temporary stub that returns a test value.

READ ME
Tess Daughton
Nathaniel Gonzales
Tyler Shelton
James Holland


Evolve Creature

Evolve creature can run in with a GUI or in headless mode. 
When running in GUI you will have a visualization window showing the best creature jumping in the physics space. 
From here you can select the drop down  near the title and select what mode you want the algorithm to run with
-Genetic/Hill Climbing (The default)
  -This will hill climb for a minimum of two minutes, after that it will wait until the fitness per minute is less than the user specified amount
-HillCliming 
-Genetic
On your right you have a select of each creature in the Gene pool, selecting one will show which creature you selected
In the bottom right hand corner there is a button to load a creature from file. This creature must be a .geno type written by genofile.
In the bottom left hand corner you can change the threshold, which is measured in
On the bottom there are two slide bars, one changes zoom level, while the other changes speed that the program is running at.

In headless mode, to run genetic/hill climbing you need a argument like silent. 
to run HillCliming use the argument hill
to run the Genetic algorithm use the argument genetic

/**************************************/
              The Details
/*************************************/


* **Genome**
    * ~~Create classe James~~
    * ~~Conversion to phenome James~~
    * ~~File input~~
    * ~~File output~~
    * ~~Creature Identifier human readable/filename~~
* **Controller**
    * ~~Create a basic controller~~
        * ~~Spawns a creature~~
        * ~~Test Validity~~
        * ~~Test fitness~~
* Mating (mixing two creatures)
    * Crosscutting (Splicing) mixing two genomes into one
    * Stacking duplicating some genes multiple times.
* Spawning
    * **Create some basic seeds for testing** Nathan
    * Random creatures
    * Pull from server (We will cultivate which seeds we want here)
* **Analysis**
    * **Check Validity** Tyler
        * ~~Does the creature have intersecting blocks at start. Tyler~~
    * **Check fitness**
    * Determine whether to save or cull
* Genetic Algorithm
    * Spawn creatures (either random or seeds)
    * Cull least fit creatures in pool periodically
    * Spawn replacement creatures for new genes
* **GUI** Tess
    * **Write generic gui that will get data from our controller**
    * Total fitness
    * setting for min fit/min
	* Autostart genetic algorithm threshold input
* **Hill Climbing** James
	* **Mutate Genome**
	   * Add Genes
       * Mutate Genes
