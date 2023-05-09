import kotlin.random.Random

class Genom() {
    var genom: MutableList<Int> = mutableListOf()
    var fitness: Int = 0
    var time: Int = 0
        //reproduce this genom with another one

        override fun toString(): String {
            return "Genom: $genom\nfitness: $fitness\ntime: $time"
        }
         //make a function that mutates the genom
        fun mutate() {

             val index1 = Random.nextInt(0, genom.size)
             val index2 = Random.nextInt(0, genom.size)
             val temp = genom[index1]
             genom[index1] = genom[index2]
             genom[index2] = temp
         }
    }