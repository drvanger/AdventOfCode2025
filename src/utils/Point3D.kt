package utils

import kotlin.math.sqrt

class Point3D(var x : Int, var y : Int, var z : Int) {
    override fun hashCode(): Int {
        return x * 31 + y * 7 + z
    }

    override fun equals(other: Any?): Boolean {
        return other is Point3D && x == other.x && y == other.y && z == other.z
    }

    override fun toString(): String {
        return "(" + x + ", " + y + ", " + z + ")"
    }

    fun distanceFromEuclidean(p : Point3D) : Double {
        return sqrt((x.toLong() - p.x)*(x - p.x) + (y.toLong() - p.y)*(y - p.y) + (z.toLong() - p.z)*(z - p.z).toDouble())
    }
}