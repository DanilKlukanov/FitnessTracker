package com.example.fitnesstracker.data

import com.example.fitnesstracker.domain.entiry.CardType
import com.example.fitnesstracker.domain.entiry.ListItem
import org.joda.time.DateTime

class CardsData {
    private val pattern = "dd.MM.YYYY"

    private val myData = listOf<ListItem>(
        ListItem.SplitDate(1, DateTime.now().toString(pattern)),
        ListItem.CardActivity(
            2,
            "14.32 км",
            "2 часа 46 минут",
            "Серфинг",
            "14 часов назад",
            CardType.my
        ),
        ListItem.SplitDate(3, "Май 2022 года"),
        ListItem.CardActivity(
            4,
            "1000 м",
            "60 минут",
            "Велосипед",
            "29.05.2022",
            CardType.my
        )
    )

    private val usersData = listOf<ListItem>(
        ListItem.SplitDate(4, "Вчера"),
        ListItem.CardActivity(
            4,
            "14 км",
            "2 часа 46 минут",
            "Серфинг",
            "29.05.2022",
            CardType.users,
            "@van_darkholme"
        ),
        ListItem.CardActivity(
            4,
            "228 м",
            "14 часов 48 минут",
            "Качели",
            "14 часов назад",
            CardType.users,
            "@techniquepasha"
        ),
        ListItem.CardActivity(
            4,
            "30 км",
            "2 часа 10 минут",
            "Езда на кадилак",
            "14 часов назад",
            CardType.users,
            "@morgen_shtern"
        )
    )

    fun getDefaultData(type: CardType): List<ListItem> {
        return when (type) {
            CardType.my -> myData
            CardType.users -> usersData
        }
    }
}