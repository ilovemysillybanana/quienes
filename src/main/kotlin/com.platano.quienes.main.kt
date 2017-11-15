import platform.linux.linux as Linux
import platform.windows.windows as Windows

/**
 * Created by jose on 10/26/17.
 */

fun main(args: Array<String>){
    val opsys = System.getProperty("os.name")

    when (opsys) {
        "AIX"      -> println("AIX Test")
        "Linux"    -> Linux().getFinishedData()
        "Solaris"  -> println("Solaris Test")
        "Windows"  -> println("Windows Test")
        else -> {
            println("Operating System: $opsys")
            println("Sorry this system is not supported by this program. :(")
        }
    }
}