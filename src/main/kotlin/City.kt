class City (
     val name: String,
     val id: Int,
     val roads: MutableList<Road> = mutableListOf()
)  {
     fun addEdge(edge: Road) {
        roads.add(edge)
    }
    //add a constructor that takes a name and id

    override fun toString(): String {
        return " $name, id = $id\n" //, roads = $roads "
    }
   fun getRandomCity(): City {
        val road = roads.random()

        if(road.city1 != this) {
            return road.city1
        } else {
            return road.city2
        }
    }
    //add a method that returns the road to another city
    fun getRoadTo(city: City): Road? {
        return roads.find { it.city1 == city || it.city2 == city }
    }
}