package platform.args

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class ArgsProcess(parser: ArgParser) {

    val data by parser.storing("-s", "--settings",
            help = "path to the settings file").default(null)

    val version by parser.flagging("-v", "--version",
            help="Prints out the version of the program")
}