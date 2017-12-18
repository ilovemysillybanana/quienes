/**
 * Created by jose on 11/15/17.
 */
package platform.windows

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.array
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader

class windows {

    private val regusers = Regex("User accounts for.*")
    private val regdashs = Regex("-----------------.*")
    private val regcompl = Regex("The command compl.*")

    fun console(cmd : String): BufferedReader {
        val output = BufferedReader(
                InputStreamReader(
                        Runtime.getRuntime().exec(cmd)
                                .inputStream))
        return output

    }

    fun userData(output: BufferedReader): MutableList<String> {
        val users = mutableListOf<String>()
        output.lines().forEach line@ {
            when {
                it.isBlank()         -> return@line
                it.isEmpty()         -> return@line
                regcompl.matches(it) -> return@line
                regusers.matches(it) -> return@line
                regdashs.matches(it) -> return@line
                else -> {
                    it.split("            ").forEach {
                        val name = it.replace("\\s".toRegex(), "")
                        if ( it.isNotBlank()){
                            //println("\"$name\"")
                            users.add(name)
                        }
                    }
                }
            }
        }

        return users
    }

    //this function takes in a list of users, and returns its corresponding groups
    fun groupData(users: MutableList<String>): MutableMap<String, MutableList<String>> {
        //only lines we care about are username and local and global groups
        //val regexusr = Regex("User name.*")
        val rexgroup = Regex(".*Group.*")
        val groups = mutableMapOf<String, MutableList<String>>()

        users.forEach {

            //run command to get output
            val user_group_data = console("net user $it")
            val lsGroupData = mutableListOf<String>()
            val name = it


            user_group_data.lines().forEach line@ {
                var strGroupData = ""
                when {
                    it.isBlank() -> return@line
                    it.isEmpty() -> return@line
                    rexgroup.matches(it) -> {
                        // NOTE, each user group starts with an asterisk. We split on that and drop
                        // the first element since it's just group category name
                        // groups.put(it, it.replace("\\s".toRegex(), "").split("*").drop(1))
                        //strGroupData = it.replace("\\s".toRegex(), "")
                        strGroupData = it.replace("\\s".toRegex(), " ").drop(1)
                        strGroupData.split("*").drop(1).forEach {
                            lsGroupData.add(it.trim())
                        }
                    }
                }
            }
            groups.put(name, lsGroupData)
        }
        return groups
    }

    fun jsonify(groups_data: MutableMap<String, MutableList<String>> ): String {

        var jsonData = "{ \"userdata\": {"

        groups_data.forEach {
            var groupData = "\"${it.key}\": ["

            it.value.forEach {
                groupData += "\"${it}\","
            }

            groupData = groupData.dropLast(1)
            groupData += "],"
            jsonData += groupData
        }

        jsonData = jsonData.dropLast(1)
        jsonData += "}}"

        return jsonData
    }

    fun jsonify(groups_data: MutableMap<String, MutableList<String>>, elevated_users: JsonArray<String>, elevated_groups: JsonArray<String>): String{
        var json_data = "{ \"userdata\": {"

        groups_data.forEach {
            var data = "\"${it.key}\": {"
            var egroups = mutableListOf<String>() //elevated group memberships
            var pgroups = mutableListOf<String>() //regular group memberships

            //find if priv user
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
        return json_data
    }

    fun getFinishedData(){
        val output = console("net user")
        val usersd = userData(output)
        val groupd = groupData(usersd)

        println(jsonify(groupd))
    }

    fun getFinishedData(settings: String){
        val output = console("net user")
        val usersd = userData(output)
        val groupd = groupData(usersd)
        val parser: Parser = Parser()
        val inputStream: InputStream = File(settings).inputStream()
        val stringBuilder: StringBuilder = StringBuilder(inputStream.bufferedReader().use{it.readText()})
        val json: JsonObject = parser.parse(stringBuilder) as JsonObject


        println(jsonify(groupd,
                json.array<String>("elevated_users")!!,
                json.array<String>("elevated_groups")!!))
    }


}