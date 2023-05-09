// create a road between two cities with the format "London-Paris 10 20"
class Road( val city1: City,  val city2: City, val time: Int, val cost: Int)  {
    //when a road is created add it to the cities
    init {
        city1.addEdge(this)
        city2.addEdge(this)
    }
     fun getFrom(): City {
        return city1
    }

     fun getTo(): City {
        return city2
    }

    override fun toString(): String {
        return " \n ${city1.name} <-> ${city2.name}: $time, $cost "
    }
}
