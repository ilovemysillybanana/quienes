import platform.linux.linux as Linux
import platform.windows.windows as Windows
import kotlin.text.Regex

/**
 * Created by jose on 10/26/17.
 */

fun main(args: Array<String>){
    val opsys = System.getProperty("os.name")
    val regwin = Regex(".*Windows.*")
    val reglin = Regex(".*Linux.*")
    val regsol = Regex(".*Solaris.*")
    val regaix = Regex(".*AIX.*")

    when {
        regaix.matches(opsys)    -> println("AIX Test")
        reglin.matches(opsys)    -> Linux().getFinishedData()
        regsol.matches(opsys)    -> println("Solaris Test")
        regwin.matches(opsys)    -> Windows().getFinishedData()
        else -> {
            println("Operating System: $opsys")
            println("Sorry this system is not supported by this program. :(")
        }
    }
}