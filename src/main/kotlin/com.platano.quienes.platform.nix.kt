package platform.nix

import java.io.*
import com.beust.klaxon.*

class nix {

    fun getUserData(): MutableList<String> {
        val usersFile = "/etc/passwd"
        val userNames = mutableListOf<String>()
        val inputStream: InputStream = File(usersFile).inputStream()

        inputStream.bufferedReader().useLines {
            lines -> lines.forEach {
            if ( !it.contains('#') ) {
                userNames.add(it.split(":")[0])
            } }
        }

        //for a more complete list of users, we'll scan the groups list for additional users as well
        val groupsFile = "/etc/group"
        val groupStream: InputStream = File(groupsFile).inputStream()

        groupStream.bufferedReader().useLines { lines ->
            lines.forEach({
                val a_names = it.split(":").drop(3)
                val s_names = mutableListOf<String>()

                //split any entries with a comma, remove them and add them individually
                a_names.forEach {
                    it.split(",").forEach {
                        s_names.add(it)
                    }
                }

                s_names.forEach line@ {
                    when {
                        it.isBlank() -> return@line
                        it.isEmpty() -> return@line
                        else -> {
                            if (!(it in userNames)) {
                                userNames.add(it)
                            }
                        }
                    }
                }
            })
        }

        // this is for mac systems only
        var mac_user_list = console("dscl . list /Users")
        mac_user_list.forEachLine {
            if (it !in userNames){
                userNames.add(it)
            }
        }
        // end of mac only code

        return userNames
    }

    fun getUserGroups(user_list: MutableList<String>): MutableMap<String, List<String>> {

        var user_group_data = mutableMapOf<String, List<String>>()

        user_list.forEach {
            var user_data = console("id $it")
            var user_str_data = ""
            user_data.readLines().forEach {
                user_str_data = it
            }

            var userdata = mutableListOf<String>()
            user_str_data.replaceBefore("groups", "").split(",").forEach{
                userdata.add(
                        it.replaceBefore("(", "")
                                .replace("(", "")
                                .replace(")", ""))
            }
            user_group_data.put(it, userdata)

        }

        return user_group_data
    }


    fun getFinishedData() {
        println(getUserData())
        println( getUserGroups(getUserData()) )

    }

    fun getFinishedData(settings: String){
        val parser: Parser = Parser()
        val inputStream: InputStream = File(settings).inputStream()
        val stringBuilder: StringBuilder = StringBuilder(inputStream.bufferedReader().use{it.readText()})
        val json: JsonObject = parser.parse(stringBuilder) as JsonObject

        processData(json.array<String>("elevated_users")!!,
                json.array<String>("elevated_groups")!!,
                getUserGroups(getUserData())
        )
    }

    fun processData(elevated_users: JsonArray<String>, elevated_groups: JsonArray<String>, groupData: MutableMap<String, List<String>>){

        var json_data = "{ \"userdata\": {"
        groupData.forEach {
            var data = "\"${it.key}\": {"
            var egroups = mutableListOf<String>() //elevated group memberships
            var pgroups = mutableListOf<String>() //regular group memberships

            // find if privileged user first
            if (it.key in elevated_users){
                data += "\"elevated_user\": true,"
            } else {
                data += "\"elevated_user\": false,"
            }


            it.value.forEach {
                if (it in elevated_groups){
                    egroups.add(it)
                } else {
                    pgroups.add(it)
                }
            }

            if (egroups.size == 0) {
                data += "\"elevated_groups\": [],"
            } else {
                data += "\"elevated_groups\": ["
                egroups.forEach {
                    data += "\"$it\","
                }
                data = data.dropLast(1)
                data += "],"
            }

            if (pgroups.size == 0) {
                data += "\"normal_groups\": [],"
            } else {
                data += "\"normal_groups\": ["
                pgroups.forEach {
                    data += "\"$it\","
                }
                data = data.dropLast(1)
                data += "],"
            }

            data = data.dropLast(1)
            data += "},"
            json_data += data
        }
        json_data = json_data.dropLast(1)
        json_data += "}}"
        println(json_data)
    }

    fun console(cmd: String): BufferedReader {
        val output = BufferedReader(
                InputStreamReader(
                        Runtime.getRuntime().exec(cmd)
                                .inputStream))
        return output

    }
}