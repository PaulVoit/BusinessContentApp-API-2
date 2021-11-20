package com.kotlinexample.businesscontentapi


import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DataLoader(var businessRepository: BusinessRepository) {

    fun String.trimIndentsAndRemoveNewlines() = this.trimIndent().replace("\n", " ")

    @PostConstruct
    fun loadData() {
        businessRepository.saveAll(mutableListOf(
                Business(
                        name = "Elf",
                        phone = "8542244",
                        price = """
                    $$$
                    """.trimIndentsAndRemoveNewlines(),
                        rating = 1
                ),
                Business(
                        name = "Esso",
                        phone = "8546666",
                        price = """
                    $
                    """.trimIndentsAndRemoveNewlines(),
                        rating = 5
                ),
                Business(
                        name = "Shell",
                        phone = "8545647",
                        price = """
                    $$
                    """.trimIndentsAndRemoveNewlines(),
                        rating = 3
                ),
                Business(
                        name = "BP",
                        phone = "1525453",
                        price = """
                    $$$
                    """.trimIndentsAndRemoveNewlines(),
                        rating = 3
                ),
                Business(
                        name = "Lukoil",
                        phone = "1542765",
                        price = """
                    $$
                    """.trimIndentsAndRemoveNewlines(),
                        rating = 9
                ),
                Business(
                        name = "Neste",
                        phone = "8542377",
                        price = """
                    $$
                    """.trimIndentsAndRemoveNewlines(),
                        rating = 10
                ),
                Business(
                        name = "Mobil1",
                        phone = "2366544",
                        price = """
                    $
                    """.trimIndentsAndRemoveNewlines(),
                        rating = 7
                ),
                Business(
                        name = "ZIC",
                        phone = "854454",
                        price = """
                    $$$
                    """.trimIndentsAndRemoveNewlines(),
                        rating = 10
                ),
                Business(
                        name = "Gazprom",
                        phone = "8542244",
                        price = """
                    $
                    """.trimIndentsAndRemoveNewlines(),
                        rating = 4
                )
        ))
    }
}