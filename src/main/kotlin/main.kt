fun main() {

    val map = Map()
    val cities: List<String> = listOf(
        "London",
        "Brussels",
        "Amsterdam",
        "Cologne",
        "Berlin",
        "Frankfurt",
        "Milan",
        "Rome",
        "Lyon",
        "Barcelona",
        "Madrid",
        "Paris"
    )
    // city1 city2 time in minutes cost

    val roads: List<String> = listOf(
        "Brussels-Amsterdam 105 48",
        "Amsterdam-Cologne 120 40",
        "Amsterdam-Berlin 364 235",
        "Berlin-Frankfurt 232 125",
        "Frankfurt-Cologne 120 40",
        "Frankfurt-Paris 480 345",
        "Frankfurt-Milan 454 240",
        "Milan-Rome 168 125",
        "Milan-Lyon 176 180",
        "Lyon-Barcelona 200 320",
        "Barcelona-Madrid 150 98",
        "Madrid-Paris 225 380",
        "Paris-Barcelona 390 400",
        "Paris-Lyon 112 185",
        "London-Paris 136 98",
        "London-Brussels 136 98",
        "Paris-Brussels 82 80"
    )
    //format for roads is city1-city2 time cost
    map.addCities(cities)
    map.addRoads(roads)
    //first Generation
    val humans = 1000
    val firstGeneration = mutableListOf<Genom>()
    val generations = mutableListOf<List<Genom>>()
    for (i in 0..humans) {
        val genom = GenomMaker.genom(map.cities)
        map.fitness(genom)
        if(genom.fitness != -1) {
            firstGeneration.add(genom)
        }
    }
    firstGeneration.sortBy { it.fitness }
    generations.add(firstGeneration)
    //print top 3 best genoms
    //println("Best genoms: ${generations.last().take(3)}")
    var count =0
    for(i in 0..100){
        val parents= generations[i]
        val newGeneration = GenomMaker.newGeneration(parents,humans,map)
        newGeneration.sortBy { it.fitness }
        generations.add(newGeneration)
        //if the number of population is the same as the previous generation, stop

        /*if(parents.first() == newGeneration.first())
            count ++
        else
            count=0
        if(count == 10) {
            println("Generation ${i-9} Best genom: ${generations.last().first()}")
            println("--------------------------------------------------------------")
            println(map.timeInHoursAndMinutes(generations.last().first().time))
            println(map.getVisitedCities(generations.last().first()))
            break
        }*/
        println("Generation ${i-9} Best genom: ${generations.last().first()}")
        println("--------------------------------------------------------------")
        println(map.timeInHoursAndMinutes(generations.last().first().time))
        println(map.getVisitedCities(generations.last().first()))
    }






}