import kotlin.random.Random

object GenomMaker {
    var number = 0
    fun genom(cities: List<City>): Genom {
        val genom = Genom()
        val index = Random.nextInt(12)
        var city = cities[index]
        genom.genom.add(index)
        for (i in 0..11) {
            city = city.getRandomCity()
            genom.genom.add(city.id)
        }
        while (!containsNumbersZeroToEleven(genom.genom)) {
            city = city.getRandomCity()
            genom.genom.add(city.id)
        }

        return genom
    }
//falta la mutación
    fun genom(parent1: Genom, parent2: Genom): Pair<Genom, Genom> {
        val child1 = Genom()
        val child2 = Genom()
        val random = Random.nextInt(5, 8)
        val child1B = parent1.genom.take(random)
        val child2B = parent2.genom.take(random)
        val city1 = child1B.last()
        val city2 = child2B.last()
        val index1 = parent2.genom.indexOf(city1)
        val index2 = parent1.genom.indexOf(city2)
        val child1E = parent2.genom.take(index1)
        val child2E = parent1.genom.take(index2)
        child1.genom = (child1B + child1E).toMutableList()
        child2.genom = (child2B + child2E).toMutableList()
        return Pair(child1, child2)
    }

    fun containsNumbersZeroToEleven(numbers: List<Int>): Boolean {
        val presenceList = MutableList(12) { 0 }

        for (number in numbers) {
            if (number in 0..11) {
                presenceList[number]++
            }
        }

        for (number in presenceList) {
            if (number == 0) {
                return false
            }
        }

        return true
    }

    fun newGeneration(parents: List<Genom>, noHumans: Int, map: Map): MutableList<Genom> {
        val generation = parents.toMutableList()

        for (i in 0 until parents.size step 2) {
            if (i + 1 >= parents.size)
                break
            val parent1 = parents[i]
            val parent2 = parents[i + 1]
            val child1 = genom(parent1, parent2).first
            child1.mutate()
            val child2 = genom(parent1, parent2).second
            child2.mutate()
            map.fitness(child1)
            map.fitness(child2)
            if (child1.fitness != -1) {
                generation.add(child1)
                number++
            }
            if (child2.fitness != -1) {
                generation.add(child2)
                number++
            }
        }
       for (i in generation.size until noHumans) {

            val newGenom = genom(map.cities)
            map.fitness(newGenom)
            if (newGenom.fitness != -1) {
                generation.add(newGenom)
                number++
            }
        }
        return generation
    }
}
