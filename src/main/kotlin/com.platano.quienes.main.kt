/**
* Created by jose on 10/26/17.
*/


import platform.linux.linux as Linux
import platform.windows.windows as Windows
import platform.unix.unix as Unix
import platform.nix.nix as Nix
import platform.args.ArgsProcess as ArgsProcessor
import com.xenomachina.argparser.ArgParser


fun main(args: Array<String>){
    var arguments = ArgsProcessor(ArgParser(args))
    val opsys = System.getProperty("os.name")
    val regwin = Regex(".*Windows.*")
    val reglin = Regex(".*Linux.*")
    val regmac = Regex(".*Mac.*")
    val regsol = Regex(".*SunOS.*") //Solaris systems return SunOS from OS Name
    val regaix = Regex(".*AIX.*")

    //print out the version of program and exit
    if (arguments.version) {
        println("Program version is: 2.0.0")
        println("Author: Jose Manuel Tobar")
        println("Get the latest version: https://github.com/ilovemysillybanana/quienes/releases")
        System.exit(0)
    }


    if (!arguments.data.isNullOrBlank()) {
        when {
            regaix.matches(opsys)    -> Nix().getFinishedData(arguments.data)
            reglin.matches(opsys)    -> Nix().getFinishedData(arguments.data)
            regmac.matches(opsys)    -> Nix().getFinishedData(arguments.data)
            regsol.matches(opsys)    -> Nix().getFinishedData(arguments.data)
            regwin.matches(opsys)    -> Windows().getFinishedData()
            else -> {
                println("Operating System: $opsys")
                println("Sorry this system is not supported by this program. :(")
            }
        }
    } else {
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
}

