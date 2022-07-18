package ru.binnyatoff.leroymerlin.repository

import ru.binnyatoff.leroymerlin.R
import ru.binnyatoff.leroymerlin.data.ProductDTO

class ProductListCache {
    val list = listOf(
        ProductDTO(
            productName = "Шпаклёвка готовая финишная Danogips SuperFinish 18.1 кг",
            itemWeight = 18.1,
            productPrice = "1155",
            twoProductPrice = "77",
            productImage = R.drawable.img_1
        ),
        ProductDTO(
            productName = "Шпаклёвка виниловая суперфинишная Knauf Ротбанд Паста 18 кг",
            itemWeight = 18.0,
            productPrice = "1155",
            twoProductPrice = "77",
            productImage = R.drawable.img_2

        ),
        ProductDTO(
            productName = "Шпаклёвка полимерная суперфинишная Axton 25 кг",
            itemWeight = 25.0,
            productPrice = "1155",
            twoProductPrice = "77",
            productImage = R.drawable.img_3
        ),
        ProductDTO(
            productName = "Шпаклёвка финишная Knauf  Ротбанд Паста Профи 5 кг",
            itemWeight = 5.0,
            productPrice = "1155",
            twoProductPrice = "77",
            productImage = R.drawable.img_3
        ),
        ProductDTO(
            productName = "Стимулятор образования завязей и роста плодов Green Belt «Бутон» 2 г",
            itemWeight = 0.002,
            productPrice = "28",
            twoProductPrice = "",
            productImage = R.drawable.img_4
        ),
        ProductDTO(
            productName = "Лампа Elektrostandard Classic FD светодионая E27",
            itemWeight = 0.02,
            productPrice = "28",
            twoProductPrice = "",
            productImage = R.drawable.img_5
        )
    )
}

