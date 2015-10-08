# creatureEvolve

#### Task list

###### For now if any thing is missing just make a temporary stub that returns a test value.

* **Genome** James
    * **Create classes**
    * **Conversion to phenome**
    * File input
    * File output
    * Creature Identifier human readable/filename
* **Controller**
    * **Create a basic controller**
        * Spawns a creature
        * Test Validity
        * Test fitness
* Mating (mixing two creatures)
    * Crosscutting (Splicing) mixing two genomes into one
    * Stacking duplicating some genes multiple times.
* Spawning
    * **Create some basic seeds for testing** Nathan
    * Random creatures
    * Pull from server (We will cultivate which seeds we want here)
* **Analysis**
    * **Check Validity**
        * Does the creature have intersecting blocks at start.
    * **Check fitness**
    * Determine whether to save or cull
* Genetic Algorithm
    * Spawn creatures (either random or seeds)
    * Cull least fit creatures in pool periodically
    * Spawn replacement creatures for new genes
* **GUI**
    * **Write generic gui that will get data from our controller**
    * For each thread
       * Total fitness
       * setting for min fit/min
	* Autostart genetic algorithm threshold input
	* Select number of threads
	* Select creature to watch
* **Hill Climbing**
	* **Mutate Genome**
	   * Add Genes
       * Mutate Genes
    * MulitThread