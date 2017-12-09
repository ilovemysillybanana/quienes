package platform.args

import com.xenomachina.argparser.ArgParser

class ArgsProcess(parser: ArgParser) {

    val data by parser.storing("-s", "--settings",
            help = "path to the settings file")

    val version by parser.flagging("-v", "--version",
            help="Prints out the version of the program")
}