package com.example.udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        solution1(2, arrayOf("desk1 request", "desk2 request", "desk3 request", "desk3 request", "desk1 release", "desk3 request"))
        // desk1 192.168.0.1

//        solution2("11111")
        // 1DBACB

//        solution3(4, intArrayOf(3,2,1,4))
        // 3
    }

    fun solution1(N: Int, queries: Array<String>): Array<String> {
        var answer = arrayOf<String>()
        val already = mutableMapOf<String, String>()
        val server = mutableMapOf<String, Boolean>()

        for (i in 1..N) {
            server["192.168.0.$i"] = false
        }

        queries.forEach {
            val splited = it.split(" ")
            val checked = check(server)
            if (splited[1] == "request") {
                if (already.contains(splited[0])) {
                    if (server[already[splited[0]]] == false) {
                        server[already[splited[0]].toString()] = true
                        answer += "${splited[0]} ${server[already[splited[0]].toString()]}"
                    }
                } else {
                    if (checked.isNotBlank()) {
                        server[check(server)] = true
                        already[splited[0]] = checked
                        answer += "${splited[0]} $checked"
                    } else {
                        answer += "${ splited[0] } reject"
                    }
                }
            } else {
                server[already[splited[0]].toString()] = false
            }
        }
        return answer
    }

    fun check(server : MutableMap<String, Boolean>): String {
        var check = ""

        server.toSortedMap().forEach {
            if (!it.value) {
                check =  it.key
                return check
            } else {
                check = ""
            }
        }
        return check
    }



    fun solution2(src: String): String {
        var answer = ""
        val numbermap = mutableMapOf<String, Char>()
        var cnt = 1

        for (i in 0..25) {
            numbermap += ("${i + 1}" to 'A'+i)
        }
        answer += src[0]

        for (i in 0..src.length-2) {
            if (src[i] == src[i+1]) {
                cnt++
            } else {
                answer += numbermap["$cnt"]
                cnt = 1
            }
        }

        answer += numbermap["$cnt"]
        return answer
    }
}