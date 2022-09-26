package com.example.udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        solution1(2, arrayOf("desk1 request", "desk2 request", "desk3 request", "desk3 request", "desk1 release", "desk3 request"))
        // desk1 192.168.0.1

//        solution2("11111")
        // 1DBACB

        solution3(4, intArrayOf(3,2,1,4))
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

    fun solution3(n: Int, result: IntArray): Int {
        // n: 4 , result: (3,2,1,4)
        var answer = 0
        var horizon = 0
        var line = Array(n-1){"0" to false}
        // line = n-1개
        // n과 n+1 사이에 가로줄이 생기면 n
        // 1부터 n-1까지의 수로 만들 수 있는 모든 순열 구하기
        // ((1,2),(2,3),(1,2))
        var new = combination(n)
        for (i in 0..3) {
            var location = 0

            //1
            new.forEach {
                location = i+1
                // it = (1,2)
                if (it[0] == location) {
                    location = it[1]
                    horizon++
                } else if (it[1] == location) {
                    location = it[0]
                    horizon++
                }
            }

            //2
            for (i in new.indices) {
                if (i == 1)  location = i+1

                if (new[i][0] == location) {
                    location = new[i][1]
                    horizon++
                } else if (new[i][1] == location) {
                    location = new[i][0]
                    horizon++
                }
            }
        }

        // 순열의 원소가 적은순으로 탐색
        // 시작 위치를 변수로 만들고 이동할때마다 현재의 위치를 변경하고 horizon++
        // result와 일치하는 결과가 나오면 탐색을 중지하고 그때의 horizon 리턴

        return answer
    }

    fun combination(n: Int): List<List<Int>> {
        var list: List<List<Int>>

        while ()

        return list
    }
}