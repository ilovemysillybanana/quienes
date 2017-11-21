import platform.linux.linux as Linux
import platform.windows.windows as Windows
import platform.unix.unix as Unix
import kotlin.text.Regex

/**
 * Created by jose on 10/26/17.
 */

fun main(args: Array<String>){
    val opsys = System.getProperty("os.name")
    val regwin = Regex(".*Windows.*")
    val reglin = Regex(".*Linux.*")
    val regmac = Regex(".*Mac.*")
    val regsol = Regex(".*SunOS.*") //Solaris systems return SunOS from OS Name
    val regaix = Regex(".*AIX.*")

    //at this time the unix files all seem to be the same, it is checking /etc/passwd and /etc/passwrd.

    when {
        regaix.matches(opsys)    -> Unix().getFinishedData()
        reglin.matches(opsys)    -> Unix().getFinishedData()
        regmac.matches(opsys)    -> Unix().getFinishedData()
        regsol.matches(opsys)    -> Unix().getFinishedData()
        regwin.matches(opsys)    -> Windows().getFinishedData()
        else -> {
            println("Operating System: $opsys")
            println("Sorry this system is not supported by this program. :(")
        }
    }
}