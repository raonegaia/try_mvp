package i.am.rauan.satanbek.trymvp.gatjet.game.utils

import android.graphics.Color

class Functions {
    companion object {
        fun hexToRgb(value: String): Int {
            var fn = 0
            var sn = 0
            var tn = 0

            var mHex = value.replace("#", "")
            if (mHex.length == 3) {
                mHex = "${mHex[0]}${mHex[0]}${mHex[1]}${mHex[1]}${mHex[2]}${mHex[2]}"
            }

            if (mHex.length != 6) return Color.BLACK

            val firstPair = mHex.substring(0, 2)
            val secondPair = mHex.substring(2, 4)
            val thirdPair = mHex.substring(4)

            fn = getNumber(firstPair[0]) * 16 + getNumber(firstPair[1])
            sn = getNumber(secondPair[0]) * 16 + getNumber(secondPair[1])
            tn = getNumber(thirdPair[0]) * 16  + getNumber(thirdPair[1])

            return Color.rgb(fn ,sn, tn)
        }

        private fun getNumber(s: Char): Int {
            when(s.toLowerCase()) {
                'a' -> {
                    return 10
                }
                'b' -> {
                    return 11
                }
                'c' -> {
                    return 12
                }
                'd' -> {
                    return 13
                }
                'e' -> {
                    return 14
                }
                'f' -> {
                    return 15
                }
                else -> {
                    return s.toInt()
                }
            }
        }
    }

}