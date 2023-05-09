//create a map list that will hold all the cities and roads
class Map() {
    val cities: MutableList<City> = mutableListOf()
    val roads: MutableList<Road> = mutableListOf()

    //add a city to the map
    fun addCity(city: City) {
        cities.add(city)
    }

    //add a road to the map
    fun addRoad(road: Road) {
        roads.add(road)
    }

    fun addRoad(city1: City, city2: City, time: Int, cost: Int) {
        val road = Road(city1, city2, time, cost)
        roads.add(road)
    }

    fun addRoad(city1: String, city2: String, time: Int, cost: Int) {
        val node1 = cities.find { it.name == city1 }
        val node2 = cities.find { it.name == city2 }
        if (node1 != null && node2 != null && node1 != node2) {
            val road = Road(node1, node2, time, cost)
            roads.add(road)

            return
        }
        //throw exception if city not found
        throw Exception("City not found $city1 or $city2")
    }

    fun addCities(cities: List<String>) {
        cities.forEachIndexed { index, city ->
            addCity(City(city, index))
        }
    }

    override fun toString(): String {
        return "cities = $cities, roads = $roads"
    }

    //add a method that takes a list of strings with the format "London-Paris 10 20" and adds the cities and roads to the map
    fun addRoads(roads: List<String>) {
        roads.forEach {
            val road = it.split(" ")
            val city1 = road[0].split("-")[0]
            val city2 = road[0].split("-")[1]
            val time = road[1].toInt()
            val cost = road[2].toInt()

            addRoad(city1, city2, time, cost)
        }
    }

    fun getCity(name: String): City {
        if (cities.isEmpty()) {
            throw Exception("Map is empty")
        }
        val city = cities.find { it.name == name }
        return city ?: throw Exception("City not found")
    }

    fun fitness(genom: Genom) {
        var cost = 0
        var time = 0
        for (i in 0 until genom.genom.count() - 1) {
            val index1 = genom.genom[i]
            val index2 = genom.genom[i + 1]
            val city1 = cities[index1]
            val city2 = cities[index2]
            val road = city1.getRoadTo(city2)
            if (road != null) {
                cost += road.cost
                time += road.time
            }else{
                genom.fitness = -1
                genom.time = time
                return
            }
        }
        if(!GenomMaker.containsAllCities(genom.genom)){
            genom.fitness = -1
            genom.time = time
            return
        }
        if(time < 4320) {
            genom.fitness = cost
            genom.time = time
            return
        }
        genom.fitness = -1
        genom.time = time
    }

    //create a function that take time in minutes and returns in hours and minutes
    fun timeInHoursAndMinutes(time: Int): String {
        val hours = time / 60
        val minutes = time % 60
        return "$hours hours and $minutes minutes"
    }
    //given a genom return a string with the visited cities
    fun getVisitedCities(genom: Genom): String {
        var result = ""
        for (i in 0 until genom.genom.count()) {
            val index = genom.genom[i]
            val city = cities[index]
            result += city.name
            if (i < genom.genom.count() - 1) {
                result += " -> "
            }
        }
        return result
    }

}
